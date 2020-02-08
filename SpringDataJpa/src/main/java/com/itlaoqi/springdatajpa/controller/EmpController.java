package com.itlaoqi.springdatajpa.controller;

import com.itlaoqi.springdatajpa.entity.Dept;
import com.itlaoqi.springdatajpa.entity.Emp;
import com.itlaoqi.springdatajpa.repository.DeptRepository;

import com.itlaoqi.springdatajpa.repository.EmptRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController //@Controller 使用@RestController的时候默认所有方法都返回JSON字符串，而不是跳转页面，我们也不用在方法上写@ResponseBody
@RequestMapping("/emp")
//默认该类的所有方法都开启事务
@Transactional(rollbackFor = Exception.class)
public class EmpController {
    @Autowired
    private EmptRepository empRepository;
    @Autowired
    private DeptRepository deptRepository;
    @GetMapping("/{id}")
    public Emp findById(@PathVariable("id") Integer id){
        return empRepository.findById(id).get();
    }
    @GetMapping("/create")
    public Emp create(){
        Emp emp = new Emp();
        emp.setComm(0f);
        emp.setEname("laoqi");
        emp.setHiredate(new Date());
        emp.setJob("Teacher");
        emp.setMgr(null);
        emp.setSal(0f);
        Dept d = deptRepository.findById(20).get();
        emp.setDept(d);
        empRepository.save(emp);
        return emp;
    }

    @GetMapping("/find")
    @Transactional(propagation = Propagation.NOT_SUPPORTED , readOnly = true)//不开启事务的方法
    public List<Emp> find(Integer deptno){
        return empRepository.findEmps(deptno);
    }

    @GetMapping("/imp")
    //在默认情况下，数据库的事物作用范围是在JpaRepository的CRUD方法上，
    //save方法一旦执行成功马上提交
    //要保证数据的完整性，那就需要将事务提高至imp方法上
    //在imp方法上开启事务，是需要增加@Transactional

    //针对于这种使用注解的事务形式，也有一个名词叫做"声明式事务" , ParseException
    //一般情况下，事务注解要写在最核心的Service上，而不是Controller
    @Transactional(rollbackFor = Exception.class)//开启事务，imp方法运行成功提交。运行失败抛出RuntimeException及其子类的时候回滚
    public void imp(){
        for(int i = 0 ; i< 10 ; i++){
            Emp emp = new Emp();
            if(i == 3){
                throw new RuntimeException("我出错啦");
            }
            emp.setComm(0f);
            emp.setEname("laoqi" + i);
            emp.setHiredate(new Date());
            emp.setJob("Teacher");
            emp.setMgr(null);
            emp.setSal(i*10f);
            Dept d = deptRepository.findById(20).get();
            emp.setDept(d);
            //saveAndFlush立即执行
            empRepository.saveAndFlush(emp);
        }
    }

}
