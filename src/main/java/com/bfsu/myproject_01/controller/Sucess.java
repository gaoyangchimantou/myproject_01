package com.bfsu.myproject_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class Sucess {

    //查出一些数据,在页面展示
    @RequestMapping("/sucess")
    public String sucess(Map<String,Object> map){
        System.out.println("sucess......................");
        map.put("infor","我是内容");
        map.put("html","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu","zhaoliu"));

        return "sucess";
    }

}
