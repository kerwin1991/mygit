package com.tujia.test.lambda;

import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by xiaopengw on 2018/7/18.
 */
public class LambdaTestOne {

    private static final Logger LOGGER = LoggerFactory.getLogger(LambdaTestOne.class);

    private static List<Student> list = new ArrayList<>();

    static {
        list.add(new Student(1L, 10, "张三", '1'));
        list.add(new Student(2L, 20, "李四", '2'));
        list.add(new Student(3L, 30, "王五", '1'));
        list.add(new Student(4L, 40, "赵柳", '1'));
    }

    public static void main(String[] args) {
//        System.out.println(list);
//        fn1();
//        fn2();
//        fn3();
//        fn4();
//        fn5();
//        fn6();
//        fn7();
//        fn8();
        fn9();
    }

    /**
     * 分隔字符串 转为 list
     */
    private static void fn9() {
        String list = "1,2,3,4,5";
        List<Long> collect = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(list).stream().map(Long::valueOf)
                .collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(String.valueOf(null));
    }

    private static void fn8() {
        String s = null;
        //System.out.println(s.length());
        try {
            System.out.println(s.length());
        } catch (Exception e) {
            LOGGER.info("aaa:{}",1,e);
        }
    }

    private static void fn7() {
        ArrayList<Student> list1 = new ArrayList<>();
        Student 张三 = new Student(1L, 10, "张三", '1');
        Student 李四 = new Student(2L, 20, "李四", '2');
        list1.add(张三);
        list1.add(李四);
        //ArrayList<Student> list2 = new ArrayList<>();
        //list2.add(list1.get(0));
        System.out.println(list1);
        Map<Long, Student> collect = list1.stream().collect(Collectors.toMap(Student::getId, Function.identity(), (key1, key2) -> key2));
        list1.remove(collect.get(1L));
        System.out.println(list1);
        System.out.println(collect);
        //System.out.println(list2);
    }

    // 求最大最小值
    private static void fn6() {
        list.stream().mapToLong(Student::getId).max().getAsLong();
        System.out.println(list.stream().mapToLong(Student::getId).max().getAsLong());
        System.out.println(list.stream().mapToLong(Student::getId).min().getAsLong());

    }

    private static void fn5() {
        List<Long> collect = list.stream()
                .filter(o -> o.getAge() > 10)
                .filter(o -> o.getAge() < 40)
                .map(Student::getId).collect(Collectors.toList());
        System.out.println(collect);
    }

    // List - List
    private static void fn4() {

        List<Long> ids = list.stream().map(Student::getId).collect(Collectors.toList());

        System.out.println(ids);

    }

    // 集合 List - Map 属性
    private static void fn2() {
        Map<Long, String> map = list.stream().collect(Collectors.toMap(Student::getId, Student::getName));

        map.forEach((key,value)->{
            System.out.println(key + "---" + value);
        });
    }

    // 集合 List - Map  对象
    private static void fn1() {
        Map<Long, Student> map = list.stream().collect(Collectors.toMap(Student::getId, Student -> Student));

        map.forEach((key,value)->{
            System.out.println(key + "---" + value);
        });

    }

    // 组成的map中的key出现重复时， (key1,key2)->key1 表示 保留第一个key对应的value  (key1,key2)->key2 表示 保留最后一个key对于的value
    private static void fn3() {
        //Map<Character, String> map = list.stream().collect(Collectors.toMap(Student::getGender, Student::getName, (key1,key2)->key1));
        // Function.identity() 表示 当前元素本身啦
        Map<Long, Student> map = list.stream().collect(Collectors.toMap(Student::getId, Function.identity(), (key1, key2) -> key1));

        map.forEach((key,value)->{
            System.out.println(key + "---" + value);
        });
        // 方法2
        Map<Long, String> collect = list.stream().collect(Collectors.toMap(t -> t.getId(), t -> t.getName()));
    }
}
