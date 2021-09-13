package com.wx.class03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyIntComparator implements Comparator<Integer> {



    @Test
    public void Test1(){
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(1);
        System.out.println(list);
        list.sort(new MyIntComparator());
        System.out.println(list);

    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2-o1;
    }
}



