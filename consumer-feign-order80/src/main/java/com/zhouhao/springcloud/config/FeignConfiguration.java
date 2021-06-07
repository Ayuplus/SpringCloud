package com.zhouhao.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//配置开启Feign的日志功能
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLog(){
        return Logger.Level.FULL;
    }
}
