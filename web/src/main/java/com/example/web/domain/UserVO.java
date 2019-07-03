package com.example.web.domain;

import java.sql.Date;
import java.util.Arrays;

public class UserVO {
    private String[] imgpath;
    private int[] typeid;
    private String[] content;
    private int[] hobbyid;
    private String voicepath;
    private int imgshow;
    private String userid;
    private String userheight;
    private String userpassword;
    private String username;
    private String userjob;
    private String usercomment;
    private String userage;
    private String usernickname;
    private String localcode;
    private String localname;
    private Date regdate;
    private String gendername;
    private boolean loginstatus;
    private int userpoint;
    private int gendercode;
    private int reasonid;
    private String soloimg;

    public String[] getImgpath() {
        return imgpath;
    }

    public void setImgpath(String[] imgpath) {
        this.imgpath = imgpath;
    }

    public String getVoicepath() {
        return voicepath;
    }

    public void setVoicepath(String voicepath) {
        this.voicepath = voicepath;
    }

    public int[] getTypeid() {
        return typeid;
    }

    public void setTypeid(int[] typeid) {
        this.typeid = typeid;
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

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
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

    public String getLocalcode() {
        return localcode;
    }

    public void setLocalcode(String localcode) {
        this.localcode = localcode;
    }

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public String getGendername() {
        return gendername;
    }

    public void setGendername(String gendername) {
        this.gendername = gendername;
    }

    public boolean isLoginstatus() {
        return loginstatus;
    }

    public void setLoginstatus(boolean loginstatus) {
        this.loginstatus = loginstatus;
    }

    public int getUserpoint() {
        return userpoint;
    }

    public void setUserpoint(int userpoint) {
        this.userpoint = userpoint;
    }

    public int getGendercode() {
        return gendercode;
    }

    public void setGendercode(int gendercode) {
        this.gendercode = gendercode;
    }

    public int[] getHobbyid() {
        return hobbyid;
    }

    public void setHobbyid(int[] hobbyid) {
        this.hobbyid = hobbyid;
    }

    public int getReasonid() {
        return reasonid;
    }

    public void setReasonid(int reasonid) {
        this.reasonid = reasonid;
    }

    @Override
    public String toString() {
        return "UserVO [content=" + Arrays.toString(content) + ", gendercode=" + gendercode + ", gendername="
                + gendername + ", hobbyid=" + Arrays.toString(hobbyid) + ", imgpath=" + Arrays.toString(imgpath)
                + ", localcode=" + localcode + ", localname=" + localname + ", loginstatus=" + loginstatus
                + ", reasonid=" + reasonid + ", regdate=" + regdate + ", typeid=" + Arrays.toString(typeid)
                + ", userage=" + userage + ", usercomment=" + usercomment + ", userheight=" + userheight + ", userid="
                + userid + ", userjob=" + userjob + ", username=" + username + ", usernickname=" + usernickname
                + ", userpassword=" + userpassword + ", userpoint=" + userpoint + ", voicepath=" + voicepath + "]";
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

    

    
}