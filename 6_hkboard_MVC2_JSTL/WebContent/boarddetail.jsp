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
<h1>글상세보기</h1>
<table border='1'>
	<tr>
		<th>작성자</th>
		<td>${requestScope.dto.id}</td>
	</tr>
	<tr>
		<th>글제목</th>
		<td>${dto.title}</td>
	</tr>
	<tr>
		<th>글내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly">${dto.content}</textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="HkController.do?command=updateform&seq=${dto.seq}" title="수정폼으로 이동, 상세정보 출력">수정</a>
			<a href="HkController.do?command=delboard&seq=${dto.seq}">삭제</a>
			<a href="HkController.do?command=boardlist">글목록</a>
		</td>
	</tr>
</table>
</body>
</html>










