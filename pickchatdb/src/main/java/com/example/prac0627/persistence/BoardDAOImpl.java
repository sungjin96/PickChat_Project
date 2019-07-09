package com.example.prac0627.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.prac0627.domain.BoardVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    SqlSession session;
    String namespace = "boardMapper";

    @Override
    public List<BoardVO> bbslist() {
        return session.selectList(namespace + ".bbslist");
    }

    @Override
    public List<BoardVO> bbsimglist() {
        return session.selectList(namespace + ".bbsimglist");
    }

    @Override
    public BoardVO bbsread(int bno) {
        return session.selectOne(namespace + ".bbsread", bno);
    }

    @Override
    public void bbsinsert(BoardVO vo) {
        session.insert(namespace + ".bbsinsert", vo);
    }

    @Override
    public void bbsdelete(int bno) {
        session.delete(namespace + ".bbsdelete", bno);
    }

    @Override
    public void bbsupdate(BoardVO vo) {
        session.update(namespace + ".bbsupdate", vo);
    }

    @Override
    public int bbscount(int bno) {
        return session.selectOne(namespace + ".bbscount", bno);
    }

    @Override
    public List<BoardVO> userbbslist(String writer) {
        return session.selectList(namespace + ".userbbslist");
    }

    // ----------------------------------------bbslikeStart
    @Override
    public List<BoardVO> bbslikeread(int bno) {
        return session.selectList(namespace + ".bbslikeread", bno);
    }

    @Override
    public int bbslikecount(int bno) {
        return session.selectOne(namespace + ".bbslikecount", bno);
    }

    @Override
    public void bbslikedelete(int bno, String liker) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("bno", bno);
        map.put("liker", liker);
        session.delete(namespace + ".bbslikedelete", map);
    }

    @Override
    public void bbslikeinsert(BoardVO vo) {
        session.insert(namespace + ".bbslikeinsert", vo);
    }
    // -----------------------------------------------bbstagStart

    @Override
    public List<BoardVO> bbstagread(String tagword) {
        return session.selectList(namespace + ".bbstagread", tagword);
    }

    @Override
    public void bbstaginsert(int bno, String tagword) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("bno", bno);
        map.put("tagword", tagword);
        session.insert(namespace + ".bbstaginsert", map);
    }

    @Override
    public int bbsmax() {
        return session.selectOne(namespace+".bbsmax");
    }

    @Override
    public List<String> bnotagread(int bno) {
        return session.selectList(namespace+".bnotagread",bno);
    }

   
}