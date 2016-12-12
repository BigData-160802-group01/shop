package com.ibeifeng.shop.service;

import com.ibeifeng.shop.model.ProTypeList;
import com.ibeifeng.shop.model.Product;
import com.ibeifeng.shop.model.Shop;
import com.ibeifeng.shop.util.Pager;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface IProTypeListService {
    void add(ProTypeList typeList);
    void update(ProTypeList typeList);
    void delete(int id);
    ProTypeList load(int id);
    Pager<ProTypeList> list();
    ProTypeList loadByName(String name);
    List<ProTypeList> listnolimit();
    List<ProTypeList> listByTypeName(String name);
}
