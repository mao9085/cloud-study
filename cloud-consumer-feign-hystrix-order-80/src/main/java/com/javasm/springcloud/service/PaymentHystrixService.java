package com.javasm.springcloud.service;


import com.javasm.springcloud.bean.CommonResult;
import com.javasm.springcloud.bean.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// fegin 接口 + 注解，通过 FeignClient去调用服务侧所能调用的服务方法，满足于controller调用service的编写习惯
//  cloud-provider-hystrix-payment
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallback.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentinfo_ok(@PathVariable("id")Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentinfo_timeout(@PathVariable("id")Integer id);

}
