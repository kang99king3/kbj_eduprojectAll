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
<%
	int sal=Integer.parseInt(request.getParameter("sal"));
	int comm=Integer.parseInt(request.getParameter("comm"));
	int deptno=Integer.parseInt(request.getParameter("deptno"));
	int empno=Integer.parseInt(request.getParameter("empno"));
	TestDao dao=new TestDao();
	boolean isS=dao.updateEmployee(sal, comm, deptno, empno);
	if(isS){
		%>
		<script type="text/javascript">
			alert("사원정보를 수정합니다.");
			location.href="emp_detail.jsp?empno=<%=empno%>";
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
			alert("사원 정보 수정 실패!!");
			location.href="emp_update.jsp?empno=<%=empno%>";
		</script>
		<%
	}
%>
</body>
</html>









