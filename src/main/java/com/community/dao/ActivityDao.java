package com.community.dao;

import com.community.model.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ActivityDao {

    //申请活动
    @Insert("insert into c_activity(u_id,a_status,co_name,a_name,a_ldname,a_ldtel,a_time,a_place,a_applytime,a_auspices,a_plan,a_expect)" +
            "values(#{u_id},0,#{co_name},#{a_name},#{a_ldname},#{a_ldtel},#{a_time},#{a_place},#{a_applytime},#{a_auspices},#{a_plan},#{a_expect})")
    public int applyActivity(Activity activity);

    //查询活动申请列表
    @Select("select * from c_activity order by a_id desc")
    public List<Activity> getApplyActivity();

    //同意活动
    @Update("update c_activity set a_status = 1 where a_id = #{a_id}")
    public int agreeActivity(Integer a_id);

    //不同意活动
    @Update("update c_activity set a_status = 2 where a_id = #{a_id}")
    public int disagreeActivity(Integer a_id);

    //查询活动
    @Select("select * from c_activity where a_status =1 order by DATE(a_time) desc")
    public List<Activity> getActivityList();

    //撤销活动
    @Update("update c_activity set a_status = 3 where a_id = #{a_id}")
    public int undoActivity(Integer a_id);

    //活动记录
    @Select("select * from c_activity where u_id = #{u_id}")
    public List<Activity> activityHistory(Integer u_id);

}
