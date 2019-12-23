package com.community.dao;

import com.community.model.Apply;
import com.community.model.Community;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommunityDao {

    //查询已有社团  community-list
    @Select("select * from c_community")
    public List<Community> getCommunity();

    //根据负责人查询自己的社团 community-bill
    @Select("select * from c_community where c_id = #{u_id}")
    public List<Community> getOneCommunity(Integer u_id);
}
