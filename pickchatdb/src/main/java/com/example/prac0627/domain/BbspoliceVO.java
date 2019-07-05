package com.example.prac0627.domain;

public class BbspoliceVO {
    private int bpno;
    private String sender;
    private int bno;
    private String pcontent;
    private int reasonid;

    
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public int getReasonid() {
        return reasonid;
    }

    public void setReasonid(int reasonid) {
        this.reasonid = reasonid;
    }

    @Override
    public String toString() {
        return "BbspoliceVO [bbspno=" + bpno + ", bno=" + bno + ", pcontent=" + pcontent + ", reasonid=" + reasonid
                + ", sender=" + sender + "]";
    }

    public int getBpno() {
        return bpno;
    }

    public void setBpno(int bpno) {
        this.bpno = bpno;
    }

}