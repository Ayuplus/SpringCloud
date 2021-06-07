package com.zhouhao.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;


public interface LoadBalanced {
    ServiceInstance getServiceInstance(List<ServiceInstance> list);
}
