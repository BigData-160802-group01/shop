package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.IProTypeDao;
import com.ibeifeng.shop.dao.IProductDao;
import com.ibeifeng.shop.model.ProType;
import com.ibeifeng.shop.model.Product;
import com.ibeifeng.shop.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
@Service
public class ProductService implements IProductService {
    private IProductDao productDao;
    private IProTypeDao proTypeDao;

    /**
     * 添加商品方法
     * @param product
     */
    public void add(Product product) {
        ProType proType=productDao.loadbyname(product.getProType().getTypeName());
        product.setProType(proType);
        productDao.add(product);
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public void delete(int id) {
        productDao.delete(id);
    }

    public Product load(int id) {
        return productDao.load(id);
    }

    public Pager<Product> list() {
        String hql="from Product";
        return productDao.pageList(hql,null);
    }

    public List<Product> listnolimit() {
        String hql="from Product";
        return productDao.list(hql,null);
    }

    @Resource
    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }
    @Resource
    public void setProTypeDao(IProTypeDao proTypeDao) {
        this.proTypeDao = proTypeDao;
    }
}
