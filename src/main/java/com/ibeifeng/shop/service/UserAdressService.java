package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.IUserAdressDao;
import com.ibeifeng.shop.model.UserAdress;
import com.ibeifeng.shop.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
@Service
public class UserAdressService implements IUserAdressService {
    private IUserAdressDao userAdressDao;
    public void add(UserAdress adress) {
        userAdressDao.add(adress);
    }

    public void update(UserAdress adress) {
        userAdressDao.update(adress);
    }

    public void delete(int id) {
        userAdressDao.delete(id);
    }

    public UserAdress load(int id) {
        return userAdressDao.load(id);
    }

    public Pager<UserAdress> list() {

        return null;
    }

    public List<UserAdress> listnolimit() {
        String Hql="from UserAdress";
        return userAdressDao.list(Hql);
    }
    @Resource
    public void setUserAdressDao(IUserAdressDao userAdressDao) {
        this.userAdressDao = userAdressDao;
    }
}
