package com.community.controller;

import com.community.model.ResultMsg;
import com.community.model.User;
import com.community.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MapperController {
    @Autowired
    private MapperService mapperService;

    @ResponseBody
    @RequestMapping("/login")
    public List<User> login(User user){
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        return mapperService.login(u);
    }


    //批量删除用户
    @RequestMapping("/delUsers")
    @ResponseBody
    public ResultMsg delUsers(String ids){

        List<Object> list = new ArrayList<>();
        String[] str = ids.split(",");
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        int i = mapperService.delUsers(list);
        if(i>0){
            return new ResultMsg(1,"删除成功");
        }else{
            return new ResultMsg(1,"删除失败");
        }
    }
}
