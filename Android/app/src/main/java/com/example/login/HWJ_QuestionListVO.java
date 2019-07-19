package com.example.login;

import java.util.Date;

public class HWJ_QuestionListVO {
    private int qno;
    private int questiontype;
    private String qwriter;
    private String qtitle;
    private String qcontent;
    private Date qregdate;
    private String qccontent;
    private Date qcregdate;
    private String userid;

    @Override
    public String toString() {
        return "HWJ_QuestionListVO{" +
                "qno=" + qno +
                ", questiontype=" + questiontype +
                ", qwriter='" + qwriter + '\'' +
                ", qtitle='" + qtitle + '\'' +
                ", qcontent='" + qcontent + '\'' +
                ", qregdate=" + qregdate +
                ", qccontent='" + qccontent + '\'' +
                ", qcregdate=" + qcregdate +
                ", userid='" + userid + '\'' +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
}