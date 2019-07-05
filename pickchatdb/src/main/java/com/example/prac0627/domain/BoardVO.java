package com.example.prac0627.domain;

import java.sql.Date;
import java.util.Arrays;

public class BoardVO {
    private int bno;
    private String writer;
    private String title;
    private String content;
    private String imgpath;
    private Date regdate;
    private Date updatedate;
    private int blno;
    private String liker;
    private int tno;
    private int tagcode;
    private String[] tagword;

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public int getTno() {
        return tno;
    }

    public void setTno(int tno) {
        this.tno = tno;
    }

    public int getTagcode() {
        return tagcode;
    }

    public void setTagcode(int tagcode) {
        this.tagcode = tagcode;
    }

    public String[] getTagword() {
        return tagword;
    }

    public void setTagword(String[] tagword) {
        this.tagword = tagword;
    }

    @Override
    public String toString() {
        return "BoardVO [blno=" + blno + ", bno=" + bno + ", content=" + content + ", imgpath=" + imgpath + ", liker="
                + liker + ", regdate=" + regdate + ", tagcode=" + tagcode + ", tagword=" + Arrays.toString(tagword)
                + ", title=" + title + ", tno=" + tno + ", updatedate=" + updatedate + ", writer=" + writer + "]";
    }

}