package com.wxp.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 员工类
 */
@AllArgsConstructor // 全参构造
@NoArgsConstructor // 空参构造
@Data // get set toString hash
@Accessors(chain = true) // 支持链式写法设置参数
public class Dept implements Serializable{ // 必须序列化

    private Long deptno;
    private String dname;
    private String db_source;

    public static void main(String[] args) {
        Dept dept = new Dept();
        dept.setDeptno(1L).setDname("wangwu");
        System.out.println(dept);
    }
}
