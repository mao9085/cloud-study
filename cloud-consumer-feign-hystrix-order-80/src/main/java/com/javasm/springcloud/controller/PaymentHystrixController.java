package com.javasm.springcloud.controller;


import com.javasm.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService service;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentinfo_ok(@PathVariable("id")Integer id){
        return "ok"+service.paymentinfo_ok(id);
    }

//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    // HystrixCommand 注解加了 fallbackMethod 就找定制的降级方法，不然就用全局的，解决了每个方法都需要配置 fallbackMethod 的代码冗余问题
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentinfo_timeout(@PathVariable("id")Integer id){
//        int a = 1/0;
        return "timeout"+service.paymentinfo_timeout(id);
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，我等1500毫秒，等不到8001支付系统繁忙";
    }

    //  下面是全部 hytrix fallback 方法，但是需要解决 业务代码跟降级方法混杂的问题
    public String payment_Global_FallbackMethod(){
        return " 我是全局 Global 方法异常处理，当前系统繁忙";
    }

}
