package com.javasm.springcloud.controller;

import com.javasm.springcloud.service.PaymentSercice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverport;

    @Resource
    private PaymentSercice sercice;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentinfo_ok(@PathVariable("id")Integer id){
        return "ok"+sercice.paymentInfo_ok(id);
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentinfo_timeout(@PathVariable("id")Integer id){
        return "timeout"+sercice.paymentInfo_timeout(id);
    }

    // 服务熔断==============
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = sercice.paymentCircuitBreaker(id);
        log.info("******result：" + result);
        return result;
    }

}
