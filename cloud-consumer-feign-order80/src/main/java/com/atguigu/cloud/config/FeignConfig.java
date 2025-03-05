package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer retryer(){
        return Retryer.NEVER_RETRY;
        //return new Retryer.Default(100,1,3);
    }
    @Bean
    public Logger.Level feignLogLevl(){
        return Logger.Level.FULL;
    }

}
