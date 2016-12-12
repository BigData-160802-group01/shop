package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.IShopDao;
import com.ibeifeng.shop.model.Order;
import com.ibeifeng.shop.model.Product;
import com.ibeifeng.shop.model.Shop;
import com.ibeifeng.shop.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/4.
 */
@Service
public class ShopService implements IShopService {
    private IShopDao shopDao;
    private IOrderService orderService;
    public void add(Shop shop) {
        shopDao.add(shop);
    }

    public void update(Shop shop) {
        shopDao.update(shop);
    }

    public void delete(int id) {
        shopDao.delete(id);
    }

    public Shop load(int id) {
        return shopDao.load(id);
    }

    public List<Shop> list(String hql) {
        String hql1="from Shop";
        return shopDao.list(hql1);
    }

    public void joinOrder(User user, Product product) {
        Order order=new Order();
        order.setProduct(product);
        order.setUser(user);
        order.setShop(user.getShop());
        orderService.add(order);
    }

    public List<Order> payShopping(Shop shop){
        Set<Order> orders=shop.getOrders();
        List<Order> list=new ArrayList<Order>();
        for (Order o :orders) {
            if (o.getStatus()==0){
                list.add(o);
            }
        }
        return list;
    }


    public List<Shop> listByProduct(String hql,Object[] param) {
        return shopDao.list(hql,param);
    }

    @Resource
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }
    @Resource
    public void setShopDao(IShopDao shopDao) {
        this.shopDao = shopDao;
    }
}
