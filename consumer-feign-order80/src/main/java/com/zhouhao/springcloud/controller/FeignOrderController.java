package com.zhouhao.springcloud.controller;

import com.zhouhao.springcloud.entities.CommonResult;
import com.zhouhao.springcloud.entities.Payment;
import com.zhouhao.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class FeignOrderController {
    @Resource
    private PaymentFeignService service;
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return service.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/add")
    public CommonResult addPayment(Payment payment){
        return service.add(payment);
    }

    @GetMapping(value = "/consumer/payment/timeout" )
    public String testFeignTimeout(){
        return service.testFeignTimeout();
    }
}
