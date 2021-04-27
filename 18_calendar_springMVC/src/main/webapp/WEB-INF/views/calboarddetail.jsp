<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="util" class="com.hk.board.utils.Util" />
<h1>일정상세보기</h1>
<table border="1">
		<tr>
			<th>ID</th>
			<td>${requestScope.dto.id}</td>
		</tr>
		<tr>
			<th>일정</th>
			<td>
				<jsp:setProperty property="toDates" name="util" value="${requestScope.dto.mdate}"/>
				<jsp:getProperty property="toDates" name="util"/>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${requestScope.dto.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly">${requestScope.dto.content}</textarea> </td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정하기"
				 onclick="location.href='updateform.do?seq=${dto.seq}'"/>
				<input type="button" value="삭제" 
				onclick="location.href='muldel.do?chk=${dto.seq}'"/>
				<input type="button" value="목록보기"
				 onclick="location.href='calboardlist.do'"/>
				<input type="button" value="달력보기" 
			onclick="location.href='calendar.do?year=${fn:substring(sessionScope.yyyyMMdd,0,4)}&month=${fn:substring(sessionScope.yyyyMMdd,4,6)}'" />
				 
			</td>
		</tr>
	</table>
	
</body>
</html>