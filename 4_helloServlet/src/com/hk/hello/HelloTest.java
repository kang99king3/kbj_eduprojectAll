package com.hk.hello;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloTest.do")
public class HelloTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application=getServletContext();
		application.setAttribute("kk", "222");
		
		ServletContext application2=getServletContext();
		application2.setAttribute("pp", "33");
		System.out.println(application.getAttribute("kk"));
		System.out.println(application.getAttribute("pp"));
		
		
	}
	
	
}
