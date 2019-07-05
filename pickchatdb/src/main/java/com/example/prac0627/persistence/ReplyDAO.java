package com.example.prac0627.persistence;
import java.util.List;

import com.example.prac0627.domain.ReplyVO;

public interface ReplyDAO {
    public List<ReplyVO> list(int bno);
    public ReplyVO read(int rno);
    public void insert(ReplyVO vo);
    public void delete(int rno);
    public void update(ReplyVO vo);
    public int count(int bno);
    
}