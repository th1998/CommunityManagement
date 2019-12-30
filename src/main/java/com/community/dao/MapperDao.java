package com.community.dao;

import com.community.model.Member;
import com.community.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapperDao {

    //批量删除用户
    public int delUsers(List<Object> list);

    public List<User> login(User user);

    //学生查看自己加入的社团
    public List<Member> getJoinCommunity(Integer u_id);
}
