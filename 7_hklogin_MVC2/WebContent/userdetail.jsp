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
<body>
<h1>나의정보</h1>
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
		<td>${dto.address}</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>${dto.phone}</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${dto.email}</td>
	</tr>
	<tr>
		<td colspan="2">
			<button onclick="updateUserForm('${dto.id}')">수정</button>
			<button onclick="delUser('${dto.id}')">탈퇴</button>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function updateUserForm(id){
		location.href="LoginController.do?command=updateForm&id="+id;
	}
	function delUser(id){
		location.href="LoginController.do?command=delUser&id="+id;
	}
	
</script>
</body>
</html>










