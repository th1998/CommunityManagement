package com.community.service;

import com.community.dao.ActivityDao;
import com.community.model.Activity;
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
}