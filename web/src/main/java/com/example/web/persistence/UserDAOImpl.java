package com.example.web.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.web.domain.CategoryVO;
import com.example.web.domain.HobbyVO;
import com.example.web.domain.LikeTypeVO;
import com.example.web.domain.LikepeopleVO;
import com.example.web.domain.MytypeVO;
import com.example.web.domain.UserImageVO;
import com.example.web.domain.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SqlSession session;

	private static final String namespace = "userMapper";

	@Override
	public List<UserVO> listUser() throws Exception {
		return session.selectList(namespace + ".list_user");
	}

	@Override
	public List<UserImageVO> listUserImage(String userid) throws Exception {
		return session.selectList(namespace + ".list_userimage", userid);
	}

	@Override
	public List<MytypeVO> listMytype(String userid) throws Exception {
		return session.selectList(namespace + ".list_mytype", userid);
	}

	@Override
	public List<HobbyVO> listHobby(String userid) throws Exception {
		return session.selectList(namespace + ".list_userhobby", userid);
	}

	@Override
	public List<LikeTypeVO> listLiketype(String userid) throws Exception {
		return session.selectList(namespace + ".list_liketype", userid);
	}

	@Override
	public List<CategoryVO> listCategory() throws Exception {
		return session.selectList(namespace+".list_category");
	}
	@Override
	public List<CategoryVO> listHobbyCategory() throws Exception {
		return session.selectList(namespace+".list_hobbycategory");
	}

	@Override
	public List<LikepeopleVO> listLikesender(String userid) throws Exception {
		return session.selectList(namespace + ".list_likesender", userid);
	}

	@Override
	public List<LikepeopleVO> listLikereceiver(String userid) throws Exception {
		return session.selectList(namespace+".list_likereceiver",userid);
	}
	@Override
	public void insertPuser(UserVO vo) throws Exception {
		session.insert(namespace + ".insert_puser", vo);
	}

	@Override
	public void insertUserpoint(String userid) throws Exception {
		session.insert(namespace + ".insert_userpoint", userid);
	}

	@Override
	public void insertLiketype(String userid, int typeid) throws Exception {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("userid", userid);
		map.put("typeid", typeid);
		session.insert(namespace + ".insert_liketype", map);
	}

	@Override
	public int checkid(String userid) throws Exception {
		return session.selectOne(namespace + ".checkid", userid);
	}

	@Override
	public int checknickname(String usernickname) throws Exception {
		return session.selectOne(namespace + ".checknickname", usernickname);
	}

	@Override
	public void insertUserinfo(UserVO vo) throws Exception {
		session.insert(namespace + ".insert_userinfo", vo);
	}

	@Override
	public void insertImg(String userid, String imgpath, int imgshow) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("imgpath", imgpath);
		map.put("imgshow", imgshow);
		session.insert(namespace + ".insert_userimage", map);
	}

	@Override
	public void insertUserHobby(String userid, int hobbyid) throws Exception {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("userid", userid);
		map.put("hobbyid", hobbyid);
		session.insert(namespace + ".insert_userhobby", map);
	}

	@Override
	public void insertUserVoice(String userid, String voicepath) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("voicepath", voicepath);
		session.insert(namespace + ".insert_uservoice", map);
	}

	@Override
	public void insertMytype(String userid, int typeid) throws Exception {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("userid", userid);
		map.put("typeid", typeid);
		session.insert(namespace + ".insert_mytype", map);
	}

	

	

	


	

	

	

	
	

	



}