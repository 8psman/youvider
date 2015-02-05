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

    public Youvider(){

    }

    /** Get content of a web page */
    public static String getWebContent(String webLink) throws IOException {
        URL webUrl = new URL(webLink);
        HttpURLConnection webCon = (HttpURLConnection) webUrl.openConnection();
        InputStream webInput = webCon.getInputStream();
        String content = Utils.getStringFromStream(webInput);
        return content;
    }
}
