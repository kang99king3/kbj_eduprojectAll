<%@page import="com.hk.dtos.HkDto"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	//상세보기 할 글의 seq값을 전달받는다.
	int seq=Integer.parseInt(request.getParameter("seq"));
	HkDao dao=new HkDao();
	HkDto dto=dao.getBoard(seq);
%>
<body>
<h1>글수정하기</h1>
<form action="after_boardupdate.jsp" method="post">
	<input type="hidden" name="seq" value="<%=dto.getSeq()%>"/>
	<table border='1'>
		<tr>
			<th>작성자</th>
			<td><%=dto.getId()%></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td><input type="text" name="title" value="<%=dto.getTitle()%>"/></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td><textarea rows="10" cols="60" name="content"><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정완료"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>










