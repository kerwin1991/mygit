package com.tujia.annotation;

import java.lang.annotation.*;

/**
 * @author xiaopengw
 * @date 2018/10/9
 * @remark
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderAlias {

    String username() default "china";

    int age() default 100;

}

