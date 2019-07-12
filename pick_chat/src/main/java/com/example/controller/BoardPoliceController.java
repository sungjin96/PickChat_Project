package com.example.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BbspoliceVO;
import com.example.persistence.BoardPoliceDAO;

@RestController
@RequestMapping(value = "/board/police/")
public class BoardPoliceController {

	@Inject
	BoardPoliceDAO pdao;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<BbspoliceVO> list() throws Exception {
		return pdao.list();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "read/{bpno}", method = RequestMethod.GET)
	public BbspoliceVO read(@PathVariable("bpno") int bpno) throws Exception {
		return pdao.read(bpno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(@RequestBody BbspoliceVO vo) throws Exception {
		pdao.insert(vo);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "delete/{bpno}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("bpno") int bpno) throws Exception {
		pdao.delete(bpno);
	}

}
