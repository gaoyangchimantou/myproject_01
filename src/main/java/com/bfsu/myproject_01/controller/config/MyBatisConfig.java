package com.bfsu.myproject_01.controller.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {


public ConfigurationCustomizer getConf(){

    return new ConfigurationCustomizer(){
        @Override
        public void customize(org.apache.ibatis.session.Configuration configuration) {
            configuration.setMapUnderscoreToCamelCase(true);
        }
    };
}


}
