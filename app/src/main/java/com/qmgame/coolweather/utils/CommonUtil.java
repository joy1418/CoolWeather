package com.qmgame.coolweather.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.qmgame.coolweather.gson.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by amd3600 on 2020/9/14.
 */

public class CommonUtil {

    /**
     * weather字符串转weather对象
     * @param response
     * @return
     */
    public static Weather handleWeatherResponse(String response){
        Log.d(CommonUtil.class.getSimpleName(),response);
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}


