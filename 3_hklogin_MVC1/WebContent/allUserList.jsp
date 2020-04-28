<%@page import="com.hk.dtos.LoginDto"%>
<%@page import="java.util.List"%>
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
	List<LoginDto>list=(List<LoginDto>)request.getAttribute("list");
%>
<body>
<h1>회원정보상태조회</h1>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>주소</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th>사용여부</th>
		<th>회원등급</th>
		<th>가입일</th>
	</tr>
	<%
		if(list==null||list.size()==0){
			out.print("<tr><td colspan='8'>"
			         +"---회원이 존재하지 않습니다.---</td></tr>");
		}else{
			for(LoginDto dto:list){
				%>
				<tr>
					<td><%=dto.getId()%></td>
					<td><%=dto.getName()%></td>
					<td><%=dto.getAddress()%></td>
					<td><%=dto.getPhone()%></td>
					<td><%=dto.getEmail()%></td>
					<td><%=dto.getEnabled()%></td>
					<td><%=dto.getRole()%></td>
					<td><%=dto.getRegdate()%></td>
				</tr>
				<%
			}
		}
	%>
	<tr>
		<td colspan="8">
			<a href="admin_main.jsp">메인</a>
		</td>
	</tr>
</table>
</body>
</html>














