package com.eightpsman.youvider;

import java.util.*;

/**
 * Youvider
 * Created by 8psman on 1/23/2015.
 * Email: 8psman@gmail.com
 */

/**
 * Youvider Media Representation Description
 */
public class YouviderMpd {

    List<Mpd> mpds;

    Map<String, String> infos;

    public YouviderMpd(){
        mpds = new ArrayList<Mpd>();
        infos = new HashMap<String, String>();
    }

    public List<Mpd> getMpdList(){
        return mpds;
    }

    public void sortByQualityDescending(){
        Collections.sort(mpds, new Comparator<Mpd>() {
            @Override
            public int compare(Mpd o1, Mpd o2) {
                try{
                    int itag1 = Integer.parseInt(o1.getParam("itag"));
                    int itag2 = Integer.parseInt(o2.getParam("itag"));
                    return Youvitag.compare(itag1, itag2);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return 0;
            }
        });
    }

    public Mpd getMdp(int index){
        if (index >= 0 && index < mpds.size())
            return mpds.get(index);
        return null;
    }

    public void addMpd(Map<String, String> maps){
        mpds.add(new Mpd(maps));
    }

    public void addMpd(Mpd mpd){
        mpds.add(mpd);
    }

    public void addInfo(String key, String content){
        infos.put(key, content);
    }

    public String getInfo(String key){
        return infos.get(key);
    }

    public String getVideoTitle(){
        String title =  infos.get("title");
        if (title == null)
            title = "Unknown video name";
        return title;
    }

    class Mpd{
        Map<String, String> params;

        public Mpd(Map<String, String> params){
            this.params = params;
        }

        public String getParam(String key){
            if (params != null)
                return params.get(key);
            return null;
        }

        public int getItag(){
            try{
                return Integer.parseInt(params.get("itag"));
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return -1;
        }

        @Override
        public String toString() {
            if (params != null){
                return params.toString();
            }else{
                return "";
            }
        }
    }

}
