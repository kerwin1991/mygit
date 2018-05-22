package com.wxp.pattern.behavior.strategy.concrete;

import com.wxp.pattern.behavior.strategy.strategy.MemberStrategy;

/**
 * ConcreteStrategy 初级会员
 */
public class PrimaryMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于初级会员的没有折扣");
        return booksPrice;
    }
}
