<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%request.setCharacterEncoding("euc-kr"); %>
<%response.setContentType("text/html; charset=euc-kr"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
.a {
	background-color: #99CC66;
	color: blue;
}
input{width:300px}
</style>
</head>
<body>
<%
 String eid=request.getParameter("eid");
 String dname=request.getParameter("dname");
 String lastname=request.getParameter("lastname");
 String sal=request.getParameter("sal");
 
%>
<center>
<form action="updateAfter.jsp" method="post">
<table border="1">
			<col width="100">
			<col width="300">
			<caption>
				<b>【수정하기】</b>
			</caption>
			<tr>
				<td class="a">사원번호</td>
				<td><input type="text" name="eid" value="<%=eid %>" readonly></td>
			</tr>
			<tr>
				<td class="a">이름</td>
				<td><input type="text" name="eid" value="<%=lastname %>" readonly></td>
			</tr>
			<tr>
				<td class="a">부서명</td>
				<td><input type="text" name="eid" value="<%=dname %>" readonly></td>
			<tr>
				<td class="a">근무지역</td>
				<td><input type="text" name="city"></td>
			</tr>
			<tr>
				<td class="a">근무지 주소</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td class="a">연봉</td>
				<td><input type="text" name="eid" value="<%=sal %>" readonly></td>
			</tr>
			<tr>
				<td class="a">email</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td class="a">phone</td>
				<td><input type="text" name="phone"></td>
			</tr>
		</table>
		<input type="submit" style="width:50px" value="완료">
		<input type="button" style="width:100px" value="돌아가기" onclick="history.go(-1)">
		</form>
		</center>
</body>
</html>