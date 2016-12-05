package com.ibeifeng.shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/12/1.
 */
@Entity
@Table(name = "t_product")
public class Product {
    private int id;
    private String name;
    private String description;
    //定义价格
    private double price;
    //定义库存
    private int stock;
    //定义分类
    private ProType proType;
    public Product() {
    }
    public Product(int id, String name, String description, double price, int stock) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, String description, double price, int stock) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    @ManyToOne
    @JoinColumn(name = "protype_id")
    public ProType getProType() {
        return proType;
    }

    public void setProType(ProType proType) {
        this.proType = proType;
    }

}
