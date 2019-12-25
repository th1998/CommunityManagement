package com.community.dao;

import com.community.model.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActivityDao {

    //申请活动
    @Insert("insert into c_activity(u_id,a_status,co_name,a_name,a_ldname,a_ldtel,a_time,a_place,a_applytime,a_auspices,a_plan,a_expect)" +
            "values(#{u_id},0,#{co_name},#{a_name},#{a_ldname},#{a_ldtel},#{a_time},#{a_place},#{a_applytime},#{a_auspices},#{a_plan},#{a_expect})")
    public int applyActivity(Activity activity);

    //查询活动申请列表
    @Select("select * from c_activity")
    public List<Activity> getApplyActivity();
}
