package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    public static final String PaymentSrv_URL = "http://localhost:8001";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping("/consumer/pay/del/{id}")
    public ResultData delOrder(@PathVariable("id") Integer id) {
        return restTemplate.exchange(PaymentSrv_URL + "/pay/del/" + id, HttpMethod.DELETE, null, ResultData.class).getBody();
    }

    @GetMapping("/consumer/pay/update")
    public ResultData updateOrder(@RequestBody PayDTO payDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PayDTO> requestEntity = new HttpEntity<>(payDTO, headers);
        return restTemplate.exchange(PaymentSrv_URL + "/pay/update", HttpMethod.PUT, requestEntity, ResultData.class).getBody();
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class);
    }

    @GetMapping("/consumer/pay/list")
    public ResultData getListPayInfo() {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/list" , ResultData.class);
    }
    @GetMapping("/consumer/error")
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
