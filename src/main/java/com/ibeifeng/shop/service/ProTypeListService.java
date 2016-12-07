package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.IProTypeDao;
import com.ibeifeng.shop.dao.IProTypeListDao;
import com.ibeifeng.shop.model.ProType;
import com.ibeifeng.shop.model.ProTypeList;
import com.ibeifeng.shop.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/6.
 */
@Service
public class ProTypeListService implements IProTypeListService {
    private IProTypeListDao proTypeListDao;
    private IProTypeDao proTypeDao;

    public void add(ProTypeList typeList) {

    }

    public void update(ProTypeList typeList) {
        proTypeListDao.update(typeList);
    }

    public void delete(int id) {
        Set<ProType> set=proTypeListDao.load(id).getType();
        for (ProType p:set) {
            p.setProTypeList(null);
            proTypeDao.update(p);
        }
        proTypeListDao.delete(id);
    }

    public ProTypeList load(int id) {
        return null;
    }

    public Pager<ProTypeList> list() {
        String hql="from ProTypeList";
        return proTypeListDao.pageList(hql,null);
    }

    public ProTypeList loadByName(String name) {
        String hql="from ProTypeList where totalListName=?";
        Object[] par={name};
        return  proTypeListDao.list(hql,par).get(0);
    }
    public List<ProTypeList> listnolimit(){
        String hql="from ProTypeList";
        return proTypeListDao.list(hql);
    }




    @Resource
    public void setProTypeDao(IProTypeDao proTypeDao) {
        this.proTypeDao = proTypeDao;
    }

    @Resource
    public void setProTypeListDao(IProTypeListDao proTypeListDao) {
        this.proTypeListDao = proTypeListDao;
    }
}
