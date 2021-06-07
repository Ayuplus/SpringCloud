package com.zhouhao.springcloud.service;

import com.zhouhao.springcloud.entities.CommonResult;
import com.zhouhao.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "provider-hystrix-payment-server",fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping("/payment/testHystrix/ok/{id}")
    String testSuccess(@PathVariable("id") Integer id);

    @GetMapping("/payment/testHystrix/timeout/{id}")
    String testFailed(@PathVariable("id")Integer id);
}
