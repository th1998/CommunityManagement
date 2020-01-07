package com.community.service;

import com.community.dao.MemberDao;
import com.community.model.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberService {
    @Resource
    MemberDao memberDao;

    //申请加入社团   member-add
    public int applyJoinCommunity(Member member){ return memberDao.applyJoinCommunity(member);}

    //查询申请入社列表  apply-member-list
    public List<Member> getMemberList(Integer co_id,String page, String limit){ return memberDao.getMemberList(co_id);}

    //审批加入社团处理同意
    public int agreeJoin(Integer m_id){ return  memberDao.agreeJoin(m_id);}

    //审批加入社团处理不同意
    public int disagreeJoin(Integer m_id){ return  memberDao.disagreeJoin(m_id);}

    //退团
    public int moveCommunity(Integer co_id,Integer u_id){ return memberDao.moveCommunity(co_id, u_id);}
}