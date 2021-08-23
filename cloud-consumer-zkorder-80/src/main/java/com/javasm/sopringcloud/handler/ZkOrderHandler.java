package com.javasm.sopringcloud.handler;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ZkOrderHandler {
    public static final String CURL ="http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk1")
    public String paymentInfo(){
        String forObject = restTemplate.getForObject(CURL + "/payment/zk", String.class);
        return forObject+"旧";

    }
    @GetMapping("/test2")
    public String test(){
        return "test";

    }
}
