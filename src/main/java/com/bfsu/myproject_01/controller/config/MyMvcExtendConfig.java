package com.bfsu.myproject_01.controller.config;

import org.springframework.context.annotation.Configuration;
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
}
