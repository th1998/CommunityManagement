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


    //同意活动
    @RequestMapping("/agreeActivity")
    @ResponseBody
    public ResultMsg agreeActivity(Integer a_id){
        int i = activityService.agreeActivity(a_id);
        if(i>0){
            return new ResultMsg(1,"审批成功！");
        }
        return new ResultMsg(0,"审批失败！");
    }

    //不同意活动
    @RequestMapping("/disagreeActivity")
    @ResponseBody
    public ResultMsg disagreeActivity(Integer a_id){
        int i = activityService.disagreeActivity(a_id);
        if(i>0){
            return new ResultMsg(1,"审批成功！");
        }
        return new ResultMsg(0,"审批失败！");
    }

    //查询活动
    @RequestMapping("/getActivityList")
    @ResponseBody
    public Map getActivityList(String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<Activity> list = activityService.getActivityList(page,limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("getActivityList",pageInfo);
        return map;
    }

    //撤销活动
    @RequestMapping("/undoActivity")
    @ResponseBody
    public ResultMsg undoActivity(Integer a_id){
        int i = activityService.undoActivity(a_id);
        if(i>0){
            return new ResultMsg(1,"撤销成功！");
        }
        return new ResultMsg(0,"撤销失败！");
    }

    //活动记录
    @RequestMapping("/activityHistory")
    @ResponseBody
    public Map activityHistory(Integer u_id){
        List<Activity>  list= activityService.activityHistory(u_id);
        Map<String,Object> map = new HashMap<>();
        map.put("activityHistory",list);
        return map;
    }

    //今日活动显示
    @RequestMapping("/showActivity")
    @ResponseBody
    public List<Activity> showActivity(){
        return activityService.showActivity();
    }

    //以后活动显示
    @RequestMapping("/showActivity2")
    @ResponseBody
    public List<Activity> showActivity2(){
        return activityService.showActivity2();
    }


    //查询活动申请列表
    @RequestMapping("/serachActivityList")
    @ResponseBody
    public Map serachApplyActivity(String a_name,String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<Activity> list = activityService.serachActivityList(a_name, page, limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("getActivityList",pageInfo);
        return map;
    }
}