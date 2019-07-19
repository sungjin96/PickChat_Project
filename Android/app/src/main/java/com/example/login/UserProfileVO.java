package com.example.login;

import android.os.Parcel;
import android.os.Parcelable;

public class UserProfileVO implements Parcelable {
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

    protected  UserProfileVO(){

    }
    protected UserProfileVO(Parcel in) {
        imgshow = in.readInt();
        soloimg = in.readString();
        userid = in.readString();
        userheight = in.readString();
        username = in.readString();
        userjob = in.readString();
        usercomment = in.readString();
        userage = in.readString();
        usernickname = in.readString();
        localname = in.readString();
        localcode = in.readString();
        gendername = in.readString();
        gendercode = in.readString();
        userpoint = in.readInt();
    }

    public static final Creator<UserProfileVO> CREATOR = new Creator<UserProfileVO>() {
        @Override
        public UserProfileVO createFromParcel(Parcel in) {
            return new UserProfileVO(in);
        }

        @Override
        public UserProfileVO[] newArray(int size) {
            return new UserProfileVO[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgshow);
        dest.writeString(soloimg);
        dest.writeString(userid);
        dest.writeString(userheight);
        dest.writeString(username);
        dest.writeString(userjob);
        dest.writeString(usercomment);
        dest.writeString(userage);
        dest.writeString(usernickname);
        dest.writeString(localname);
        dest.writeString(localcode);
        dest.writeString(gendername);
        dest.writeString(gendercode);
        dest.writeInt(userpoint);
    }
}
