package com.example.web.persistence;

import java.util.List;

import com.example.web.domain.CategoryVO;
import com.example.web.domain.HobbyVO;
import com.example.web.domain.LikeTypeVO;
import com.example.web.domain.LikepeopleVO;
import com.example.web.domain.MytypeVO;
import com.example.web.domain.UserImageVO;
import com.example.web.domain.UserVO;

public interface UserDAO {
	public List<UserVO> listUser()throws Exception;
	public List<UserImageVO> listUserImage(String userid)throws Exception;
	public List<MytypeVO> listMytype(String userid)throws Exception;
	public List<HobbyVO> listHobby(String userid)throws Exception;
	public List<LikeTypeVO> listLiketype(String userid)throws Exception;
	public List<LikepeopleVO> listLikesender(String userid)throws Exception;
	public List<LikepeopleVO> listLikereceiver(String userid)throws Exception;
	public List<CategoryVO> listCategory()throws Exception;
	public List<CategoryVO> listHobbyCategory()throws Exception;
	public void insertImg(String userid,String imgpath,int imgshow)throws Exception;
	public int checkid(String userid)throws Exception;
	public int checknickname(String usernickname)throws Exception;
	public void insertPuser(UserVO vo)throws Exception;
	public void insertUserpoint(String userid)throws Exception;
	public void insertMytype(String userid,int typeid)throws Exception;
	public void insertLiketype(String userid, int typeid)throws Exception;
	public void insertUserinfo(UserVO vo)throws Exception;
	public void insertUserHobby(String userid,int hobbyid)throws Exception;
	public void insertUserVoice(String userid, String voicepath)throws Exception;
	
	
}