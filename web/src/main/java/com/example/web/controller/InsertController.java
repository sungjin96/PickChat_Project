package com.example.web.controller;

import com.example.web.domain.LikepeopleVO;
import com.example.web.domain.UserVO;
import com.example.web.persistence.InsertDAO;
import com.example.web.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/user/*" })
public class InsertController {

  @Autowired
  private InsertDAO idao;

  @Autowired
  private UserService service;
  
  // 내 매력 입력
  @PostMapping(value = "insert_mytype")
  public void insertMytype(@RequestBody UserVO vo) throws Exception {
    try {
      if (vo.getTypeid() != null) {
        for (int i = 0; i < vo.getTypeid().length; i++) {
          int[] typeid = vo.getTypeid();
          idao.insertMytype(vo.getUserid(), typeid[i]);
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
          idao.insertLiketype(vo.getUserid(), typeid[i]);
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
          idao.insertUserHobby(vo.getUserid(), hobbyid[i]);
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
        idao.insertUserVoice(vo.getUserid(), vo.getVoicepath());
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

  //좋아요 입력
  @PostMapping(value="insert_likepeople")
  public void insertLikepeople(@RequestBody LikepeopleVO vo)throws Exception {
    try{
      idao.insertLikePeople(vo.getSender(), vo.getReceiver());
    }catch(Exception e){
      System.out.println("좋아요 입력 오류" + e.toString());
    }
  }
  
}

  