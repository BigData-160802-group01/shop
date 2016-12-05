package com.ibeifeng.shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/1.
 */
@Entity
@Table(name = "t_shop")
public class Shop {
    private int id;
    private Set<Order> orders;
    private User user;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @OneToOne(mappedBy = "shop")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @OneToMany(mappedBy = "shop")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Shop(int id) {
        this.id = id;
    }

    public Shop() {

    }
}
