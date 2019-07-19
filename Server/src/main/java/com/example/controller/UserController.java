package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BlockuserVO;
import com.example.domain.BoardVO;
import com.example.domain.CategoryVO;
import com.example.domain.HobbyVO;
import com.example.domain.LikeTypeVO;
import com.example.domain.LikepeopleVO;
import com.example.domain.MytypeVO;
import com.example.domain.UserImageVO;
import com.example.domain.UserVO;
import com.example.persistence.UserDAO;

@RestController
@RequestMapping(value = { "/user/*" })
public class UserController {

	@Inject
	private UserDAO dao;

	// 유저 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list_user", method = RequestMethod.GET)
	public List<UserVO> getListUser() throws Exception {
		return dao.listUser();
	}

	// 한 사람에 대한 리스트 읽기
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "read/{userid}", method = RequestMethod.GET)
	public UserVO oneUserRead(@PathVariable String userid) throws Exception {
		return dao.readUser(userid);
	}

	// 유저 사진 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list_img/{userid}", method = RequestMethod.GET)
	public List<UserImageVO> getImageList(@PathVariable String userid) throws Exception {
		return dao.listUserImage(userid);
	}

	// 유저 매력 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list_mytype/{userid}", method = RequestMethod.GET)
	public List<MytypeVO> userinfoList(@PathVariable String userid) throws Exception {
		return dao.listMytype(userid);
	}

	// 유저 취미 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list_userhobby/{userid}", method = RequestMethod.GET)
	public List<HobbyVO> userHobbyList(@PathVariable String userid) throws Exception {
		return dao.listHobby(userid);
	}

	// 유저 이상형 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list_liketype/{userid}", method = RequestMethod.GET)
	public List<LikeTypeVO> userLikeType(@PathVariable String userid) throws Exception {
		return dao.listLiketype(userid);
	}

	// 카테고리 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list_category", method = RequestMethod.GET)
	public List<CategoryVO> listCategory() throws Exception {
		return dao.listCategory();
	}

	// 취미 카테고리 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list_hobbycategory", method = RequestMethod.GET)
	public List<CategoryVO> listhobbyCategory() throws Exception {
		return dao.listHobbyCategory();
	}

	// 좋아요 테이블 리스트-내가 좋아요 한 사람 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list_likesender/{userid}", method = RequestMethod.GET)
	public List<LikepeopleVO> userLikesender(@PathVariable String userid) throws Exception {
		return dao.listLikesender(userid);
	}

	// 내가 좋아요 한 사람 프로필 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "user_sender_profile/{userid}", method = RequestMethod.GET)
	public List<UserVO> likesenderlist(@PathVariable String userid) throws Exception {
		List<LikepeopleVO> array = dao.listLikesender(userid);
		ArrayList<UserVO> array2 = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			UserVO vo = dao.readUser(array.get(i).getReceiver());
			// System.out.println("...................."+vo.toString());
			array2.add(vo);
		}
		return array2;
	}

	// 좋아요 테이블 리스트-나를 좋아요 한 사람 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list_likereceiver/{userid}", method = RequestMethod.GET)
	public List<LikepeopleVO> userLikereceiver(@PathVariable String userid) throws Exception {
		return dao.listLikereceiver(userid);
	}

	// 나를 좋아요 한 사람의 프로필 리스트
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "user_receiver_profile/{userid}", method = RequestMethod.GET)
	public List<UserVO> likereceiverlist(@PathVariable String userid) throws Exception {
		List<LikepeopleVO> array = dao.listLikereceiver(userid);
		ArrayList<UserVO> array2 = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			UserVO vo = dao.readUser(array.get(i).getSender());
			// System.out.println("...................."+vo.toString());
			array2.add(vo);
		}
		return array2;
	}

	// 유저 아이디 중복확인
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "checkid/{userid}", method = RequestMethod.GET)
	public int checkid(@PathVariable String userid) throws Exception {
		return dao.checkid(userid);
	}

	// 유저 닉네임 중복확인
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "checknickname/{usernickname}", method = RequestMethod.GET)
	public int checknickname(@PathVariable String usernickname) throws Exception {
		return dao.checknickname(usernickname);
	}

	// 약속 읽기
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "appointmentread/{userid}", method = RequestMethod.GET)
	public UserVO appointmentread(@PathVariable String userid) throws Exception {
		return dao.appointmentread(userid);
	}

	// 약속 삭제
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "appointmentdelete/{userid}", method = RequestMethod.DELETE)
	public void appointmentdelete(@PathVariable String userid) throws Exception {
		dao.appointmentdelete(userid);
	}

	// 한 명 차단
	@CrossOrigin("*")
	@RequestMapping(value = "delete_blockuser/{blockno}", method = RequestMethod.POST)
	public void deleteBlockuser(@PathVariable int blockno) throws Exception {
		dao.deleteBlockuser(blockno);
	}

	// 차단 유저리스트
	@CrossOrigin("*")
	@RequestMapping(value = "list_blockuser/{blocker}", method = RequestMethod.GET)
	public List<BlockuserVO> listblockuser(@PathVariable String blocker) throws Exception {
		return dao.listBlockUser(blocker);
	}

	// 나를 차단한 유저리스트
	@CrossOrigin("*")
	@RequestMapping(value = "list_blockeduser/{blocked}", method = RequestMethod.GET)
	public List<BlockuserVO> listblockeduser(@PathVariable String blocked) throws Exception {
		return dao.listBlockedUser(blocked);
	}

	// 채팅 가능 한 사람의 리스트 프로필
	@CrossOrigin("*")
	@RequestMapping(value = "user_eachlike/{userid}", method = RequestMethod.GET)
	public List<UserVO> userEachlike(@PathVariable String userid) throws Exception {
		List<String> array = dao.listEachlike(userid);
		ArrayList<UserVO> array2 = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			UserVO vo = dao.readUser(array.get(i));
			array2.add(vo);
		}
		return array2;
	}

	// 포인트 업데이트
	@CrossOrigin("*")
	@RequestMapping(value = "update_userpoint/{userid}/{userpoint}", method = RequestMethod.PATCH)
	public void updateUserpoint(@PathVariable String userid, @PathVariable int userpoint) throws Exception {
		dao.updateUserpoint(userid, userpoint);
	}

	// 토탈 profit
	@CrossOrigin("*")
	@RequestMapping(value = "total_userprofit", method = RequestMethod.GET)
	public int totalUserprofit() throws Exception {
		return dao.totalUserprofit();
	}

	// 로그인 상태
	@CrossOrigin("*")
	@RequestMapping(value = "update_userstate/{userid}", method = RequestMethod.PATCH)
	public void userState(@PathVariable String userid) throws Exception {
		dao.userState(userid);
	}

	// 채팅
	@CrossOrigin("*")
	@RequestMapping(value = "setusertoken", method = RequestMethod.GET)
	public void setusertoken(@RequestBody UserVO vo) throws Exception{
		dao.setusertoken(vo);

	}
	@CrossOrigin("*")
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public int login( UserVO vo) throws Exception{
			if(dao.checkid(vo.getUserid())==1) {
				if(dao.checklogin(vo)==1) {
					return 3;
				}else {
					return 2;
				}
			}else {
				return 1;
			}
		
	}

}