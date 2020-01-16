package com.community.service;

import com.community.dao.MapperDao;
import com.community.model.MemberApply;
import com.community.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MapperService {
    @Resource
    MapperDao mapperDao;

    public List<User> login(User user){ return mapperDao.login(user);}

    //批量删除用户
    public int delUsers(List<Object> list){ return mapperDao.delUsers(list);}

    //学生查看自己加入的社团
   // public List<MemberApply> getJoinCommunity(Integer u_id, String page, String limit){ return mapperDao.getJoinCommunity(u_id);}
}
