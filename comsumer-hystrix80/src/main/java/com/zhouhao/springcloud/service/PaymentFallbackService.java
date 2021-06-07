package com.zhouhao.springcloud.service;

import org.springframework.stereotype.Component;
//服务端宕机时的服务降级
@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public String testSuccess(Integer id) {
        return "服务降级";
    }

    @Override
    public String testFailed(Integer id) {
        return "服务降级";
    }
}
