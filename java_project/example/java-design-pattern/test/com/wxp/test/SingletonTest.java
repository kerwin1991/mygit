package com.wxp.test;

import com.wxp.pattern.create.singleton.PlusSingleton;

/**
 *
 */
public class SingletonTest {

    public static void main(String[] args) {
        System.out.println(PlusSingleton.getInstance());
        System.out.println(PlusSingleton.getInstance());
    }
}
