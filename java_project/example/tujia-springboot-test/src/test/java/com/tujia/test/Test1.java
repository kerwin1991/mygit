package com.tujia.test;

import cn.hutool.core.date.DateUtil;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tujia.dto.People;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author xiaopengw
 * @date 2018/8/14
 * @remark
 */
public class Test1 {
    public static void main(String[] args) {
        //new Test1().fn1();
        //new Test1().fn2();
//        new Test1().fn3();
//        new Test1().fn4();
       // new Test1().fn5();
//        new Test1().fn6();
//        new Test1().fn7(1000);
//        new Test1().fn8();
//        new Test1().fn9();
        new Test1().fn10();

    }

    private void fn10() {
        List<Long> unitIdList = Lists.newArrayList(3L, 6L, 1L);
        Long along = unitIdList.stream().max(Comparator.comparing(aLong -> aLong)).get();
        System.out.println(along);
        Set<Integer> emptySet = Collections.emptySet();
        System.out.println(emptySet.size());
        System.out.println(emptySet.contains(1));
        emptySet.addAll(Sets.newHashSet(22));

    }

    private void fn9() {
        ConcurrentHashMap<String, People> cmap = new ConcurrentHashMap<>();
        cmap.put("zs_1", new People("zs", 1));
        System.out.println(cmap);
        People people = cmap.computeIfAbsent("zs_2", t -> new People("zs", 2));
        System.out.println(people);
        cmap.computeIfAbsent("zs_3", t -> {
            System.out.println(t);
            return new People("zs", 3);
        });
        System.out.println(cmap);
        System.out.println(BigDecimal.ZERO);

    }

    private void fn8() {
        People tujia = new People("tujia", 33);
        System.out.println("abc"+null);
        StringBuilder stringBuilder = new StringBuilder("abc");
        String a = null;
        System.out.println(stringBuilder.append(a).toString());

        System.out.println((byte)0x2);
        System.out.println(0x2);

    }

    // switch
    private void fn7(int i) {
        switch (i) {
            case 0:
                System.out.println("零");
                break;
            case 1:
                System.out.println("一");
                //break;
            case 2:
            case 1000:
                System.out.println("二");
                break;
            default:
                System.out.println("other");
                break;
        }
        System.out.println(DateUtil.parse("2018-10-17", "yyyy-MM-dd").getTime());

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

        ArrayList<People> objects = Lists.newArrayList();

        Set<String> collect = objects.stream().map(t -> t.getUsername()).collect(Collectors.toSet());

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
