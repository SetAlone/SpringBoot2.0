package com.itlaoqi.springbootdeploy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author caiww
 * @date 2019/12/16 -11:47
 */
@Controller
public class TestController {
    @RequestMapping
    public String index(){
        System.out.println("ssssss");
        return "index";
    }
}
