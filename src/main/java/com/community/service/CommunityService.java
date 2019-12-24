package com.community.service;

import com.community.dao.CommunityDao;
import com.community.model.Apply;
import com.community.model.Community;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommunityService {

    @Resource
    CommunityDao communityDao;

    //查询已有社团  community-list
    public List<Community> getCommunity(String page, String limit){
        return communityDao.getCommunity();
    }

    //根据负责人查询自己的社团 community-bill
    public List<Community> getOneCommunity(Integer u_id){ return communityDao.getOneCommunity(u_id);}

    //上传社团海报 community-bill
    public int uploadBill(String co_bill,Integer co_id){ return communityDao.uploadBill(co_bill, co_id);}

    //显示所有社团 show-community
    public List<Community> showCommunity(){ return communityDao.showCommunity();}
}