package com.example.login;

public class JHJ_AppointmentVO {
    private String userid;
    private String latitude;
    private String longitude;
    private String  apaddress;
    private String apdate;
    private String aptime;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getApaddress() {
        return apaddress;
    }

    public void setApaddress(String apaddress) {
        this.apaddress = apaddress;
    }

    public String getApdate() {
        return apdate;
    }

    public void setApdate(String apdate) {
        this.apdate = apdate;
    }

    public String getAptime() {
        return aptime;
    }

    public void setAptime(String aptime) {
        this.aptime = aptime;
    }

    @Override
    public String toString() {
        return "JHJ_AppointmentVO{" +
                "userid='" + userid + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", apaddress='" + apaddress + '\'' +
                ", apdate='" + apdate + '\'' +
                ", aptime='" + aptime + '\'' +
                '}';
    }
}
