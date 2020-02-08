package com.laoqi.springbootmybatis.controller;

import com.laoqi.springbootmybatis.entity.Emp;
import com.laoqi.springbootmybatis.service.EmpService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author caiww
 * @date 2019/9/10 -1:11
 */
@RestController
public class EmpController {
    @Resource
    private EmpService empService = null;

    @RequestMapping("/emp/{id}")
    public Emp  findById(@PathVariable("id") Integer id){
        Emp emp = empService.findById(id);
        return emp;
    }
    @RequestMapping("/emp/list")
    public List<Map> findDepts(String dname,Float sal){
        List<Map> list =  empService.findDepts(dname,sal);
        return list;
    }

    @RequestMapping("/emp/create")
    public Emp create(){
        Emp emp = new Emp();
        emp.setSal(1000f);
        emp.setComm(0f);
        emp.setDeptno(30);
        emp.setEname("laoqi");
        emp.setHiredate(new Date());
        emp.setJob("teacher");
        emp.setMgr(null);
        empService.create(emp);
        return  emp;
    }

    @RequestMapping("/emp/update")
    public Emp update(){
        Emp emp = empService.findById(7900);
        emp.setSal(emp.getSal() * 2);
        empService.update(emp);
        return emp;
    }

    @RequestMapping("/emp/delete")
    public String delete(Integer empno){
        empService.delete(empno);
        return "Delete success!!!!";
    }
}
