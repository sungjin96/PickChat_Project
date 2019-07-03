package com.example.web.domain;

public class CategoryVO{
    private int typeid;
    private int hobbyid;
    private String content;

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getHobbyid() {
        return hobbyid;
    }

    public void setHobbyid(int hobbyid) {
        this.hobbyid = hobbyid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CategoryVO [content=" + content + ", hobbyid=" + hobbyid + ", typeid=" + typeid + "]";
    }
    
}