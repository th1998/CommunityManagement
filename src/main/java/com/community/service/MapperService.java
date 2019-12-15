package com.community.service;

import com.community.dao.MapperDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MapperService {
    @Resource
    MapperDao mapperDao;

    //批量删除用户
    public int delUsers(List<Object> list){ return mapperDao.delUsers(list);}

}
