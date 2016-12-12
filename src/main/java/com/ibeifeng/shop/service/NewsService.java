package com.ibeifeng.shop.service;

import com.ibeifeng.shop.dao.INewsDao;
import com.ibeifeng.shop.model.News;
import com.ibeifeng.shop.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
@Service
public class NewsService implements INewsService {
    private INewsDao newsDao;
    public void add(News news) {
        newsDao.add(news);
    }

    public void update(News news) {
        newsDao.update(news);
    }

    public void delete(int id) {
        newsDao.delete(id);
    }

    public News load(int id) {

        return newsDao.load(id);
    }

    public Pager<News> list() {
        String Hql="from News";
        return newsDao.pageList(Hql,null);
    }

    public List<News> listnolimit() {
        String hql="from News";
        return newsDao.list(hql,null);
    }


    public List<News> listbyProduct(String Hql,Object[] par) {
        return newsDao.list(Hql,par);
    }
    @Resource
    public void setNewsDao(INewsDao newsDao) {
        this.newsDao = newsDao;
    }
}
