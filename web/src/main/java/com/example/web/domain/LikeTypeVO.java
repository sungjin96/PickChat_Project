package com.example.web.domain;

public class LikeTypeVO{
    private int lno;
    private String content;
    private String userid;
    private int typeid;

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

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    @Override
    public String toString() {
        return "LikeTypeVO [content=" + content + ", typeid=" + typeid + ", userid=" + userid + "]";
    }

    public int getLno() {
        return lno;
    }

    public void setLno(int lno) {
        this.lno = lno;
    }

    
    
    
}