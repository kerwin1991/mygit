package com.tujia.test;

import com.tujia.dto.Product;

/**
 * @author xiaopengw
 * @date 2018/8/29
 * @remark
 */
public class BuilderTest {
    public static void main(String[] args) {
        Product.ProductBuilder productBuilder = new Product.ProductBuilder()
                .name("苹果")
                .price(2)
                .category(1)
                .summary("富士苹果")
                .discount(50);

        Product product = new Product.ProductBuilder()
                .name("苹果")
                .price(2)
                .category(1)
                .summary("富士苹果")
                .discount(50).build();


    }
}
