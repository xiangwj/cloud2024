package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "支付微服务模块", description = "支付CURD")
@RestController
@Slf4j
public class PayController {
    @Resource
    PayService payService;

    @Operation(summary = "新增", description = "新增支付流水方法，json串作为参数")
    @PostMapping(value="/pay/add")
    public String addPay(@RequestBody Pay pay) {
        int i = payService.add(pay);
        log.info(pay.toString());
        return "插入返回值:" + i;
    }

    @Operation(summary = "删除", description = "删除支付流水方法")
    @DeleteMapping(value = "/pay/del/{id}")
    public Integer delPay(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @Operation(summary = "修改支付流水", description = "修改支付流水方法")
    @PutMapping(value = "/pay/update")
    public String updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return "更新返回值:" + i;
    }

    @Operation(summary = "通过ID获取支付流水", description = "通过ID获取支付流水方法")
    @GetMapping(value = "/pay/get/{id}")
    public Pay getPayById(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }

    @Operation(summary = "获取所有支付流水", description = "获取所有支付流水方法")
    @GetMapping("/pay/list")
    public List<Pay> list() {
        return payService.getAll();
    }
}
