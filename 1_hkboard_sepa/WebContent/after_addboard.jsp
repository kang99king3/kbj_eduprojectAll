<%@page import="com.hk.dtos.HkDto"%>
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
	//파라미터를 받는 메서드: getParameter(keyname)
	String id=request.getParameter("id");
	String title=request.getParameter("title");
	String content=request.getParameter("content");
	//입력박스에 입력안하고 전달하면 ""값을 받는다
	//단 받는 이름을 "id"가 아닌 "ids"로 파라미터 이름이 틀리면 null을 반환한다.
	if(id==""||title==""||content==""){
		%>
		<script type="text/javascript">
			alert("값을 모두 입력하세요");
			location.href="addboard.jsp";
		</script>
		<%
	}else{
		HkDao dao=new HkDao();
		boolean isS=dao.insertBoard(new HkDto(id,title,content));
		if(isS){
			%>
			<script type="text/javascript">
				alert("새글을 등록합니다.");
				location.href="boardlist.jsp";
			</script>
			<%
		}else{
			%>
			<script type="text/javascript">
				alert("새글을 등록실패~");
				location.href="addboard.jsp";
			</script>
			<%
		}
	}
%>
</body>
</html>






