package com.community.dao;

import com.community.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    //查询用户-用户列表 user-list
    @Select("select * from c_user order by role desc")
    public List<User> getAllUser();

    //添加用户 user-add
    @Insert("insert into c_user(username,password,no,deptment,role)" +
            "values(#{username},#{password},#{no},#{deptment},#{role})")
    public int userAdd(User user);

    //删除单个用户  user-list
    @Delete("delete from c_user where id = #{id}")
    public int userDel(Integer id);

    //模糊查询用户 user-list
    @Select("select * from c_user where no like '%${no}%'")
    public List<User> sreachUser(@Param(value="no")String no);
}