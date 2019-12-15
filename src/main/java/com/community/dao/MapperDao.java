package com.community.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapperDao {

    //批量删除用户
    public int delUsers(List<Object> list);
}
