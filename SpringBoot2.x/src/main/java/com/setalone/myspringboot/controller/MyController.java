package com.setalone.myspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @Value("${app.name}")
    private String name;
    @Value("${app.page-size}")
    private Integer pageSize;

    //动态注入IOC容器中匹配的bean
    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/")
    //@ResponseBody
    public String index() {
        return "idx";
    }

//    @RequestMapping("/out")
//    @ResponseBody//ResponseBody注解可以帮我们将返回的对象JSON序列化
//    public Date out(Date sdate){
//        return sdate;
//    }
}
