package com.eightpsman.youvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Youvider
 * Created by 8psman on 2/4/2015.
 * Email: 8psman@gmail.com
 */
public class Itag {

//    private static Map<Integer, String> itags = new HashMap<Integer, String>();

//    static{
//        itags.put(  5, "Low Quality, 240p, FLV, 400x240");
//        itags.put( 17, "Low Quality, 144p, 3GP, 0x0");
//        itags.put( 18, "Medium Quality, 360p, MP4, 480x360");
//        itags.put( 22, "High Quality, 720p, MP4, 1280x720");
//        itags.put( 34, "Medium Quality, 360p, FLV, 640x360");
//        itags.put( 35, "Standard Definition, 480p, FLV, 854x480");
//        itags.put( 36, "Low Quality, 240p, 3GP, 0x0");
//        itags.put( 37, "Full High Quality, 1080p, MP4, 1920x1080");
//        itags.put( 38, "Original Definition, MP4, 4096x3072");
//        itags.put( 43, "Medium Quality, 360p, WebM, 640x360");
//        itags.put( 44, "Standard Definition, 480p, WebM, 854x480");
//        itags.put( 45, "High Quality, 720p, WebM, 1280x720");
//        itags.put( 46, "Full High Quality, 1080p, WebM, 1280x720");
//        itags.put( 82, "Medium Quality 3D, 360p, MP4, 640x360");
//        itags.put( 84, "High Quality 3D, 720p, MP4, 1280x720");
//        itags.put(100, "Medium Quality 3D, 360p, WebM, 640x360");
//        itags.put(102, "High Quality 3D, 720p, WebM, 1280x720");
//    }

    public static List<Itag> itags = new ArrayList<Itag>();

    static{
        itags.add(new Itag( 17, "Low Quality"           , "144p"    , "3GP"     , "176x144"));
        itags.add(new Itag( 36, "Low Quality"           , "240p"    , "3GP"     , "320x180"));
        itags.add(new Itag(  5, "Low Quality"           , "240p"    , "FLV"     , "426x240"));
        itags.add(new Itag( 34, "Medium Quality"        , "360p"    , "FLV"     , "640x360"));
        itags.add(new Itag( 18, "Medium Quality"        , "360p"    , "MP4"     , "640x360"));
        itags.add(new Itag( 43, "Medium Quality"        , "360p"    , "WebM"    , "640x360"));
        itags.add(new Itag( 82, "Medium Quality 3D"     , "360p"    , "MP4"     , "640x360"));
        itags.add(new Itag(100, "Medium Quality 3D"     , "360p"    , "WebM"    , "640x360"));

        itags.add(new Itag( 35, "Standard Definition"   , "480p"    , "FLV"     , "854x480"));
        itags.add(new Itag( 44, "Standard Definition"   , "480p"    , "WebM"    , "854x480"));

        itags.add(new Itag( 22, "High Quality"          , "720p"    , "MP4"     , "1280x720"));
        itags.add(new Itag( 45, "High Quality"          , "720p"    , "WebM"    , "1280x720"));
        itags.add(new Itag( 84, "High Quality 3D"       , "720p"    , "MP4"     , "1280x720"));
        itags.add(new Itag(102, "High Quality 3D"       , "720p"    , "WebM"    , "1280x720"));

        itags.add(new Itag( 37, "Full High Quality"     , "1080p"   , "MP4"     , "1920x1080"));
        itags.add(new Itag( 46, "Full High Quality"     , "1080p"   , "WebM"    , "1280x720"));

        itags.add(new Itag( 38, "Original Definition"   , ""        , "MP4"     , "4096x3072"));
    }

    public final int itag;
    public final String description;
    public final String quality;
    public final String format;
    public final String size;

    public Itag(){
        this.itag = -1;
        this.description = "N/A";
        this.quality = "N/A";
        this.format = "N/A";
        this.size = "N/A";
    }

    public Itag(int itag, String description, String quality, String format, String size){
        this.itag = itag;
        this.description = description;
        this.quality = quality;
        this.format = format;
        this.size = size;
    }

    @Override
    public String toString(){
        return (description + ", " + quality + ", " + format + ", " + size);
    }

    public static Itag getItag(int itag){
        for (Itag youvitag : itags)
            if (youvitag.itag == itag)
                return youvitag;
        return new Itag();
    }

    public static int getItagIndex(int itag){
        for (int i=0; i<itags.size(); i++)
            if (itags.get(i).itag == itag)
                return i;
        return -1;
    }

    public static String getItagDescription(int itag){
        Itag youvitag = getItag(itag);
        if (youvitag != null)
            return youvitag.toString();
        return new Itag().toString();
    }

    public static int compare(Integer itag1, Integer itag2) {
        int index1 = getItagIndex(itag1);
        int index2 = getItagIndex(itag2);
        return Integer.compare(index1, index2);
    }
}
