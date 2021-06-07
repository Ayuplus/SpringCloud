package com.zhouhao.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhouhao.springcloud.entities.CommonResult;
import com.zhouhao.springcloud.entities.Payment;
import com.zhouhao.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
//  默认服务降级
@DefaultProperties(defaultFallback = "TimeoutGlobalHandler")
public class HystrixController {
    @Resource
    private PaymentService service;

    @GetMapping("/consumer/payment/testHystrix/ok/{id}")
    public String testHystrixS(@PathVariable("id") Integer id){
        return service.testSuccess(id);
    }

    @GetMapping("/consumer/payment/testHystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
//    })
    @HystrixCommand
    public String testHystrixF(@PathVariable("id") Integer id){
        //System.out.println(1/0);
        return service.testFailed(id);
    }

    //指定的服务降级方法
    public String TimeOutHandler(Integer id){
        return "服务降级～～～～～～～～～～";
    }

    //全局默认服务降级方法
    public String TimeoutGlobalHandler(){
        return "全局服务降级方法～～～～～～～～～～";
    }
}
