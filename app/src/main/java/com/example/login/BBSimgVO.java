package com.example.login;

import java.io.Serializable;
import java.util.Date;

public class BBSimgVO implements Serializable {
    private int bno;//게시판 번호
    private String title;
    private String writer;
    private String content;
    private String imgpath;//게시판 이미지 경로
    private Date regdate;
    private Date updatedate;
    private int blno;
    private String liker;
    private int tno;
    private String[] tagword;
    private String soloimg;

    public String getSoloimg() {
        return soloimg;
    }

    public void setSoloimg(String soloimg) {
        this.soloimg = soloimg;
    }

    public String[] getTagword() {
        return tagword;
    }

    public void setTagword(String[] tagword) {
        this.tagword = tagword;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public int getBlno() {
        return blno;
    }

    public void setBlno(int blno) {
        this.blno = blno;
    }

    public String getLiker() {
        return liker;
    }

    public void setLiker(String liker) {
        this.liker = liker;
    }

    public int getTno() {
        return tno;
    }

    public void setTno(int tno) {
        this.tno = tno;
    }



}
