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


    /** Get content of a web page */
    public static String getWebContent(String webLink) throws IOException {
        URL webUrl = new URL(webLink);
        HttpURLConnection webCon = (HttpURLConnection) webUrl.openConnection();
        InputStream webInput = webCon.getInputStream();
        String content = Utils.getStringFromStream(webInput);
        return content;
    }

    public static YouviderInfo getVideoInfo(String webLink) throws IOException {
        /** Get web content */
        String webContent = Youvider.getWebContent(webLink);

        if (webContent == null){
            System.out.println("Error downloading page content");
            return null;
        }

        /** Parse video info content */
        String videoInfo = YouviderParser.parseVideoInfo(webContent);
        if (videoInfo == null){
            System.out.print("Error parsing video info");
            return null;
        }

        /** Get video info params*/
        YouviderInfo youviderInfo = new YouviderInfo();
        youviderInfo.videoTitle = YouviderParser.getVideoParam(videoInfo, YouviderParser.VIDEO_PARAM_TITLE);
        if (youviderInfo.videoTitle == null){
            System.out.println("Error parsing video title");
        }

        /** Get video encoded streams */
        youviderInfo.encodedStreams = YouviderParser.parseEncodedStream(videoInfo);
        if (youviderInfo.encodedStreams == null || youviderInfo.encodedStreams.size() == 0){
            System.out.println("Error parsing encoded streams");
        }

        return youviderInfo;
    }

    public static boolean downloadEncodedStream(EncodedStream stream, String filePath){

        boolean isSuccess = false;

        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        URL url = null;

        try {
            url = new URL(stream.url);
            connection = (HttpURLConnection )url.openConnection();
            connection.connect();

//            connection.setReadTimeout(20000);

            // Output file
            File file = new File(filePath);

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
                        System.out.println("Downloading... " + progress + "%");
                    }
                }
            }
            isSuccess = true;
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
        return isSuccess;
    }
}
