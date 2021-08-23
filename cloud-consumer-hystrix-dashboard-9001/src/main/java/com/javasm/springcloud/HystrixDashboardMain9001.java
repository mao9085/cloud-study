package com.javasm.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard //启用Hystrix仪表板
@SpringBootApplication
public class HystrixDashboardMain9001 {

    //  访问地址：http://127.0.0.1:9001/hystrix
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }

}