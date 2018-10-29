package com.tujia.test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.Set;

/**
 * @author xiaopengw
 * @date 2018/9/21
 * @remark guavamap 测试类
 */
public class MapTest {

    public static void main(String[] args) {
        new MapTest().listMultiMapTest();
        //new MapTest().multiMapTest();

    }

    // 有序的集合
    public void listMultiMapTest() {
        ArrayListMultimap<Integer, Integer> multimap = ArrayListMultimap.create();
        multimap.put(1, 2);
        multimap.put(1, 222);
        multimap.put(1, 22);
        multimap.put(1, 33);
        multimap.put(1, 3);
        multimap.put(2, 8);
        printMap(multimap);

        List<Integer> integers = multimap.get(1);

    }

    // 无序的集合
    public void multiMapTest() {
        HashMultimap<Integer, Integer> multimap = HashMultimap.create();
        multimap.put(1, 2);
        multimap.put(1, 222);
        multimap.put(1, 22);
        multimap.put(1, 33);
        multimap.put(1, 3);
        multimap.put(2, 8);
        printMap(multimap);

        Set<Integer> integers = multimap.get(1);
    }



    public <K,V> void printMap(Multimap<K, V> multimap) {
        for (K key : multimap.keySet()) {
            System.out.println(multimap.get(key));
        }
    }


}
