package com.zhouhao.springcloud.controller;

import com.zhouhao.springcloud.entities.CommonResult;
import com.zhouhao.springcloud.entities.Payment;
import com.zhouhao.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService service;
    //读取配置文件端口号
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


    @PostMapping(value = "/payment/add")
    public CommonResult<Object> add(@RequestBody Payment payment){
        int result = service.add(payment);
        log.info(""+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功 ,Server Port : "+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = service.getPaymentById(id);
        //log.info(payment.toString());
        if (payment != null){
            return new CommonResult(200,"查询成功,Server Port : "+serverPort,payment);
        }else {
            return new CommonResult(444,"查询失败,查询id:"+id,null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            log.info(s);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT-SERVER");
        for (ServiceInstance s:instances) {
            log.info(s.getServiceId()+"\t"+s.getHost()+"\t"+s.getPort()+"\t"+s.getUri());
        }
        return discoveryClient;
    }

//    @GetMapping(value = "/payment/testLB")
//    public String testLB(){
//        return serverPort;
//    }

/*//    @GetMapping(value = "/payment/timeout" )
//    public String testFeignTimeout(){
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return serverPort;
//    }*/

    @GetMapping("/payment/testHystrix/ok/{id}")
    public String testHystrixS(@PathVariable("id") Integer id){
        return service.testSuccess(id);
    }

    @GetMapping("/payment/testHystrix/timeout/{id}")
    public String testHystrixF(@PathVariable("id") Integer id){
        return service.testFailed(id);
    }


}
