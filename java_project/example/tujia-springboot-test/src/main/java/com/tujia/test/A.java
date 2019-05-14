package com.tujia.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaopengw
 * @date 2018/12/17
 * @remark
 */
public class A {
    public static void main(String[] args) {
        String line = "/Date(1516291200000+0800)/";
        System.out.println(line.indexOf("("));
        System.out.println(line.indexOf("+"));

        HashMap<Integer, List<String>> map = Maps.newHashMap();
        map.put(1, Lists.newArrayList("abc"));
        map.put(2, Lists.newArrayList("cba"));
        System.out.println(map.get(3));

        List<Integer> list1 = Lists.newArrayList();
        List<Integer> list2 = null;
        for (Integer in : list1) {
            System.out.println(in);
        }
        for (Integer in : list2) {
            System.out.println(in);
        }
    }

    public static void method_one() {
        // 按指定模式在字符串查找
        String line = "/Date(1516291200000+0800)/";
        //String pattern = "(\\d+)";
        String pattern = "(\\d+)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
        } else {
            System.out.println("NO MATCH");
        }
    }
}
