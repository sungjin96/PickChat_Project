package com.example.prac0627.controller;

import java.util.List;

// import javax.imageio.ImageIO;

// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.net.URL;
// import java.time.LocalTime;

import com.example.prac0627.persistence.BoardDAO;
import com.example.prac0627.service.BbsService;
import com.example.prac0627.domain.BoardVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping(value = "/board/")
public class BoardController {

    @Autowired
    BoardDAO dao;

    @Autowired
    private BbsService service;

    @CrossOrigin("*")
    @GetMapping(value = "bbslist")
    public List<BoardVO> bbslist() throws Exception {
        return service.bbslist();
    }

    @CrossOrigin("*")
    @GetMapping(value = "bbsimglist")
    public List<BoardVO> bbsimglist() throws Exception {
        return dao.bbsimglist();
    }

    @CrossOrigin("*")
    @GetMapping(value = "bbsread/{bno}")
    public BoardVO bbsread(@PathVariable("bno") int bno) throws Exception {
        return service.bbsread(bno);
    }

    @CrossOrigin("*")
    @GetMapping(value = "bbsupdateread/{bno}")
    public BoardVO bbsupdateread(@PathVariable("bno") int bno) throws Exception {
        return dao.bbsread(bno);
    }

    @CrossOrigin("*")
    @PatchMapping(value = "bbsupdate")
    public void bbsupdate(@RequestBody BoardVO vo) throws Exception {
        dao.bbsupdate(vo);
    }

    @CrossOrigin("*")
    @GetMapping(value = "userbbslist/{userid}")
    public List<BoardVO> userbbslist(@PathVariable("userid") String userid) throws Exception {
        return dao.userbbslist(userid);
    }

    // service-----bbs&bbsimg------------------------------------------------------------------
    @CrossOrigin("*")
    @PostMapping(value = "bbsinsert")
    public void bbsinsert(@RequestBody BoardVO vo) throws Exception {

        try {
            service.bbsinsert(vo);
        } catch (Exception e) {
            System.out.println("서비스 입력 오류" + e.toString());
        }
    }

    @CrossOrigin("*")
    @DeleteMapping(value = "bbsdelete/{bno}")
    public void bbsdelete(@PathVariable("bno") int bno) throws Exception {
        dao.bbsdelete(bno);
    }

    // -------------------------------------------------------------bbslikeStart
    @CrossOrigin("*")
    @GetMapping(value = "bbslikeread/{bno}")
    public List<BoardVO> bbslikeread(@PathVariable("bno") int bno) throws Exception {
        return dao.bbslikeread(bno);
    }

    @CrossOrigin("*")
    @GetMapping(value = "bbslikecount/{bno}")
    public int bbslikecount(@PathVariable("bno") int bno) throws Exception {
        return dao.bbslikecount(bno);
    }

    @CrossOrigin("*")
    @DeleteMapping(value = "bbslikedelete/{bno}/{liker}")
    public void bbslikedelete(@PathVariable("bno") int bno, @PathVariable("liker") String liker) throws Exception {
        dao.bbslikedelete(bno, liker);
    }

    @CrossOrigin("*")
    @PostMapping(value = "bbslikeinsert")
    public void bbslikeinsert(@RequestBody BoardVO vo) throws Exception {
        dao.bbslikeinsert(vo);
    }

    // -------------------------------------------------------------bbstagStart
    @CrossOrigin("*")
    @GetMapping(value = "bbstagread/{tagword}")
    public List<BoardVO> bbstagread(@PathVariable("tagword") String tagword) throws Exception {
        return dao.bbstagread(tagword);
    }

    @CrossOrigin("*")
    @PostMapping(value = "bbstaginsert")
    public void bbstaginsert(@RequestBody BoardVO vo) throws Exception {
        try {
            for (int i = 0; i < vo.getTagword().length; i++) {
                String[] tagword = vo.getTagword();
                dao.bbstaginsert(vo.getBno(), tagword[i]);
            }
        } catch (Exception e) {
            System.out.println("태그 인서트" + e.toString());
        }
    }
}