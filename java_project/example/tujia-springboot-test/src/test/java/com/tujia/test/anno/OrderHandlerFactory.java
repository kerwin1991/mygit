package com.tujia.test.anno;

import com.tujia.annotation.OrderAlias;

/**
 * @author xiaopengw
 * @date 2018/10/9
 * @remark
 */
public class OrderHandlerFactory {


    public static void registerOrderService(OrderAlias alias, AbstractOrder abstractOrder) {

        System.out.println(alias.username()+"-"+alias.age()+"-"+abstractOrder);
        //zhangsan-200-com.tujia.test.anno.AgeOrder@53a5e217
        //lisi-900-com.tujia.test.anno.NameOrder@624a24f6
    }
}
