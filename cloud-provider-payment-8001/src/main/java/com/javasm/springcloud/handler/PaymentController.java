package com.javasm.springcloud.handler;

import ch.qos.logback.core.util.TimeUtil;
import com.javasm.springcloud.bean.CommonResult;
import com.javasm.springcloud.bean.Payment;
import com.javasm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j  //日志
public class PaymentController {


    @Resource
    private DiscoveryClient discoveryClient;

    //  引入本 model yml 文件
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    //前后端分离，所以不能直接返回对象，数据要先经过CommonResult封装再返回
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入的数据为：server.port"+serverPort + payment);
        log.info("******插入结果：" + result);

        if (result > 0) {
            //插入成功
            return new CommonResult(200, "插入数据库成功server.port:"+serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败server.port:"+serverPort);
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果：" + payment);

        if (payment != null) {
            //查询成功
            return new CommonResult(200, "查询成功server.port:"+serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录，查询ID：" + id);
        }
    }

    //  自定义 riboon 负载均衡 时用来获取端口
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    //  一个休眠 3 秒的 Controller ，用来测试 Hystrix 的降级与熔断等
    @GetMapping("/payment/timeout")
    public String timeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
