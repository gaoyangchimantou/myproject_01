package com.bfsu.myproject_01.controller;

import com.bfsu.myproject_01.dao.DepartmentDao;
import com.bfsu.myproject_01.dao.DepartmentMapper;
import com.bfsu.myproject_01.dao.EmployeeDao;
import com.bfsu.myproject_01.entities.Department;
import com.bfsu.myproject_01.entities.Employee;
import com.bfsu.myproject_01.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

//用它来处理,员工相关的操作
@Controller
public class EmployeeController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Cacheable(cacheNames = {"emp"})
    @RequestMapping("/emps")
    public String emps( Model model){
        Collection<Employee> employees = employeeDao.getAll();
       // List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM department");
     //   System.out.println(maps);
        Department dep = departmentMapper.getDep(1);
        System.out.println(dep);
        model.addAttribute("emps",employees);

        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        //到添加页面之前先把所有的部门给查出来
         Collection<Department> departments = departmentDao.getDepartments();
         model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String saveEmployee(Employee employee){

        employeeDao.save(employee);

        // 重定向到emps页面中
        //也可以使用 foward 请求转发  他也会重新走遍controller而不是直接由视图解析器来处理
        return "redirect:/emps";
    }
    @GetMapping("/emp/{id}")
    public String editEmployee(@PathVariable("id") String id,Model model){

        Employee employee = employeeDao.get(Integer.parseInt(id));

        model.addAttribute("emp",employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //修改与添加用同一个中间页面   add页面
        return "emp/add";
    }
    @PutMapping("/emp")
    public  String editedEmployee(Employee employee){

        employeeDao.save(employee);


        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String delEmployee(@PathVariable("id") Integer id ){

        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @GetMapping("/empaa")
    @ResponseBody
    public String empaa(Model model){
        //到添加页面之前先把所有的部门给查出来
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
      //
        //  Department dep = departmentMapper.getDep(1);
        //System.out.println(dep);
        String empaa = departmentService.empaa(1).toString();
        return empaa;
    }

    @GetMapping("/deptUp")
    @ResponseBody
    public String empaa(Department department){
        //到添加页面之前先把所有的部门给查出来
        Department dep= departmentService.updateDep(department);
        return dep.toString();
    }
    @GetMapping("/delDep")
    @ResponseBody
    public String delDep(Integer id){
        departmentService.delDep(id);
        return "success";
    }


}
