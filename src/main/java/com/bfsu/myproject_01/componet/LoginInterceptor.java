package com.bfsu.myproject_01.componet;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//检查登录的拦截器
public class LoginInterceptor  implements HandlerInterceptor{
    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object  loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser!=null){

            return true;
        }else{
            request.setAttribute("msg","您没有权限!");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
    }

}
