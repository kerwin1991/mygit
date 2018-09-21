package com.tujia.dto;

/**
 * @author xiaopengw
 * @date 2018/8/29
 * @remark
 */
public class Banana extends Product {

    // 是否国产
    private boolean local;

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public Banana(ProductBuilder builder) {
        super(builder);
    }

    public Banana(ProductBuilder builder, boolean local) {
        super(builder);
        this.local = local;
    }

    // builder 模式
    public static Banana getInstance(ProductBuilder builder, boolean local) {
        return new Banana(builder, local);
    }

}
