package com.community.controller;

import com.community.model.Apply;
import com.community.model.Community;
import com.community.model.ResultMsg;
import com.community.service.CommunityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    //查询已有社团  community-list
    @RequestMapping("/getCommunity")
    @ResponseBody
    public Map getCommunity(String page, String limit){
        PageHelper.startPage(Integer.valueOf(page).intValue(), Integer.valueOf(limit).intValue());
        List<Community> list = communityService.getCommunity(page,limit);
        PageInfo pageInfo = new PageInfo(list);
        Map<String,Object> map = new HashMap<>();
        map.put("CommunityList",pageInfo);
        return map;
    }

    @RequestMapping("/getOneCommunity")
    @ResponseBody
    public List<Community> getOneCommunity(Integer u_id){
        return communityService.getOneCommunity(u_id);
    }

    @RequestMapping("/uploadBill")
    @ResponseBody
    public ResultMsg uploadBill(@RequestParam(value = "file",required = false) MultipartFile file, HttpServletRequest request) throws IOException, ParseException {
        String co_id = request.getParameter("co_id");
        System.out.println(co_id);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();//文件集合


        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println(path);
        File upload = new File(path.getAbsolutePath(),"static/images/upload/");
        System.out.println(upload);

        if(!upload.exists()) {
            upload.mkdirs();
        }
            String realPath = upload.getAbsolutePath();
            System.out.println("upload url:" + upload.getAbsolutePath());
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTime = sj.format(new Date());

                SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
                String date=df2.format(new Date());
                System.out.println(date + "当前时间");

                MultipartFile file2 = entity.getValue();
                System.out.println(file2);
                String filename = file2.getOriginalFilename();

                File filepath = new File(path, filename);
                //判断路径是否存在，如果不存在就创建一个
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                // 扩展名
                String fileExt = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();//扩展名
                Date date2=df2.parse(date);
                //新的文件名
                String newFileName=date+"_"+new Random().nextInt(100)+"."+fileExt;
                System.out.println("新文件名："+newFileName);
                //保存的路径
                String Savepath="/imag/"+newFileName;
                System.out.println("存储路径"+Savepath);
                String aaa = realPath+"\\"+newFileName;
                System.out.println("我测试的1："+aaa);
                aaa = aaa.replaceAll("\\\\", "/");
                System.out.println("我测试的1："+aaa);

                //保存图片数据
                //将上传的文件保存到目标文件中
                File ff = new File(aaa);
                file2.transferTo(ff);
                //图片url
                String url = "/upload/"+newFileName;
                System.out.println(path+File.separator+filename);
            }

        return new ResultMsg(1,"成功");
    }
}