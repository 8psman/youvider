package com.eightpsman.youvider;

import java.io.IOException;
import java.util.Scanner;

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

    static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws IOException {

        String webLink = null;

        if (webLink == null){
            System.out.println("Youtube video link:");
            webLink = scanner.next();
        }

        System.out.println("Getting video info ...");
        YouviderInfo info = Youvider.getVideoInfo(webLink);

        if (info != null){
            if (info.encodedStreams != null && info.encodedStreams.size() > 0){
                /** Get user's choice of video to download */
                for (int i=0; i<info.encodedStreams.size(); i++){
                    EncodedStream s = info.encodedStreams.get(i);
                    System.out.println((i+1) + ": " + s.itag.toString());
                }
                System.out.print("Choose video to download (0 to exit): ");
                int choice;
                while (true){
                    String sChoice = scanner.next();
                    try{
                        choice = Integer.parseInt(sChoice);
                        if (choice >= 0 && choice <= info.encodedStreams.size())
                            break;
                    }catch (Exception ex){

                    }
                    System.out.print("Invalid choice, choose again (0 to exit): ");
                }
                if (choice > 0){
                    EncodedStream stream = info.encodedStreams.get(choice - 1);
                    boolean result = Youvider.downloadEncodedStream(
                            stream,
                            String.format("E:/%s.%s", info.videoTitle, stream.itag.format)) ;
                    if (result){
                        System.out.println("Download finished successfully");
                    }else{
                        System.out.println("Error downloading video");
                    }
                }
            }
        }
    }
}
