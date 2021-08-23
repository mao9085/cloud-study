package com.javasm.springcloud;

import com.javasm.ruler.MyselfRuler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//  访问指定的微服务(要用大写不然不生效)，并使用指定负载均衡算法
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRuler.class)
public class OrderMain80  {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
