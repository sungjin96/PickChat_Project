package com.example.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.UserVO;
import com.example.persistence.InsertDAO;
import com.example.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    UserDAO dao;

    @Inject
    InsertDAO idao;

    @Transactional
    @Override
    public void insertUser(UserVO vo) throws Exception {
        idao.insertPuser(vo);
        String userid = vo.getUserid();
        int userpoint = vo.getUserpoint();
        idao.insertPoint(userid);
        for (int i = 0; i < vo.getImgpath().length; i++) {
            String[] imgpath = vo.getImgpath();
            idao.insertImg(userid, imgpath[i], vo.getImgshow());
        }
        idao.insertUserinfo(vo);
    }

    // @Override
    // public List<UserVO> listUser() throws Exception { 
    //     List<DistinctVO> array=dao.distinctUser();
    //     ArrayList<UserVO> array2=new ArrayList<UserVO>();
    //     for(int i=0; i<array.size();i++){
    //         UserVO vo = dao.readUser(array.get(i).getUserid());
    //         array2.add(vo);
    //     }
    //     return array2;
    // }

   

    
    
}