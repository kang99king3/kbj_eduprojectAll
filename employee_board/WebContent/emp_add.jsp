<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>사원등록하기</h1>
<form action="after_emp_add.jsp" method="post">
	<table border="1">
		<col width="100px"><col width="200px"/>
		<tr>
			<th>사원번호</th>
			<td><input type="text" name="empno"/></td>
		</tr>
		<tr>
			<th>사원이름</th>
			<td><input type="text" name="ename"/></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" name="job"/></td>
		</tr>
		<tr>
			<th>관리자번호</th>
			<td><input type="text" name="mgr"/></td>
		</tr>
		<tr>
			<th>급여</th>
			<td><input type="text" name="sal"/></td>
		</tr>
		<tr>
			<th>부서번호</th>
			<td><input type="text" name="deptno"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="등록">
				<button type="button" onclick="location.href='index.jsp'">메인</button>
				<button type="button" onclick="location.href='employeeList.jsp'">사원목록</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>