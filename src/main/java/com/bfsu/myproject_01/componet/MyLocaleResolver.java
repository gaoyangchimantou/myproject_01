package com.bfsu.myproject_01.componet;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 我们可以在链接上携带区域信息
 *
 */
public class MyLocaleResolver implements LocaleResolver{


    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        Locale locale=Locale.getDefault();

        String l = httpServletRequest.getParameter("l");
        if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale=new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
