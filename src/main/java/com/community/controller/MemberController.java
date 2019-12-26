package com.community.controller;

import com.community.model.Member;
import com.community.model.ResultMsg;
import com.community.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;



    //申请加入社团   member-add
    @RequestMapping("/applyJoinCommunity")
    @ResponseBody
    public ResultMsg applyJoinCommunity(Member member){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  m_applytime= df.format(new Date());

        Member m = new Member();
        m.setCo_id(member.getCo_id());
        m.setU_id(member.getU_id());
        m.setM_name(member.getM_name());
        m.setM_no(member.getM_no());
        m.setM_class(member.getM_class());
        m.setM_dept(member.getM_dept());
        m.setM_tel(member.getM_tel());
        m.setM_applytime(m_applytime);
        int i = memberService.applyJoinCommunity(m);
        if(i>0){
            return new ResultMsg(1,"申请成功，等待审批！");
        }
        return new ResultMsg(1,"申请失败，请重试！");
    }

    //查询申请入社列表  apply-member-list
    @RequestMapping("/getMemberList")
    @ResponseBody
    public Map getMemberList(String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<Member> list = memberService.getMemberList(page,limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("getMemberList",pageInfo);
        return map;
    }


    //审批加入社团处理同意
    @RequestMapping("/agreeJoin")
    @ResponseBody
    public ResultMsg agreeJoin(Integer m_id){
        int i = memberService.agreeJoin(m_id);
        if(i>0){
            return new ResultMsg(1,"审批成功！");
        }
        return new ResultMsg(0,"审批失败！");
    }

    //审批加入社团处理不同意
    @RequestMapping("/disagreeJoin")
    @ResponseBody
    public ResultMsg disagreeJoin(Integer m_id){
        int i = memberService.disagreeJoin(m_id);
        if(i>0){
            return new ResultMsg(1,"审批成功！");
        }
        return new ResultMsg(0,"审批失败！");
    }
}