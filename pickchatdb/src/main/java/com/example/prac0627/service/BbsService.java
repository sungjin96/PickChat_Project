package com.example.prac0627.service;


import java.util.List;

import com.example.prac0627.domain.BoardVO;

public interface BbsService {
    public void bbsinsert(BoardVO vo);    
    public List<BoardVO> bbslist();
    public BoardVO bbsread(int bno); 
}