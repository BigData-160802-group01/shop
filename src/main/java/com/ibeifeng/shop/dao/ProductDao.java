package com.ibeifeng.shop.dao;

import com.ibeifeng.shop.model.ProType;
import com.ibeifeng.shop.model.ProTypeList;
import com.ibeifeng.shop.model.Product;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/7.
 */
@Repository
public class ProductDao extends BaseDao<Product> implements IProductDao {
    public ProType loadbyname(String name){
        return (ProType) getsession().createQuery("from ProType where typeName=:name").setParameter("name",name).uniqueResult();
    }
}
