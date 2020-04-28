package com.hk.board;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.board.dtos.HkDto;
import com.hk.board.service.IHkService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//@Autowired: 스프링 컨테이너가 xml에 선언된 객체의 타입으로 찾아서 넣어준다.
	//@Qualifier("hkdao"): 스프링 컨테이너가 xml에 선언된 객체 이름으로 찾아서 넣어준다.
	@Autowired
	private IHkService hkService;
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/boardlist.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String boardList(Locale locale, Model model) {
		logger.info("글목록 보여주기 {}.", locale);
		
		List<HkDto> list=hkService.getAllList();
		
		//Model객체: requestScope와 같은 역할을 함
		model.addAttribute("list", list );
		
		return "boardlist";//응답할 페이지 이름만 적어준다.
		                 //viewResolver가 "WEB-INF/views/"+boardlist+".jsp"
	}
	
	@RequestMapping(value = "/addboard.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String addboard(Locale locale, Model model) {
		logger.info("글쓰기폼이동 {}.", locale);
		
		return "addboard";//응답할 페이지 이름만 적어준다.
		                 //viewResolver가 "WEB-INF/views/"+boardlist+".jsp"
	}
	
	@RequestMapping(value = "/addinsert.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String addinsert(Locale locale, Model model,HkDto dto) {
		//메서드의 파라미터를 활용하면 클라이언트로부터 전달되는 파리미터를 쉽게 받을 수 있다.
		//전달되는 파리미터의 이름과 일치시켜면 됨. id=hk --> String id
		logger.info("글추가하기 {}.", locale);
		
		boolean isS=hkService.insertBoard(dto);
		if(isS) {
			//글을 추가한 다음 글목록으로 응답---> boardlist.do
//			return "boardlist.do";//"WEB-INF/views/"+boardlist.do+".jsp"
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg", "글추가실패");
			return "error";
		}
	}
	
	@RequestMapping(value = "/boarddetail.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String boarddetail(Locale locale, Model model,int seq) {
		logger.info("글상세보기 {}.", locale);
		
		HkDto dto=hkService.getBoard(seq);
		
		//Model객체: requestScope와 같은 역할을 함
		model.addAttribute("dto", dto );
		
		return "boarddetail";//응답할 페이지 이름만 적어준다.
		                 //viewResolver가 "WEB-INF/views/"+boardlist+".jsp"
	}
	
	@RequestMapping(value = "/updateform.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateform(Locale locale, Model model,
			@RequestParam("seq") int seq) {
		logger.info("글수정하기폼이동 {}.", locale);
		
		HkDto dto=hkService.getBoard(seq);
		
		//Model객체: requestScope와 같은 역할을 함
		model.addAttribute("dto", dto );
		
		return "boardupdate";//응답할 페이지 이름만 적어준다.
		                 //viewResolver가 "WEB-INF/views/"+boardlist+".jsp"
	}
	
	@RequestMapping(value = "/updateboard.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateboard(Locale locale, Model model,HkDto dto) {
	
		logger.info("글수정하기 {}.", locale);
		
		boolean isS=hkService.updateBoard(dto);
		if(isS) {
			//글을 추가한 다음 글목록으로 응답---> boardlist.do
//			return "boardlist.do";//"WEB-INF/views/"+boardlist.do+".jsp"
			return "redirect:boarddetail.do?seq="+dto.getSeq();
		}else {
			model.addAttribute("msg", "글수정실패");
			return "error";
		}
	}
	
	@RequestMapping(value = "/delboard.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String delboard(Locale locale, Model model,HkDto dto) {
		
		logger.info("글삭제하기 {}.", locale);
		
		boolean isS=hkService.delBoard(dto.getSeq());
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg", "글삭제실패");
			return "error";
		}
	}
	
	@RequestMapping(value = "/muldel.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String muldel(Locale locale, Model model,String[] chk) {
		//String [] chk: chk라는 이름으로 여러개의 값이 전달될 경우 받을 수 있다.
		logger.info("여러글삭제하기 {}.", locale);
		
		boolean isS=hkService.mulDel(chk);
		if(isS) {
			return "redirect:boardlist.do";
		}else {
			model.addAttribute("msg", "여러글삭제실패");
			return "error";
		}
	}
}



