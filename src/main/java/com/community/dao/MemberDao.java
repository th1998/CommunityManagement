package com.community.dao;

import com.community.model.MApplyCommunityView;
import com.community.model.Member;
import com.community.model.MemberApply;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberDao {

    //检查是否过该加入社团   member-add
    @Select("select count(*) from c_memberapply where u_id = #{u_id} and co_id = #{co_id} and m_status = 1")
    public int checkJoinCommunity(@Param("u_id") Integer u_id,@Param("co_id") Integer co_id);

    //申请加入社团   member-add
    @Insert("insert into c_memberapply(co_id,u_id,m_status,m_name,m_no,m_class,m_dept,m_tel,m_applytime)" +
            "values(#{co_id},#{u_id},0,#{m_name},#{m_no},#{m_class},#{m_dept},#{m_tel},#{m_applytime})")
    public int applyJoinCommunity(MemberApply memberApply);

    //查询申请入社列表  apply-memberapply-list
    @Select("select * from c_memberapply where co_id = #{co_id} and m_status != 10 and m_status != 3")
    public List<MemberApply> getMemberList(Integer co_id);

    //退团申请列表  apply-membermove-list
    @Select("select * from c_memberapply where co_id = #{co_id} and m_status = 3")
    public List<MemberApply> getMoveMemberList(Integer co_id);

    //审批加入社团处理同意
    @Update("update c_memberapply set m_status = 1 where m_id = #{m_id}")
    public int agreeJoin(Integer m_id);

    //同意加入社团插入成员表
    @Insert("insert into c_member(co_id,u_id,v_name,v_no,v_class,v_dept,v_tel)" +
            "values(#{co_id},#{u_id},#{v_name},#{v_no},#{v_class},#{v_dept},#{v_tel})")
    public int insertMember(Member member);

    //审批加入社团处理不同意
    @Update("update c_memberapply set m_status = 2 where m_id = #{m_id}")
    public int disagreeJoin(Integer m_id);

    //退团申请
    @Update("update c_memberapply set m_status = 3,m_movetime = NOW() where co_id = #{co_id} and u_id = #{u_id}")
    public int moveCommunity(@Param("co_id") Integer co_id,@Param("u_id") Integer u_id);

    //同意退团申请
    @Delete("delete from c_member where co_id = #{co_id} and u_id = #{u_id}")
    public int agreeMove(@Param("co_id") Integer co_id,@Param("u_id") Integer u_id);

    @Update("update c_memberapply set m_status = 10 where co_id = #{co_id} and u_id = #{u_id}")
    public int updateStatus(@Param("co_id") Integer co_id,@Param("u_id") Integer u_id);

    //不同意退团申请
    @Update("update c_memberapply set m_status = 1,m_movetime='' where co_id = #{co_id} and u_id = #{u_id}")
    public int disagreeMove(@Param("co_id") Integer co_id,@Param("u_id") Integer u_id);

    //社团负责人查看本社团成员
    @Select("select * from c_member where co_id = #{co_id}")
    public List<Member> memberAll(Integer co_id);

    //删除社团成员
    @Delete("delete from c_member where v_id = #{v_id}")
    public int memberDel(Integer v_id);

    //用户查看自己加入的社团的历史信息
    @Select("select * from mapplycommunityview where u_id = #{u_id}")
    public List<MApplyCommunityView> MApplyCommunityView(Integer u_id);
}
