package com.zhouhao.springcloud.service;

import com.zhouhao.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int add(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
