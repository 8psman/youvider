package com.eightpsman.youvider;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 * Youvider
 * Created by 8psman on 1/23/2015.
 * Email: 8psman@gmail.com
 */
public class Youvider {

    public static void main(String args[]) throws IOException {

        String webLink = "https://www.youtube.com/watch?v=9bZkp7q19f0";

        /** Get web page content */
        URL webUrl = new URL(webLink);
        HttpURLConnection webCon = (HttpURLConnection) webUrl.openConnection();

        InputStream webInput = webCon.getInputStream();

        String content = Utils.getStringFromStream(webInput);

        List<YouviderChunk> chunks = YouviderParser.parse(content);

        YouviderChunk chunk = chunks.get(0);
        String link = chunk.getParam("url");

        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        URL url;

        try {
            url = new URL(link);
            connection = (HttpURLConnection )url.openConnection();
            connection.connect();

//            connection.setReadTimeout(20000);

            // Output file
            File file = new File("E:/test.mp4");

            /** video size length */
            int fileLength = connection.getContentLength();

            System.out.println("File size: " + (fileLength / 1024f / 1024f) + " MB");

            /** download video */
            input = new BufferedInputStream(connection.getInputStream());
            output = new FileOutputStream(file);

            byte buffered[] = new byte[1024];
            long downloaded = 0;
            int count;
            int progress = 0;

            while (true) {
                count = input.read(buffered);
                if (count < 0) break;
                downloaded += count;
                output.write(buffered, 0, count);

                if (fileLength > 0){
                    int newProgress = (int)(downloaded * 100f / fileLength);
                    if (newProgress > progress){
                        progress = newProgress;
                        System.out.println("Downloading... " + progress + "%%");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                if (output != null)
                    output.flush();
                if (output != null)
                    output.close();
                if (input!= null)
                    input.close();
                if (connection != null)
                    connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
