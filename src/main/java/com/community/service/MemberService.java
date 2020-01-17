package com.community.service;

import com.community.dao.MemberDao;
import com.community.model.MApplyCommunityView;
import com.community.model.Member;
import com.community.model.MemberApply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberService {
    @Resource
    MemberDao memberDao;

    //检查是否过该加入社团   member-add
    public int checkJoinCommunity(Integer u_id,Integer co_id){ return memberDao.checkJoinCommunity(u_id, co_id);}

    //申请加入社团   member-add
    public int applyJoinCommunity(MemberApply memberApply){ return memberDao.applyJoinCommunity(memberApply);}

    //查询申请入社列表  apply-memberapply-list
    public List<MemberApply> getMemberList(Integer co_id, String page, String limit){ return memberDao.getMemberList(co_id);}

    //退团申请列表  apply-membermove-list
    public List<MemberApply> getMoveMemberList(Integer co_id,String page, String limit){ return memberDao.getMoveMemberList(co_id);}

    //审批加入社团处理同意
    public int agreeJoin(Integer m_id){ return  memberDao.agreeJoin(m_id);}

    //同意加入社团插入成员表
    public int insertMember(Member member){ return memberDao.insertMember(member);}

    //审批加入社团处理不同意
    public int disagreeJoin(Integer m_id){ return  memberDao.disagreeJoin(m_id);}

    //退团申请
    public int moveCommunity(Integer co_id,Integer u_id){ return memberDao.moveCommunity(co_id, u_id);}

    //同意退团申请
    public int agreeMove(Integer co_id,Integer u_id){ return memberDao.agreeMove(co_id, u_id);}
    public int updateStatus(Integer co_id,Integer u_id){ return  memberDao.updateStatus(co_id, u_id);}

    //不同意退团申请
    public int disagreeMove(Integer co_id,Integer u_id){ return memberDao.disagreeMove(co_id, u_id);}

    //社团负责人查看本社团成员
    public List<Member> memberAll(Integer co_id,String page, String limit){ return  memberDao.memberAll(co_id);}

    //删除社团成员
    public int memberDel(Integer v_id){ return memberDao.memberDel(v_id);}

    //用户查看自己加入的社团的历史信息
    public List<MApplyCommunityView> MApplyCommunityView(Integer u_id,String page, String limit){ return memberDao.MApplyCommunityView(u_id);};

    //修改编辑成员信息
    public int editMember(Member member){ return memberDao.editMember(member);}
}