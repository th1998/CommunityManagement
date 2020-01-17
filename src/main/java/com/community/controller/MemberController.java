package com.community.controller;

import com.community.model.MApplyCommunityView;
import com.community.model.Member;
import com.community.model.MemberApply;
import com.community.model.ResultMsg;
import com.community.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.ir.RuntimeNode;
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

    //检查是否过该加入社团   member-add
    @RequestMapping("/checkJoinCommunity")
    @ResponseBody
    public int checkJoinCommunity(Integer u_id,Integer co_id){
        return memberService.checkJoinCommunity(u_id, co_id);
    }

    //申请加入社团   member-add
    @RequestMapping("/applyJoinCommunity")
    @ResponseBody
    public ResultMsg applyJoinCommunity(MemberApply memberApply){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  m_applytime= df.format(new Date());

        MemberApply m = new MemberApply();
        m.setCo_id(memberApply.getCo_id());
        m.setU_id(memberApply.getU_id());
        m.setM_name(memberApply.getM_name());
        m.setM_no(memberApply.getM_no());
        m.setM_class(memberApply.getM_class());
        m.setM_dept(memberApply.getM_dept());
        m.setM_tel(memberApply.getM_tel());
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
    public Map getMemberList(Integer co_id,String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<MemberApply> list = memberService.getMemberList(co_id,page,limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("getMemberList",pageInfo);
        return map;
    }

    //退团申请列表  apply-membermove-list
    @RequestMapping("/getMoveMemberList")
    @ResponseBody
    public Map getMoveMemberList(Integer co_id,String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<MemberApply> list = memberService.getMoveMemberList(co_id,page,limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("getMoveMemberList",pageInfo);
        return map;
    }


    //审批加入社团处理同意
    @RequestMapping("/agreeJoin")
    @ResponseBody
    public ResultMsg agreeJoin(Integer m_id,Integer co_id,Integer u_id,String m_name,String m_class,String m_no,String m_dept,String m_tel){
        System.out.println("---:"+co_id);
        System.out.println("---:"+u_id);
        System.out.println("---:"+m_name);
        System.out.println("---:"+m_class);
        System.out.println("---:"+m_no);
        System.out.println("---:"+m_dept);
        System.out.println("---:"+m_tel);
        int i = memberService.agreeJoin(m_id);
        if(i>0){
            Member m = new  Member();
            m.setCo_id(co_id);
            m.setU_id(u_id);
            m.setV_name(m_name);
            m.setV_no(m_no);
            m.setV_class(m_class);
            m.setV_dept(m_dept);
            m.setV_tel(m_tel);
            memberService.insertMember(m);
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

    //退团申请
    @RequestMapping("/moveCommunity")
    @ResponseBody
    public ResultMsg moveCommunity(Integer co_id,Integer u_id){
        int i = memberService.moveCommunity(co_id, u_id);
        if(i>0){
            return new ResultMsg(1,"请等待审批！");
        }
        return new ResultMsg(0,"退团失败！");
    }

    //同意退团申请
    @RequestMapping("/agreeMove")
    @ResponseBody
    public ResultMsg agreeMove(Integer co_id,Integer u_id){
        int i = memberService.agreeMove(co_id,u_id);
        if(i>0){
            memberService.updateStatus(co_id,u_id);
            return new ResultMsg(1,"审批成功！");
        }
        return new ResultMsg(0,"审批失败！");
    }

    //同意退团申请
    @RequestMapping("/disagreeMove")
    @ResponseBody
    public ResultMsg disagreeMove(Integer co_id,Integer u_id){
        int i = memberService.disagreeMove(co_id,u_id);
        if(i>0){
            return new ResultMsg(1,"审批成功！");
        }
        return new ResultMsg(0,"审批失败！");
    }

    //社团负责人查看本社团成员
    @RequestMapping("/memberAll")
    @ResponseBody
    public Map memberAll(Integer co_id,String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<Member> list = memberService.memberAll(co_id,page,limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("memberAll",pageInfo);
        return map;
    }

    //删除社团成员
    @RequestMapping("/memberDel")
    @ResponseBody
    public ResultMsg memberDel(Integer v_id){
        int i = memberService.memberDel(v_id);
        if(i>0){
            return new ResultMsg(1,"删除成员成功");
        }
        return new ResultMsg(0,"删除成员成功");
    }

    //用户查看自己加入的社团的历史信息
    @RequestMapping("/getJoinCommunity")
    @ResponseBody
    public Map getJoinCommunity(Integer u_id,String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<MApplyCommunityView> list = memberService.MApplyCommunityView(u_id, page, limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("getJoinCommunityList",pageInfo);
        return map;
    }

    //修改编辑成员信息
    @RequestMapping("/editMember")
    @ResponseBody
    public ResultMsg editMember(Member member){
        Member m = new Member();
        m.setV_id(member.getV_id());
        m.setV_no(member.getV_no());
        m.setV_class(member.getV_class());
        m.setV_tel(member.getV_tel());
        m.setV_dept(member.getV_dept());
        int i = memberService.editMember(m);
        if(i>0){
            return new ResultMsg(1,"修改成功！");
        }
        return new ResultMsg(0,"修改失败！");
    }

}