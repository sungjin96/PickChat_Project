package com.example.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BlockuserVO;
import com.example.domain.LikepeopleVO;
import com.example.domain.UserVO;
import com.example.persistence.InsertDAO;
import com.example.service.UserService;

@RestController
@RequestMapping(value = { "/user/*" })
public class InsertController {

	@Inject
	private InsertDAO idao;

	@Inject
	private UserService service;

	// 내 매력 입력
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "insert_mytype", method = RequestMethod.POST)
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
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "insert_liketype", method = RequestMethod.POST)
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
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "insert_userhobby", method = RequestMethod.POST)
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
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "insert_uservoice", method = RequestMethod.POST)
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
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "insert_puser", method = RequestMethod.POST)
	public void insertPuser(@RequestBody UserVO vo) throws Exception {
		// 기본정보, 포인트, 이미지
		try {
			service.insertUser(vo);
		} catch (Exception e) {
			System.out.println("서비스 입력 오류" + e.toString());
		}

	}

	// 좋아요 입력
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "insert_likepeople", method = RequestMethod.POST)
	public void insertLikepeople(@RequestBody LikepeopleVO vo) throws Exception {
		try {
			idao.insertLikePeople(vo.getSender(), vo.getReceiver());
		} catch (Exception e) {
			System.out.println("좋아요 입력 오류" + e.toString());
		}
	}

	// 약속 추가
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "appointmentinsert", method = RequestMethod.POST)
	public void appointmentinsert(@RequestBody UserVO vo) throws Exception {
		try {
			idao.appointmentinsert(vo);
		} catch (Exception e) {
			System.out.println("약속잡기 입력 오류" + e.toString());
		}
	}

	// 차단유저입력
	@CrossOrigin("*")
	@RequestMapping(value = "insert_blockuser", method = RequestMethod.POST)
	public void insertBlockUser(@RequestBody BlockuserVO vo) throws Exception {
		idao.insertBlockuser(vo);
	}

	// 이익금액입력
	@CrossOrigin("*")
	@RequestMapping(value = "insert_profit/{userid}/{profit}", method = RequestMethod.POST)
	public void insertProfit(@PathVariable("userid") String userid, @PathVariable("profit") int profit) throws Exception {
		UserVO vo = new UserVO();
		vo.setUserid(userid);
		vo.setProfit(profit);
		idao.insertUserProfit(vo);
	}
}
