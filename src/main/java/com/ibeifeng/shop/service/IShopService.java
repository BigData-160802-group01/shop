package com.ibeifeng.shop.service;

import com.ibeifeng.shop.model.Order;
import com.ibeifeng.shop.model.Product;
import com.ibeifeng.shop.model.Shop;
import com.ibeifeng.shop.model.User;

import javax.servlet.http.HttpSession;
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
    void joinOrder(User user, Product product);
    List<Order> payShopping(Shop shop);
    List<Shop> listByProduct(String hql,Object[] param);
}
