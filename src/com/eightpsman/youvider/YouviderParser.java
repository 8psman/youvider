package com.eightpsman.youvider;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Youvider
 * Created by 8psman on 1/23/2015.
 * Email: 8psman@gmail.com
 */
public class YouviderParser {

    public static final String REGEX = "(\"adaptive_fmts\": \"([^\"]*)\")";

    public static List<YouviderChunk> parse(String content){
        List<YouviderChunk> chunks = new ArrayList<YouviderChunk>();

        Pattern pattern = Pattern.compile(REGEX);

        Matcher matcher = pattern.matcher(content);

        if (matcher.find()){
            // Get adaptive streaming data
            String adaptive_fmts = matcher.group(matcher.groupCount());

            try {
                adaptive_fmts = URLDecoder.decode(adaptive_fmts, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            System.out.println(adaptive_fmts);

            // Get chanel info
            String[] chanels = adaptive_fmts.split(",");
            for (String chanel : chanels){
                System.out.print("++" + chanel);
                Map<String, String> chanelInfo = new HashMap<String, String>();
                // Get chanel params
                String[] params = chanel.split("\\\\u0026");
                for (String param : params){
                    System.out.println("--" + param);
                    if (param.contains(";")){
                        String[] segments = param.split(";");
                        for (String segment : segments){
                            String[] valuePair = segment.split("=", 2);
                            chanelInfo.put(valuePair[0], valuePair[1]);
                        }
                    }else{
                        String[] valuePair = param.split("=", 2);
                        chanelInfo.put(valuePair[0], valuePair[1]);
                    }
                }
                YouviderChunk chunk = new YouviderChunk(chanelInfo);
                chunks.add(chunk);
            }
        }

        return chunks;
    }

}
