package com.tujia.test.lambda.function;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author xiaopengw
 * @date 2019/4/17
 * @remark
 */
public class TestFunctionInterface {

    public static final List<MyFunctionInterface> getNameFunctionList = Lists.newArrayList();

    public static final List<CheckFunctionInterface<TestFunctionInterface, Integer, Long, Boolean>> checkList = Lists.newArrayList();

    static {
        getNameFunctionList.add(TestFunctionInterface::getNameOne);
        getNameFunctionList.add(TestFunctionInterface::getNameTwo);

        checkList.add(TestFunctionInterface::checkOne);
        checkList.add(TestFunctionInterface::checkTwo);
        checkList.add(TestFunctionInterface::checkThree);
    }

    private Boolean checkThree(Integer integer, Long aLong) {


        return null;
    }

    private Boolean checkTwo(Integer integer, Long aLong) {



        return null;
    }

    private Boolean checkOne(Integer integer, Long aLong) {



        return null;
    }

    private static String getNameTwo(String s) {
        return s + "_b";
    }

    private static String getNameOne(String s) {

        return s + "_a";
    }

    public static void main(String[] args) {
        for (MyFunctionInterface function : getNameFunctionList) {
            System.out.println(function.myName("wxp"));
        }

    }

    public boolean check(Integer i, Long l) {
        for (CheckFunctionInterface<TestFunctionInterface, Integer, Long, Boolean> func : checkList) {
            Boolean check = func.check(this, 10, 100L);
        }
        return true;
    }


}
