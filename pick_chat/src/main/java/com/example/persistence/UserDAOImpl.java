package com.example.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.domain.CategoryVO;
import com.example.domain.HobbyVO;
import com.example.domain.LikeTypeVO;
import com.example.domain.LikepeopleVO;
import com.example.domain.MytypeVO;
import com.example.domain.UserImageVO;
import com.example.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	SqlSession session;

	private static final String namespace = "userMapper";

	@Override
	public List<UserVO> listUser() throws Exception {
		return session.selectList(namespace + ".list_user");
	}

	@Override
	public UserVO readUser(String userid) {
		return session.selectOne(namespace + ".read_user", userid);
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
		return session.selectList(namespace + ".list_category");
	}

	@Override
	public List<CategoryVO> listHobbyCategory() throws Exception {
		return session.selectList(namespace + ".list_hobbycategory");
	}

	@Override
	public List<LikepeopleVO> listLikesender(String userid) throws Exception {
		return session.selectList(namespace + ".list_likesender", userid);
	}

	@Override
	public List<LikepeopleVO> listLikereceiver(String userid) throws Exception {
		return session.selectList(namespace + ".list_likereceiver", userid);
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
	public UserVO appointmentread(String userid) throws Exception {
		return session.selectOne(namespace + ".appointmentread", userid);
	}

}