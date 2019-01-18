package com.tujia.test.tujia;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiaopengw
 * @date 2018/12/3
 * @remark
 */
public class Test1203 {


    public static void streamTest01() {
        List<String> collect = Stream.of("b", "c", "a").sorted().collect(Collectors.toList());
        System.out.println(collect);
        Map<Long, Long> map = new HashMap<>();
        System.out.println(map.keySet());

    }



    public static void main(String[] args) {
        System.out.println(DateUtil.parse("2018-12-07", "yyyy-MM-dd").getTime());
        System.out.println(DateUtil.parse("2018-12-14", "yyyy-MM-dd").getTime());

    }


}
