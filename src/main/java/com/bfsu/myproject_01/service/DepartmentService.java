package com.bfsu.myproject_01.service;

import com.bfsu.myproject_01.dao.DepartmentMapper;
import com.bfsu.myproject_01.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Cacheable(cacheNames = {"emp"})
    public Department empaa(int i){
        Department dep = departmentMapper.getDep(i);
        System.out.println(dep.toString());
        return dep;
    }
   /* @Cacheable(cacheNames = {"emp"},key="#root.method+'['+#i+']'")
    public String empaa(int i){
        Department dep = departmentMapper.getDep(i);
        System.out.println(dep.toString());
        return dep.toString();
    }*/
    /*@Cacheable(cacheNames = {"emp"},keyGenerator = "myKeyGenerator")
    public String empaa(int i){
        Department dep = departmentMapper.getDep(i);
        System.out.println(dep.toString());
        return dep.toString();
    }*/

    @CachePut(value = "emp",key = "#department.id")
    public Department updateDep(Department department){


        departmentMapper.upDepartment(department);
        System.out.println("部门更新了"+department.getDepartmentName());
        return department;
    }

    @CacheEvict(value = "emp",key = "#id"/*,beforeInvocation = true*/)//beforeInvocation = true  默认为false  如果设为true
    //则会在目标方法执行之前   就将缓存中的数据清除掉（不管目标方法报不报错）   默认是在目标方法执行之后才执行。
    //被@CachePut注解 标注的方法  一定会被执行的
    public void delDep(Integer id){
        System.out.println("部门被删除。。。。");
        //departmentMapper.deleteDepartment(id);

    }
}
