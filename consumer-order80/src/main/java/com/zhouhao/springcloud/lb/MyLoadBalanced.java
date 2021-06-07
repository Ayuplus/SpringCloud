package com.zhouhao.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
//@Component
public class MyLoadBalanced implements LoadBalanced{
    private AtomicInteger index = new AtomicInteger(0);
    private  int increment(){
        int current;
        int next;
        do {
            current = index.get();
            next = current + 1;
        }while (!this.index.compareAndSet(current,next));
        return next;
    }

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> list) {
        int index = increment() % list.size();
        return list.get(index);
    }
}
