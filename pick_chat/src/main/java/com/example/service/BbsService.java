package com.example.service;


import java.util.List;

import com.example.domain.BoardVO;

public interface BbsService {
    public void bbsinsert(BoardVO vo);    
    public List<BoardVO> bbslist();
    public BoardVO bbsread(int bno); 
}