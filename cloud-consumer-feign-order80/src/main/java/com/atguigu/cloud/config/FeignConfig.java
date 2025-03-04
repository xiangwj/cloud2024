package com.atguigu.cloud.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer retryer(){
        //return Retryer.NEVER_RETRY;//默认重试
        return new Retryer.Default(100,1,3);
    }
}
