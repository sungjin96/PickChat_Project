package com.example.login;

public class HWJ_TypeVO {
    private int typeid;
    private String content;
    private String userid;

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "TypeVO{" +
                "typeid=" + typeid +
                ", content='" + content + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
