package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class OrderController {
  @Resource
  private PayFeignApi payFeignApi;
    @PostMapping(value = "/feign/pay/add")
    public ResultData<String> addPay(@RequestBody PayDTO payDTO){
        return payFeignApi.addPay(payDTO);
    }
    @DeleteMapping(value = "/feign/pay/del/{id}")
    public ResultData<Integer> delPay(@PathVariable("id") Integer id){
        return payFeignApi.delPay(id);
    }
    @PutMapping(value = "/feign/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        return payFeignApi.addPay(payDTO);
    }
    @GetMapping(value = "/feign/pay/get/{id}")
    public ResultData<PayDTO> getPayById(@PathVariable("id") Integer id){
        ResultData<PayDTO> payDTO = null;
        try{
            System.out.println("调用开始========"+ DateUtil.now());
            payDTO=payFeignApi.getPayById(id);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("调用结束========"+ DateUtil.now());
        }
        return payDTO;
    }
    @GetMapping("/feign/pay/list")
    public ResultData<List<PayDTO>> list(){
        return payFeignApi.list();
    }
    @GetMapping("/feign/pay/get/info")
    public String mylb(){
        return payFeignApi.mylb();
    }
}
