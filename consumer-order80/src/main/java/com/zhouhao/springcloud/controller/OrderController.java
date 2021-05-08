package com.zhouhao.springcloud.controller;

import com.zhouhao.springcloud.entities.CommonResult;
import com.zhouhao.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://PROVIDER-PAYMENT-SERVER";

    @Resource
    private RestTemplate template;

    @GetMapping("/consumer/payment/add")
    public CommonResult add(Payment payment){
        return template.postForObject(PAYMENT_URL+"/payment/add",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        return template.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
