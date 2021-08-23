package com.javasm.springcloud.Handler;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PaymentHandler {

    @Value("${server.port}")        //获取端口号
    private String serverPort;

    @GetMapping("/payment/zk")
    public String paymentzk(){
        return "springcloud with zookeeper：" + serverPort + "\t" + UUID.randomUUID().toString();
    }

}