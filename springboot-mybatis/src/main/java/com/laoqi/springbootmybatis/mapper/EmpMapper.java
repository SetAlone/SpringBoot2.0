package com.laoqi.springbootmybatis.mapper;

import com.laoqi.springbootmybatis.entity.Emp;

import java.util.List;
import java.util.Map;

/**
 * @author caiww
 * @date 2019/9/10 -1:02
 */
public interface EmpMapper {

    public Emp findById(Integer empno);

    public List<Map> findDepts(Map param);

    public void create(Emp emp);

    //update
    public void update(Emp emp);
    //delete
    public void delete(Integer empno);
}
