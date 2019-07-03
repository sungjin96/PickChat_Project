package com.example.web.domain;

import java.sql.Date;

public class LikepeopleVO {
    private int pno;
    private String sender;
    private String receiver;
    private Date regdate;

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "Likepeople [pno=" + pno + ", receiver=" + receiver + ", regdate=" + regdate + ", sender=" + sender
                + "]";
    }
    

}