package com.javasm.springcloud.controller;

import com.javasm.springcloud.bean.CommonResult;
import com.javasm.springcloud.bean.Payment;
import com.javasm.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OpenfeginOrderController {

    @Resource
    private PaymentFeignService service;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){

        CommonResult paymentById = service.getPaymentById(id);
        return paymentById;
    }

    //  feign 客户端 超时控制
    @GetMapping("/consumer/payment/timeout")
    public String timeOut(){
        //  客户端默认等待 1 秒钟，service.timeOut 等待 3 秒钟，直接报错
        String s = service.timeOut();
        return s;
    }

}
