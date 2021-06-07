package com.zhouhao.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    public static final String INVOKE_URL = "http://cloud-provider-payment";
    @Resource
    private RestTemplate template;

    @RequestMapping("/consumer/payment/zk")
    public String paymentInfo(){
        String res = template.getForObject(INVOKE_URL+"/payment/zk",String.class);
        return res;
    }
}
