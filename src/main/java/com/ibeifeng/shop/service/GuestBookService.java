package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.GuestBookDao;
import com.ibeifeng.shop.dao.IGuestBookDao;
import com.ibeifeng.shop.model.GuestBook;
import com.ibeifeng.shop.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
@Service
public class GuestBookService implements IGuestBookService{
    private IGuestBookDao guestBookDao;

    public void add(GuestBook book) {
        guestBookDao.add(book);
    }

    public void update(GuestBook book) {
        guestBookDao.update(book);
    }

    public void delete(int id) {
        guestBookDao.delete(id);
    }

    public GuestBook load(int id) {
        return guestBookDao.load(id);
    }

    public Pager<GuestBook> list() {
        String Hql="from GuestBook";
        return guestBookDao.pageList(Hql,null);
    }

    public List<GuestBook> listnolimit() {
        String Hql="from GuestBook";
        return guestBookDao.list(Hql,null);
    }



    @Resource
    public void setGuestBookDao(IGuestBookDao guestBookDao) {
        this.guestBookDao = guestBookDao;
    }
}
