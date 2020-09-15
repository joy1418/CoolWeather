package com.qmgame.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amd3600 on 2020/9/14.
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{

        @SerializedName("txt")
        public String info;
    }
}
