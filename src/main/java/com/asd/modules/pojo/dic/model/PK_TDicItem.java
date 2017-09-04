package com.asd.modules.pojo.dic.model;

import java.io.Serializable;

/**
 * 组子表主键
 * Created by lenovo on 2017/2/9.
 */
public class PK_TDicItem implements Serializable {


    private String item_code;

    private String group_code;

    public PK_TDicItem(){

    }

    public PK_TDicItem(String item_code,String group_code){
        this.item_code = item_code;
        this.group_code = group_code;
    }


    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    /**
     * 覆盖hashCode方法，必须要有
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (item_code == null ? 0 : item_code.hashCode());
        result = PRIME * result + (group_code == null ? 0 : group_code.hashCode());
        return result;
    }
    /**
     * 覆盖equals方法，必须要有
     */

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof PK_TDicItem)) return false;
        PK_TDicItem objKey = (PK_TDicItem)obj;
        if(item_code.equalsIgnoreCase(objKey.item_code) &&
                group_code.equalsIgnoreCase(objKey.group_code)) {
            return true;
        }
        return false;
    }


}
