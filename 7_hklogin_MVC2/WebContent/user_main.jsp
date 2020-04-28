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
// 	LoginDto ldto=(LoginDto)session.getAttribute("ldto");
%>
<body>
<h1>일반회원메인</h1>
<div><%=ldto.getId()%>님 반갑습니다.
     (등급:<%=ldto.getRole().equals("USER")?"일반회원":"정회원"%>)
	 <a href="LoginController.do?command=logout">로그아웃</a>     
</div>

<ul>
	<li>
		<a href="LoginController.do?command=userinfo&id=<%=ldto.getId()%>">
		나의 정보</a>
	</li>
</ul>
</body>
</html>










