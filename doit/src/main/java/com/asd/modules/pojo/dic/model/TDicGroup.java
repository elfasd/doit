package com.asd.modules.pojo.dic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 字典组实体
 * Created by lenovo on 2017/2/9.
 */
@Entity
@Table(name = "T_DIC_GROUP")
public class TDicGroup {

    private String group_code;

    private String group_name;

    @Id
    @Column(name="group_code")
    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    @Column(name="group_name")
    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}
