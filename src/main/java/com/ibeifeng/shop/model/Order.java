package com.ibeifeng.shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/1.
 */
@Entity
@Table(name = "t_order")
public class Order {
    private int id;
    //定义 订单状态   0 为 未付款  1位 已付款
    private int status;
    private Date createtime;
    private int payType;
    //商品数量
    private int count;
    //定义付款方式  0 为现金  1 为 货到付款
    private double totalPrice;
    private User user;
    private Shop shop;
    private Product product;


    public Order(int status, Date createtime, int payType,double totalPrice) {
        this.status = status;
        this.createtime = createtime;
        this.payType = payType;
        this.totalPrice=totalPrice;
    }

    public Order() {

    }

    public Order(int id, int status, Date createtime, int payType,double totalPrice) {
        this.totalPrice=totalPrice;
        this.id = id;
        this.status = status;
        this.createtime = createtime;
        this.payType = payType;
    }
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @ManyToOne
    @JoinColumn(name = "shop_id")
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    @OneToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
