package com.ibeifeng.shop.dao;

import com.ibeifeng.shop.model.ProType;
import com.ibeifeng.shop.model.ProTypeList;
import com.ibeifeng.shop.model.Product;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface IProductDao extends IBaseDao<Product> {
    ProType loadbyname(String name);
}
