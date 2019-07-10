package com.example.prac0627.controller;

import java.util.List;

import com.example.prac0627.domain.BbspoliceVO;
import com.example.prac0627.persistence.BoardPoliceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping(value = "/board/police/")
public class BoardPoliceController {

    @Autowired
    BoardPoliceDAO pdao;

    @CrossOrigin("*")
    @GetMapping(value = "list")
    public List<BbspoliceVO> list() throws Exception {
        return pdao.list();
    }

    @CrossOrigin("*")
    @GetMapping(value = "read/{bpno}")
    public BbspoliceVO read(@PathVariable("bpno") int bpno) throws Exception {
        return pdao.read(bpno);
    }

    @CrossOrigin("*")
    @PostMapping(value = "insert")
    public void insert(@RequestBody BbspoliceVO vo) throws Exception {
        pdao.insert(vo);
    }

    @CrossOrigin("*")
    @DeleteMapping(value = "delete/{bpno}")
    public void delete(@PathVariable("bpno") int bpno) throws Exception {
        pdao.delete(bpno);
    }

}
