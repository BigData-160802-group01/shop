package com.ibeifeng.shop.model;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/1.
 */
@Entity
@Table(name = "t_protypelist")
public class ProTypeList {
    private int id;
        private String totalListName;
    private Set< ProType> type;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTotalListName() {
        return totalListName;
    }

    public void setTotalListName(String totalListName) {
        this.totalListName = totalListName;
    }

    public ProTypeList() {

    }

    public ProTypeList(int id,String totalListName) {
        this.totalListName=totalListName;
        this.id = id;
    }

    public ProTypeList(String totalListName) {
        this.totalListName = totalListName;
    }

    @OneToMany(mappedBy = "proTypeList")
    public Set<ProType> getType() {
        return type;
    }

    public void setType(Set<ProType> type) {
        this.type = type;
    }
}
