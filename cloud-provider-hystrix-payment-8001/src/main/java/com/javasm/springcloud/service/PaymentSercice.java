package com.javasm.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentSercice {

    public String paymentInfo_ok(Integer id){
        return "Thread:"+Thread.currentThread().getName()+"paymentInfo_ok:"+id;
    }
    // 这个方法出了问题，找下面注解里面的方法
    @HystrixCommand(
            fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
            //运行异常和超时异常都可以进行处理！
            //设置自身超时调用时间的峰值为3秒，峰值内可以正常运行，超过了需要有兜底的方法处理，服务降级fallback

    public String paymentInfo_timeout(Integer id){
        try {
            int timeout = 3;
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  设置运行错误 ：
        // int a = 1/0;
        return "Thread:"+Thread.currentThread().getName()+"paymentInfo_timeout哈哈哈 :"+id;
    }

    //  fallback降级方法
    public String paymentInfo_TimeOutHandler(Integer id){
        return "8001提供者，线程池：" + Thread.currentThread().getName() +
                "\tpaymentInfo_TimeOutHandler系统繁忙，请稍后再试，id：" + id;
    }

    //  下面的是服务熔断方法=========================================
    //  三种熔断类型，打开、关闭、半开
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            //开启断路器（时间窗口内，总数阈值达到，错误百分比达到，触发断路器开启，直接调用服务降级）
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //请求总数阈值（默认20）
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //快照时间窗口，默认五秒，断路器为半开状态，尝试处理请求
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //  错误百分比阈值
            //HystrixProperty时间内 HystrixProperty次数 的错误率达到 HystrixProperty 跳闸（百分率%，默认50%）
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")})
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id < 0){
            throw  new RuntimeException("****id 不能为负数");
        }

        String serialNumber = IdUtil.simpleUUID();

        return  Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }
    //  fallback 方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数,请稍后再试， id: " + id;
    }
}
