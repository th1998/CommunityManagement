package com.community.dao;

import com.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginDao {

    //用户登录
    @Select("select * from c_user where username = #{username} and password = #{password}")
    public List<User> Login(@Param("username") String username,@Param("password") String password);
}
