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
<body>
<h1>나의정보수정</h1>
<form action="loginController.jsp" method="post" enctype="application/x-www-form-urlencoded">
<input type="hidden" name="command" value="userupdate"/>
<input type="hidden" name="id" value="${dto.id}"/>
<table border="1">
	<tr>
		<th>아이디</th>
		<td>${dto.id}</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${dto.name}</td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input type="text" name="address" value="${dto.address}"/></td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td><input type="tel" name="phone" value="${dto.phone}"/></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="email" name="email" value="${dto.email}"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="수정완료"/>
			<input type="button" value="나의정보"
			 onclick="location.href='loginController.jsp?command=userinfo&id=${dto.id}'"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>



