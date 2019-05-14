package com.tujia.test.lambda.function;

/**
 * @author xiaopengw
 * @date 2019/4/17
 * @remark
 */
@FunctionalInterface
public interface CheckFunctionInterface<A, B, C, Rsp> {

    Rsp check(A req0, B req1, C req2);

}
