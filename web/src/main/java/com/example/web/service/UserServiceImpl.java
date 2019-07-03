package com.example.web.service;

import com.example.web.domain.UserVO;
import com.example.web.persistence.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO dao;

    @Transactional
    @Override
    public void insertUser(UserVO vo) throws Exception {
        dao.insertPuser(vo);
        String userid=vo.getUserid();
        dao.insertUserpoint(userid); 
        for(int i=0; i<vo.getImgpath().length; i++){
            String[] imgpath=vo.getImgpath();
            dao.insertImg(userid, imgpath[i],vo.getImgshow());
    }
        dao.insertUserinfo(vo);
    }

   

    
    
}