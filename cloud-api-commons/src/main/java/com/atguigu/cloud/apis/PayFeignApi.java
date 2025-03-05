package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("cloud-payment-service")//申明接口用于连接哪个服务
public interface PayFeignApi {
    @PostMapping(value = "/pay/add")
    public ResultData<String> addPay(@RequestBody PayDTO payDTO);

    @DeleteMapping(value = "/pay/del/{id}")
    public ResultData<Integer> delPay(@PathVariable("id") Integer id);

    @PutMapping(value = "/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO);

    @GetMapping(value = "/pay/get/{id}")
    public ResultData<PayDTO> getPayById(@PathVariable("id") Integer id);

    @GetMapping("/pay/list")
    public ResultData<List<PayDTO>> list() ;

    @GetMapping("/pay/get/info")
    public String mylb();

    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);
}

