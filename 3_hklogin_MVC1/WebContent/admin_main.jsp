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

<h1>관리자메인</h1>
<div>${sessionScope.ldto.id}님 반갑습니다.
     (등급:${sessionScope.ldto.role == 'ADMIN'?'관리자':''})
     	  
	 <a href="loginController.jsp?command=logout">로그아웃</a>     
</div>

<ul>
	<li>
		<a href="loginController.jsp?command=allUserStatus">
		회원상태 정보조회</a>
	</li>
	<li>
		<a href="loginController.jsp?command=allUserList">
		회원정보 목록조회</a>
	</li>
</ul>
</body>
</html>





