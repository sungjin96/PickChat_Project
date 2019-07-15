package com.example.domain;

public class BlockuserVO{
    private int blockno;
    private String blocker;
    private String blocked;
    private String blockedname;

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
        return "BlockuserVO [blocked=" + blocked + ", blockedname=" + blockedname + ", blocker=" + blocker
                + ", blockno=" + blockno + "]";
    }
    
}