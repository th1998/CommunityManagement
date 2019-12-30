package com.community.dao;

import com.community.model.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberDao {

    //申请加入社团   member-add
    @Insert("insert into c_member(co_id,u_id,m_status,m_name,m_no,m_class,m_dept,m_tel,m_applytime)" +
            "values(#{co_id},#{u_id},0,#{m_name},#{m_no},#{m_class},#{m_dept},#{m_tel},#{m_applytime})")
    public int applyJoinCommunity(Member member);

    //查询申请入社列表  apply-member-list
    @Select("select * from c_member")
    public List<Member> getMemberList();

    //审批加入社团处理同意
    @Update("update c_member set m_status = 1 where m_id = #{m_id}")
    public int agreeJoin(Integer m_id);

    //审批加入社团处理不同意
    @Update("update c_member set m_status = 2 where m_id = #{m_id}")
    public int disagreeJoin(Integer m_id);

    //退团
    @Update("update c_member set m_status = 3 where co_id = #{co_id} and u_id = #{u_id}")
    public int moveCommunity(@Param("co_id") Integer co_id,@Param("u_id") Integer u_id);
}
