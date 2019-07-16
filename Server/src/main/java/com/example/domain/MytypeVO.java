package com.example.domain;

public class MytypeVO{
    private int mno;
    private String userid;
    private int typeid;
    private String content;

    
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

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

    @Override
    public String toString() {
        return "MytypeVO [content=" + content + ", typeid=" + typeid + ", userid=" + userid + "]";
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }
    
    
}