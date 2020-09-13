package com.qmgame.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by amd3600 on 2020/9/13.
 */

public class County extends DataSupport {

    private int id;

    private String countyName;

    private int countId;

    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getCountId() {
        return countId;
    }

    public void setCountId(int countId) {
        this.countId = countId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
