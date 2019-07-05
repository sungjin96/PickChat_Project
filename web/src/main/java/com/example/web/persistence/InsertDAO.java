package com.example.web.persistence;

import com.example.web.domain.UserVO;

public interface InsertDAO {
    public void insertImg(String userid,String imgpath,int imgshow)throws Exception;
	public void insertPuser(UserVO vo)throws Exception;
	public void insertUserpoint(String userid)throws Exception;
	public void insertMytype(String userid,int typeid)throws Exception;
	public void insertLiketype(String userid, int typeid)throws Exception;
	public void insertUserinfo(UserVO vo)throws Exception;
	public void insertUserHobby(String userid,int hobbyid)throws Exception;
	public void insertUserVoice(String userid, String voicepath)throws Exception;
	public void insertLikePeople(String sender,String receiver)throws Exception;
}