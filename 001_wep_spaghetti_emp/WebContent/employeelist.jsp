<%@page import="com.kbj.web.DepartMentDto"%>
<%@page import="com.kbj.web.EmployeeDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%request.setCharacterEncoding("euc-kr"); %>
<%response.setContentType("text/html; charset=euc-kr"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>리스트</title>
<style type="text/css">
	
	th{
	   background-color:blue;
	   color:white;
	  }
	td{
	   background-color:#99FF99;
	   text-align:center;
	   }
</style>

</head>
<body>
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
    String url="jdbc:oracle:thin:@localhost:1521:xe";
    String user="hr";
    String passwd="hr";
    Connection conn=null;
    PreparedStatement psmt=null;
    ResultSet rs=null;
    List<EmployeeDto> list=new ArrayList<EmployeeDto>();
    List<DepartMentDto> plist=new ArrayList<DepartMentDto>();
    
//     try{
    conn=DriverManager.getConnection(url,user,passwd);
    String sql="SELECT EMPLOYEE_ID,FIRST_NAME,D.DEPARTMENT_NAME"
             +" FROM EMPLOYEES E JOIN DEPARTMENTS D"
             +" ON E.DEPARTMENT_ID=D.DEPARTMENT_ID";
  
    psmt=conn.prepareStatement(sql);
    rs=psmt.executeQuery();
    while(rs.next()){
    	EmployeeDto dto=new EmployeeDto();
    	DepartMentDto pdto=new DepartMentDto();
    	dto.setEmployee_id(rs.getInt("employee_id"));
    	dto.setFirst_name(rs.getString("first_name"));
    	pdto.setDepartment_name(rs.getString("department_name"));
    	list.add(dto);
    	plist.add(pdto);
       }
//     }catch(Exception e){
//     	System.out.println(e);
//     }finally{
//     	try{
      if(rs!=null){
    	   rs.close();
      }
      if(psmt!=null){
       	   psmt.close();
         }
      if(conn!=null){
       	   conn.close();
         }
//     	}catch(Exception e){
//     		e.printStackTrace();
//     	}
//     }
%>
<center>
<form action="addlist.jsp" method="post">
<table>
<caption><b>◎사원 리스트◎</b></caption>
<col width="100"><col width="150"><col width="200">
	<tr>
<!-- 	    <th>선택</th> -->
	    <th>사원번호</th>
	    <th>이름</th>
	    <th>부서이름</th>
	</tr>
	<%
	  for(int i=0;i<list.size();i++){
		  EmployeeDto dto=list.get(i);
		  DepartMentDto pdto=plist.get(i);
		  %>
	<tr>
	    <td id="<%=dto.getEmployee_id()%>"><a href="detail.jsp?id=<%=dto.getEmployee_id()%>"><%=dto.getEmployee_id() %></a></td>
	    <td><%=dto.getFirst_name() %></td>
	    <td><%=pdto.getDepartment_name() %></td>
	</tr>
		  <%
	  }
	%>    
</table>
</form>
</center>
</body>
</html>
