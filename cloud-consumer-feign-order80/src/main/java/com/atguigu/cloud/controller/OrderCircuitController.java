package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name="cloud-payment-service",fallbackMethod = "myCircuitFallback")
    public String myCircuit(@PathVariable("id") Integer id){
        return payFeignApi.myCircuit(id);
    }
    public String myCircuitFallback(Throwable t){
        return "myCircuitFallback,系统繁忙，请稍后再试------/(TOT)/~~";
    }
}
