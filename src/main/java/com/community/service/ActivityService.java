package com.community.service;

import com.community.dao.ActivityDao;
import com.community.model.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityService {
    @Resource
    ActivityDao activityDao;

    //申请活动
    public int applyActivity(Activity activity){ return activityDao.applyActivity(activity);}

    //查询活动申请列表
    public List<Activity> getApplyActivity(String page, String limit){ return activityDao.getApplyActivity();}

    //同意活动
    public int agreeActivity(Integer a_id){ return activityDao.agreeActivity(a_id);}

    //不同意活动
    public int disagreeActivity(Integer a_id){ return activityDao.disagreeActivity(a_id);}

    //查询活动
    public List<Activity> getActivityList(String page, String limit){ return  activityDao.getActivityList();}

    //撤销活动
    public int undoActivity(Integer a_id){ return activityDao.undoActivity(a_id);}

    //活动记录
    public List<Activity> activityHistory(Integer u_id){ return activityDao.activityHistory(u_id);}

    //今日活动显示
    public List<Activity> showActivity(){ return  activityDao.showActivity();}

    //以后活动显示
    public List<Activity> showActivity2(){ return  activityDao.showActivity2();}

    //模糊查询活动
    public List<Activity> serachActivityList(String a_name,String page, String limit){ return activityDao.serachActivityList(a_name);}
}