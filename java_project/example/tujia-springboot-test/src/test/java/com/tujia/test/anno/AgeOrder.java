package com.tujia.test.anno;

import com.tujia.annotation.OrderAlias;
import org.springframework.stereotype.Service;

/**
 * @author xiaopengw
 * @date 2018/10/9
 * @remark
 */
@OrderAlias(username = "zhangsan", age = 200)
@Service
public class AgeOrder extends AbstractOrder {
}
