package com.bfsu.myproject_01.controller;

import com.bfsu.myproject_01.exception.UserNotExitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

//第一种异常处理机制,用mvc为我们提供的ExceptionHandler来处理
//缺点:没有自适应效果.  比如 若如果是浏览器访问我们就返回页面,如果是其他客户端访问我们就返回json数据
@ControllerAdvice
public class MyExceptionHandler {

    //这是第一种异常处理方式
   /* @ResponseBody//浏览器和客户端返回的都是json数据
    @ExceptionHandler(UserNotExitException.class)
    public Map<String,Object> handleException(Exception e){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code","user.exit");
        map.put("message",e.getMessage());
        return map;
    }*/

    //第二种异常处理方式
    ////重点是  必须设置错误状态码 //但是使用这种方法   设置的code和message都不生效
    //即我们自己设置的错误消息都无法携带出去
    @ExceptionHandler(UserNotExitException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.exit");
/*
        map.put("message",e.getMessage());
*/
        map.put("message","用户不存在啊啊啊啊啊");
        request.setAttribute("ext",map);
        //转发到/error  由BasicErrorController处理,但必须传入错误状态码,否则  他的状态码是200,因为请求转发(转发)到/error时  是正常的就是200
        return "forward:/error";
    }

}
