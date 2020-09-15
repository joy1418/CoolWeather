package com.qmgame.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amd3600 on 2020/9/13.
 */

public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}


