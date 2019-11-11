package com.bfsu.myproject_01.controller;

import com.bfsu.myproject_01.service.AsynicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsynicController {

    @Autowired
    AsynicService asynicService;
    @RequestMapping("/asynic")
    public String hello(){
            asynicService.asynic();

        return "success";
    }


}
