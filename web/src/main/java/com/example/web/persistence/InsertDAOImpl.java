package com.example.web.persistence;

import java.util.HashMap;
import java.util.Map;

import com.example.web.domain.BlockuserVO;
import com.example.web.domain.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InsertDAOImpl implements InsertDAO {

    @Autowired
	SqlSession session;

    private static final String namespace2 = "insertMapper";
    
    @Override
	public void insertPuser(UserVO vo) throws Exception {
		session.insert(namespace2 + ".insert_puser", vo);
	}

	@Override
	public void insertUserpoint(String userid) throws Exception {
		session.insert(namespace2 + ".insert_userpoint", userid);
	}

	@Override
	public void insertLiketype(String userid, int typeid) throws Exception {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("userid", userid);
		map.put("typeid", typeid);
		session.insert(namespace2 + ".insert_liketype", map);
	}

	

	@Override
	public void insertUserinfo(UserVO vo) throws Exception {
		session.insert(namespace2 + ".insert_userinfo", vo);
	}

	@Override
	public void insertImg(String userid, String imgpath, int imgshow) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("imgpath", imgpath);
		map.put("imgshow", imgshow);
		session.insert(namespace2 + ".insert_userimage", map);
	}

	@Override
	public void insertUserHobby(String userid, int hobbyid) throws Exception {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("userid", userid);
		map.put("hobbyid", hobbyid);
		session.insert(namespace2 + ".insert_userhobby", map);
	}

	@Override
	public void insertUserVoice(String userid, String voicepath) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("voicepath", voicepath);
		session.insert(namespace2 + ".insert_uservoice", map);
	}

	@Override
	public void insertMytype(String userid, int typeid) throws Exception {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("userid", userid);
		map.put("typeid", typeid);
		session.insert(namespace2 + ".insert_mytype", map);
	}

	@Override
	public void insertLikePeople(String sender, String receiver) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sender", sender);
		map.put("receiver", receiver);
		session.insert(namespace2+".insert_likepeople",map);
	}

	@Override
	public void insertBlockuser(BlockuserVO vo) throws Exception {
		session.insert(namespace2+".insert_blockuser",vo);
	}

}