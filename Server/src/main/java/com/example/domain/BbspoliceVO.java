package com.example.domain;

public class BbspoliceVO {
	private int bpno;
	private String sender;
	private int bno;
	private String pcontent;
	private String rpcontent;
	private int reasonid;

	public String getRpcontent() {
		return rpcontent;
	}

	public void setRpcontent(String rpcontent) {
		this.rpcontent = rpcontent;
	}

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

	public int getBpno() {
		return bpno;
	}

	public void setBpno(int bpno) {
		this.bpno = bpno;
	}

	@Override
	public String toString() {
		return "BbspoliceVO [bpno=" + bpno + ", sender=" + sender + ", bno=" + bno + ", pcontent=" + pcontent
				+ ", rpcontent=" + rpcontent + ", reasonid=" + reasonid + "]";
	}

}