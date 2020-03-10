package com.community.controller;

import com.community.model.User;
import com.community.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ResponseBody
    @RequestMapping("/Login")
    public List<User> Login(String no, String password){
        return loginService.Login(no,password);
    }
}