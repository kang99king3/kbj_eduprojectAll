<%@page import="com.hk.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// 	String seqs=request.getParameter("param");
// 	if(seqs==null){
// 		pageContext.forward("index.jsp");
// 		response.sendRedirect("index.jsp");
// 	}
// 	int seq=Integer.parseInt(seqs);
	
	HkDao dao=new HkDao();//DAO객체 생성
	List<HkDto> list=dao.getAllList();//글목록조회하는 메서드 실행
%>
<h1>글목록 조회하기</h1>
<table border="1">
	<col width="50px"><col width="100px"><col width="300px"><col width="200px">
	<tr>
		<th>글번호</th><th>작성자</th><th>글제목</th><th>작성일</th>
	</tr>
	<%  //향상된 for문
		for(HkDto dto:list){
			%>
			<tr>
				<td><%=dto.getSeq()%></td>
				<td><%=dto.getId()%></td>
				<td><a href="boarddetail.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
				<td><%=dto.getRegdate()%></td>
			</tr>
			<%
		}
	%>
	<tr>
		<td colspan="4">
			<a href="addboard.jsp">글쓰기</a>
		</td>
	</tr>
</table>
</body>
</html>