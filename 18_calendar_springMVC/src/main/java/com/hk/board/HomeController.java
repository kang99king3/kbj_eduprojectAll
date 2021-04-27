package com.hk.board;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.board.dtos.CalDto;
import com.hk.board.service.ICalService;
import com.hk.board.utils.Util;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ICalService calService;
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/calendar.do", method = RequestMethod.GET)
	public String calendar(String year,String month, HttpServletRequest request, Locale locale, Model model) {
		logger.info("달력보기 {}.", locale);
		HttpSession session=request.getSession();
//		String id=((LoginDto)(session.getAttribute("ldto"))).getId();
		String id="hk";
		
		if(year==null||month==null) {
			Calendar cal=Calendar.getInstance();
			year=cal.get(Calendar.YEAR)+"";
			month=(cal.get(Calendar.MONTH)+1)+"";
		}else {
			int yearInt=Integer.parseInt(year);
			int monthInt=Integer.parseInt(month);
			//월이 증가하다가 12에서 13으로 넘어가는 과정에서 14,15,16...증가되는거 처리하기
			if(monthInt>12){
				monthInt=1;
				yearInt++;
			}
			
			//월이 감소하다가 1에서 0또는 -1,-2... 변경되는거 처리하기
			if(monthInt<1){
				monthInt=12;
				yearInt--;
			}
			year=yearInt+"";
			month=monthInt+"";
		}
		
		String yyyyMM=year+Util.isTwo(month);
		List<CalDto> clist=calService.getCalViewList(id, yyyyMM);
		model.addAttribute("clist", clist);
		return "calendar";
	}
	
	@RequestMapping(value = "/insertcalform.do", method = RequestMethod.GET)
	public String insertCalForm(Locale locale, Model model) {
		logger.info("일정추가폼이동 {}.", locale);
	
//		return "redirect:WEB-INF/views/insertboard.jsp";(X)
		return "insertboard";
	}
	
	@RequestMapping(value = "/insertboard.do", method = RequestMethod.POST)
	public String insertBoard(CalDto dto, Locale locale, Model model) {
		logger.info("일정추가하기 {}.", locale);
	
		//mdate는 12자리: 202012291527  --> 1자리인값은 2자리고 변환처리
		String mdate=dto.getYear()
				+Util.isTwo(dto.getMonth())
				+Util.isTwo(dto.getDate())
				+Util.isTwo(dto.getHour())
				+Util.isTwo(dto.getMin());
		
		boolean isS=calService.insertCalBoard(
				new CalDto(0, dto.getId(), dto.getTitle(), dto.getContent(), mdate, null));
		
		if(isS) {
			return "redirect:calendar.do";
		}else {
			model.addAttribute("msg", "일정등록실패!!");
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/calboardlist.do", method = RequestMethod.GET)
	public String calBoardList(HttpServletRequest request, CalDto dto, Locale locale, Model model) {
		logger.info("일정목록보기 {}.", locale);
	
		HttpSession session=request.getSession();
		String yyyyMMdd="";
		
		if(dto.getYear()==null) {
			yyyyMMdd=(String)session.getAttribute("yyyyMMdd");
		}else {
			//년월일 8자리가 필요함
			yyyyMMdd=dto.getYear()+Util.isTwo(dto.getMonth())
			+Util.isTwo(dto.getDate());		
			
			//session에 년월일정보를 저장해 둔다.
			session.setAttribute("yyyyMMdd", yyyyMMdd);
		}
		
		//ID값 구하기: 로그인을 한 회원의 일정을 조회---> 로그인된 아이디 구해서 전달하면 됨
//		LoginDto ldto=(LoginDto)request.getSession().getAttribute("ldto");
//		String id=ldto.getId();
		String id="hk";
		
		List<CalDto>list=calService.calBoardList(id, yyyyMMdd);
		
		model.addAttribute("list", list);
		return "calboardlist";
	}
	
	@RequestMapping(value = "/calboarddetail.do", method = RequestMethod.GET)
	public String calBoardDetail(int seq,Locale locale, Model model) {
		logger.info("일정상세보기 {}.", locale);
		
		CalDto dto=calService.calBoardDetail(seq);
		model.addAttribute("dto", dto);
		
		return "calboarddetail";
	}
	
	@RequestMapping(value = "/updateform.do", method = RequestMethod.GET)
	public String updateForm(int seq,Locale locale, Model model) {
		logger.info("일정수정폼이동 {}.", locale);
		
		CalDto dto=calService.calBoardDetail(seq);
		model.addAttribute("dto", dto);
		
		return "calboardupdate";
	}
	
	@RequestMapping(value = "/calupdate.do", method = RequestMethod.POST)
	public String calUpdate(CalDto dto,Locale locale, Model model) {
		logger.info("일정수정하기 {}.", locale);
		//dto  <--seq, year, month,date,hour,min,title,content
		String mdate=dto.getYear()
				+Util.isTwo(dto.getMonth())
				+Util.isTwo(dto.getDate())
				+Util.isTwo(dto.getHour())
				+Util.isTwo(dto.getMin()); //12자리로 만들어서 mdate값 구한다.
		dto.setMdate(mdate);//만들어진 12자리 값을 dto에 mdate필드에 저장한다.
		boolean isS=calService.calBoardUpdate(dto);
		if(isS) {
			return "redirect:calboarddetail.do?seq="+dto.getSeq();
		}else {
			model.addAttribute("msg", "일정수정실패");
			return "error";			
		}
		
	}
	
	@RequestMapping(value = "/muldel.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String mulDel(String[] chk,Locale locale, Model model) {
		logger.info("일정삭제하기 {}.", locale);
		
		boolean isS=calService.calMuldel(chk);
		if(isS) {
			return "redirect:calboardlist.do";
		}else {
			model.addAttribute("msg", "글삭제실패");
			return "error";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/calcountAjax.do", method = {RequestMethod.POST,RequestMethod.GET})
	public Map<String, Integer> calCountAjax(String yyyyMMdd,Locale locale, Model model) {
		logger.info("일정개수보여주기 {}.", locale);
		//ID값 구하기: 로그인을 한 회원의 일정을 조회---> 로그인된 아이디 구해서 전달하면 됨
//		LoginDto ldto=(LoginDto)request.getSession().getAttribute("ldto");
//		String id=ldto.getId();
		String id="hk";
		int count=calService.calBoardCount(id, yyyyMMdd);
		//json으로 변환해서 전달해야 되므로 java에서는 map에 담아서 보내준다.
		Map<String, Integer>map=new HashMap<>();
		map.put("count", count);
		
		return map;
	}
}






