package com.hmc.controller;

import com.hms.pojo.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author：bingfeng
 * @Date：2024/11/5 11:20
 */

@Controller
public class HttpController {
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println("requestBody:  "+requestBody);   //requestBody:username=admin&password=123456
        return "success";
    }

    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity){
        System.out.println("requestHeader请求头:"+requestEntity.getHeaders());
        System.out.println("requestBody请求体:"+requestEntity.getBody());
        return "success";
    }

    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello response");
    }

    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "hello response";  //因为加上了@ResponseBody注解，所以返回的是字符串，而不是视图名
    }

    @RequestMapping("/testResponseUser")
    @ResponseBody
    public User testResponseUser(){
        return new User(1001,"admin","ll",18,"0");   //浏览器的页面中展示的结果：{"id":1001,"username":"admin","password":"123456","age":23,"sex":"男"}
    }

    @RequestMapping("/testAxios")
    @ResponseBody
    public String testAxios(String username,String password){
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        return "hello axios";   //浏览器弹窗显示：hello axios
    }
    @RequestMapping("/testFileUpload")
    public String testFileUpload(MultipartFile headImg, HttpSession session) throws IOException {  //MultipartFile 通过在spingMVC.xml中配置了一个文件上传解析器来将浏览器提交的文件封装到MultipartFile对象中   参数名必须和表单中input的name一致
//获取上传的文件的文件名
        String fileName = headImg.getOriginalFilename();
        //处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;
        //获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo");
        File file = new File(photoPath);
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;//File.separator 路径分隔符
        //实现上传功能
        headImg.transferTo(new File(finalPath));
        return "success";
    }

}
