package com.eightpsman.youvider;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Youvider
 * Created by 8psman on 1/23/2015.
 * Email: 8psman@gmail.com
 */

/**
 * Step 1: Get web content
 * Step 2: Parse content to get list of chunk
 * Step 3: Download chunk
 */

public class Demo {

    public static void main(String args[]) throws IOException {

        String webLink = "https://www.youtube.com/watch?v=j86i-xHzYXc";

        String content = Youvider.getWebContent(webLink);

        YouviderMpd mpd = YouviderParser.parseEncodedStream(content);

//
//        for (YouviderMpd.Mpd m: mpd.getMpdList()) {
//            String itag = m.getParam("itag");
//            System.out.println(itag + ": " + Youvitag.getDescription(Integer.parseInt(itag)));
//        }

//        YouviderParser.parseVideoInfo(content);

//        if (true)
        if (mpd.getMpdList().size() == 0)
            return;

        YouviderMpd.Mpd targetMpd = mpd.getMdp(mpd.getMpdList().size()-1);
        Youvitag itag = Youvitag.getItag(targetMpd.getItag());

        String link = mpd.getMpdList().get(0).getParam("url");
        String videoTitle = mpd.getVideoTitle();

        for (YouviderMpd.Mpd m: mpd.getMpdList()){
            if (m.getParam("itag").equals("36"))
                link = m.getParam("url");
        }

        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        URL url = null;

        try {
            url = new URL(link);
            connection = (HttpURLConnection )url.openConnection();
            connection.connect();

//            connection.setReadTimeout(20000);

            // Output file
            File file = new File(String.format("E:/%s.%s", videoTitle, itag.format.toLowerCase()));

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
