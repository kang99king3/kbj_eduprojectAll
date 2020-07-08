package com.hk.chat;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController implements ServletConfigAware
{
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public ServletContext application;//servletContext공용장바구니
	
	//servletConfig메서드로 초기화 : ServletConfigAware에서 구현
	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		//사용자들을 저장할 application 객체생성(servletContext공용장바구니)
		application=servletConfig.getServletContext();
	}
	
	//로그인후 메인 이동
	@RequestMapping(value="login.do", method = RequestMethod.POST)
	public String login(Locale locale,String id,
			HttpServletRequest request) {
		logger.info("로그인 {}.", locale);
		System.out.println("아이디:"+id);
		//로그인한 아이디를 세션스코프에 저장해둔다.
		request.getSession().setAttribute("userId", id);
		return "redirect:main.do";
	}
	@RequestMapping(value="main.do", method = RequestMethod.GET)
	public String main(Locale locale,String id,HttpServletRequest request) {
		logger.info("메인이동 {}.", locale);
		return "main";
	}
	@RequestMapping(value = "/home.do")
	public String home(Locale locale,HttpServletRequest request) {
		logger.info("채팅창이동 {}.", locale);
		//로그인 한 userId를 담고 있는 List객체를 servletContext에서 구한다.
		List<String>userList=(List<String>)application.getAttribute("userlist");
		
		if(userList==null) {//만약 null이라면 즉, 채팅 처음 접속이면
			userList=new ArrayList<>();//새로 생성해주고
			
			//채팅접속한 아이디를 userList에 추가한다.
			userList.add((String)request.getSession().getAttribute("userId"));
			
			//추가한 userList를 servletContext에 담는다.
			application.setAttribute("userlist", userList);
			
		}else { //처음이 아니라면 
			
			//채팅접속한 아이디를 userList에 추가한다.
			userList.add((String)request.getSession().getAttribute("userId"));
			
			//추가한 userList를 servletContext에 담는다.
			application.setAttribute("userlist", userList);
		}
		
		logger.info("현재 채팅 참여자수:"+userList.size()+"명");
		
		return "home";
	}

	@RequestMapping(value = "/chatOut.do")
	public void chatOut(Locale locale,HttpServletRequest request) {
		logger.info("채팅종료하기 {}.", locale);
		
		//채팅을 종료하는 사용자의 아이디를 세션에서 구한다.
		String userId=(String)request.getSession().getAttribute("userId");
		
		//채팅사용자들을 저장해둔 userList를 가져온다.
		List<String>userList=(List<String>)application.getAttribute("userlist");
		if(userList!=null) {//userList에 저장된 아이디가 있다면
			userList.remove(userId);//userList에서 종료할 해당 사용자 아이디를 삭제			
		}
		
		//종료한 사용자 아이디를 제거한 userList를 다시 servletContext에 담는다.(사용자 갱신)
	    application.setAttribute("userlist", userList);
	    
	    logger.info("채팅 종료 후 사용자 수:"+userList.size()+"명");
	}
		
	@ResponseBody
	@RequestMapping(value = "/userlistview.do", method = RequestMethod.POST)
	public Map<String, List<String>> userlistview(Locale locale,
			HttpServletRequest request) {
		logger.info("채팅참여자 리스트 구하기 {}.", locale);
	
		//servletContext에 담아둔 userList를 가져온다.
		List<String>userList=(List<String>)application.getAttribute("userlist");
		
		//맵에 userList 객체 담기
		Map<String, List<String>> map=new HashMap<>();
		map.put("userlist", userList);

		//요청한 body로 던진다~~~
		return map;
	}
}
