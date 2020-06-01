package com.hk.board;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.board.service.IMailService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private IMailService mailService;
	
	//클라이언트에서 home.do라고 get방식으로 요청을 하면 home()메서드를 실행하겠다~
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//Model객체: requestScope와 같은 역할을 함
		model.addAttribute("serverTime", formattedDate );
		
		return "home";  //뷰리졸버 실행 "web-inf/views/"+"home"+".jsp" 
	}
	
	@RequestMapping(value = "/regist.do", method = RequestMethod.POST)
	public String regist(String email,Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		mailService.create(email);
		
		return "home";  //뷰리졸버 실행 "web-inf/views/"+"home"+".jsp" 
	}
	
}




