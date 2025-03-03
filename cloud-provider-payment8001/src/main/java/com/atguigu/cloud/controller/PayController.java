package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
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
    @PostMapping(value = "/pay/add")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        int i = payService.add(pay);
        log.info(pay.toString());
        return ResultData.success("成功插入记录：" + i);
    }

    @Operation(summary = "删除", description = "删除支付流水方法")
    @DeleteMapping(value = "/pay/del/{id}")
    public ResultData<Integer> delPay(@PathVariable("id") Integer id) {
        int delete = payService.delete(id);
        return ResultData.success(delete);
    }

    @Operation(summary = "修改支付流水", description = "修改支付流水方法")
    @PutMapping(value = "/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值:" + i);
    }

    @Operation(summary = "通过ID获取支付流水", description = "通过ID获取支付流水方法")
    @GetMapping(value = "/pay/get/{id}")
    public ResultData<Pay> getPayById(@PathVariable("id") Integer id) {
        if(id==-4){
            throw new RuntimeException("id不能为负数");
        }
        Pay byId = payService.getById(id);
        return ResultData.success(byId);
    }

    @Operation(summary = "获取所有支付流水", description = "获取所有支付流水方法")
    @GetMapping("/pay/list")
    public ResultData<List<Pay>> list() {
        List<Pay> all = payService.getAll();
        return ResultData.success(all);
    }
    @GetMapping("/pay/error")
    public ResultData<Integer> getPayError(){
        Integer integer = Integer.valueOf("200");
        try{
            int age =10/0;
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }
        return ResultData.success(integer);
    }
}
