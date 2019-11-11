package com.bfsu.myproject_01.dao;

import com.bfsu.myproject_01.entities.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DepartmentMapper {
    @Select("select * from department where id=#{id}")
    public Department getDep(Integer id);
    //UPDATE employee SET
    @Update("UPDATE department SET departmentName=#{departmentName} WHERE id=#{id}")
    public void upDepartment(Department department);
    @Delete("DELETE FROM department WHERE id=#{id}")
    public void deleteDepartment(Integer id);
}
