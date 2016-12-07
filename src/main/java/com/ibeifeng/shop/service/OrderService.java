package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.IOrderDao;
import com.ibeifeng.shop.model.Order;
import com.ibeifeng.shop.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
@Service
public class OrderService implements IOrderService {
    private IOrderDao orderDao;
    public void add(Order order) {
        orderDao.add(order);
    }

    public void update(Order order) {
        orderDao.update(order);
    }

    public void delete(int id) {
        orderDao.delete(id);
    }

    public Order load(int id) {
        return orderDao.load(id);
    }

    public Pager<Order> list() {
        String hql="from Order";
        return orderDao.pageList(hql,null);
    }

    public List<Order> listnolimit() {
        String hql="from Order";
        return orderDao.list(hql,null);
    }

    @Resource
    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
