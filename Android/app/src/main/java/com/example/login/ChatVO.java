package com.example.login;

public class ChatVO {
    private String content;
    private String userid;
    private String wdate;

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

    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }



    @Override
    public String toString() {
        return "ChatVO{" +
                "content='" + content + '\'' +
                ", userid='" + userid + '\'' +
                ", wdate='" + wdate + '\'' +

                '}';
    }
}
