package com.tujia.test.lambda;

import java.math.BigDecimal;

/**
 * Created by xiaopengw on 2018/7/18.
 */
public class Price {

    private int gender;
    private BigDecimal salePrice;
    private BigDecimal primaryPrice;

    public Price() {
    }

    public Price(int gender, BigDecimal salePrice, BigDecimal primaryPrice) {
        this.gender = gender;
        this.salePrice = salePrice;
        this.primaryPrice = primaryPrice;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getPrimaryPrice() {
        return primaryPrice;
    }

    public void setPrimaryPrice(BigDecimal primaryPrice) {
        this.primaryPrice = primaryPrice;
    }
}
