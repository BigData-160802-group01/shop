package com.ibeifeng.shop.dao;

import com.ibeifeng.shop.util.PageSystemContext;
import com.ibeifeng.shop.util.Pager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */
public class BaseDao<T>extends HibernateDaoSupport implements IBaseDao<T> {
    private Class<T> cla;
    public Session getsession(){
       return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    public void add(T t) {
        this.getHibernateTemplate().save(t);
    }


    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }


    public void delete(int id) {
        this.getHibernateTemplate().delete(this.load(id));
    }


    public T load(int id) {
        return this.getHibernateTemplate().load(getCla(),id);
    }

    public List<T> list(String hql) {
        List<T> list=this.getSessionFactory().getCurrentSession().createQuery(hql).list();
        return list;
    }


    public List<T> list(String hql, Object[] obj) {
        Query query=this.getSessionFactory().getCurrentSession().createQuery(hql);
        if(obj!=null&&obj.length>0){
            for (int i = 0; i <obj.length ; i++) {
                query.setParameter(i,obj[i]);
            }
        }
        List<T> list=query.list();
        return list;
    }

    public Pager<T> pageList(String hql,Object[] obj){
        int pageIndex=PageSystemContext.getPageIndex();
        int pageSize=PageSystemContext.getPageSize();
        Long tempTotalvalue=(Long)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(countHql(hql)).uniqueResult();
        int totalValue=Integer.parseInt(tempTotalvalue.toString());
        int totalPage=totalValue%pageSize==0?totalValue/pageSize:totalValue/pageSize+1;
        Query query=this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        if (obj!=null&&obj.length>0){
            for (int i = 0; i <obj.length ; i++) {
                query.setParameter(i,obj[i]);
            }
        }
        List<T> datas=query.setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize).list();
        return new Pager(pageSize,pageIndex,totalPage,totalValue,datas);
    }



    public String countHql(String hql){
        int fromIndex=hql.indexOf("from");
        String temp=hql.substring(fromIndex);
        return "select count(*) "+temp;
    }

    public Class<T> getCla() {
        if(cla==null){
           cla=(Class<T>)((ParameterizedType)(getClass().getGenericSuperclass())).getActualTypeArguments()[0];
        }
        return cla;
    }
}
