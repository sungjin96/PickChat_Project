package com.example.prac0627.persistence;

import java.util.List;

import com.example.prac0627.domain.BbspoliceVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardPoliceDAOImpl implements BoardPoliceDAO {

    @Autowired
    SqlSession session;
    String namespace = "boardpoliceMapper";

    @Override
    public List<BbspoliceVO> list() {
        return session.selectList(namespace + ".list");
    }

    @Override
    public BbspoliceVO read(int bpno) throws Exception {
        return session.selectOne(namespace + ".read", bpno);
    }

    @Override
    public void insert(BbspoliceVO vo) throws Exception {
        session.insert(namespace + ".insert", vo);
    }

    @Override
    public void delete(int bpno) throws Exception {
        session.delete(namespace + ".delete", bpno);
    }

}