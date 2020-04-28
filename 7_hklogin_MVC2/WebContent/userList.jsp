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
	List<LoginDto> list=(List<LoginDto>)request.getAttribute("list");
%>
<body>
<h1>회원목록조회</h1>
<table border="1">
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>등급</th>
		<th>가입일</th>
	</tr>
	<%
		for(int i=0;i<list.size();i++){
			LoginDto dto=list.get(i);
			%>
			<tr>
				<td><%=i+1%></td>
				<td><%=dto.getId()%></td>
				<td><%=dto.getName()%></td>
				<td><%=dto.getRole()%>
					<button onclick="auth('<%=dto.getId()%>')">변경</button>
				</td>
				<td><%=dto.getRegdate()%></td>
			</tr>
			<%
		}
	%>
	<tr>
		<td colspan="5"><button onclick="location.href='admin_main.jsp'">메인</button> </td>
	</tr>
</table>
<script type="text/javascript">
	function auth(id){
		//등급변경 폼으로 이동
		location.href="LoginController.do?command=roleForm&id="+id;
	}
</script>
</body>
</html>















