package com.example.persistence;

import java.util.List;

import com.example.domain.BlockuserVO;
import com.example.domain.CategoryVO;
import com.example.domain.HobbyVO;
import com.example.domain.LikeTypeVO;
import com.example.domain.LikepeopleVO;
import com.example.domain.MytypeVO;
import com.example.domain.UserImageVO;
import com.example.domain.UserVO;

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
	public UserVO readUser(String userid);
	public int checkid(String userid)throws Exception;
	public int checklogin(UserVO VO);
	public int checknickname(String usernickname)throws Exception;	
	
	public UserVO appointmentread(String userid)throws Exception;
	public void appointmentdelete(String userid)throws Exception;
	
	public void deleteBlockuser(int blockno)throws Exception;
	public List<BlockuserVO> listBlockUser(String blocker)throws Exception;
	public List<BlockuserVO> listBlockedUser(String blocked)throws Exception;
	public List<String> listEachlike(String userid)throws Exception;
	
	public void updateUserpoint(String userid, int userpoint)throws Exception;
	public int totalUserprofit()throws Exception;
	public void userState(String userid)throws Exception;
	
	public void setusertoken(UserVO vo)throws Exception;
}