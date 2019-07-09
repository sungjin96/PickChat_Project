package com.example.prac0627.service;

import java.util.List;

import com.example.prac0627.domain.BoardVO;
import com.example.prac0627.persistence.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BbsServiceImpl implements BbsService {

    @Autowired
    BoardDAO dao;

    @Transactional

    @Override
    public void bbsinsert(BoardVO vo) {
        dao.bbsinsert(vo);
        int bno = dao.bbsmax();
        String[] tagword = vo.getTagword();
        for (int i = 0; i < tagword.length; i++) {
            dao.bbstaginsert(bno, tagword[i]);
        }
    }

    @Override
    public List<BoardVO> bbslist() {
        List<BoardVO> list = dao.bbslist();
        for (int i = 0; i < list.size(); i++) {
            List<String> tagwords = dao.bnotagread(list.get(i).getBno());
            list.get(i).setTagword(tagwords.toArray((new String[tagwords.size()])));
        }
        return list;
    }

    @Override
    public BoardVO bbsread(int bno) {
        BoardVO read = dao.bbsread(bno);
        List<String> tagwords = dao.bnotagread(bno);
        read.setTagword(tagwords.toArray((new String[tagwords.size()])));
        return read;
    }  
    

}
