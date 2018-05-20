package com.wxp.mapper;

import com.wxp.pojo.Customer;
import org.apache.ibatis.annotations.*;

/**
 * Created by Administrator on 2018/5/15.
 */
@Mapper
public interface CustomerMapper {

    @Select("select * from customer where custId = #{id}")
    Customer selectById(int id);

    @Update("UPDATE customer SET custName = #{custName},address=#{address},desc_info=#{descInfo} WHERE custId = #{custId}")
    void updateById(Customer customer);

    @Insert("INSERT INTO customer (custName,address) VALUES (#{custName},#{address})")
    void insert(Customer customer);

    @Delete("DELETE FROM customer WHERE custId=#{custId}")
    void deleteCust(int custId);

    @Select("select * from customer where custName = #{custName}")
    Customer queryByName(String custName);
}
