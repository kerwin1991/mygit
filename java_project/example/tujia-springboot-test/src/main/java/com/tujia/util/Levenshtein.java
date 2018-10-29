package com.tujia.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author xiaopengw
 * @date 2018/9/21
 * @remark
 */
public class Levenshtein {

    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList("a", "bb", "ccc", "dddd");
        int length = strings.iterator().next().length();
        System.out.println(length);

        // get 到了
        Map<Integer, String> map = Maps.newHashMap();
        map.put(1, "1");
        map.put(2, "22");
        map.values().iterator().next().length(); // 这个可以获取集合的第一个元素，当无法通过 get(0) 获得第一个集合元素时，使用


        ArrayList<Object> objects = Lists.newArrayListWithExpectedSize(10);
        //ArrayList<Object> objects = null;
        for (Object o : objects) {
            System.out.println("--");
            System.out.println(o.getClass());
        }

    }

}
