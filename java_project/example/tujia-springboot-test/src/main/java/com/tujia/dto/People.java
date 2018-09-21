package com.tujia.dto;

/**
 * @author xiaopengw
 * @date 2018/7/27
 * @remark
 */
public class People {
    private String username;
    private int gender;
    private String[] cities;

    public People() {
    }

    public People(String username, int gender) {
        this.username = username;
        this.gender = gender;
    }

    public String[] getCities() {
        return cities;
    }

    public void setCities(String[] cities) {
        this.cities = cities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "People{" +
                "username='" + username + '\'' +
                ", gender=" + gender +
                ", cities=" + cities +
                '}';
    }
}
