package com.example.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BoardVO;
import com.example.persistence.BoardDAO;
import com.example.persistence.ReplyDAO;
import com.example.service.BbsService;
import com.example.service.ImageNameSaveService;

@RestController
@RequestMapping(value = "/board/")
public class BoardController {

	ImageNameSaveService ImageName = ImageNameSaveService.getImageService();

	@Inject
	BoardDAO dao;

	@Inject
	ReplyDAO rdao;

	@Inject
	private BbsService service;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbslist", method = RequestMethod.GET)
	public List<BoardVO> bbslist() throws Exception {
		return service.bbslist();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbsimglist", method = RequestMethod.GET)
	public List<BoardVO> bbsimglist() throws Exception {
		return dao.bbsimglist();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbsread/{bno}", method = RequestMethod.GET)
	public BoardVO bbsread(@PathVariable("bno") int bno) throws Exception {
		return service.bbsread(bno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbsupdateread/{bno}", method = RequestMethod.GET)
	public BoardVO bbsupdateread(@PathVariable("bno") int bno) throws Exception {
		return dao.bbsread(bno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbsupdate", method = RequestMethod.PATCH)
	public void bbsupdate(@RequestBody BoardVO vo) throws Exception {
		dao.bbsupdate(vo);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "userbbslist/{userid}", method = RequestMethod.GET)
	public List<BoardVO> userbbslist(@PathVariable("userid") String userid) throws Exception {
		return dao.userbbslist(userid);
	}

	// service-----bbs&bbsimg------------------------------------------------------------------
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbsinsert", method = RequestMethod.POST)
	public void bbsinsert(@RequestBody BoardVO vo) throws Exception {
		String name = "null";
		try {
			if (ImageName.getSingleImage() != null) {
				name = ImageName.getSingleImage();
			}
			String result = "http://sungjin5891.cafe24.com/img/" + name;
			vo.setImgpath(result);

			service.bbsinsert(vo);
		} catch (Exception e) {
			System.out.println("bbsinsert error" + e.toString());
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbsdelete/{bno}", method = RequestMethod.DELETE)
	public void bbsdelete(@PathVariable("bno") int bno) throws Exception {
		rdao.delete(0, bno);
		dao.bbsdeletelikedelete(bno);
		dao.bbstagdelete(bno);
		dao.bbsdelete(bno);
	}

	// -------------------------------------------------------------bbslikeStart
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbslikeread/{bno}", method = RequestMethod.GET)
	public List<BoardVO> bbslikeread(@PathVariable("bno") int bno) throws Exception {
		return dao.bbslikeread(bno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbslikestate/{bno}/{liker}", method = RequestMethod.GET)
	public int bbslikestate(@PathVariable("bno") int bno, @PathVariable("liker") String liker) throws Exception {
		return dao.bbslikestate(bno, liker);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbslikecount/{bno}", method = RequestMethod.GET)
	public int bbslikecount(@PathVariable("bno") int bno) throws Exception {
		return dao.bbslikecount(bno);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbslikedelete/{bno}/{liker}", method = RequestMethod.DELETE)
	public void bbslikedelete(@PathVariable("bno") int bno, @PathVariable("liker") String liker) throws Exception {
		dao.bbslikedelete(bno, liker);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbslikeinsert", method = RequestMethod.POST)
	public void bbslikeinsert(@RequestBody BoardVO vo) throws Exception {
		dao.bbslikeinsert(vo);
	}

	// -------------------------------------------------------------bbstagStart
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbstagread/{tagword}", method = RequestMethod.GET)
	public List<BoardVO> bbstagread(@PathVariable("tagword") String tagword) throws Exception {
		return dao.bbstagread(tagword);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "bbstaginsert", method = RequestMethod.POST)
	public void bbstaginsert(@RequestBody BoardVO vo) throws Exception {
		try {
			for (int i = 0; i < vo.getTagword().length; i++) {
				String[] tagword = vo.getTagword();
				dao.bbstaginsert(vo.getBno(), tagword[i]);
			}
		} catch (Exception e) {
			System.out.println("bbstaginsert error" + e.toString());
		}
	}
}