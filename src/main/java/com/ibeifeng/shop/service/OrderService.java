package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.IOrderDao;
import com.ibeifeng.shop.dao.IProductDao;
import com.ibeifeng.shop.dao.IShopDao;
import com.ibeifeng.shop.dao.IUserDao;
import com.ibeifeng.shop.model.Order;
import com.ibeifeng.shop.model.Product;
import com.ibeifeng.shop.model.Shop;
import com.ibeifeng.shop.model.User;
import com.ibeifeng.shop.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
@Service
public class OrderService implements IOrderService {
    private IOrderDao orderDao;
    private IProductDao productDao;
    private IShopDao shopDao;
    private IUserDao userDao;
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

    public void createOrder(HttpSession session, Order order) {
        Product product=productDao.load(order.getProduct().getId());
        order.setProduct(product);
        order.setTotalPrice(order.getCount()*order.getProduct().getPrice());
        User user=(User) session.getAttribute("LoginUser");
        order.setUser(user);
        Shop shop=user.getShop();
        if (shop==null){
            shop=new Shop();
            shop.setUser(user);
            shopDao.add(shop);
        }
        user.setShop(shop);
        userDao.update(user);
        order.setShop(shop);
        orderDao.add(order);
    }

    public List<Order> listByProduct(String hql,Object[] param) {
        return orderDao.list(hql,param);
    }

    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
    @Resource
    public void setShopDao(IShopDao shopDao) {
        this.shopDao = shopDao;
    }
    @Resource
    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }
    @Resource
    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }
}
