package com.example.persistence;


import java.util.List;

import com.example.domain.BbspoliceVO;

public interface BoardPoliceDAO {
    public List<BbspoliceVO> list() throws Exception;
    public BbspoliceVO read(int bpno) throws Exception;
    public void insert(BbspoliceVO vo) throws Exception;
    public void delete(int bpno) throws Exception;
}