package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.IUserDao;
import com.ibeifeng.shop.model.User;
import com.ibeifeng.shop.util.Pager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
@Service
public class UserService implements IUserService {
    private IUserDao userDao;
    private IShopService shopService;
    public String login(String username, String password, HttpSession session) {
        User user=userDao.load(username,password);
        if (user==null){
            //如果dao层方法返回是空  说明  用户名不存在 ，返回给controller 信息
            return "usernameerror";
        }
        if (user.getLogin()==1){
            //如果用户的login 为1  说明 用户名和密码正确， 判断同一账户是否已经登陆 如果没有，放行
            Map<String,String> loginUserMap=(Map<String, String>)session.getServletContext().getAttribute("loginUM");
            if (loginUserMap==null){
                loginUserMap=new HashMap<String, String>();
            }
            String sessionId=session.getId();
            boolean isExist=false;
            for (String userna:loginUserMap.keySet()){
//                判断用户名 是否不在已登陆的用户名集合中或者 sessionID是否在集合中
                if (!userna.equals(user.getName())||loginUserMap.containsValue(sessionId)){
                    continue;
                }
                isExist=true;
                break;
            }
            if (isExist){
                loginUserMap.remove(user.getName());
                session.getServletContext().setAttribute("loginUM",loginUserMap);
                return "isExist";
            }else {
                //可以登录
                loginUserMap.put(user.getName(),sessionId);
                session.setAttribute("LoginUser",user);
                session.getServletContext().setAttribute("loginUM",loginUserMap);
                return "success";
            }
        }else if (user.getLogin()==2){
            //如果 用户login 为2  说明 密码错误， 返回给controller 密码错误
            return "passworderror";
        }
        return null;
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User load(int id) {
       return userDao.load(id);
    }

    public void add(User user, HttpSession session) {
        userDao.add(user);
        Map<String,String> loginUserMap=(Map<String, String>)session.getServletContext().getAttribute("loginUM");
        if (loginUserMap==null){
            loginUserMap=new HashMap<String, String>();
        }
        session.setAttribute("LoginUser",user);
        loginUserMap.put(user.getName(),session.getId());
        session.getServletContext().setAttribute("loginUM",loginUserMap);
    }

    public void delete(User user){
        userDao.delete(user.getId());
        shopService.delete(user.getShop().getId());
    }
    /**
     * 获取所有用户的信息
     * @return
     */
    public Pager<User> pageList() {
        String hql="from User";
       return userDao.pageList(hql,null);
    }

    /**
     * 获取所有用户信息的集合
     * @return
     */


    public String searchusername(String username){
        User user=userDao.load(username,null);
        if (user!=null){
            return "false";
        }else {
            return "OK";
        }
    }

    public String judgeStatus(HttpSession session) {
        User user=(User)session.getAttribute("LoginUser");
        if (user!=null){
            if (user.getStatus()==1){
                return "OK";
            }
        }else {
            return "false";
        }
        return "";
    }
    @Resource
    public void setShopService(IShopService shopService) {
        this.shopService = shopService;
    }

    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
