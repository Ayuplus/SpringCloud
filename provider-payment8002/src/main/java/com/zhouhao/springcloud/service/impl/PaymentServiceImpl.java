package com.zhouhao.springcloud.service.impl;

import com.zhouhao.springcloud.dao.PaymentDao;
import com.zhouhao.springcloud.entities.Payment;
import com.zhouhao.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
