package com.community.controller;

import com.community.model.CommunityCountView;
import com.community.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CountController {
    @Autowired
    private CountService countService;

    //统计各个社团人数
    @RequestMapping("/communityCount")
    @ResponseBody
    public List<CommunityCountView> communityCount(){
        return countService.communityCount();
    }
}