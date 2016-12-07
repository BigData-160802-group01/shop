package com.ibeifeng.shop.service;

import com.ibeifeng.shop.model.Order;
import com.ibeifeng.shop.model.Product;
import com.ibeifeng.shop.util.Pager;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface IOrderService {
    void add(Order order);
    void update(Order order);
    void delete(int id);
    Order load(int id);
    Pager<Order> list();
    List<Order> listnolimit();
}
