package com.example.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.NoticeVO;
import com.example.persistence.NoticeDAO;

@RestController
@RequestMapping(value = "/")
public class NoticeController {

	@Inject
	NoticeDAO dao;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "notice/list", method = RequestMethod.GET)
	public List<NoticeVO> noticelist() throws Exception {
		return dao.notice_list();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "notice/read/{nno}", method = RequestMethod.GET)
	public NoticeVO noticeread(@PathVariable("nno") int nno) throws Exception {
		return dao.notice_read(nno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "notice/insert", method = RequestMethod.POST)
	public void noticeinsert(@RequestBody NoticeVO vo) throws Exception {
		dao.notice_insert(vo);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "notice/updateread/{nno}", method = RequestMethod.GET)
	public NoticeVO noticeupdateread(@PathVariable("nno") int nno) throws Exception {
		return dao.notice_read(nno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "notice/update", method = RequestMethod.PATCH)
	public void noticeupdate(@RequestBody NoticeVO vo) throws Exception {
		dao.notice_update(vo);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "notice/delete/{nno}", method = RequestMethod.DELETE)
	public void noticedelete(@PathVariable("nno") int nno) throws Exception {
		dao.notice_delete(nno);
	}

	// ==============================================================================notice-ending
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "question/list", method = RequestMethod.GET)
	public List<NoticeVO> questionlist() throws Exception {
		return dao.question_list();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "question/read/{qno}", method = RequestMethod.GET)
	public NoticeVO questionread(@PathVariable("qno") int qno) throws Exception {
		return dao.question_read(qno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "question/insert", method = RequestMethod.POST)
	public void questioninsert(@RequestBody NoticeVO vo) throws Exception {
		dao.question_insert(vo);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "question/delete/{qno}", method = RequestMethod.DELETE)
	public void questiondelete(@PathVariable("qno") int qno) throws Exception {
		dao.question_delete(qno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "question/updateread/{qno}", method = RequestMethod.GET)
	public NoticeVO questionupdateread(@PathVariable("qno") int qno) throws Exception {
		return dao.question_read(qno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "question/update", method = RequestMethod.PATCH)
	public void questionupdate(@RequestBody NoticeVO vo) throws Exception {
		dao.question_update(vo);
	}

}