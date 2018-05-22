package com.wxp.pattern.behavior.strategy.concrete;

import com.wxp.pattern.behavior.strategy.strategy.MemberStrategy;

/**
 * ConcreteStrategy 高级会员
 * 具体的实现，封装不同的算法、业务
 */
public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.8;
    }
}
