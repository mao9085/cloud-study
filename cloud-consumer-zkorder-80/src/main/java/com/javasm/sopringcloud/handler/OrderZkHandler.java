package com.javasm.sopringcloud.handler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderZkHandler {

    public static final String CURL ="http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk2")
    public String paymentInfo(){
        String forObject = restTemplate.getForObject(CURL + "/payment/zk", String.class);
        return forObject+"新";

    }
    @GetMapping("/test1")
    public String test(){
        return "test";

    }
}
