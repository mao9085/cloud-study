package com.javasm.springcloud.service;

import org.springframework.stereotype.Component;

@Component
//  用到这里需要 yml 文件打开 feign 对 hystrix 的支持，分离 fallback 代码与业务代码的耦合
public class PaymentFallback implements PaymentHystrixService {
    @Override
    public String paymentinfo_ok(Integer id) {
        return "fallback-----------ok";
    }

    @Override
    public String paymentinfo_timeout(Integer id) {
        return "fallback----------timeout";
    }
}
