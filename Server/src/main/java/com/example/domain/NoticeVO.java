package com.example.domain;

import java.sql.Date;

public class NoticeVO {
	private int nno;
	private String title;
	private String content;
	private Date regdate;
	private int qno;
	private int questiontype;
	private String qwriter;
	private String qtitle;
	private String qcontent;
	private Date qregdate;
	private String qccontent;
	private Date qcregdate;
	private String usernickname;
	private String userid;

	public String getUsernickname() {
		return usernickname;
	}

	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
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

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public int getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(int questiontype) {
		this.questiontype = questiontype;
	}

	public String getQwriter() {
		return qwriter;
	}

	public void setQwriter(String qwriter) {
		this.qwriter = qwriter;
	}

	public String getQtitle() {
		return qtitle;
	}

	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}

	public String getQcontent() {
		return qcontent;
	}

	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}

	public Date getQregdate() {
		return qregdate;
	}

	public void setQregdate(Date qregdate) {
		this.qregdate = qregdate;
	}

	public String getQccontent() {
		return qccontent;
	}

	public void setQccontent(String qccontent) {
		this.qccontent = qccontent;
	}

	public Date getQcregdate() {
		return qcregdate;
	}

	public void setQcregdate(Date qcregdate) {
		this.qcregdate = qcregdate;
	}

	@Override
	public String toString() {
		return "NoticeVO [nno=" + nno + ", title=" + title + ", content=" + content + ", regdate=" + regdate + ", qno="
				+ qno + ", questiontype=" + questiontype + ", qwriter=" + qwriter + ", qtitle=" + qtitle + ", qcontent="
				+ qcontent + ", qregdate=" + qregdate + ", qccontent=" + qccontent + ", qcregdate=" + qcregdate
				+ ", usernickname=" + usernickname + ", userid=" + userid + "]";
	}

}