package com.hk.board.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.board.dtos.HkDto;
import com.hk.board.service.HkService;

@Controller
public class HkController {

	private static final Logger logger=LoggerFactory.getLogger(HkController.class);
	
	@Autowired
	private HkService hkService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model,HttpServletRequest request){
		
		logger.info("인덱스페이지");
		return "index";
	}
	
	@RequestMapping(value = "/boardlist.do", method = RequestMethod.GET)
	public String boardlist(Model model) {
		logger.info("글목록조회하기");
		List<HkDto>list=hkService.findAll();
		model.addAttribute("list", list);
		return "boardlist";
	}
	
	@RequestMapping(value = "/boarddetail.do", method = RequestMethod.GET)
	public String boarddetail(@Param("seq") String seq, Model model) {
		logger.info("글상세조회하기");
		System.out.println("파라미터:"+seq);
		List<HkDto> getBoard=hkService.findOne(seq);
		System.out.println("출력:"+seq);
		model.addAttribute("dto", getBoard.get(0));
		return "boarddetail";
	}
}



