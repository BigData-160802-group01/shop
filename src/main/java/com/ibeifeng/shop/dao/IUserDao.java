package com.ibeifeng.shop.dao;

import com.ibeifeng.shop.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public interface IUserDao extends IBaseDao<User> {
    User load(String username,String password);
    void add(User user);
    void update(User user);
    void delete(int id);
    List<User> list(String hql);
}
