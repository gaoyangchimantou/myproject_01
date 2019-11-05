package com.bfsu.myproject_01.dao;

import com.bfsu.myproject_01.entities.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {
    @Select("select * from department where id=#{id}")
    public Department getDep(Integer id);




}
