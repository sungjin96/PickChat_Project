package com.example.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.ReplyVO;
import com.example.persistence.ReplyDAO;

@RestController
@RequestMapping(value = "board/reply/")
public class ReplyController {

	@Inject
	ReplyDAO dao;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list/{bno}", method = RequestMethod.GET)
	public List<ReplyVO> list(@PathVariable("bno") int bno) throws Exception {
		return dao.list(bno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(@RequestBody ReplyVO vo) throws Exception {
		dao.insert(vo);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "delete/{rno}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("rno") int rno) throws Exception {
		dao.delete(rno, 0);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "updateread/{rno}", method = RequestMethod.GET)
	public ReplyVO updateread(@PathVariable("rno") int rno) throws Exception {
		return dao.read(rno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "update", method = RequestMethod.PATCH)
	public void questioncommentupdate(@RequestBody ReplyVO vo) throws Exception {
		dao.update(vo);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "count/{bno}", method = RequestMethod.GET)
	public int count(@PathVariable("bno") int bno) throws Exception {
		return dao.count(bno);
	}

}