package com.community.controller;

import com.community.model.Activity;
import com.community.model.ResultMsg;
import com.community.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    //申请活动
    @RequestMapping("/applyActivity")
    @ResponseBody
    public ResultMsg applyActivity(Activity activity){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  a_applytime= df.format(new Date());

        Activity a = new Activity();
        a.setA_applytime(a_applytime);
        a.setA_auspices(activity.getA_auspices());
        a.setA_expect(activity.getA_expect());
        a.setA_ldname(activity.getA_ldname());
        a.setA_ldtel(activity.getA_ldtel());
        a.setA_name(activity.getA_name());
        a.setA_place(activity.getA_place());
        a.setA_plan(activity.getA_plan());
        a.setA_time(activity.getA_time());
        a.setCo_name(activity.getCo_name());
        a.setU_id(Integer.valueOf(activity.getU_id()));

        int i = activityService.applyActivity(a);
        if(i>0){
            return new ResultMsg(1,"活动申请成功！");
        }
        return new ResultMsg(0,"活动申请失败！");
    }

    //查询活动申请列表
    @RequestMapping("/getApplyActivity")
    @ResponseBody
    public Map getApplyActivity(String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<Activity> list = activityService.getApplyActivity(page,limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("getApplyActivity",pageInfo);
        return map;
    }
}