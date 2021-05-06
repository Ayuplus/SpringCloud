package com.zhouhao.springcloud.controller;

import com.zhouhao.springcloud.entities.CommonResult;
import com.zhouhao.springcloud.entities.Payment;
import com.zhouhao.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    PaymentService service;

    @PostMapping(value = "/payment/add")
    public CommonResult add(@RequestBody Payment payment){
        int result = service.add(payment);
        System.out.println(result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功",result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = service.getPaymentById(id);
        System.out.println(payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功",payment);
        }else {
            return new CommonResult(444,"查询失败,查询id:"+id,null);
        }
    }
}
