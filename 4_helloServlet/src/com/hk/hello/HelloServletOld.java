package com.hk.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//현재 클래스를 서버와 클라이언트간에 통신을 하기위한 객체로 구현: Servlet을 구현
//계층구조 확인
//Servlet<<interface>> --> GenericServlet<abstact> (service()미구현)
//--> HttpServlet(모두 구현하였음:service()를 doGet()/doPost()구현)

public class HelloServletOld extends HttpServlet{
	
	//init()은 servlet이 요청될때 최초에 한번 실행되는 메서드(서블릿의 초기작업 코드를 작성)
	@Override
	public void init() throws ServletException {
		System.out.println("init()최초한번 실행함");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 파리미터가 한글일때 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//응답할때 브라우저에 표현할 한글 인코딩 처리
		response.setContentType("text/html;charset=utf-8");
		
		String param=request.getParameter("param");

		//콘솔용 프린터기 사용
		System.out.println("get방식으로 요청했어요"+":param:"+param);
		
		//브라우저용 프린터기 사용
		PrintWriter pw=response.getWriter();
		pw.print("<h1>서블릿 생성 실습</h2>");
		pw.print("<h2>get방식으로 요청했어요"+":param:"+param+"</h2>");
		pw.print("<a href='index.jsp'>돌아가기</a>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post방식으로 요청했어요");
	}
	
	@Override
	public void destroy() {
		System.out.println("더이상 요청이 없으므로 서블릿 소멸시킴(이 작업은 가비지컬렉터가 알아서 실행함");
	}
}












