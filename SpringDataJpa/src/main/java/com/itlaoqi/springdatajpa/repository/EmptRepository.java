package com.itlaoqi.springdatajpa.repository;


import com.itlaoqi.springdatajpa.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author caiww
 * @date 2019/9/3 -11:18
 */
//JpaRepository是SB为我们提供的简化类，默认提供了增删改查方法
//我们只需要定义接口就可以了，在sb启动的时候会自动帮我们生成具体的实现类，来实现CRUD方法
//Dept,Integer使用JpaRepository需要传入实体类及主键的类型

public interface EmptRepository extends JpaRepository<Emp,Integer> {

    @Query("select e from Emp e where e.dept.deptno =:dn")
    public List<Emp> findEmps(@Param("dn") Integer deptno);


}
