<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일정수정하기</h1>
<form action="CalController.do" method="post">
	<input type="hidden" name="command" value="updateCal"/>
	<input type="hidden" name="seq" value="${dto.seq}"/>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>일정(JSTL)</th>
			<td>
				<select name="year">
					<c:set var="yearP" value="${fn:substring(dto.mdate,0,4)}" />
					<c:forEach step="1" var="i" begin="${yearP-5}" end="${yearP+5}">
						<option ${yearP==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>
				</select>년
				<select name="month">
					<c:set var="monthP" value="${fn:substring(dto.mdate,4,6)}" />
					<c:forEach step="1" var="i" begin="1" end="12">
						<option ${monthP==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>
				</select>월
				<select name="date">
					<c:set var="dateP" value="${fn:substring(dto.mdate,6,8)}" />
					<c:forEach begin="1" end="31" step="1" var="i">
						<option ${dateP==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>
				</select>일
				<select name="hour">
					<c:set var="hourP" value="${fn:substring(dto.mdate,8,10)}" />
					<c:forEach begin="0" end="23" step="1" var="i">
						<option ${hourP==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>
				</select>시
				<select name="min">
					<c:set var="minP" value="${fn:substring(dto.mdate,10,12)}" />
					<c:forEach begin="0" end="59" step="1" var="i">
						<option ${minP==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>
				</select>분
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${dto.title}"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" rows="10" cols="60">${dto.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="일정수정"/>
				<input type="button" value="상세일정" 
				onclick="location.href='CalController.do?command=calDetail&seq=${dto.seq}'"/>
				<input type="button" value="달력"
				onclick="location.href='CalController.do?command=calendar'"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>