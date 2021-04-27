<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="calupdate.do" method="post">
<input type="hidden" name="seq" value="${dto.seq}"/>
<table border="1">
		<tr>
			<th>ID</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>일정</th>
			<td>
				<select name="year">
					<c:set var="year" value="${fn:substring(dto.mdate,0,4)}" />
					<c:forEach var="i" step="1" begin="${year-5}" end="${year+5}">
						<option ${year==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>	
				</select>년
				<select name="month">
					<c:set var="month" value="${fn:substring(dto.mdate,4,6)}" />
					<c:forEach var="i" step="1" begin="1" end="12">
						<option ${month==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>	
				</select>월
				<select name="date">
					<c:set var="date" value="${fn:substring(dto.mdate,6,8)}" />
					<c:forEach var="i" step="1" begin="1" end="31">
						<option ${date==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>
				</select>일
				<select name="hour">
					<c:set var="hour" value="${fn:substring(dto.mdate,8,10)}" />
					<c:forEach var="i" step="1" begin="0" end="23">
						<option ${hour==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>
				</select>시
				<select name="min">
					<c:set var="min" value="${fn:substring(dto.mdate,10,12)}" />
					<c:forEach var="i" step="1" begin="0" end="59">
						<option ${min==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>
				</select>분
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${dto.title}" required="required"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="content" required="required">${dto.content}</textarea> </td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="일정수정하기"/>
				<input type="button" value="목록보기"
				 onclick="location.href='calboardlist.do'"/>
				<input type="button" value="달력보기" 
			onclick="location.href='calendar.do?year=${fn:substring(sessionScope.yyyyMMdd,0,4)}&month=${fn:substring(sessionScope.yyyyMMdd,4,6)}'" />
			</td>
		</tr>
	</table>
</form>
</body>
</html>