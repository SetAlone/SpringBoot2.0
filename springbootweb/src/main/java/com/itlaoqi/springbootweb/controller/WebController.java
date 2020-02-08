package com.itlaoqi.springbootweb.controller;

import com.itlaoqi.springbootweb.entity.Dept;
import com.itlaoqi.springbootweb.entity.Emp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class WebController {
    Logger logger = LoggerFactory.getLogger(WebController.class);
    private List<Emp> emps = new ArrayList<Emp>();
    private List<Dept> depts = new ArrayList<Dept>();
    @Value(value = "${app.upload.location}")
    private String path;

    public WebController() {
        emps.add(new Emp(7782, "CLARK", "DEVELOPER", "2017-01-02", 7780f, "RESEARCH"));
        emps.add(new Emp(7839, "KING", "CSO", "2018-03-04", 8820f, "SALES"));
        depts.add(new Dept(10, "REASERCH", "2017-10-07"));
        depts.add(new Dept(20, "SALES", "2015-12-01"));
        depts.add(new Dept(30, "ACCOUNTING", "2013-03-02"));
    }

    //RequestMethod.GET 只有GET请求才能访问这个方法，如果是Post则会提示405错误

    //设置上下文数据，其实就是页面中要读取的记录

    //在Spring MVC中常用的设置上下文有三种：

    /**
     * 1、ModelAndView
     * 2、Model
     * 3、WebRequest或者原生的HttpServletRequest对象
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("emps", emps);
        return mav;
    }
//    //高内聚，低耦合的设计原则
//    public String index(Model model){
//        model.addAttribute("emp",emps);
//        return "index";
//    }
//    //setAttribute是向当前的请求中放入对象，这种方式与web容器强耦合
//    public String index(WebRequest req,HttpServletRequest request){
//        req.setAttribute("emps",emps);
//        request.setAttribute("emps",emps);
//        return  "index";
//
//    }

    //@RequestMapping(value = "dept",method = RequestMethod.GET)
    //AJAX返回的是JSON数据，而不是跳转页面
    @GetMapping("/dept")
    //@ResponseBody代表将返回值JSON序列化后送给浏览器，Spring Boot默认使用的JSON序列化工具是Jackson
    @ResponseBody
    public List<Dept> obtainDept() {
        List<Dept> newDepts = new ArrayList();
        newDepts.add(new Dept(-1, "请选择", "1970-01-01"));
        newDepts.addAll(depts);
        return newDepts;
    }

    /**
     * 级联
     *
     * @param d
     * @return jobs
     */
    @GetMapping("/job")
    @ResponseBody
    public List<String> obtainDept(String d) {
        List<String> jobs = new ArrayList<String>();
        jobs.add("请选择");
        if (d.equals("REASERCH")) {
            jobs.add("CTO");
            jobs.add("Program");
        } else if (d.equals("SALES")) {
            jobs.add("CSO");
            jobs.add("saler");
        } else if (d.equals("ACCOUNTING")) {
            jobs.add("CFO");
            jobs.add("Cashier");
        }
        return jobs;
    }

    /**
     * 文件上传
     *
     * @param photo
     * @return
     */
    @PostMapping("/create")
    //MultipartFile是上传文件接口，对应了保存的临时文件
    //参数名与前端的name值保持一致
    //@RequestParam("photo")代表了photo参数对应与前端name=photo的file框
    /**
     * 前后端数据绑定，后端使用bean接收，要求属性和前端name保持一致就可以自动注入
     */
    public ModelAndView create(Emp emp, @RequestParam("photo") MultipartFile photo) throws IOException {
        //String path ="E:/upload/";
        String fileanme = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));
        if (!suffix.equals(".jpg") && !suffix.equals(".png")) {
            throw new RuntimeException("无效的图片格式！");
        }
        emp.setPhotoFile(fileanme + suffix);
        emps.add(emp);//向数据源增加一个emp对象

        //Spring提供了一个文件操作类FileCopyUtil
        //对上传文件的复制，称为“归档”。
        FileCopyUtils.copy(photo.getInputStream(), new FileOutputStream(path + fileanme + suffix));
        //页面重定向到localhost
        //格式为redirect：跳转地址
        ModelAndView mav = new ModelAndView("redirect:/");
        return mav;
    }
}
