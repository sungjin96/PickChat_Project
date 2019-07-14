package com.example.login;

public class User_listVO {

    private String userid;
    private String username;
    private String userpassword;
    private String usernickname;
    private String localcode;
    private int gendercode;
    private int userage;
    private String userjob;
    private String userheight;
    private String usercomment;
    private int imgshow;

    public int getImgshow() {
        return imgshow;
    }

    public void setImgshow(int imgshow) {
        this.imgshow = imgshow;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }



    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public String getLocalcode() {
        return localcode;
    }

    public void setLocalcode(String localcode) {
        this.localcode = localcode;
    }

    public int getGendercode() {
        return gendercode;
    }

    public void setGendercode(int gendercode) {
        this.gendercode = gendercode;
    }

    public int getUserage() {
        return userage;
    }

    public void setUserage(int userage) {
        this.userage = userage;
    }

    public String getUserjob() {
        return userjob;
    }

    public void setUserjob(String userjob) {
        this.userjob = userjob;
    }

    public String getUserheight() {
        return userheight;
    }

    public void setUserheight(String userheight) {
        this.userheight = userheight;
    }

    public String getUsercomment() {
        return usercomment;
    }

    public void setUsercomment(String usercomment) {
        this.usercomment = usercomment;
    }

    @Override
    public String toString() {
        return "User_listVO{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", usernickname='" + usernickname + '\'' +
                ", localcode='" + localcode + '\'' +
                ", gendercode=" + gendercode +
                ", userage=" + userage +
                ", userjob='" + userjob + '\'' +
                ", userheight='" + userheight + '\'' +
                ", usercomment='" + usercomment + '\'' +
                ", imgshow=" + imgshow +
                '}';
    }
}
