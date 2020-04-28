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

import com.hk.daos.HkDao;
import com.hk.dtos.HkDto;
import com.hk.page.Paging;

@WebServlet("/HkController.do")
public class HkController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String command=request.getParameter("command");
		
		HkDao dao=new HkDao();
		
		if(command.equals("boardlist")){//글목록 요청처리
			//요청 페이지 번호 받기
			String pnum=request.getParameter("pnum");
			
			//특별한 페이지 요청이 없다면 1페이지로 셋팅
			if(pnum==null) {
				pnum="1";
			}
			
			List<HkDto> list=dao.getAllList(pnum);//글목록 구하기
			int pcount=dao.getPCount();//페이지 개수 구하기
			
			request.setAttribute("list", list);//list객체를 request 스코프에 "list"이름으로 저장
			request.setAttribute("pcount", pcount);//Object <---(Integer) int
			dispatch("boardlist.jsp", request, response);
		}else if(command.equals("addboard")){//글추가폼으로 이동
			response.sendRedirect("addboard.jsp");
		}else if(command.equals("addinsert")){//글추가실행
			//4단계:파라미터 받기
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			//5단계:Dao메서드 실행
			boolean isS=dao.insertBoard(new HkDto(id,title,content));
			if(isS){
				response.sendRedirect("HkController.do?command=boardlist");
			}else{
				request.setAttribute("msg", "글추가에 실패하였습니다.");
//				pageContext.forward("error.jsp");// jsp
				dispatch("error.jsp", request, response);//servlet
			}
		}else if(command.equals("boarddetail")){//글상세보기
			int seq=Integer.parseInt(request.getParameter("seq"));
			HkDto dto=dao.getBoard(seq);
			//dto객체를 boarddetail.jsp로 전달하기 위해 scope객체에 담는다.
			request.setAttribute("dto", dto);
//			pageContext.forward("boarddetail.jsp");
			dispatch("boarddetail.jsp", request, response);
		}else if(command.equals("updateform")){//수정폼으로 이동
			int seq=Integer.parseInt(request.getParameter("seq"));
			HkDto dto=dao.getBoard(seq);
			//dto객체를 boarddetail.jsp로 전달하기 위해 scope객체에 담는다.
			request.setAttribute("dto", dto);
//			pageContext.forward("boardupdate.jsp");
			dispatch("boardupdate.jsp", request, response);
		}else if(command.equals("updateboard")){ //글수정실행
			//수정한 내용 파라미터 받기
			int seq=Integer.parseInt(request.getParameter("seq"));
			String title=request.getParameter("title");
			String content=request.getParameter("content");

			//Dao객체에 updateBoard메서드를 실행해서 수정 내용을 DB에 반영
			boolean isS=dao.updateBoard(new HkDto(seq,title,content));
			// 수정 성공/실패에 따라 페이지 이동 
			if(isS){
				response.sendRedirect("HkController.do?command=boarddetail&seq="+seq);
			}else{
				request.setAttribute("msg", "글수정에 실패하였습니다.");
//				pageContext.forward("error.jsp");
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("delboard")){
			int seq=Integer.parseInt(request.getParameter("seq"));
			boolean isS=dao.delBoard(seq);
			if(isS){
				response.sendRedirect("HkController.do?command=boardlist");
			}else{
				request.setAttribute("msg", "글삭제실패했어요");
//	 			response.sendRedirect("error.jsp"); (X)
//				pageContext.forward("error.jsp");
				dispatch("error.jsp", request, response);
			}
		}else if(command.equals("muldel")){
			//getParameterValues("chk"): chk라는 이름으로 전달되는 파라미터들을 
			//                           배열로 담아서 반환시켜준다.
			String[] seqs=request.getParameterValues("chk");
			if(seqs==null||seqs.length==0){
				jsForward("HkController.do?command=boardlist"
						  , "최소 하나이상 체크하세요!!"
						  , response);
				
//				String str="<script type='text/javascript'>"+
//							"alert('최소 하나이상 체크하세요!!');"+
//							"location.href='HkController.do?command=boardlist';"+
//							"</script>";
//				PrintWriter out=response.getWriter();//브라우저 출력용 프린터기 생성
//				out.print(str);//브라우저로 스크립트가 출력되면서 실행됨
				
			}else{
				boolean isS=dao.mulDel(seqs);
				if(isS){
					response.sendRedirect("HkController.do?command=boardlist");
				}else{
					request.setAttribute("msg", "글 여러개 삭제 실패!!");
//					pageContext.forward("error.jsp");
					dispatch("error.jsp", request, response);
				}
			}
			
		}
	}//doPost()종료
	
	//forward 메서드 구현하기
	public void dispatch(String url, HttpServletRequest request,
			             HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch=request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	//script로 이동 구현하기
	public void jsForward(String url,String msg,
			                   HttpServletResponse response) throws IOException {
		String str="<script type='text/javascript'>"+
				"alert('"+msg+"');"+
				"location.href='"+url+"';"+
				"</script>";
		PrintWriter out=response.getWriter();//브라우저 출력용 프린터기 생성
		out.print(str);//브라우저로 스크립트가 출력되면서 실행됨
	}

}












