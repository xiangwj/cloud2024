package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PayController {
    @Resource
    PayService payService;

    @PostMapping("/pay/add")
    public String addPay(@RequestBody Pay pay) {
        int i = payService.add(pay);
        log.info(pay.toString());
        return "插入返回值:" + i;
    }

    @DeleteMapping(value = "/pay/del/{id}")
    public Integer delPay(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @PutMapping(value = "/pay/update")
    public String updatePay(@RequestBody  PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return "更新返回值:" + i;
    }

    @GetMapping(value="/pay/get/{id}")
    public Pay getPayById(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }
    @GetMapping("/pay/list")
    public List<Pay> list(){
        return payService.getAll();
    }
}
