package com.tujia.test;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tujia.dto.People;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xiaopengw
 * @date 2018/8/14
 * @remark
 */
public class Test1 {
    public static void main(String[] args) {
        new Test1().fn1();
//        new Test1().fn2();
//        new Test1().fn3();
//        new Test1().fn4();
       // new Test1().fn5();
//        new Test1().fn6();

    }

    private void fn6() {

        People people = new People();
        people.setGender(1);
        People people1 = people;
        people1.setGender(2);
        System.out.println(people.getGender());

        AtomicBoolean isRunning = new AtomicBoolean(false);
        boolean b = isRunning.compareAndSet(false, true);
        System.out.println(b);// true
    }

    // bigdecimal
    private void fn5() {
        BigDecimal bigDecimal = new BigDecimal("0");
        System.out.println(bigDecimal.compareTo(new BigDecimal(0)));
        System.out.println(bigDecimal.compareTo(new BigDecimal(1)));
        System.out.println(bigDecimal.compareTo(new BigDecimal(0)) >= 0 && bigDecimal.compareTo(new BigDecimal(1)) < 0);
    }

    private void fn4() {

//        BigDecimal bigDecimal = new BigDecimal("0.25");
//        BigDecimal bigDecimal = new BigDecimal("25");
        BigDecimal bigDecimal = new BigDecimal("0");
        System.out.println(validateBigDecimal(bigDecimal, 5, 4));

    }

    // 将List转换为另一种泛型的集合
    private void fn3() {
        List<String> originList = Lists.newArrayList("01","02","03");
        List<Integer> newList = Lists.transform(originList, new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.parseInt(input);
            }
        });
        newList.forEach(num -> {
            System.out.println(num);
        });
        System.out.println(originList);
        System.out.println(newList);
    }

    // 求差集  set1 有 set2 无
    private void fn2() {
        HashSet<Integer> set1 = Sets.newHashSet(1, 3, 5);
        HashSet<Integer> set2 = Sets.newHashSet(5,7);
        Sets.SetView<Integer> difference = Sets.difference(set1, set2);
        System.out.println(difference);
    }

    private void fn1() {
        //System.out.println(new Date().getTime());
        // 1534223624783

        long a = 0L;
        System.out.println(a == 0);
    }

    /**
     * 校验 整数位 小数位 位数
     * @param value
     * @param intCount
     * @param floatCount
     * @return
     */
    public static boolean validateBigDecimal(BigDecimal value, int intCount, int floatCount) {
        if (value == null) return true;
        if (intCount == 0) intCount = 16;
        if (floatCount == 0) floatCount = 4;
        String[] split = value.toString().split("\\.");
        if (split.length == 1)
            return split[0].length() <= intCount;
        else if (split.length == 2)
            return split[0].length() <= intCount && split[1].length() <= floatCount;
        return true;
    }
}
