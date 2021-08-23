package com.javasm.springcloud.controller;

import com.javasm.springcloud.bean.CommonResult;
import com.javasm.springcloud.bean.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderConsulController {

    //    public static final String PAYMENT_URL="http://127.0.0.1:8001";
    public static final String PAYMENT_URL="http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;


    //  http://127.0.0.1/consumer/payment/create?serial="孙大"  请求示例
    @GetMapping("/payment/consul")
    public String create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/consul",payment,String.class);
    }

}
