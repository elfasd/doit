package com.asd.modules.pojo.dic.model;

import javax.persistence.*;

/**
 * 字典组子表
 * Created by lenovo on 2017/2/9.
 */
@Entity
@Table(name = "T_DIC_ITEM")
@IdClass(PK_TDicItem.class)
public class TDicItem {

    @Id
    @Column(name="item_code")
    private String item_code;

    @Column(name="item_name")
    private String item_name;

    @Id
    @Column(name="group_code")
    private String group_code;


    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }


    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }


    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }
}
