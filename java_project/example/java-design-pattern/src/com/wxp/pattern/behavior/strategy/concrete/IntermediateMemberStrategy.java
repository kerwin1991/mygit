package com.wxp.pattern.behavior.strategy.concrete;

import com.wxp.pattern.behavior.strategy.strategy.MemberStrategy;

/**
 * ConcreteStrategy 中级会员
 */
public class IntermediateMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于中级会员的折扣为10%");
        return booksPrice * 0.9;
    }
}
