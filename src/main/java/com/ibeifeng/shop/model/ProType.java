package com.ibeifeng.shop.model;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;


/**
 * Created by Administrator on 2016/12/1.
 */
@Entity
@Table(name = "t_protype")
public class ProType {
    private int id;
    private String typeName;
        private Set<Product> products;
    private ProTypeList proTypeList;

    public ProType(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    @ManyToOne
    @JoinColumn(name = "protypelist_id")
    public ProTypeList getProTypeList() {
        return proTypeList;
    }

    public void setProTypeList(ProTypeList proTypeList) {
        this.proTypeList = proTypeList;
    }
    @OneToMany(mappedBy = "proType")

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public ProType() {
    }
    public ProType(String typeName) {
        this.typeName = typeName;
    }
}
