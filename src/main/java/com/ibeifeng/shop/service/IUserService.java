package com.ibeifeng.shop.service;

import com.ibeifeng.shop.model.User;
import com.ibeifeng.shop.util.Pager;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public interface IUserService {
    //用户登录方法
    String login(String username, String password, HttpSession session);
    void update(User user);
    User load(int id);
    void add(User user,HttpSession session);
    String searchusername(String username);
    String judgeStatus(HttpSession session);
    Pager<User> pageList();
}
