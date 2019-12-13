package com.community.controller;

import com.community.model.ResultMsg;
import com.community.model.User;
import com.community.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //查询用户-用户列表 user-list
    @RequestMapping("/getAllUser")
    @ResponseBody
    public Map getAllUser(String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<User> list = userService.getAllUser(page,limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("data",pageInfo);
        return map;
    }

    //添加用户 user-add
    @ResponseBody
    @RequestMapping("/userAdd")
    public ResultMsg userAdd(User user){
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setNo(user.getNo());
        u.setDeptment(user.getDeptment());
        u.setRole(user.getRole());

        int i = userService.userAdd(u);
        if(i>0){
            return new ResultMsg(1,"用户添加成功");
        }else{
            return new ResultMsg(0,"用户添加失败");
        }
    }

    //删除单个用户  user-list
    @RequestMapping("/userDel")
    @ResponseBody
    public ResultMsg userDel(Integer id){
        int i = userService.userDel(id);
        if(i>0){
            return new ResultMsg(1,"用户删除成功");
        }else{
            return new ResultMsg(0,"用户删除失败");
        }
    }
}