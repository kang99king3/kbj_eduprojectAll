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
<h1>글상세보기</h1>
<table border='1'>
	<tr>
		<th>작성자</th>
		<td><%=dto.getId()%></td>
	</tr>
	<tr>
		<th>글제목</th>
		<td><%=dto.getTitle()%></td>
	</tr>
	<tr>
		<th>글내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent() %></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="boardupdate.jsp?seq=<%=dto.getSeq()%>" title="수정폼으로 이동, 상세정보 출력">수정</a>
			<a href="after_delboard.jsp?seq=<%=dto.getSeq()%>">삭제</a>
		</td>
	</tr>
</table>
</body>
</html>










