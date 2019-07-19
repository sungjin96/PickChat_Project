package com.example.login;

public class JHJ_FireBaseChatVO {
    private String content;
    private String email;
    private String wdate;
    private String token;
    private String userName;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", email='" + email + '\'' +
                ", wdate='" + wdate + '\'' +
                '}';
    }
}
