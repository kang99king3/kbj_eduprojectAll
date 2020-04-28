package com.hk.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/*" }, 
		   initParams = { 
		   @WebInitParam(name = "encoding", value = "utf-8")
})
public class CharacterEncodingFiler implements Filter {

	FilterConfig config;
//	//처음 초기화작을 위해 실행되는 메서드
	public void init(FilterConfig fConfig) throws ServletException {
		config=fConfig;
	}

	//작업할 코드를 작성하는 메서드 doFilter()
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//서블릿 실행전 작업할 코드 작성
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		System.out.println("필터전처리");
		
		//로그인체크시
//		HttpServletRequest httpRequest=(HttpServletRequest)request;
//		HttpSession session=httpRequest.getSession();
		
		chain.doFilter(request, response);
		//서블릿 실행 후 브라우저 응답전 작업할 코드 작성
		System.out.println("필터후처리");
	}


	public void destroy() {
		
	}

}
