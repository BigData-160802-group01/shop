package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.IProTypeDao;
import com.ibeifeng.shop.dao.ProTypeDao;
import com.ibeifeng.shop.model.ProType;
import com.ibeifeng.shop.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/12/6.
 */
@Service
public class ProTypeService implements IProTypeService {
    private IProTypeDao proTypeDao;
    public void add(ProType type) {
        proTypeDao.add(type);
    }

    public void update(ProType type) {
        proTypeDao.update(type);
    }

    public void delete(int id) {
        proTypeDao.delete(id);
    }

    public ProType load(int id) {
        return proTypeDao.load(id);
    }

    public Pager<ProType> list() {
        String Hql="from ProType";
        return proTypeDao.pageList(Hql,null);
    }

    @Resource
    public void setProTypeDao(IProTypeDao proTypeDao) {
        this.proTypeDao = proTypeDao;
    }
}
