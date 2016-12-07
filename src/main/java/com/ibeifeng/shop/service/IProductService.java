package com.ibeifeng.shop.service;

import com.ibeifeng.shop.model.ProType;
import com.ibeifeng.shop.model.Product;
import com.ibeifeng.shop.util.Pager;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface IProductService {
    void add(Product product);
    void update(Product product);
    void delete(int id);
    Product load(int id);
    Pager<Product> list();
    List<Product> listnolimit();
}
