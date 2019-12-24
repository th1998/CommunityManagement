package com.community.controller;

import com.community.model.Member;
import com.community.model.ResultMsg;
import com.community.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;


    @RequestMapping("/a")
    @ResponseBody
    public String a(Member member){
        System.out.println("aaaa");
        return "aa";
    }
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
}