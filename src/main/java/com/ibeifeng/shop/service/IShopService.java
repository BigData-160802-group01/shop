package com.ibeifeng.shop.service;

import com.ibeifeng.shop.model.Shop;

import java.util.List;

/**
 * Created by Administrator on 2016/12/4.
 */
public interface IShopService {
    void add(Shop shop);
    void update(Shop shop);
    void delete(int id);
    Shop load(int id);
    List<Shop> list(String hql);
}
