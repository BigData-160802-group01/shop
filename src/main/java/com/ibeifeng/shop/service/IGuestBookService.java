package com.ibeifeng.shop.service;

import com.ibeifeng.shop.model.GuestBook;
import com.ibeifeng.shop.model.Order;
import com.ibeifeng.shop.util.Pager;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public interface IGuestBookService {
    void add(GuestBook book);
    void update(GuestBook book);
    void delete(int id);
    GuestBook load(int id);
    Pager<GuestBook> list();
    List<GuestBook> listnolimit();
}
