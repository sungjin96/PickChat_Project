package com.example.prac0627.controller;

import java.util.List;

import com.example.prac0627.persistence.ReplyDAO;
import com.example.prac0627.domain.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "board/reply/")
public class ReplyController {

    @Autowired
    ReplyDAO dao;

    @CrossOrigin("*")
    @GetMapping(value = "list/{bno}")
    public List<ReplyVO> list(@PathVariable("bno") int bno) throws Exception {
        return dao.list(bno);
    }

    @CrossOrigin("*")
    @PostMapping(value = "insert")
    public void insert(@RequestBody ReplyVO vo) throws Exception {
        dao.insert(vo);
    }

    @CrossOrigin("*")
    @DeleteMapping(value = "delete/{rno}")
    public void delete(@PathVariable("rno") int rno) throws Exception {
        dao.delete(rno);
    }

    @CrossOrigin("*")
    @GetMapping(value = "updateread/{rno}")
    public ReplyVO updateread(@PathVariable("rno") int rno) throws Exception {
        return dao.read(rno);
    }

    @CrossOrigin("*")
    @PatchMapping(value = "update")
    public void questioncommentupdate(@RequestBody ReplyVO vo) throws Exception {
        dao.update(vo);
    }

    @CrossOrigin("*")
    @GetMapping(value = "count/{bno}")
    public int count(@PathVariable("bno") int bno) throws Exception {
        return dao.count(bno);
    }

}