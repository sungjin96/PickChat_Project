package com.example.domain;

import java.sql.Date;

public class ReplyVO {
    private int rno;
    private int bno;
    private String writer;
    private String content;
    private Date regdate;
    private Date updatedate;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
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
	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", updatedate=" + updatedate + "]";
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	} 

    
}