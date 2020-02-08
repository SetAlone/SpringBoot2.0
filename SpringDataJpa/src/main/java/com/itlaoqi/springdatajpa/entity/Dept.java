package com.itlaoqi.springdatajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caiww
 * @date 2019/9/3 -11:05
 */
@Entity//告诉SB这是一个实体类，在启动SB的时候会加载这个类
@Table(name = "dept")//Dept类对应的表
@Getter//lombok使用
@Setter//lombok使用
public class Dept {
    @Id//说明下面的deptno是主键
    //GenerationType.IDENTITY代表使用数据库底层自动增长的数字作为主键
    //oracle数据库没有自动增长属性，而是使用Seuence序列生成
    //@SequenceGenerator()生成Oracle主键值
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deptno")//deptno属性对应与deptno字段
    private Integer deptno;
    //@Column(name = "dname")
    private String dname;
    @Column(name = "loc")
    private String location;

    //在绝大多数的情况下我们不配置OneToMany
    //1、数据获取效率差
    //2、会形成死循环
//    @OneToMany
//    @JoinColumn(name = "deptno")
//    private List<Emp> emps = new ArrayList<>();

}
