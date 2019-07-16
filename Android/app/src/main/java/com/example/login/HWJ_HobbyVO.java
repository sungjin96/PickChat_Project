package com.example.login;

public class HWJ_HobbyVO {
    private int hobbyid;
    private String content;
    private String userid;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "HobbyVO{" +
                "hobbyid=" + hobbyid +
                ", content='" + content + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}