package com.example.web.service;



import com.example.web.domain.UserVO;
import com.example.web.persistence.InsertDAO;
import com.example.web.persistence.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO dao;

    @Autowired
    InsertDAO idao;

    @Transactional
    @Override
    public void insertUser(UserVO vo) throws Exception {
        idao.insertPuser(vo);
        String userid = vo.getUserid();
        idao.insertUserpoint(userid);
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