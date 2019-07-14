package com.example.login;

public class type_categoryVO {
    private int typeid;
    private String content;
    private boolean check;

    public boolean isCheck() { return check; }

    public void setCheck(boolean check) { this.check = check; }

    public int getTypeid() {
        return typeid;
    }

    public String getContent() {
        return content;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "type_categoryVO{" +
                "typeid=" + typeid +
                ", content='" + content + '\'' +
                '}';
    }
}
