package com.ibeifeng.shop.dao;

import com.ibeifeng.shop.model.ProTypeList;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/6.
 */
@Repository
public class ProTypeListDao extends BaseDao<ProTypeList> implements IProTypeListDao {
    public ProTypeList loadbyname(String name){
        return (ProTypeList) getsession().createQuery("from ProTypeList where totalListName=:name").setParameter("name",name).uniqueResult();
    }
}
