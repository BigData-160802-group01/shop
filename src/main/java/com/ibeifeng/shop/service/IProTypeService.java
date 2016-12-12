package com.ibeifeng.shop.service;

import com.ibeifeng.shop.model.ProType;
import com.ibeifeng.shop.model.ProTypeList;
import com.ibeifeng.shop.util.Pager;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface IProTypeService {
    void add(ProType type);
    void update(ProType type);
    void delete(int id);
    ProType load(int id);
    Pager<ProType> list();
    List<ProType> listnolimit();
    ProType loadByName(String name);
}
