package com.community.service;

import com.community.dao.CountDao;
import com.community.model.CommunityCountView;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CountService {

    @Resource
    CountDao countDao;

    //统计各个社团人数
    public List<CommunityCountView> communityCount(){ return countDao.communityCount();}
}