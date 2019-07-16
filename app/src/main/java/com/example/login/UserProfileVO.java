package com.example.login;

public class UserProfileVO {
    private int imgshow;
    private String soloimg;
    private String userid;
    private String userheight;
    private String username;
    private String userjob;
    private String usercomment;
    private String userage;
    private String usernickname;
    private String localname;
    private String localcode;
    private String gendername;
    private String gendercode;
    private int userpoint;

    public int getUserpoint() {
        return userpoint;
    }

    public void setUserpoint(int userpoint) {
        this.userpoint = userpoint;
    }

    public int getImgshow() {
        return imgshow;
    }

    public void setImgshow(int imgshow) {
        this.imgshow = imgshow;
    }

    public String getSoloimg() {
        return soloimg;
    }

    public void setSoloimg(String soloimg) {
        this.soloimg = soloimg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserheight() {
        return userheight;
    }

    public void setUserheight(String userheight) {
        this.userheight = userheight;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserjob() {
        return userjob;
    }

    public void setUserjob(String userjob) {
        this.userjob = userjob;
    }

    public String getUsercomment() {
        return usercomment;
    }

    public void setUsercomment(String usercomment) {
        this.usercomment = usercomment;
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage;
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname;
    }

    public String getLocalcode() {
        return localcode;
    }

    public void setLocalcode(String localcode) {
        this.localcode = localcode;
    }

    public String getGendername() {
        return gendername;
    }

    public void setGendername(String gendername) {
        this.gendername = gendername;
    }

    public String getGendercode() {
        return gendercode;
    }

    public void setGendercode(String gendercode) {
        this.gendercode = gendercode;
    }

    @Override
    public String toString() {
        return "UserProfileVO{" +
                "imgshow=" + imgshow +
                ", soloimg='" + soloimg + '\'' +
                ", userid='" + userid + '\'' +
                ", userheight='" + userheight + '\'' +
                ", username='" + username + '\'' +
                ", userjob='" + userjob + '\'' +
                ", usercomment='" + usercomment + '\'' +
                ", userage='" + userage + '\'' +
                ", usernickname='" + usernickname + '\'' +
                ", localname='" + localname + '\'' +
                ", localcode='" + localcode + '\'' +
                ", gendername='" + gendername + '\'' +
                ", gendercode='" + gendercode + '\'' +
                ", userpoint=" + userpoint +
                '}';
    }
}
