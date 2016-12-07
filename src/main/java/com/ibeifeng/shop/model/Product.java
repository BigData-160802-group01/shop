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
    private String proname;
    private String description;
    //定义价格
    private double price;
    //定义库存
    private int stock;
    //定义分类
    private ProType proType;
//    图片路径
    private String pitch;
    public Product() {
    }
    public Product(int id, String name, String description, double price, int stock,String pitch) {

        this.id = id;
        this.proname = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.pitch=pitch;
    }

    public Product(String name, String description, double price, int stock,String pitch) {

        this.proname = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.pitch=pitch;
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

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
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

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
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
