package com.eightpsman.youvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Youvider
 * Created by 8psman on 2/4/2015.
 * Email: 8psman@gmail.com
 */
public class Youvitag{

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

    public static List<Youvitag> itags = new ArrayList<Youvitag>();

    static{
        itags.add(new Youvitag( 17, "Low Quality"           , "144p"    , "3GP"     , "176x144"));
        itags.add(new Youvitag( 36, "Low Quality"           , "240p"    , "3GP"     , "320x180"));
        itags.add(new Youvitag(  5, "Low Quality"           , "240p"    , "FLV"     , "426x240"));
        itags.add(new Youvitag( 34, "Medium Quality"        , "360p"    , "FLV"     , "640x360"));
        itags.add(new Youvitag( 18, "Medium Quality"        , "360p"    , "MP4"     , "640x360"));
        itags.add(new Youvitag( 43, "Medium Quality"        , "360p"    , "WebM"    , "640x360"));
        itags.add(new Youvitag( 82, "Medium Quality 3D"     , "360p"    , "MP4"     , "640x360"));
        itags.add(new Youvitag(100, "Medium Quality 3D"     , "360p"    , "WebM"    , "640x360"));

        itags.add(new Youvitag( 35, "Standard Definition"   , "480p"    , "FLV"     , "854x480"));
        itags.add(new Youvitag( 44, "Standard Definition"   , "480p"    , "WebM"    , "854x480"));

        itags.add(new Youvitag( 22, "High Quality"          , "720p"    , "MP4"     , "1280x720"));
        itags.add(new Youvitag( 45, "High Quality"          , "720p"    , "WebM"    , "1280x720"));
        itags.add(new Youvitag( 84, "High Quality 3D"       , "720p"    , "MP4"     , "1280x720"));
        itags.add(new Youvitag(102, "High Quality 3D"       , "720p"    , "WebM"    , "1280x720"));

        itags.add(new Youvitag( 37, "Full High Quality"     , "1080p"   , "MP4"     , "1920x1080"));
        itags.add(new Youvitag( 46, "Full High Quality"     , "1080p"   , "WebM"    , "1280x720"));

        itags.add(new Youvitag( 38, "Original Definition"   , ""        , "MP4"     , "4096x3072"));
    }

    public int itag;
    public String description;
    public String quality;
    public String format;
    public String size;

    public Youvitag(){
        this.itag = -1;
        this.description = "N/A";
        this.quality = "N/A";
        this.format = "N/A";
        this.size = "N/A";
    }

    public Youvitag(int itag, String description, String quality, String format, String size){
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

    public static Youvitag getItag(int itag){
        for (Youvitag youvitag : itags)
            if (youvitag.itag == itag)
                return youvitag;
        return new Youvitag();
    }

    public static int getItagIndex(int itag){
        for (int i=0; i<itags.size(); i++)
            if (itags.get(i).itag == itag)
                return i;
        return -1;
    }

    public static String getItagDescription(int itag){
        Youvitag youvitag = getItag(itag);
        if (youvitag != null)
            return youvitag.toString();
        return new Youvitag().toString();
    }

    public static int compare(Integer itag1, Integer itag2) {
        int index1 = getItagIndex(itag1);
        int index2 = getItagIndex(itag2);
        return Integer.compare(index1, index2);
    }
}
