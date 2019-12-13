package com.community.service;

import com.community.dao.LoginDao;
import com.community.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginService {
    @Resource
    LoginDao loginDao;
    //用户登录
    public List<User> Login(String username,String password){ return loginDao.Login(username, password);}
}