package com.ibeifeng.shop.dao;

import com.ibeifeng.shop.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/3.
 */
@Repository
public class UserDao extends BaseDao<User> implements IUserDao {
    public User load(String username, String password) {
        User user=(User) getsession().createQuery("from User where name=:name").setParameter("name",username).uniqueResult();
        System.out.println(user.getUserAdresses());
        if (user!=null){
            if (user.getPassword().equals(password)){
                user.setLogin(1);
                return user;
            }else {
                //用户名和密码不一致,设置用户状态为 未登录，方便service判断
                user.setLogin(2);
                return user;
            }
        //用户名错误，返回空 方便service 判断 //
        }else {
            return null;
        }
    }

    /**
     * 注册验证用户名是否存在
     * @param username
     * @return
     */
    public User load1(String username) {
        User user=(User) getsession().createQuery("from User where name=:name").setParameter("name",username).uniqueResult();
        if (user!=null){
                return user;
        }else {
            return null;
        }
    }
}
