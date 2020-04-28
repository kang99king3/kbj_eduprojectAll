<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.hk.test.dtos.TestDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.test.daos.TestDao"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	th{background: orange;}
	td{text-align: center;}
</style>
</head>
<body>
<%  // *.java  --> compile --> *.class --> JVM 실행
	// *.jsp  --> *_jsp.class 로 컴파일 되서 실행--> class파일은 java파일--> servlet
	//사원목록보기페이지를 요청--> DB에 연결해서 사원정보를 조회--> DAO 클래스
	TestDao dao=new TestDao();
	List<TestDto> list=dao.getAllList();
%>
<h1>사원전체목록보기</h1>
<table border="1">
	<col width="100px">
	<col width="150px">
	<col width="200px">
	<tr>
		<th>사원번호</th>
		<th>사원이름</th>
<!-- 		<th>직업</th> -->
<!-- 		<th>관리자번호</th> -->
		<th>입사일</th>
<!-- 		<th>급여</th> -->
<!-- 		<th>커미션</th> -->
<!-- 		<th>부서번호</th> -->
	</tr>
	<%  //list = [dto,dto,dto,.....]
		for(int i=0;i<list.size();i++){
			TestDto dto=list.get(i);//각각 dto를 하나씩 꺼낸다
			%>
			<tr>
				<td><%=dto.getEmpno()%></td>
				<td>
					<a href="emp_detail.jsp?empno=<%=dto.getEmpno()%>">
					<%=dto.getEname()%></a>
				</td>
				<td><%=dto.getHiredate()%></td>
			</tr>
			<% 
		}
  	%> 
	<tr>
		<td colspan="3">
			<button onclick="addEmp()">사원등록</button>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function addEmp(){
		location.href="emp_add.jsp";
	}
</script>
<%@include file="emp_add.jsp" %>
</body>
</html>




















