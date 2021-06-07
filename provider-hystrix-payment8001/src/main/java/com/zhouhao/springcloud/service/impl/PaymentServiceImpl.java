package com.zhouhao.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhouhao.springcloud.dao.PaymentDao;
import com.zhouhao.springcloud.entities.Payment;
import com.zhouhao.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;

    @Override
    public int add(Payment payment) {
        return paymentDao.add(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    public String testSuccess(Integer id){
        return "线程池"+Thread.currentThread().getName() + "  id :" +id;
    }
    @HystrixCommand(fallbackMethod = "TimeOutHandler" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" ,value = "3000")
    })
    public String testFailed(Integer id) {
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(1/0);
        return "线程池"+Thread.currentThread().getName() + "  id :" +id;
    }
    public String TimeOutHandler(Integer id){
        return "服务降级～～～～～～～～～～";
    }
}
