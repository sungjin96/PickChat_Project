package com.example.web.domain;

public class UserImageVO{
    private String imgpath;
    private String userid;
    private int imgshow;

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UserTempVO [imgpath=" + imgpath + ", userid=" + userid + "]";
    }

    public int getImgshow() {
        return imgshow;
    }

    public void setImgshow(int imgshow) {
        this.imgshow = imgshow;
    }
    
   
    
}