package com.community.service;

import com.community.dao.ApplyDao;
import com.community.model.Apply;
import com.community.model.Community;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApplyService {
    @Resource
    ApplyDao applyDao;

    //申请创立社团 apply-community
    public int applyCommunity(Apply apply){ return applyDao.applyCommunity(apply);}

    //申请社团列表 apply-community-list
    public List<Apply> applyCommunityList(String page, String limit){ return applyDao.applyCommunityList();}

    //批准成立社团  apply-community-look
    public int ratifyCommunity(Integer ap_id){ return applyDao.ratifyCommunity(ap_id);}

    //将审批通过的社团插入到社团表  apply-community-look
    public int insertCommunity(Community community){ return applyDao.insertCommunity(community);}
}