package com.zhouhao.springcloud.controller;

import com.zhouhao.springcloud.entities.CommonResult;
import com.zhouhao.springcloud.entities.Payment;
import com.zhouhao.springcloud.lb.LoadBalanced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://PROVIDER-PAYMENT-SERVER";
    @Resource
    private DiscoveryClient client;

    @Resource
    private RestTemplate template;

//    @Resource
//    private LoadBalanced loadBalanced;

    @GetMapping("/consumer/payment/add")
    public CommonResult add(Payment payment){
        return template.postForObject(PAYMENT_URL+"/payment/add",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        return template.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

//    @GetMapping("/consumer/payment/testLB")
//    public String testLB(){ List<ServiceInstance> instances = client.getInstances("PROVIDER-PAYMENT-SERVER");
//        ServiceInstance serviceInstance = loadBalanced.getServiceInstance(instances);
//        URI uri = serviceInstance.getUri();
//        log.info(uri+"");
//        return template.getForObject(uri+"/payment/testLB",String.class);
//    }
}
