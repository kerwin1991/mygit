package com.tujia.test.lambda.function;

/**
 * @author xiaopengw
 * @date 2019/4/17
 * @remark
 */
@FunctionalInterface
public interface MyFunctionInterface {

    String myName(String name);

    public static void main(String[] args) {
        MyFunctionInterface name = username -> username + "_ret";

        System.out.println(name.myName("kerwin"));
    }

}
