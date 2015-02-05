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

    // Regular Expression to get presentations information
    public static final String VIDEO_INFO_REGEX = "(\"args\"\\s*:\\s*\\{([^\\}]*)\\})";

    public static final String ADAPTIVE_STREAM_REGEX = "(\"adaptive_fmts\"\\s*:\\s*\"([^\"]*)\")";

    public static final String ENCODED_STREAM_REGEX = "(\"url_encoded_fmt_stream_map\"\\s*:\\s*\"([^\"]*)\")";

    public static final String ELEMENT_REGEX = "(\"%s\"\\s*:\\s*\"([^\"]*)\")"; // %s will be replaced by a name

    public static final String VIDEO_PARAM_TITLE = "title";

    public static String parseVideoInfo(String content) {
        String videoInfo = null;

        Pattern pattern = Pattern.compile(VIDEO_INFO_REGEX);

        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            videoInfo = matcher.group(matcher.groupCount());
        }
        return videoInfo;
    }

    public static String getVideoParam(String content, String key){
        String param = null;

        String regex = String.format(ELEMENT_REGEX, key);

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(content);

        if (matcher.find()){
            param = matcher.group(matcher.groupCount());
        }
        return param;
    }

//    public static YouviderMpd parseAdaptiveStream(String pageContent){
//        // Get video information
//        String content = parseVideoInfo(pageContent);
//
//        YouviderMpd mpd = new YouviderMpd();
//
//        Pattern pattern = Pattern.compile(ADAPTIVE_STREAM_REGEX);
//
//        Matcher matcher = pattern.matcher(content);
//
//        if (matcher.find()){
//            // Get adaptive streaming data
//            String adaptive_fmts = matcher.group(matcher.groupCount());
//
//            // Do not decode before parsing
////            try {
////                adaptive_fmts = URLDecoder.decode(adaptive_fmts, "UTF-8");
////            } catch (UnsupportedEncodingException e) {
////                e.printStackTrace();
////            }
//
//            // Get chanel info
//            String[] presentations = adaptive_fmts.split(",");
//            for (String presentation : presentations){
//                Map<String, String> presentationInfo = new HashMap<String, String>();
//                // Get chanel params
//                String[] params = presentation.split("\\\\u0026");
//                for (String param : params){
//                    if (param.contains(";")){
//                        String[] segments = param.split(";");
//                        for (String segment : segments){
//                            String[] valuePair = segment.split("=", 2);
//                            presentationInfo.put(valuePair[0], valuePair[1]);
//                        }
//                    }else{
//                        String[] valuePair = param.split("=", 2);
//                        presentationInfo.put(valuePair[0], valuePair[1]);
//                    }
//                }
//                mpd.addMpd(presentationInfo);
//            }
//        }
//        return mpd;
//    }

    public static List<EncodedStream> parseEncodedStream(String content){
        List<EncodedStream> streams = new ArrayList<EncodedStream>();

        Pattern pattern = Pattern.compile(ENCODED_STREAM_REGEX);

        Matcher matcher = pattern.matcher(content);

        if (matcher.find()){
            // Get adaptive streaming data
            String adaptive_fmts = matcher.group(matcher.groupCount());

            // Do not decode before parsing
//            try {
//                adaptive_fmts = URLDecoder.decode(adaptive_fmts, "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }

            // Get chanel info
            String[] presentations = adaptive_fmts.split(",");
            for (String presentation : presentations){
                Map<String, String> presentationInfo = new HashMap<String, String>();
                // Get chanel params
                String[] params = presentation.split("\\\\u0026");
                for (String param : params){
                    if (param.contains(";")){
                        String[] segments = param.split(";");
                        for (String segment : segments){
                            String[] valuePair = segment.split("=", 2);
                            String key = decodeUTF8(valuePair[0]);
                            String value = decodeUTF8(valuePair[1]);
                            presentationInfo.put(key, value);
                        }
                    }else{
                        String[] valuePair = param.split("=", 2);
                        // Decode content
                        String key = decodeUTF8(valuePair[0]);
                        String value = decodeUTF8(valuePair[1]);
                        presentationInfo.put(key, value);
                    }
                }

                String url = presentationInfo.get("url");
                int itag = Integer.parseInt(presentationInfo.get("itag"));
                streams.add(new EncodedStream(itag, url));
            }
        }

        return streams;
    }

    public static String decodeUTF8(String content){
        String decoded = content;
        try {
            decoded = URLDecoder.decode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decoded;
    }
}
