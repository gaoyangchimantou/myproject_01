package com.bfsu.myproject_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
   /* @RequestMapping(value="/user/login",method = RequestMethod.POST)*/
   @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Map<String,Object> map, HttpSession session){

        if (!StringUtils.isEmpty(password)&&password.equals("123456")){
           //return "dashboard";
            session.setAttribute("loginUser","GaoYang");
            return  "redirect:/main.html";//加 /   表示绝对路径
        }else{
            map.put("msg","用户名或密码错误!");
            return "login";
        }

    }


}
