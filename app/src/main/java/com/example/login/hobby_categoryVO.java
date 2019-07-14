package com.example.login;

public class hobby_categoryVO {
    private int hobbyid;
    private String content;
    private boolean check;

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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "hobby_categoryVO{" +
                "hobbyid=" + hobbyid +
                ", content='" + content + '\'' +
                ", check=" + check +
                '}';
    }
}
