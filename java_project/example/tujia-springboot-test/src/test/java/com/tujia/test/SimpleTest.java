package com.tujia.test;

import com.google.common.base.Joiner;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.tujia.dto.People;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiaopengw
 * @date 2018/9/14
 * @remark
 */
public class SimpleTest {

    public void fn1() {
        System.out.println(null == null); // true
        System.out.println(null == new People()); // false
        People people = new People();
        System.out.println(people == people); // true
    }

    public void integerToString() {
        Integer a = null;
        Integer b = 88;
        // 方式一
        System.out.println(String.valueOf(a)); // 不报错     ？？？ todo why???
        System.out.println(String.valueOf(null)); // 报错 空指针   ？？？
        System.out.println(String.valueOf(b));
        // 方式二
        System.out.println(a+"");
        System.out.println(b+"");

        // Integer --> String 如果 Integer 为 null 会报错， 因此 建议直接 value+"" 方式将Integer转为String
        System.out.println(String.valueOf(null));
    }

    public static void main(String[] args) {
//        new SimpleTest().fn1();
//        new SimpleTest().integerToString();
//        new SimpleTest().multimapTest();
//        new SimpleTest().joinTest();
        new SimpleTest().mapTest();
    }

    private void mapTest() {

        HashMap<Integer, String> map = Maps.newHashMap();
        map.put(1, "aa");
        map.put(2, "bb");
        System.out.println(map.containsKey(3));
        System.out.println(map.get(3));
    }

    /**
     * 分隔链接
     */
    private void joinTest() {
        ArrayList<People> peoples = Lists.newArrayList();
        peoples.add(new People("kk", 12));
        peoples.add(new People("ww", 18));
        peoples.add(new People("dd", 99));
        String join = Joiner.on(';').join(peoples.stream().map(p -> p.getGender() + "-" + p.getUsername()).collect(Collectors.toList()));
        System.out.println(join);// 12-kk;18-ww;99-dd
        System.out.println(/*this.*/getClass().getSimpleName());
    }

    public void multimapTest() {

        Multimap<Long, Integer> hotelIdVhotelMap = HashMultimap.create();
        hotelIdVhotelMap.put(1L, 10);
        hotelIdVhotelMap.put(2L, 90);
        hotelIdVhotelMap.put(1L, 20);
        hotelIdVhotelMap.put(1L, 30);
        Set<Integer> integers = (Set<Integer>) hotelIdVhotelMap.get(1L); // ok
        List<Integer> integers1 = Lists.newArrayList(integers);
        System.out.println(integers);
        System.out.println(integers1);

    }

}
