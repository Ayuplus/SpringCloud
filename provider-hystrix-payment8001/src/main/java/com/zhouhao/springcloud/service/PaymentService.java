package com.zhouhao.springcloud.service;

import com.zhouhao.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int add(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
    String testSuccess(Integer id);
    String testFailed(Integer id);
    String TimeOutHandler(Integer id);
}
