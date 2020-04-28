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
<%
	int empno=Integer.parseInt(request.getParameter("empno"));
	String ename=request.getParameter("ename");
	String job=request.getParameter("job");
	int mgr=Integer.parseInt(request.getParameter("mgr"));
	int sal=Integer.parseInt(request.getParameter("sal"));
	int deptno=Integer.parseInt(request.getParameter("deptno"));
	
	//post방식으로 전달된 6개의 값을 DB에 추가해야 됨--> DAO객체 불러오기
	TestDao dao=new TestDao();
	boolean isS=dao.addEmployee(new TestDto(empno,ename,job,mgr,null,sal,0,deptno));
	if(isS){
		%>
		<script type="text/javascript">
			alert("신규사원을 등록합니다.");
			location.href="employeeList.jsp";
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
			alert("신규사원등록실패!!");
			location.href="emp_add.jsp";
		</script>
		<%
	}
	
%>

<%!     // TestDto dto=new TestDto(empno,ename,job,mgr,null,sal,0,deptno);
// 	public boolean addEmployee(TestDto dto){
		
// 	}
%>
</body>
</html>










