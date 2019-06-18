package com.bfsu.myproject_01.controller;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyErrorAttribute extends DefaultErrorAttributes {


    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> mapReq = (Map<String, Object>)webRequest.getAttribute("ext", 0);
        mapReq.put("ext",mapReq);
        return errorAttributes;
    }
}
