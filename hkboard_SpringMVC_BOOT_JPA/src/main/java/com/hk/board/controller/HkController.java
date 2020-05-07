package com.hk.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
	public String boarddetail(@Param("seq") int seq, Model model) {
		logger.info("글상세조회하기");
		System.out.println("파라미터:"+seq);
		HkDto getBoard=hkService.findDetail(seq);
		System.out.println("출력:"+seq);
		model.addAttribute("dto", getBoard);
		return "boarddetail";
	}
	
	@RequestMapping(value = "/insertBoard.do", method = RequestMethod.POST)
	public String insertBoard(HkDto dto, Model model) throws UnsupportedEncodingException {
		logger.info("글추가하기");
		HkDto insertDto=hkService.save(dto);
		
		if(insertDto==null) {
			String msg=URLEncoder.encode("글추가실패","utf-8");
			return "redirect:error.do?msg="+msg;			
		}else {	
			return "redirect:boardlist.do";			
		}
	}
	
	@RequestMapping(value = "/insertform.do", method = RequestMethod.GET)
	public String insertform(Model model) {
		logger.info("글추가폼이동");
		return "insertboard";
	}
	
	@RequestMapping(value = "/updateForm.do", method = RequestMethod.GET)
	public String updateForm(@Param("seq") int seq, Model model) {
		logger.info("글수정하기 폼이동");
		System.out.println("파라미터:"+seq);
		HkDto getBoard=hkService.findDetail(seq);
		model.addAttribute("dto", getBoard);
		return "updateForm";
	}
	
	@RequestMapping(value = "/updateBoard.do", method = RequestMethod.POST)
	public String updateBoard(HkDto dto, Model model) throws UnsupportedEncodingException {
		logger.info("글수정하기");
		HkDto updateDto=hkService.save(dto);
		
		if(updateDto==null) {
			String msg=URLEncoder.encode("글수정실패","utf-8");
			return "redirect:error.do?msg="+msg;			
		}else {	
			return "redirect:boarddetail.do?seq="+dto.getSeq();			
		}
	}
	
	@RequestMapping(value = "/error.do", method = RequestMethod.GET)
	public String error(String msg, Model model) throws UnsupportedEncodingException {
		logger.info("오류");
		String msgDecode=URLDecoder.decode(msg,"utf-8");
		model.addAttribute("msg", msgDecode);
		return "error";
	}
}



