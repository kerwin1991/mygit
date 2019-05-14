package com.tujia.test.clone;

import com.tujia.dto.Address;
import com.tujia.dto.Location;
import com.tujia.dto.Man;
import com.tujia.dto.Woman;

/**
 * @author xiaopengw
 * @date 2019/4/26
 * @remark
 */
public class CloneTestTwo {

    public static void main(String[] args) {

        new CloneTestTwo().fn1();

    }

    // 序列化的方式实现 深度克隆
    private void fn1() {
        Woman woman = new Woman();
        woman.setBirthYear(2019);
        Location location = new Location();
        location.setCity("北京");
        location.setLng(100);
        woman.setLocation(location);
        // 流+序列化 实现 深度克隆
        Woman cloneWoman = woman.cloneWoman();
        System.out.println(woman);
        System.out.println(cloneWoman);
        System.out.println("-------深度克隆验证--------");
        location.setCity("天津");
        location.setLng(200);
        System.out.println(woman);
        System.out.println(cloneWoman);
    }



    /**
     * 如果引用类型里面还包含很多引用类型，或者内层引用类型的类里面又包含引用类型，使用clone方法就会很麻烦。这时我们可以用序列化的方式来实现对象的深克隆。
     *
     */
}
