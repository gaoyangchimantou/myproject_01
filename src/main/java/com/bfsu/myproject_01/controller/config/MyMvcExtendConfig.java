package com.bfsu.myproject_01.controller.config;

import com.bfsu.myproject_01.componet.LoginInterceptor;
import com.bfsu.myproject_01.componet.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//springboot2.0之后扩展mvc配置是用实现WebMvcConfigurer接口
//而在springboot1.X是用继承WebMvcConfigurerAdapter这个类实现扩展sprmvc.
@Configuration
public class MyMvcExtendConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //直接去找视图解析器,不走controller了
        registry.addViewController("/hello").setViewName("sucess");
    }
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){

        WebMvcConfigurer webMvcConfigurer =new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                //registry.addViewController("/index.html").setViewName("index");  老师的这个代码没生效
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                HandlerInterceptor loginHandlerInterceptor=new LoginInterceptor();

                registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/","/login","/user/login","/login.html","/asserts/**","/webjars/**");
            }
        };
        return webMvcConfigurer;
    }
@Bean
    public LocaleResolver localeResolver(){

        return new MyLocaleResolver();
    }
}

