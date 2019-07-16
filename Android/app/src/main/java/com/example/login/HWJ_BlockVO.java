package com.example.login;

public class HWJ_BlockVO {
    int blockno;
    String blocker;
    String blocked;
    String blockedname;

    public int getBlockno() {
        return blockno;
    }

    public void setBlockno(int blockno) {
        this.blockno = blockno;
    }

    public String getBlocker() {
        return blocker;
    }

    public void setBlocker(String blocker) {
        this.blocker = blocker;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public String getBlockedname() {
        return blockedname;
    }

    public void setBlockedname(String blockedname) {
        this.blockedname = blockedname;
    }

    @Override
    public String toString() {
        return "BlockVO{" +
                "blockno=" + blockno +
                ", blocker='" + blocker + '\'' +
                ", blocked='" + blocked + '\'' +
                ", blockedname='" + blockedname + '\'' +
                '}';
    }
}
