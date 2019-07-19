package com.example.login;

public class BBSPoliceVO {
    private int bno;
    private int bpno;
    private String sender;
    private String pcontent;
    private int reasonid;
    private String rpcontent;

    public String getRpcontent() {
        return rpcontent;
    }

    public void setRpcontent(String rpcontent) {
        this.rpcontent = rpcontent;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public int getBpno() {
        return bpno;
    }

    public void setBpno(int bpno) {
        this.bpno = bpno;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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
}
