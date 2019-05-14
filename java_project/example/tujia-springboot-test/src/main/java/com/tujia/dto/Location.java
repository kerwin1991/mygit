package com.tujia.dto;

import java.io.Serializable;

/**
 * @author xiaopengw
 * @date 2019/5/10
 * @remark
 */
public class Location implements Serializable {

    private static final long serialVersionUID = 5474550250655690001L;

    private String city;

    private int lng;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", lng=" + lng +
                '}';
    }
}
