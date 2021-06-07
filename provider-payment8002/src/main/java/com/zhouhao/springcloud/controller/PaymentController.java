package com.zhouhao.springcloud.controller;

import com.zhouhao.springcloud.entities.CommonResult;
import com.zhouhao.springcloud.entities.Payment;
import com.zhouhao.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService service;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/add")
    public CommonResult<Object> add(@RequestBody Payment payment){
        int result = service.add(payment);
        //System.out.println(result);
        if (result > 0){
            return new CommonResult(200,"插入数据库成功,Server Port : "+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = service.getPaymentById(id);
        //System.out.println(payment);
        if (payment != null){
            return new CommonResult(200,"查询成功,Server Port : "+serverPort,payment);
        }else {
            return new CommonResult(444,"查询失败,查询id:"+id,null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){

        return discoveryClient;
    }
    @GetMapping(value = "/payment/testLB")
    public String testLB(){
        return serverPort;
    }
    @GetMapping(value = "/payment/timeout" )
    public String testFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
