package com.eightpsman.youvider;

import java.util.Map;

/**
 * Youvider
 * Created by 8psman on 1/23/2015.
 * Email: 8psman@gmail.com
 */
public class YouviderChunk {

    Map<String, String> params;

    public YouviderChunk(Map<String, String> params){
        this.params = params;
    }

    public String getParam(String key){
        if (params != null)
            return params.get(key);
        return null;
    }

}
