package com.javasm.springcloud.service;

import com.javasm.springcloud.bean.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}