package com.eightpsman.youvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * Youvider
 * Created by 8psman on 1/24/2015.
 * Email: 8psman@gmail.com
 */
public class Utils {

    public static String getStringFromStream(InputStream inputStream){
        StringWriter writer = new StringWriter();
        char[] buffer = new char[1024];
        int bytes;
        try{
            InputStreamReader reader = new InputStreamReader(inputStream);
            while ((bytes = reader.read(buffer)) != -1){
                writer.write(buffer, 0, bytes);
            }
        }catch (IOException ex){
            return null;
        }
        return writer.toString();
    }
}
