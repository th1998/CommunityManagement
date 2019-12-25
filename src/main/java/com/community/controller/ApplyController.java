package com.community.controller;

import com.community.model.Apply;
import com.community.model.Community;
import com.community.model.ResultMsg;
import com.community.service.ApplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    //申请创立社团 apply-community
    @RequestMapping("/applyCommunity")
    @ResponseBody
    public ResultMsg applyCommunity(Apply apply){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  ap_time= df.format(new Date());
        Apply ap = new Apply();
        ap.setU_id(apply.getU_id());
        ap.setAp_status(0);
        ap.setAp_name(apply.getAp_name());
        ap.setLd_name(apply.getLd_name());
        ap.setLd_tel(apply.getLd_tel());
        ap.setLd_deptment(apply.getLd_deptment());
        ap.setLd_class(apply.getLd_class());
        ap.setGu_name(apply.getGu_name());
        ap.setGu_tel(apply.getGu_tel());
        ap.setAp_introduce(apply.getAp_introduce());
        ap.setAp_target(apply.getAp_target());
        ap.setAp_time(ap_time);

        int i = applyService.applyCommunity(ap);
        if(i>0){
            return new ResultMsg(1,"申请成功，请等待审批！");
        }else {
            return new ResultMsg(0,"申请失败");
        }

    }
    //申请社团列表 apply-community-list
    @RequestMapping("/applyCommunityList")
    @ResponseBody
    public Map applyCommunityList(String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<Apply> list = applyService.applyCommunityList(page, limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("applyCommunityList",pageInfo);
        return map;
    }

    //批准成立社团  apply-community-look
    @RequestMapping("/ratifyCommunity")
    @ResponseBody
    public ResultMsg ratifyCommunity(Integer ap_id,Integer u_id,String co_name,String co_ldname,String co_ldtel,String co_guname,String co_gutel,String co_introduce){
        int i = applyService.ratifyCommunity(ap_id);

        if(i>0){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String  co_creattime= df.format(new Date());
            Community co = new Community();
            co.setU_id(u_id);
            co.setCo_name(co_name);
            co.setCo_ldname(co_ldname);
            co.setCo_ldtel(co_ldtel);
            co.setCo_guname(co_guname);
            co.setCo_gutel(co_gutel);
            co.setCo_introduce(co_introduce);
            co.setCo_creattime(co_creattime);
            int j = applyService.insertCommunity(co);
            return new ResultMsg(1,"审批完成！");
        }else {
            return new ResultMsg(0,"审批失败！");
        }
    }

}