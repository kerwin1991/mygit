package com.wxp.pattern.behavior.strategy;

import com.wxp.pattern.behavior.strategy.concrete.AdvancedMemberStrategy;
import com.wxp.pattern.behavior.strategy.context.Price;
import com.wxp.pattern.behavior.strategy.strategy.MemberStrategy;

/**
 * 客户端
 * 需要根据不同级别的用户，不同的折扣率，得出其购买商品价格
 *
 *
 * 策略模式仅仅封装算法，方便 新的算法插入到已有系统中，以及老算法从系统中“退休” ，策略模式并不决定在何时使用何种算法。
 * 在什么情况下使用什么算法是由客户端决定的。
 *
 */
public class Client {

    public static void main(String[] args) {
        //选择并创建需要使用的策略对象  高级会员 由客户端决定使用什么样的算法
        MemberStrategy strategy = new AdvancedMemberStrategy();
        //创建环境 高级会员
        Price price = new Price(strategy);
        //计算价格 环境会根据高级会员计算价格
        double quote = price.quote(300);
        System.out.println("图书的最终价格为：" + quote);
    }

}
