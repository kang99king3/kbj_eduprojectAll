<%@page import="com.hk.test.dtos.TestDto"%>
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
<%  //전달된 파라미터 "empno"의 값을 받기
	int empno=Integer.parseInt(request.getParameter("empno"));
	TestDao dao=new TestDao();
	TestDto dto=dao.getEmplyee(empno);
%>
<h1>사원정보수정</h1>
<form action="after_emp_update.jsp" method="post">
	<input type="hidden" name="empno" value="<%=dto.getEmpno()%>"/>
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
			<td><input type="text" name="sal" value="<%=dto.getSal()%>"/></td>
		</tr>
		<tr>
			<th>커미션</th>
			<td><input type="text" name="comm" value="<%=dto.getComm()%>"/></td>
		</tr>
		<tr>
			<th>부서</th>
			<td><select name="deptno">
					<option <%=dto.getDeptno()==10?"selected":""%> value="10">ACCOUNTING</option>
					<option <%=dto.getDeptno()==20?"selected":""%> value="20">RESEARCH</option>
					<option <%=dto.getDeptno()==30?"selected":""%> value="30">SALES</option>
					<option <%=dto.getDeptno()==40?"selected":""%> value="40">OPERATIONS</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정완료"/>
				<button type="button" onclick="location.href='index.jsp'">메인</button>
				<button type="button" onclick="location.href='employeeList.jsp'">사원목록</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>








