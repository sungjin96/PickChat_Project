package com.example.persistence;

import java.util.List;

import com.example.domain.ReplyVO;

public interface ReplyDAO {
    public List<ReplyVO> list(int bno);
    public ReplyVO read(int rno);
    public void insert(ReplyVO vo);
    public void delete(int rno, int bno);
    public void update(ReplyVO vo);
    public int count(int bno);
}