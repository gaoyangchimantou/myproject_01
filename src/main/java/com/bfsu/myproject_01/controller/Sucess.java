package com.bfsu.myproject_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class Sucess {

    //第一种默认访问首页的方法 ,就是在一个controller里写一个方法来实现    映射里面的"/"  就表示 所有的有映射到的请求都走我这个方法,去首页
   /* @RequestMapping({"index","/"})
    public String index(){


        return "index";
    }*/
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
