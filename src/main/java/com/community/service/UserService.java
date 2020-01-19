package com.community.service;

import com.community.dao.UserDao;
import com.community.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserDao userDao;
    //查询用户-用户列表 user-list
    public List<User> getAllUser(String page, String limit){
        return userDao.getAllUser();
    }

    //添加用户 user-add
    public int userAdd(User user){ return userDao.userAdd(user);}

    //删除单个用户  user-list
    public int userDel(Integer id){ return userDao.userDel(id);}

    //模糊查询用户 user-list
    public List<User> sreachUser(String no,String page, String limit){ return userDao.sreachUser(no);}
}