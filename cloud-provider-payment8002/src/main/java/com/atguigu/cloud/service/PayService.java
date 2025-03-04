package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;

import java.util.List;

public interface PayService {
    public int add(Pay pay);

    public int delete(int id);

    public int update(Pay pay);

    public Pay getById(int id);

    public List<Pay> getAll();
}
