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
	int seq=Integer.parseInt(request.getParameter("seq"));
	String title=request.getParameter("title");
	String content=request.getParameter("content");
	
	HkDao dao=new HkDao();
	boolean isS=dao.updateBoard(new HkDto(seq,title,content));
	
// 생성자를 사용하지 않으면 이렇게 불편함
// 	HkDto dto1=new HkDto();
// 	dto1.setSeq(seq);
// 	dto1.setTitle(title);
// 	dto1.setContent(content);
// 	dao.updateBoard(dto1);
	
	if(isS){
		%>
		<script type="text/javascript">
			alert("글을 수정하였습니다.");
			location.href="boarddetail.jsp?seq=<%=seq%>";
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
			alert("글 수정 실패!!");
			location.href="boardupdate.jsp?seq=<%=seq%>";
		</script>
		<%
	}
%>
</body>
</html>











