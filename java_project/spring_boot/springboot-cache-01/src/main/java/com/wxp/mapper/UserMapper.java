package com.wxp.mapper;

import com.wxp.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where uid = #{uid}")
    User findById(int uid);

}
