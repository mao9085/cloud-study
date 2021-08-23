package com.javasm.springcloud.service;

import com.javasm.springcloud.bean.CommonResult;
import com.javasm.springcloud.bean.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// fegin 接口 + 注解，通过 FeignClient去调用服务侧所能调用的服务方法，满足于controller调用service的编写习惯
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

//    CommonResult<Payment> getPaymentById(@Param("id") Long id);

    //  这里相当于直接调用了 provider
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    public String timeOut();

}
