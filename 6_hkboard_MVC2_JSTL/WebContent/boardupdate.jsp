<%@page import="com.hk.dtos.HkDto"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- <%request.setCharacterEncoding("utf-8"); %> --%>
<%-- <%response.setContentType("text/html;charset=utf-8"); %> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글수정하기</h1>
<form action="HkController.do" method="post">
	<input type="hidden" name="command" value="updateboard"/>
	<input type="hidden" name="seq" value="${dto.seq}"/>
	<table border='1'>
		<tr>
			<th>작성자</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>글제목</th>
			<td><input type="text" name="title" value="${dto.title}"/></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td><textarea rows="10" cols="60" name="content">${dto.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정완료"/>
				<a href="HkController.do?command=boardlist">글목록</a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>










