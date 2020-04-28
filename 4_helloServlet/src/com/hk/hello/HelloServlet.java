package com.hk.hello;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/HelloServlet.do")  //간단하게 url만 맵핑할때 
@WebServlet(
		urlPatterns = { "/HelloServlet.do" }, 
		initParams = { 
				@WebInitParam(name = "url", value = "jdbc:oracle:thin:@localhost:1521:xe"), 
				@WebInitParam(name = "user", value = "hk"), 
				@WebInitParam(name = "password", value = "hk")
		})
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	private ServletContext application;
	
	public void init(ServletConfig config) throws ServletException {
		//ServletConfig:환경설정정보를 관리하는 객체
		// -getServletContext():ServletContext객체를 생성한다.
		// -getInitParameter():설정된 초기값을 가져오는 메서드
		
		//모든 서블릿에서 공유되는 파라미터 사용
		ServletContext application=config.getServletContext();
		String driver=application.getInitParameter("driver");
		System.out.println("application 초기값:"+driver);
		
		//모든 서블릿에서 공유되는 객체 저장
		application.setAttribute("count", 10);
		System.out.println(application.getAttribute("count"));
		
		String url=config.getInitParameter("url");
		String user=config.getInitParameter("user");
		String password=config.getInitParameter("password");
		System.out.println("특정서블릿에서 사용되는 초기값:"+url+","+user+","+password);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//특정 사용자에서 사용하게 될 scope
		//session객체를 사용할때 생성방법
		HttpSession session= request.getSession();
		session.setAttribute("ldto", "로그인정보");
		
		//모든 사용자에서 공유하게 될 scope
		//applicaiton객체 사용할때 생성방법
//		ServletContext application= getServletContext();
		//init(Servlet Config)에서 ServletContext객체를 생성해서 
		//쓰기 때문에 여기서는 생성을 못함
//		application.setAttribute("test", "객체저장");
//		System.out.println(application.getAttribute("test"));
		
		//객체 전달할 때 사용될 forward() 구현 방법
//		RequestDispatcher dispatch=request.getRequestDispatcher("url");
//		dispatch.forward(request, response);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
