package com.hk.ansboard;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.ansboard.dtos.AnsDto;
import com.hk.ansboard.service.IAnsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IAnsService ansService;
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	//원하는 쿠키를 구하기
	public Cookie getCookie(String cookieName,HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		Cookie cookie=null;
		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals(cookieName)) {
				cookie=cookies[i];
			}
		}
		return cookie;
	}
	
	@RequestMapping(value = "/boardlist.do", method = RequestMethod.GET)
	public String boardlist(HttpServletRequest request ,HttpServletResponse response,Locale locale, Model model) {
		logger.info("글목록 이동하기 {}.", locale);
		System.out.println("테스트:"+ansService.getAllList().size());
		
		//글목록을 요청하면 쿠키삭제하기
		Cookie cookie=getCookie("rseq", request);
		if(cookie!=null) {
			cookie.setMaxAge(0);//해당 쿠키 삭제
			response.addCookie(cookie);//클라이언트에 반영	
		}
		
		List<AnsDto>list=ansService.getAllList();
		model.addAttribute("list", list);
		
		return "boardlist";
	}
	
	@RequestMapping(value = "/boarddetail.do", method = RequestMethod.GET)
	public String boarddetail(HttpServletRequest request ,
			HttpServletResponse response,@RequestParam("seqparam") int seq,Locale locale, Model model) {
		 // @RequestParam("seqparam"): "seqparam" 이름으로 파라미터가 전달되면 seq변수에 받겠다 
		logger.info("글상세보기 {}.", locale);
		
		AnsDto dto=ansService.getBoard(seq);
		
		//쿠키의 값들을 가져오기(반환타입:배열)
		Cookie[] cookies=request.getCookies();
		String s=null;
		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("rseq")) {
				s=cookies[i].getValue();
			}
		}
		//쿠키생성작업의 전제조건: "rseq"라는 이름의 쿠키값이 없을때
		//현재 저장된 쿠키의 값과 조회한 seq의 값이 다른 경우   
		if(s==null||!s.equals(seq+"")) {
			Cookie cookie=new Cookie("rseq", seq+"");//쿠키생성
			cookie.setMaxAge(10*60);//쿠키유효기간
			response.addCookie(cookie);//브라우저로 생성한 쿠키 추가
			ansService.readCount(seq);//조회수증가	
		}
		model.addAttribute("dto", dto);
		
		return "ansboardDetail";
	}
	
	@RequestMapping(value = "/addForm.do", method = RequestMethod.GET)
	public String addForm(Locale locale, Model model) {
		 // @RequestParam("seqparam"): "seqparam" 이름으로 파라미터가 전달되면 seq변수에 받겠다 
		logger.info("글추가폼이동 {}.", locale);

		return "ansAddBoard";
	}
	
	@RequestMapping(value = "ansAddBoard.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String ansAddBoard(AnsDto dto, Locale locale, Model model) {
		 
		logger.info("글추가하기 {}.", locale);
		boolean isS=ansService.insertBoard(dto);
		if(isS) {
			return "redirect:boardlist.do";			
		}else {
			model.addAttribute("msg", "글추가실패");
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/updateForm.do", method = RequestMethod.GET)
	public String updateForm(@RequestParam("seq") int seq,Locale locale, Model model) {
		 // @RequestParam("seq"): "seq" 이름으로 파라미터가 전달되면 seq변수에 받겠다 
		logger.info("글수정폼이동 {}.", locale);
		
		AnsDto dto=ansService.getBoard(seq);
		
		model.addAttribute("dto", dto);
		
		return "ansboardUpdate";
	}
	
	@RequestMapping(value = "ansboardUpdate.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String ansboardUpdate(AnsDto dto, Locale locale, Model model) {
		 
		logger.info("글수정하기 {}.", locale);
		boolean isS=ansService.ansBoardUpdate(dto);
		if(isS) {
//			return "boarddetail.do";//viewresolver 실행됨
			return "redirect:boarddetail.do?seqparam="+dto.getSeq();			
		}else {
			model.addAttribute("msg", "글수정실패");
			return "error";
		}
		
	}
	
	@RequestMapping(value = "muldel.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String muldel(String[] seq, Locale locale, Model model) {
		 
		logger.info("글삭제하기 {}.", locale);
		boolean isS=ansService.mulDel(seq);
		if(isS) {
			return "redirect:boardlist.do";			
		}else {
			model.addAttribute("msg", "글삭제실패");
			return "error";
		}
		
	}
	
	//PrintWriter pw=response.getWrite();
	//pw.print("하이~~")
	@ResponseBody
	@RequestMapping(value = "contentAjax.do", method = {RequestMethod.GET,RequestMethod.POST}
	                ,produces="application/json; charset=utf-8")
	public Map<String, AnsDto> contentAjax(HttpServletRequest req, HttpServletResponse response, int seq, Locale locale, Model model) throws UnsupportedEncodingException {
//		req.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		logger.info("글내용 미리보기 {}.", locale);
		AnsDto dto=ansService.getBoard(seq);
		Map<String, AnsDto>map=new HashMap<String, AnsDto>();
		map.put("dto", dto);
		
		return map;
	}
}






