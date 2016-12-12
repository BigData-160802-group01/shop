package com.ibeifeng.shop.service;

import com.ibeifeng.shop.model.News;
import com.ibeifeng.shop.model.UserAdress;
import com.ibeifeng.shop.util.Pager;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public interface IUserAdressService {
    void add(UserAdress adress);
    void update(UserAdress adress);
    void delete(int id);
    UserAdress load(int id);
    Pager<UserAdress> list();
    List<UserAdress> listnolimit();
}
