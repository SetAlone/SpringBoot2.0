package com.laoqi.springbootmybatis.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author caiww
 * @date 2019/9/10 -0:56
 */
@Getter
@Setter
//@AllArgsConstructor
public class Emp {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Float sal;
    private Float comm;
    private Integer deptno;
}
