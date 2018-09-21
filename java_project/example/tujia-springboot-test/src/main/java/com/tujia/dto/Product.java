package com.tujia.dto;

import java.io.Serializable;

/**
 * @author xiaopengw
 * @date 2018/8/29
 * @remark builder 模式
 */
public class Product implements Serializable {

    private static final long serialVersionUID = -595660829645568069L;

    private String name;
    private long price;
    private int category;
    private String summary;
    private long discount;

    // builder构造
    public Product(ProductBuilder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.category = builder.category;
        this.summary = builder.summary;
        this.discount = builder.discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    /**
     * builder 模式  内部类
     */
    public static class ProductBuilder{
        private String name;
        private long price;
        private int category;
        private String summary;
        private long discount;

        // 无参构
        public ProductBuilder() {
        }

        /**
         * 这些set方法  return  this
         * @param name
         * @return
         */
        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder price(long price) {
            this.price = price;
            return this;
        }

        public ProductBuilder category(int category) {
            this.category = category;
            return this;
        }

        public ProductBuilder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public ProductBuilder discount(long discount) {
            this.discount = discount;
            return this;
        }

        // 最后build返回实体
        public Product build() {
            return new Product(this);
        }
    }
}
