package com.qmgame.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;

import com.qmgame.coolweather.gson.Weather;
import com.qmgame.coolweather.utils.CommonUtil;
import com.qmgame.coolweather.utils.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by amd3600 on 2020/9/15.
 */

public class AutoUpdateService extends Service {

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.d(AutoUpdateService.class.getSimpleName(),"定时更新天气信息服务启动!");
        updatWeather();
        updateBingPic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int hour = 8*60*60*1000;
        long triggerTime  = SystemClock.elapsedRealtime() + hour;
        Intent i = new Intent(this,AutoUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,0,i,0);
        manager.cancel(pendingIntent);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerTime,pendingIntent);
        return super.onStartCommand(intent,flags,startId);
    }


    public void updatWeather(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this);
        String weatherString = preferences.getString("wather",null);
        if(weatherString !=null){
            Weather weather = CommonUtil.handleWeatherResponse(weatherString);
            final String weatherId = weather.basic.weatherId;
            String url= "http://guolin.tech/api/weather?cityid="+weatherId;
            HttpUtil.sendHttpRequest(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String reponseString = response.body().string();
                    Weather weather1 = CommonUtil.handleWeatherResponse(reponseString);
                    if(weather1!=null && "ok".equals(weather1.status)){
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("weather",reponseString);
                        editor.apply();
                    }
                }
            });
        }
    }

    public void updateBingPic(){
        String url = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bing_pic",bingPic);
                editor.apply();
            }
        });

    }
}
