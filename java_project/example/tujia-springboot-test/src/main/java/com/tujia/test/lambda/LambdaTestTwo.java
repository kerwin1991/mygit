package com.tujia.test.lambda;

import com.google.common.collect.Lists;
import com.tujia.dto.Animal;
import com.tujia.dto.People;
import com.tujia.dto.PeopleAnimalMapper;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xiaopengw on 2018/7/18.
 */
public class LambdaTestTwo {

    public static void main(String[] args) {
        //new LambdaTestTwo().fn1();
        //new LambdaTestTwo().fn2();
        //new LambdaTestTwo().fn3();
        //new LambdaTestTwo().fn4();
        //new LambdaTestTwo().fn5();
        new LambdaTestTwo().fn6();
    }

    // 求最大
    private void fn6() {
        ArrayList<Integer> list = Lists.newArrayList(2, 1, 44, 3, 23);
        Integer integer = list.stream().max(Comparator.comparing(a -> a)).get();
        System.out.println(integer);

    }

    // 过滤
    private void fn5() {
        List<People> peoples = Lists.newArrayList(
                new People("猫猫", 8),
                new People("狗狗", 18),
                new People("黑猫", 88),
                new People("白猫", 188),
                new People("兔兔", 28));
        List<People> collect = peoples.stream().filter(t -> t.getGender() >= 88).collect(Collectors.toList());
        List<String> collect1 = peoples.stream().filter(t -> t.getGender() >= 88).map(t -> t.getUsername()).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect1);
    }

    /**
     * 集合实体转换  好用啊!!!!!!  可以用于大集合内实体的转换   com.tujia.tns.price.biz.mappers
     *
     * 也可以调用当前类中方法 this 进行实体转换
     *
     * commentInfoBeanList.stream().map(this::mappingComment).collect(Collectors.toList())
     *
     */
    private void fn4() {
        List<People> peoples = Lists.newArrayList(
                new People("猫猫", 8),
                new People("狗狗", 18),
                new People("兔兔", 28));
        List<Animal> animals = peoples.stream().map(new PeopleAnimalMapper()).filter(t -> t != null).collect(Collectors.toList());
        System.out.println(animals);
    }

    /**
     * 过滤
     */
    private void fn3() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> collect = list.stream().filter(t -> t > 2).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 将集合对象中的属性，根据筛选条件，抽取为另一个集合
     */
    private void fn2() {
        List<Price> list = Arrays.asList(
                new Price(10, new BigDecimal(1), new BigDecimal(2)),
                new Price(22, new BigDecimal(10), new BigDecimal(20)),
                new Price(33, new BigDecimal(100), new BigDecimal(200)),
                new Price(44, new BigDecimal(100), new BigDecimal(200))
        );

        Set<Integer> collect = list.stream().map(t -> t.getGender()).filter(t -> t > 10).collect(Collectors.toSet());
        System.out.println(collect);
    }

    /**
     * 求和
     */
    private void fn1() {
        List<Price> list = Arrays.asList(
                new Price(1, new BigDecimal(1), new BigDecimal(2)),
                new Price(2, new BigDecimal(10), new BigDecimal(20)),
                new Price(3, new BigDecimal(100), new BigDecimal(200))
        );
        // 求和
        BigDecimal bigDecimal = list.stream().map(false ? Price::getSalePrice : Price::getPrimaryPrice).reduce(BigDecimal::add).get();
        System.out.println(bigDecimal);

        // 求和  ？？？
        Integer reduce = list.stream().map(Price::getGender).reduce(0, (sum, obj) -> sum + obj);
        System.out.println(reduce);

        // 返回 boolean
        boolean ret = Stream.of("AA",
                "BB",
                "CC",
                "DD",
                "EE").anyMatch(a -> a.equals("FF"));
        System.out.println(ret);
    }


    /**
     * apiResponse.getData().stream().filter(d -> d != null).forEach(d -> {
     *    resultList.add(d);
     * });
     */

}
