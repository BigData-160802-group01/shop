package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.IProTypeDao;
import com.ibeifeng.shop.dao.IProTypeListDao;
import com.ibeifeng.shop.dao.IProductDao;
import com.ibeifeng.shop.dao.ProTypeDao;
import com.ibeifeng.shop.model.ProType;
import com.ibeifeng.shop.model.ProTypeList;
import com.ibeifeng.shop.model.Product;
import com.ibeifeng.shop.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
@Service
public class ProTypeService implements IProTypeService {
    private IProTypeDao proTypeDao;
    private IProTypeListDao proTypeListDao;
    private IProductDao productDao;


    /**
     * 新增二级分类
     * @param type
     */
    public void add(ProType type) {
        ProTypeList list=proTypeListDao.loadbyname(type.getProTypeList().getTotalListName());
        System.out.println(list);
        type.setProTypeList(list);
        proTypeDao.add(type);
    }

    /**
     * 修改二级分类
     * @param type
     */
    public void update(ProType type) {
        ProTypeList list=proTypeListDao.load(type.getProTypeList().getId());
        list.setTotalListName(type.getProTypeList().getTotalListName());
        proTypeListDao.update(list);
        type.setProTypeList(list);
        proTypeDao.update(type);
    }

    /**
     * 删除二级分类根据id
     * @param id
     */
    public void delete(int id) {
        String Hql="from Product where proType.id=?";
        Object[] par={id};
        List<Product> list=productDao.list(Hql,par);
        for (Product p:list) {
            p.setProType(null);
            productDao.update(p);
        }
        proTypeDao.delete(id);
    }

    public ProType load(int id) {
        return proTypeDao.load(id);
    }

    public ProType loadByName(String name){
        return productDao.loadbyname(name);
    }

    public Pager<ProType> list() {
        String hql="from ProType";
        return proTypeDao.pageList(hql,null);
    }

    @Resource
    public void setProTypeDao(IProTypeDao proTypeDao) {
        this.proTypeDao = proTypeDao;
    }
    @Resource
    public void setProTypeListDao(IProTypeListDao proTypeListDao) {
        this.proTypeListDao = proTypeListDao;
    }
    @Resource
    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }
}
