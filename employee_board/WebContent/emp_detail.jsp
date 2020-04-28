<%@page import="com.hk.test.dtos.TestDto"%>
<%@page import="com.hk.test.daos.TestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% //요청된 정보(request객체)에서 "empno"란 이름의 값을 가져온다.
	String empno=request.getParameter("empno");//전달하는 값은 모두 텍스트
	int empnoInt=Integer.parseInt(empno);//int형으로 변환 "7339"-> 7339
	TestDao dao=new TestDao();
	TestDto dto=dao.getEmplyee(empnoInt);// 1row에 대한 정보 반환--> Dto
%>
<h1>사원상세정보조회</h1>
<table border="1">
	<col width="100px"><col width="200px"/>
	<tr>
		<th>사원번호</th>
		<td><%=dto.getEmpno()%></td>
	</tr>
	<tr>
		<th>사원이름</th>
		<td><%=dto.getEname()%></td>
	</tr>
	<tr>
		<th>직업</th>
		<td><%=dto.getJob()%></td>
	</tr>
	<tr>
		<th>관리자번호</th>
		<td><%=dto.getMgr()%></td>
	</tr>
	<tr>
		<th>입사일</th>
		<td><%=dto.getHiredate()%></td>
	</tr>
	<tr>
		<th>급여</th>
		<td><%=dto.getSal()%></td>
	</tr>
	<tr>
		<th>커미션</th>
		<td><%=dto.getComm()%></td>
	</tr>
	<tr>
		<th>부서번호</th>
		<td><%=dto.getDeptno()%></td>
	</tr>
	<tr>
		<td colspan="2">
			<button onclick="location.href='index.jsp'">메인</button>
			<button onclick="location.href='employeeList.jsp'">사원목록</button>
			<button onclick="location.href='emp_update.jsp?empno=<%=dto.getEmpno()%>'">사원수정하기</button>
			<button onclick="location.href='emp_delete.jsp'">사원삭제</button>
		</td>
	</tr>
</table>
</body>
</html>









