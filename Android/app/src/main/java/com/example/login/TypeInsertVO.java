package com.example.login;

import java.util.Arrays;

public class TypeInsertVO {
    private Integer[] typeid;
    private Integer[] hobbyid;
    private String userid;

    public Integer[] getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer[] typeid) {
        this.typeid = typeid;
    }

    public Integer[] getHobbyid() {
        return hobbyid;
    }

    public void setHobbyid(Integer[] hobbyid) {
        this.hobbyid = hobbyid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "TypeInsertVO{" +
                "typeid=" + Arrays.toString(typeid) +
                ", hobbyid=" + Arrays.toString(hobbyid) +
                ", userid='" + userid + '\'' +
                '}';
    }
}
