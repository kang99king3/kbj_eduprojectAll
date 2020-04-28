<%@page import="com.hk.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 

	//1단계:command값 받기(어떤요청인지 확인하기 위해)
	String command=request.getParameter("command");

	//2단계:dao객체 생성하기(DB에 연결해서 작업하기 위해 준비)
	HkDao dao=new HkDao();

	//3단계:command에 의한 분기 실행(요청에 대한 처리 분기)
	if(command.equals("boardlist")){//글목록 요청처리
		List<HkDto> list=dao.getAllList();//글목록 구하기
		// setAttribute(key,value);
		//6단계:스코프에 객체 담기
		request.setAttribute("list", list);//list객체를 request 스코프에 "list"이름으로 저장
		//7단계:페이지 이동
		pageContext.forward("boardlist.jsp");
		
		//이동방법
// 		response.sendRedirect(url);
//      외부 페이지로 이동이 가능, 웹브라우저에 어디로 이동하겠다고 알려주고 나서 이동을 함
//      get방식으로 실행
// 		pageContext.forward(url);
//      내부 페이지 이동 가능, 웹브라우저에 알려주지 않고 바로 이동 	
//      주로 scope객체와 함께 사용된다. 
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
			response.sendRedirect("hkcontroller.jsp?command=boardlist");
		}else{
			request.setAttribute("msg", "글추가에 실패하였습니다.");
			pageContext.forward("error.jsp");
		}
	}else if(command.equals("boarddetail")){//글상세보기
		int seq=Integer.parseInt(request.getParameter("seq"));
		HkDto dto=dao.getBoard(seq);
		//dto객체를 boarddetail.jsp로 전달하기 위해 scope객체에 담는다.
		request.setAttribute("dto", dto);
		pageContext.forward("boarddetail.jsp");
	}else if(command.equals("updateform")){//수정폼으로 이동
		int seq=Integer.parseInt(request.getParameter("seq"));
		HkDto dto=dao.getBoard(seq);
		//dto객체를 boarddetail.jsp로 전달하기 위해 scope객체에 담는다.
		request.setAttribute("dto", dto);
		pageContext.forward("boardupdate.jsp");
	}else if(command.equals("updateboard")){ //글수정실행
		//수정한 내용 파라미터 받기
		int seq=Integer.parseInt(request.getParameter("seq"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");

		//Dao객체에 updateBoard메서드를 실행해서 수정 내용을 DB에 반영
		boolean isS=dao.updateBoard(new HkDto(seq,title,content));
		// 수정 성공/실패에 따라 페이지 이동 
		if(isS){
			response.sendRedirect("hkcontroller.jsp?command=boarddetail&seq="+seq);
		}else{
			request.setAttribute("msg", "글수정에 실패하였습니다.");
			pageContext.forward("error.jsp");
		}
	}else if(command.equals("delboard")){
		int seq=Integer.parseInt(request.getParameter("seq"));
		boolean isS=dao.delBoard(seq);
		if(isS){
			response.sendRedirect("hkcontroller.jsp?command=boardlist");
		}else{
			request.setAttribute("msg", "글삭제실패했어요");
// 			response.sendRedirect("error.jsp"); (X)
			pageContext.forward("error.jsp");
		}
	}else if(command.equals("muldel")){
		//getParameterValues("chk"): chk라는 이름으로 전달되는 파라미터들을 
		//                           배열로 담아서 반환시켜준다.
		String[] seqs=request.getParameterValues("chk");
		if(seqs==null||seqs.length==0){
			%>
			<script type="text/javascript">
				alert("최소 하나이상 체크하세요!!");
				location.href="hkcontroller.jsp?command=boardlist";
			</script>
			<%
		}else{
			boolean isS=dao.mulDel(seqs);
			if(isS){
				response.sendRedirect("hkcontroller.jsp?command=boardlist");
			}else{
				request.setAttribute("msg", "글 여러개 삭제 실패!!");
				pageContext.forward("error.jsp");
			}
		}
		
	}
%>
</body>
</html>















