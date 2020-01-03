package com.community.dao;

import com.community.model.CommunityCountView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CountDao {

    //统计各个社团人数
    @Select("select * from communityCountView")
    public List<CommunityCountView> communityCount();
}