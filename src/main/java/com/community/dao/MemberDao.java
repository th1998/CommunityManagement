package com.community.dao;

import com.community.model.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    //申请加入社团   member-add
    @Insert("insert into c_member(co_id,u_id,m_status,m_name,m_no,m_class,m_dept,m_tel,m_applytime)" +
            "values(#{co_id},#{u_id},0,#{m_name},#{m_no},#{m_class},#{m_dept},#{m_tel},#{m_applytime})")
    public int applyJoinCommunity(Member member);
}
