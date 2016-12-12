package com.ibeifeng.shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/12/9.
 */
@Entity
@Table(name = "t_adress")
public class UserAdress {
    private int id;
    private String adress;
    //收货人
    private String consignee;
    //收货电话
    private String telphone;
    private int chose;
    private User aduser;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public int getChose() {
        return chose;
    }

    public void setChose(int chose) {
        this.chose = chose;
    }

    @ManyToOne
    @JoinColumn(name = "u_id")

    public User getAduser() {
        return aduser;
    }

    public void setAduser(User aduser) {
        this.aduser = aduser;
    }
}
