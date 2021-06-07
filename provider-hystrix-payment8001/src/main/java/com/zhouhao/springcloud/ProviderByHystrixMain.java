package com.zhouhao.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class ProviderByHystrixMain {
    public static void main(String[] args) {
        SpringApplication.run(ProviderByHystrixMain.class,args);
    }
}
