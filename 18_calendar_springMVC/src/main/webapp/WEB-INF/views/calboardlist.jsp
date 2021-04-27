<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Time"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hk.board.dtos.CalDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
// 	list를 받는 코드작성이 필요
// 	List<CalDto>list=(List<CalDto>)request.getAttribute("list");
%>
<body>
<jsp:useBean id="util" class="com.hk.board.utils.Util" />
<h1>일정목록보기</h1>
<form action="muldel.do" method="post">
<table border="1">
	<col width="50px" >
	<col width="50px" >
	<col width="300px" >
	<col width="250px" >
	<col width="250px" >
	<tr>
		<th><input type="checkbox" name="allchk" onclick="allChk(this.checked)" /> </th>
		<th>번호</th>
		<th>제목</th>
		<th>일정</th>
		<th>작성일</th>
	</tr>
	<c:choose>
		<c:when test="${empty list}">
			<tr>
				<td colspan="5">--작성된 일정이 없습니다.--</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td><input type="checkbox" name="chk" value="${dto.seq}"/> </td>
					<td>${dto.seq}</td>
					<td><a href="calboarddetail.do?seq=${dto.seq}">${dto.title}</a></td>
					<td>
						<jsp:setProperty property="toDates" name="util" value="${dto.mdate}"/>
						<jsp:getProperty property="toDates" name="util"/>
					</td>
					<td><fmt:formatDate pattern="yyyy년MM월dd일" value="${dto.regdate}"/> </td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="5">
			<input type="submit" value="삭제"/>
			<input type="button" value="달력보기" 
			onclick="location.href='calendar.do?year=${fn:substring(sessionScope.yyyyMMdd,0,4)}&month=${fn:substring(sessionScope.yyyyMMdd,4,6)}'" />
		</td>
	</tr>
</table>
</form>

</body>
</html>






