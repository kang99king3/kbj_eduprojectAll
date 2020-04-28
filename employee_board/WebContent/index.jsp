<%@page import="com.hk.test.dtos.TestDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.test.daos.TestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!  //선언부: 메서드 정의
	public void ret(){
	
	}
%>
<%  //실행부: 코드 실행
	ret();
 	String s="실행부-JAVA영역";
%>
<h1>사원관리시스템</h1>
<%-- <strong><%=s%></strong> --%>
<a href="employeeList.jsp">사원목록보기</a>
<%
	String sss="test";
	out.write("<b>"+sss+"</b>");
%>

</body>
</html>









