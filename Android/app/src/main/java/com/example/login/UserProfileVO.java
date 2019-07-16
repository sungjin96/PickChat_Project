package com.example.login;

public class UserProfileVO {
    private String soloimg;
    private String userid;
    private String username;
    private String userage;
    private String usernickname;
    private String localname;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "UserVO{" +
                "soloimg='" + soloimg + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", userage='" + userage + '\'' +
                ", usernickname='" + usernickname + '\'' +
                ", localname='" + localname + '\'' +
                '}';
    }
}
