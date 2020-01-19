package com.community.dao;

import com.community.model.Apply;
import com.community.model.Community;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommunityDao {

    //查询已有社团  community-list
    @Select("select * from c_community")
    public List<Community> getCommunity();

    //根据负责人查询自己的社团 community-bill
    @Select("select * from c_community where u_id = #{u_id}")
    public List<Community> getOneCommunity(Integer u_id);

    //上传社团海报 community-bill
    @Update("update c_community set co_bill = #{co_bill} where co_id = #{co_id}")
    public int uploadBill(@Param("co_bill") String co_bill,@Param("co_id") Integer co_id);

    //显示所有社团 show-community , all-member-list
    @Select("select * from c_community")
    public List<Community> showCommunity();

    //模糊查询社团  community-list
    @Select("select * from c_community where co_name like '%${co_name}%'")
    public List<Community> sreachCommunity(@Param(value="co_name") String co_name);
}
