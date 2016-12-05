package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.IShopDao;
import com.ibeifeng.shop.model.Shop;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/12/4.
 */
@Service
public class ShopService implements IShopService {
    private IShopDao shopDao;
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

    @Resource
    public void setShopDao(IShopDao shopDao) {
        this.shopDao = shopDao;
    }
}
