package com.ibeifeng.shop.service;

import com.ibeifeng.shop.model.News;
import com.ibeifeng.shop.model.Order;
import com.ibeifeng.shop.util.Pager;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public interface INewsService {
    void add(News news);
    void update(News news);
    void delete(int id);
    News load(int id);
    Pager<News> list();
    List<News> listnolimit();
    List<News> listbyProduct(String Hql,Object[] par);
}
