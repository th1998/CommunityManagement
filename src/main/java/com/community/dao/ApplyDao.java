package com.community.dao;

import com.community.model.Apply;
import com.community.model.Community;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApplyDao {

    //申请创立社团 apply-community
    @Insert("insert into c_apply(u_id,ap_status,ap_name,ld_name,ld_tel,ld_deptment,ld_class,gu_name,gu_tel,ap_introduce,ap_target,ap_time) " +
            "values(#{u_id},#{ap_status},#{ap_name},#{ld_name},#{ld_tel},#{ld_deptment},#{ld_class},#{gu_name},#{gu_tel},#{ap_introduce},#{ap_target},#{ap_time})")
    public int applyCommunity(Apply apply);

    //申请社团列表 apply-community-list
    @Select("select * from c_apply order by ap_id desc")
    public List<Apply> applyCommunityList();

    //批准成立社团  apply-community-look
    @Update("update c_apply set ap_status = 1 where ap_id = #{ap_id}")
    public int ratifyCommunity(Integer ap_id);

    //不批准成立社团  apply-community-look
    @Update("update c_apply set ap_status = 2 where ap_id = #{ap_id}")
    public int disagreeCommunity(Integer ap_id);

    //将审批通过的社团插入到社团表  apply-community-look
    @Insert("insert into c_community(u_id,co_name,co_ldname,co_ldtel,co_guname,co_gutel,co_introduce,co_creattime) " +
            "values(#{u_id},#{co_name},#{co_ldname},#{co_ldtel},#{co_guname},#{co_gutel},#{co_introduce},#{co_creattime})")
    public int insertCommunity(Community community);

    //社团申请记录
    @Select("select * from c_apply where u_id = #{u_id}")
    public List<Apply> communityHistory(Integer u_id);
}
