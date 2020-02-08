package com.itlaoqi.springdatajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author caiww
 * @date 2019/9/3 -20:59
 */
@Entity
@Table(name = "emp")
@Getter
@Setter
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Float sal;
    private Float comm;
    //dept与Emp的关系是1对多的关系
    @ManyToOne//在多的一方使用ManyToOne多对一
    @JoinColumn(name = "deptno")//JoinColumn指定关联的一方的关联字段，通常是主键
    //只要获取dept的时候，会自动查询select * from dept where deptno = ...
    private  Dept dept;


}
