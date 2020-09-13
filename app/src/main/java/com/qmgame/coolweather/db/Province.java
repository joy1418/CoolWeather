package com.qmgame.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by amd3600 on 2020/9/13.
 */

public class Province extends DataSupport {

    private int id;

    private String provinceName;

    private int privinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getPrivinceId() {
        return privinceId;
    }

    public void setPrivinceId(int privinceId) {
        this.privinceId = privinceId;
    }
}
