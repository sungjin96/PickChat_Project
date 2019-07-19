package com.example.persistence;

import java.util.List;

import com.example.domain.NoticeVO;

public interface NoticeDAO {
      
    public List<NoticeVO> notice_list();
    public int notice_count(int nno)throws Exception;
    public NoticeVO notice_read(int nno);
    public void notice_insert(NoticeVO vo);
    public void notice_update(NoticeVO vo);
    public void notice_delete(int nno);   
  
    public List<NoticeVO> question_list(); 
    public List<NoticeVO> question_userlist(String userid);
    public NoticeVO question_read(int qno);
    public void question_insert(NoticeVO vo);
    public void question_delete(int qno);
    public void question_update(NoticeVO vo);    
}