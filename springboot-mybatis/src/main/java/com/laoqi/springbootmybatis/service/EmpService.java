package com.laoqi.springbootmybatis.service;

import com.laoqi.springbootmybatis.entity.Emp;
import com.laoqi.springbootmybatis.mapper.EmpMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caiww
 * @date 2019/9/10 -1:09
 */
@Service
public class EmpService {
    @Resource
    private EmpMapper empMapper = null;

    public Emp findById(Integer empno) {
        Emp emp = empMapper.findById(empno);
        return emp;
    }

    public List<Map> findDepts(String dname, Float sal) {
        Map param = new HashMap();
        param.put("pdname", dname);
        param.put("psal", sal);
        return empMapper.findDepts(param);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Emp emp){
        empMapper.create(emp);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp){
        empMapper.update(emp);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer empno){
        empMapper.delete(empno);
    }

}
