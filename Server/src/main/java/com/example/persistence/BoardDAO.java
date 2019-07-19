package com.example.persistence;

import java.util.List;
import com.example.domain.BoardVO;

public interface BoardDAO {
      
    public List<BoardVO> bbslist();
    public List<BoardVO> bbsimglist();
    public int bbscount(int bno);
    public BoardVO bbsread(int bno);
    public void bbsinsert(BoardVO vo);
    public void bbsdelete(int bno);
    public void bbsupdate(BoardVO vo);
    public int bbsmax();
    public List<BoardVO> userbbslist(String userid);

    public List<BoardVO> bbslikeread(int bno);
    public int bbslikestate(int bno, String liker);
    public int bbslikecount(int bno);
    
    public void bbslikedelete(int bno, String liker);
    public void bbsdeletelikedelete(int bno);
    public void bbslikeinsert(BoardVO vo);

    public List<String> bnotagread(int bno);
    public List<BoardVO> bbstagread(String tagword);
    public void bbstaginsert(int bno, String tagword);
    public void bbstagdelete(int bno);

}