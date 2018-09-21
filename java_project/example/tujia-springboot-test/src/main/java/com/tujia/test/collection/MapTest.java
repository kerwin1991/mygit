package com.tujia.test.collection;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiaopengw
 * @date 2018/7/28
 * @remark
 */
public class MapTest {

    // map  get(null)  返回  null
    private void fn1() {
        HashMap<Integer, String> map = new HashMap<>(5);
        String s = map.get(null);
        System.out.println(s);
    }

    private void fn2() {
        System.out.println("\"" + "月结" + "\"" + " 修改为 " + "\"" + "周结" + "\"");
    }

    private void fn3() {
        System.out.println(new Date().getTime());//  1533119824806  1号 时间戳
//        System.out.println(new Date(1533111213250L));
    }


    public static void main(String[] args) {
        MapTest test = new MapTest();
//        test.fn1();
//        test.fn2();
//        test.fn3();
//        test.fn4();
        test.fn5();
    }

    private void fn5() {
        List<Integer> need = Arrays.asList(1, 2, 11);
        List<Integer> own = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(own.containsAll(need));

    }

    private void fn4() {
        String message = "渠道创建成功!"
                + "\n" + "渠道名称:携程"
                + "\n" + "渠道号:32"
                + "\n" + "渠道负责人:我问问";
        System.out.println(message);
    }

}
