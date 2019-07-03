package com.example.web.controller;

import java.util.List;

import com.example.web.domain.CategoryVO;
import com.example.web.domain.HobbyVO;
import com.example.web.domain.LikeTypeVO;
import com.example.web.domain.LikepeopleVO;
import com.example.web.domain.MytypeVO;
import com.example.web.domain.UserImageVO;
import com.example.web.domain.UserVO;
import com.example.web.persistence.UserDAO;
import com.example.web.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/user/*" })
public class UserController {

  @Autowired
  private UserDAO dao;

  @Autowired
  private UserService service;

  // 유저 리스트
  @GetMapping(value = "list_user")
  public List<UserVO> getListUser() throws Exception {
    return dao.listUser();
  }

  // 유저 사진 리스트
  @GetMapping(value = "list_img/{userid}")
  public List<UserImageVO> getImageList(@PathVariable String userid) throws Exception {
    return dao.listUserImage(userid);
  }

  // 유저 매력 리스트
  @GetMapping(value = "list_mytype/{userid}")
  public List<MytypeVO> userinfoList(@PathVariable String userid) throws Exception {
    return dao.listMytype(userid);
  }

  // 유저 취미 리스트
  @GetMapping(value = "list_userhobby/{userid}")
  public List<HobbyVO> userHobbyList(@PathVariable String userid) throws Exception {
    return dao.listHobby(userid);
  }

  // 유저 이상형 리스트
  @GetMapping(value = "list_liketype/{userid}")
  public List<LikeTypeVO> userLikeType(@PathVariable String userid) throws Exception {
    return dao.listLiketype(userid);
  }

  //카테고리 리스트
  @GetMapping(value = "list_category")
  public List<CategoryVO> listCategory()throws Exception{
    return dao.listCategory();
  }
  //취미 카테고리 리스트
  @GetMapping(value = "list_hobbycategory")
  public List<CategoryVO> listhobbyCategory()throws Exception{
    return dao.listHobbyCategory();
  }
  //좋아요 테이블 리스트-내가 좋아요 한 사람 리스트
  @GetMapping(value = "list_likesender/{userid}")
  public List<LikepeopleVO> userLikesender(@PathVariable String userid)throws Exception{
      return dao.listLikesender(userid);
  }
  //좋아요 테이블 리스트-나를 좋아요 한 사람 리스트
  @GetMapping(value = "list_likereceiver/{userid}")
  public List<LikepeopleVO> userLikereceiver(@PathVariable String userid)throws Exception{
      return dao.listLikereceiver(userid);
  }
  // 유저 아이디 중복확인
  @GetMapping(value = "checkid/{userid}")
  public int checkid(@PathVariable String userid) throws Exception {
    return dao.checkid(userid);
  }

  // 유저 닉네임 중복확인
  @GetMapping(value = "checknickname/{usernickname}")
  public int checknickname(@PathVariable String usernickname) throws Exception {
    return dao.checknickname(usernickname);
  }

  // 내 매력 입력
  @PostMapping(value = "insert_mytype")
  public void insertMytype(@RequestBody UserVO vo) throws Exception {
    try {
      if (vo.getTypeid() != null) {
        for (int i = 0; i < vo.getTypeid().length; i++) {
          int[] typeid = vo.getTypeid();
          dao.insertMytype(vo.getUserid(), typeid[i]);
        }
      }
    } catch (Exception e) {
      System.out.println("이상형 입력 오류" + e.toString());
    }
  }

  // 이상형 입력
  @PostMapping(value = "insert_liketype")
  public void insertLiketype(@RequestBody UserVO vo) throws Exception {
    try {
      if (vo.getTypeid() != null) {
        for (int i = 0; i < vo.getTypeid().length; i++) {
          int[] typeid = vo.getTypeid();
          dao.insertLiketype(vo.getUserid(), typeid[i]);
        }
      }
    } catch (Exception e) {
      System.out.println("이상형 입력 오류" + e.toString());
    }
  }

  // 유저 취미 입력
  @PostMapping(value = "insert_userhobby")
  public void insertHobby(@RequestBody UserVO vo) throws Exception {
    try {
      if (vo.getHobbyid() != null) {
        for (int i = 0; i < vo.getHobbyid().length; i++) {
          int[] hobbyid = vo.getHobbyid();
          dao.insertUserHobby(vo.getUserid(), hobbyid[i]);
        }
      }
    } catch (Exception e) {
      System.out.println("취미 입력 오류" + e.toString());
    }
  }

  // 유저 보이스
  @PostMapping(value = "insert_uservoice")
  public void insertVoice(@RequestBody UserVO vo) throws Exception {
    try {
      if (vo.getVoicepath() != null) {
        dao.insertUserVoice(vo.getUserid(), vo.getVoicepath());
      }
    } catch (Exception e) {
      System.out.println("목소리 입력 오류" + e.toString());
    }
  }

  // 유저 회원가입
  @PostMapping(value = "insert_puser")
  public void insertPuser(@RequestBody UserVO vo) throws Exception {
    // 기본정보, 포인트, 이미지
    try {
      service.insertUser(vo);
    } catch (Exception e) {
      System.out.println("서비스 입력 오류" + e.toString());
    }

  }

}