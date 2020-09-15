package com.qmgame.coolweather.utils;

import android.text.TextUtils;

import com.qmgame.coolweather.db.City;
import com.qmgame.coolweather.db.County;
import com.qmgame.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by amd3600 on 2020/9/13.
 */

public class HttpUtil {

    public static void sendHttpRequest(String url,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    //存入数据库
    public static boolean handleProvinceResponse(String data){
        if(!TextUtils.isEmpty(data)){
            try{
                JSONArray jsonArray = new JSONArray(data);
                for(int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(jsonObject.getString("name"));
                    province.setPrivinceCode(jsonObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String data,int provinceId){
        if(!TextUtils.isEmpty(data)){
            try{
                JSONArray jsonArray = new JSONArray(data);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setCityName(jsonObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String data,int cityId){
        if(!TextUtils.isEmpty(data)){
            try{
                JSONArray jsonArray = new JSONArray(data);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(jsonObject.getString("name"));
                    county.setWeatherId(jsonObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }


}
