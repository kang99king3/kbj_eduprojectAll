<%@page import="com.hk.dtos.LoginDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	LoginDto dto=(LoginDto)request.getAttribute("dto");
%>
<body>
<h1>등급변경</h1>
<form action="loginController.jsp" method="post">
	<input type="hidden" name="command" value="roleChange"/>
	<input type="hidden" name="id" value="<%=dto.getId()%>"/>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><%=dto.getId()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=dto.getName()%></td>
		</tr>
		<tr>
			<th>등급</th>
			<td>
				<select name="role">
					<option value="ADMIN"
					 <%=dto.getRole().equals("ADMIN")?"selected":""%>>관리자</option>
					<option value="MANAGER" 
					 <%=dto.getRole().equals("MANAGER")?"selected":""%> >정회원</option>
					<option value="USER" 
					<%=dto.getRole().equals("USER")?"selected":""%>>일반회원</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>가입일</th>
			<td><%=dto.getRegdate()%></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="등급변경"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>













