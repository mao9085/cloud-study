package com.javasm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//  激活
@EnableFeignClients
public class OpenfeginOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OpenfeginOrderMain80.class,args);
    }
}
