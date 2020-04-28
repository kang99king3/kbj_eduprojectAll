<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	//EL 사용할때 값을 받는 방법
	//${requestScope.객체이름}  : ${requestScope.list}
	//${sessionScope.객체이름}
	//${applicationScope.객체이름}
	//${pageScope.객체이름}  : pageContext.setAttribute("key","value")
	//${param.값이름}

	//추가할 일정의 날짜를 받는다
	int year=Integer.parseInt(request.getParameter("year"));
	int month=Integer.parseInt(request.getParameter("month"));
	int date=Integer.parseInt(request.getParameter("date"));
	
	//현재시간을 구한다.
	Calendar cal=Calendar.getInstance();
	int hour=cal.get(Calendar.HOUR_OF_DAY);
	int min=cal.get(Calendar.MINUTE);
	
	//EL에서 꺼내 쓰기 위해 pageScope에 담아놓기 
	pageContext.setAttribute("hour", hour);
	pageContext.setAttribute("min", min);
%>
<body>
<h1>일정추가하기</h1>
<form action="CalController.do" method="post">
	<input type="hidden" name="command" value="insertCal"/>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" value="hk" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>일정</th>
			<td>
				<select name="year">
					<%
						for(int i=year-5;i<year+5;i++){
							%>
							<option  <%=year==i?"selected":""%> value="<%=i%>"><%=i%></option>
							<%
						}
					%>
				</select>년
				<select name="month">
					<%
						for(int i=1;i<=12;i++){
							%>
							<option  <%=month==i?"selected":""%> value="<%=i%>"><%=i%></option>
							<%
						}
					%>
				</select>월
				<select name="date">
					<%
						for(int i=1;i<=31;i++){
							%>
							<option  <%=date==i?"selected":""%> value="<%=i%>"><%=i%></option>
							<%
						}
					%>
				</select>일
				<select name="hour">
					<%
						for(int i=0;i<=23;i++){
							%>
							<option  <%=hour==i?"selected":""%> value="<%=i%>"><%=i%></option>
							<%
						}
					%>
				</select>시
				<select name="min">
					<%
						for(int i=0;i<=59;i++){
							%>
							<option  <%=min==i?"selected":""%> value="<%=i%>"><%=i%></option>
							<%
						}
					%>
				</select>분
			</td>
		</tr>
		<tr>
			<th>일정(JSTL)</th>
			<td>
				<select name="jyear">
					<c:forEach step="1" var="i" begin="${param.year-5}" end="${param.year+5}">
						<option ${param.year==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>
				</select>년
				<select name="jmonth">
					<c:forEach step="1" var="i" begin="1" end="12">
						<option ${param.month==i?"selected":""} value="${i}">${i}</option>
					</c:forEach>
				</select>월
				<select name="jdate">
					<c:forEach step="1" var="i" begin="1" end="31">
						<option value="${i}" ${param.date==i?"selected":""} >${i}</option>
					</c:forEach>
				</select>일
				<select name="jhour">
					<c:set var="hourParam" value="${pageScope.hour}" />
					<c:forEach step="1" var="i" begin="0" end="23">
						<option value="${i}" ${hourParam==i?"selected":""} >${i}</option>
					</c:forEach>
				</select>시
				<select name="jmin">
					<c:set var="minParam" value="${pageScope.min}" />
					<c:forEach step="1" var="i" begin="0" end="59">
						<option value="${i}" ${minParam==i?"selected":""} >${i}</option>
					</c:forEach>
				</select>분
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" rows="10" cols="60"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="일정추가"/>
				<input type="button" value="달력"
				onclick="location.href='CalController.do?command=calendar'"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>













