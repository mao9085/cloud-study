package com.javasm.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    // 开启 eureka 自带的负载均衡策略，要自定义策略则不加
    // @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
