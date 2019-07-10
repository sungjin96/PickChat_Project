package com.example.web.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.web.domain.BlockuserVO;
import com.example.web.domain.CategoryVO;
import com.example.web.domain.HobbyVO;
import com.example.web.domain.LikeTypeVO;
import com.example.web.domain.LikepeopleVO;
import com.example.web.domain.MytypeVO;
import com.example.web.domain.UserImageVO;
import com.example.web.domain.UserVO;
import com.example.web.persistence.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping(value = { "/user/*" })
public class UserController {
  
  @Autowired
  private UserDAO dao;

  

  // 유저 리스트
  @CrossOrigin("*")
  @GetMapping(value = "list_user")
  public List<UserVO> getListUser() throws Exception {
    return dao.listUser();
  }

  //한 사람에 대한 리스트 읽기
  @CrossOrigin("*")
  @GetMapping(value = "read/{userid}")
  public UserVO oneUserRead(@PathVariable String userid)throws Exception{
    return dao.readUser(userid);
  }
  
  // 유저 사진 리스트
  @CrossOrigin("*")
  @GetMapping(value = "list_img/{userid}")
  public List<UserImageVO> getImageList(@PathVariable String userid) throws Exception {
    return dao.listUserImage(userid);
  }

  // 유저 매력 리스트
  @CrossOrigin("*")
  @GetMapping(value = "list_mytype/{userid}")
  public List<MytypeVO> userinfoList(@PathVariable String userid) throws Exception {
    return dao.listMytype(userid);
  }

  // 유저 취미 리스트
  @CrossOrigin("*")
  @GetMapping(value = "list_userhobby/{userid}")
  public List<HobbyVO> userHobbyList(@PathVariable String userid) throws Exception {
    return dao.listHobby(userid);
  }

  // 유저 이상형 리스트
  @CrossOrigin("*")
  @GetMapping(value = "list_liketype/{userid}")
  public List<LikeTypeVO> userLikeType(@PathVariable String userid) throws Exception {
    return dao.listLiketype(userid);
  }

  //카테고리 리스트
  @CrossOrigin("*")
  @GetMapping(value = "list_category")
  public List<CategoryVO> listCategory()throws Exception{
    return dao.listCategory();
  }
  //취미 카테고리 리스트
  @CrossOrigin("*")
  @GetMapping(value = "list_hobbycategory")
  public List<CategoryVO> listhobbyCategory()throws Exception{
    return dao.listHobbyCategory();
  }

  //좋아요 테이블 리스트-내가 좋아요 한 사람 리스트
  @CrossOrigin("*")
  @GetMapping(value = "list_likesender/{userid}")
  public List<LikepeopleVO> userLikesender(@PathVariable String userid)throws Exception{
      return dao.listLikesender(userid);
  }

  
  //내가 좋아요 한 사람 프로필 리스트
  @CrossOrigin("*")
  @GetMapping(value= "user_sender_profile/{userid}")
  public List<UserVO> likesenderlist(@PathVariable String userid) throws Exception{
    List<LikepeopleVO> array = dao.listLikesender(userid);
    ArrayList<UserVO> array2 = new ArrayList<>();
    for(int i =0; i<array.size();i++){
      UserVO vo = dao.readUser(array.get(i).getReceiver());
          //System.out.println("...................."+vo.toString());
          array2.add(vo);
        }
  return array2;
} 
  //좋아요 테이블 리스트-나를 좋아요 한 사람 리스트
  @CrossOrigin("*")
  @GetMapping(value = "list_likereceiver/{userid}")
  public List<LikepeopleVO> userLikereceiver(@PathVariable String userid)throws Exception{
      return dao.listLikereceiver(userid);
  }

  //나를 좋아요 한 사람의 프로필 리스트
  @CrossOrigin("*")
  @GetMapping(value= "user_receiver_profile/{userid}")
  public List<UserVO> likereceiverlist(@PathVariable String userid) throws Exception{
    List<LikepeopleVO> array = dao.listLikereceiver(userid);
    ArrayList<UserVO> array2 = new ArrayList<>();
    for(int i =0; i<array.size();i++){
      UserVO vo = dao.readUser(array.get(i).getSender());
          //System.out.println("...................."+vo.toString());
          array2.add(vo);
        }
  return array2;
} 

  // 유저 아이디 중복확인
  @CrossOrigin("*")
  @GetMapping(value = "checkid/{userid}")
  public int checkid(@PathVariable String userid) throws Exception {
    return dao.checkid(userid);
  }

  // 유저 닉네임 중복확인
  @CrossOrigin("*")
  @GetMapping(value = "checknickname/{usernickname}")
  public int checknickname(@PathVariable String usernickname) throws Exception {
    return dao.checknickname(usernickname);
  }

  //차단 유저리스트
  @CrossOrigin("*")
  @GetMapping(value = "list_blockuser")
  public List<BlockuserVO> listblockuser()throws Exception{
    return dao.listBlockUser();
  }
}