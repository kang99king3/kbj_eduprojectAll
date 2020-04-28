package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import com.hk.daos.AnsDao;
import com.hk.dtos.AnsDto;

import net.sf.json.JSONObject;

@WebServlet("/AnsController.do")
public class AnsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 처리
//		request.setCharacterEncoding("utf-8");//웹브라우저로부터 전달되는 파라미터 인코딩처리
//		response.setContentType("text/html; charset=utf-8");//서버에서 웹브라우저로 응답할때 인코딩처리
		System.out.println("서블릿실행");
		
		String command=request.getParameter("command");
		
		AnsDao dao=new AnsDao();
		
		if(command.equals("boardlist")) {
			
			//글목록을 요청하면 쿠키삭제하기
			Cookie cookie=getCookie("rseq", request);
			if(cookie!=null) {
				cookie.setMaxAge(0);//해당 쿠키 삭제
				response.addCookie(cookie);//클라이언트에 반영	
			}
				   
//			Cookie[] cookies=request.getCookies();
//			for (int i = 0; i < cookies.length; i++) {
//				if(cookies[i].getName().equals("rseq")) {
//					cookies[i].setMaxAge(0);
//					response.addCookie(cookies[i]);
//				}
//			}
			
			List<AnsDto> list=dao.getAllList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("boardlist.jsp")
			       .forward(request, response);
		}else if(command.equals("addForm")) {
			response.sendRedirect("ansAddBoard.jsp");
		}else if(command.equals("ansAddBoard")) {
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			boolean isS=dao.insertBoard(new AnsDto(id,title,content));
			if(isS) {
				response.sendRedirect("AnsController.do?command=boardlist");
			}else {
				request.setAttribute("msg", "새글추가실패");
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("boarddetail")) {//글 상세보기
			int seq=Integer.parseInt(request.getParameter("seq"));
			AnsDto dto=dao.getBoard(seq);
			
//			String s=String.valueOf(seq);
//			String ss=seq+"";
			
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
				dao.readCount(seq);//조회수증가	
			}
	
			request.setAttribute("dto", dto);
			dispatch("ansboardDetail.jsp", request, response);
		}else if(command.equals("updateForm")) {
			int seq=Integer.parseInt(request.getParameter("seq"));
			AnsDto dto=dao.getBoard(seq);
			request.setAttribute("dto", dto);
			dispatch("ansboardUpdate.jsp", request, response);
		}else if(command.equals("ansboardUpdate")) {
			int seq=Integer.parseInt(request.getParameter("seq"));
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			boolean isS=dao.ansBoardUpdate(new AnsDto(seq,title,content));
			if(isS) {
				response.
				sendRedirect("AnsController.do?command=boarddetail&seq="+seq);
			}else {
				request.setAttribute("msg", "글수정실패");
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("muldel")) {
			//체크박스에  name=seq로 모두 이름이 같다--> seq[23,4,5,6]
			String [] seqs=request.getParameterValues("seq");
			boolean isS=dao.mulDel(seqs);
			if(isS) {
				response.sendRedirect("AnsController.do?command=boardlist");
			}else {
				request.setAttribute("msg", "글삭제실패");
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("replyBoard")) {
			int seq=Integer.parseInt(request.getParameter("seq"));//부모의 seq
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			boolean isS=dao.replyBoard(new AnsDto(seq,id,title,content));
			if(isS) {
				response.sendRedirect("AnsController.do?command=boardlist");
			}else {
				request.setAttribute("msg", "답글추가실패");
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("contentAjax")) {
			//서버에서 클라이언트로 보낼때 인코딩처리
			response.setContentType("text/html;charset=utf-8");
			
			int seq=Integer.parseInt(request.getParameter("seq"));
			System.out.println("클라이언트로부터 전달받은 seq값:"+seq);
			AnsDto dto=dao.getBoard(seq);
			
			//text 하나 보낼꺼면  출력하면 된다.
			String content=dto.getContent();
			
			PrintWriter pw=response.getWriter();
			pw.print(content);
			
			
			
//			dto.setRegdate(null);//dto에 regdate값을 null로 설정한다.
//			                     //date값은 json으로 변환을 못한다.
//			
//			//dto객체를 map에 담는다.
//			Map<String, AnsDto> map=new HashMap<>();
//			map.put("dto", dto);
//			
//			//map의 데이터구조: key:value   , json의 데이터구조: key:value
//			JSONObject obj=JSONObject.fromObject(map); // map--> json객체로 변환
//			PrintWriter pw=response.getWriter();//브라우저프린터 생성
//			obj.write(pw);//obj는 프린터가 없어서 프린터기를 보내줌(pw)
		}
		
	}//doPost()종료
	
	
	
	//forward 메서드 구현하기
		public void dispatch(String url, HttpServletRequest request,
				             HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dispatch=request.getRequestDispatcher(url);
			dispatch.forward(request, response);
		}

		
}








