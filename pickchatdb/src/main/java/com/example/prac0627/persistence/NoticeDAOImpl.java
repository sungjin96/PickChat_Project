package com.example.prac0627.persistence;

import java.util.List;

import com.example.prac0627.domain.NoticeVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

    @Autowired
    SqlSession session;
    String namespace = "noticeMapper";

    @Override
    public List<NoticeVO> notice_list() {
        return session.selectList(namespace + ".notice_list");
    }

    @Override
    public NoticeVO notice_read(int nno) {
        return session.selectOne(namespace + ".notice_read", nno);
    }

    @Override
    public void notice_update(NoticeVO vo) {
        session.update(namespace + ".notice_update", vo);
    }

    @Override
    public void notice_insert(NoticeVO vo) {
        session.insert(namespace + ".notice_insert", vo);
    }

    @Override
    public void notice_delete(int nno) {
        session.delete(namespace + ".notice_delete", nno);
    }

    @Override
    public int notice_count(int nno) throws Exception {
        return session.selectOne(namespace + ".notice_count", nno);
    }

    @Override
    public List<NoticeVO> question_list() {
        return session.selectList(namespace + ".question_list");
    }

    @Override
    public NoticeVO question_read(int qno) {
        return session.selectOne(namespace + ".question_read", qno);
    }

    @Override
    public void question_insert(NoticeVO vo) {
        session.insert(namespace + ".question_insert", vo);
    }

    @Override
    public void question_delete(int qno) {
        session.delete(namespace + ".question_delete", qno);
    }

    @Override
    public void question_update(NoticeVO vo) {
        session.update(namespace + ".question_update", vo);
    }

}