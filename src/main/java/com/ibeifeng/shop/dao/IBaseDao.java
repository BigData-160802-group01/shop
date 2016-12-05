package com.ibeifeng.shop.dao;

import com.ibeifeng.shop.util.Pager;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */
public interface IBaseDao<T> {
    void add(T t);
    void update(T t);
    void delete(int id);
//    查询单个对象
    T load(int id);
//    查询列表
    List<T> list(String hql);
//    带参数的查询
    List<T> list(String hql, Object[] obj);

    Pager<T> pageList(String hql, Object[] obj);
}
