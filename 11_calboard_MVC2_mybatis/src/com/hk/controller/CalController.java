package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.daos.CalDao;
import com.hk.dtos.CalDto;
import com.hk.utils.Util;

@WebServlet("/CalController.do")
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command=request.getParameter("command");
		
		//dao객체 생성
		CalDao dao=new CalDao();
		
		if(command.equals("calendar")) {
			
			response.sendRedirect("calendar.jsp");
//			dispatch("calendar.jsp", request, response);
		}else if(command.equals("calWrite")) {
			
			//서버가 웹브라우저한테 calWrite.jsp페이지를 요청해달라고 전달함
			//--> 웹브라우저가 서버한테 요청을 함 어떻게?? calWrite.jsp를 요청
//			response.sendRedirect("calWrite.jsp");
			dispatch("calWrite.jsp", request, response);
		}else if(command.equals("insertCal")) {
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			String hour=request.getParameter("hour");
			String min=request.getParameter("min");
			
			
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			//mdate는 12자리--> 각각의 받은 값을 두자리로 변환해서 합치자
			String mdate=year
					+Util.isTwo(month)+Util.isTwo(date)+Util.isTwo(hour)+Util.isTwo(min);
			
			boolean isS=dao.insertCal(new CalDto(0,id,title,content,mdate,null));
			if(isS) {
				response.sendRedirect("CalController.do?command=calendar");
//				dispatch("CalController.do?command=calendar", request, response);
			}else {
				request.setAttribute("msg", "일정추가실패");
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("calList")) {
			//---- 목록페이지로 다시 돌아오기 위해 필요한 파라미터 구하기
			String urlParams=request.getQueryString();
			//command=calList&year=2020...
			request.getSession().setAttribute("calListQuery", urlParams);
			//-----
			
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			
//			request.getSession().setAttribute("yyyyMM", new String[] {year,Util.isTwo(month)});
			
			String id="hk";// request.getSession().getAttribute("ldto").getId()
			String yyyyMMdd=year+Util.isTwo(month)+Util.isTwo(date);//8자리 만듬
			List<CalDto> list=dao.getCalList(id, yyyyMMdd);
			request.setAttribute("list", list);
			dispatch("calBoardList.jsp", request, response);
		}else if(command.equals("muldel")) {
			String[] seqs=request.getParameterValues("seq");
			boolean isS=dao.mulDelCal(seqs);
			if(isS) {
				response.sendRedirect("CalController.do?command=calendar");
			}else {
				request.setAttribute("msg", "글삭제실패");
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("calDetail")) {
			int seq=Integer.parseInt(request.getParameter("seq"));
			CalDto dto=dao.getCalDetail(seq);
			request.setAttribute("dto", dto);
			dispatch("calDetail.jsp", request, response);
		}else if(command.equals("calUpdateForm")) {
			int seq=Integer.parseInt(request.getParameter("seq"));
			CalDto dto=dao.getCalDetail(seq);
			request.setAttribute("dto", dto);
			dispatch("calUpdate.jsp", request, response);
		}else if(command.equals("updateCal")) {
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			String hour=request.getParameter("hour");
			String min=request.getParameter("min");
			
			int seq=Integer.parseInt(request.getParameter("seq"));
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			//mdate는 12자리--> 각각의 받은 값을 두자리로 변환해서 합치자
			String mdate=year
					+Util.isTwo(month)+Util.isTwo(date)+Util.isTwo(hour)+Util.isTwo(min);
			
			boolean isS=dao.updateCal(new CalDto(seq, null, title, content, mdate, null));
			if(isS) {
				response.sendRedirect("CalController.do?command=calDetail&seq="+seq);
			}else {
				request.setAttribute("msg", "일정수정실패");
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("calCount")) {
			String year=request.getParameter("year");
			String month=request.getParameter("month");
			String date=request.getParameter("date");
			
			String yyyyMMdd=year+Util.isTwo(month)+Util.isTwo(date);//8자리생성
			String id="hk";// <--request.getSession().getAttribute("ldto").getId();
			int count=dao.getCalCount(id, yyyyMMdd);
			
			PrintWriter pw=response.getWriter();
			pw.print(count);
		}
		
	}//doPost()종류
	
	//forward 메서드 구현하기
	public void dispatch(String url, HttpServletRequest request,
			             HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}





