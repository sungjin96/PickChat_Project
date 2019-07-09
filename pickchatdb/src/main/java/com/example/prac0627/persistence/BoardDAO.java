package com.example.prac0627.persistence;

import java.util.List;

import com.example.prac0627.domain.BoardVO;


public interface BoardDAO {
      
    public List<BoardVO> bbslist();
    public List<BoardVO> bbsimglist();
    public int bbscount(int bno);
    public BoardVO bbsread(int bno);
    public void bbsinsert(BoardVO vo);
    public void bbsdelete(int bno);
    public void bbsupdate(BoardVO vo);   
    public int bbsmax();
    public List<BoardVO> userbbslist(String writer);

    public List<BoardVO> bbslikeread(int bno);
    public int bbslikecount(int bno);
    public void bbslikedelete(int bno, String liker);
    public void bbslikeinsert(BoardVO vo);

    public List<String> bnotagread(int bno);
    public List<BoardVO> bbstagread(String tagword);
    public void bbstaginsert(int bno, String tagword); 
    

}