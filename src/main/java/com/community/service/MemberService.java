package com.community.service;

import com.community.dao.MemberDao;
import com.community.model.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberService {
    @Resource
    MemberDao memberDao;

    //申请加入社团   member-add
    public int applyJoinCommunity(Member member){ return memberDao.applyJoinCommunity(member);}
}