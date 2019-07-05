package com.example.prac0627.controller;

import java.util.List;
import com.example.prac0627.persistence.NoticeDAO;
import com.example.prac0627.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class NoticeController {

    @Autowired
    NoticeDAO dao;

    @GetMapping(value = "notice/list")
    public List<NoticeVO> noticelist()throws Exception{
        return dao.notice_list();
    }
    
    @GetMapping(value = "notice/read/{nno}")
    public NoticeVO noticeread(@PathVariable("nno") int nno) throws Exception {
        return dao.notice_read(nno);
    }

    @PostMapping(value = "notice/insert")
    public void noticeinsert(@RequestBody NoticeVO vo) throws Exception {
        dao.notice_insert(vo);
    }

    @GetMapping(value = "notice/updateread/{nno}")
    public NoticeVO noticeupdateread(@PathVariable("nno") int nno) throws Exception {
        return dao.notice_read(nno);
    }

    @PatchMapping(value = "notice/update")
    public void noticeupdate(@RequestBody NoticeVO vo) throws Exception {
        dao.notice_update(vo);
    }

    @DeleteMapping(value = "notice/delete/{nno}")
    public void noticedelete(@PathVariable("nno") int nno) throws Exception {
        dao.notice_delete(nno);
    }

    // ==============================================================================notice-ending
    @GetMapping(value = "question/list")
    public List<NoticeVO> questionlist()throws Exception{
        return dao.question_list();
    }
    
    @GetMapping(value = "question/read/{qno}")
    public NoticeVO questionread(@PathVariable("qno") int qno) throws Exception {
        return dao.question_read(qno);
    }

    @PostMapping(value = "question/insert")
    public void questioninsert(@RequestBody NoticeVO vo) throws Exception {
        dao.question_insert(vo);
    }

    @DeleteMapping(value = "question/delete/{qno}")
    public void questiondelete(@PathVariable("qno") int qno) throws Exception {
        dao.question_delete(qno);
    }
   
    @GetMapping(value = "question/updateread/{qno}")
    public NoticeVO questionupdateread(@PathVariable("qno") int qno) throws Exception {
        return dao.question_read(qno);
    }

    @PatchMapping(value = "question/update")
    public void questionupdate(@RequestBody NoticeVO vo) throws Exception {
        dao.question_update(vo);
    }

}