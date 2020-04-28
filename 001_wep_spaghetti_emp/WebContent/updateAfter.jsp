<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%request.setCharacterEncoding("euc-kr");%>
<%response.setContentType("text/html; charset=euc-kr");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
 String city=request.getParameter("city");
 String address=request.getParameter("address");
 String email=request.getParameter("email");
 String phone=request.getParameter("phone");
 String eid=request.getParameter("eid");
 
 Class.forName("oracle.jdbc.driver.OracleDriver");
 Connection conn=null;
 PreparedStatement psmt=null;
 
 String url="jdbc:oracle:thin:@localhost:1521:xe";
 String user="hr";
 String passwd="hr";
 
 String sql1="UPDATE EMPLOYEES SET EMAIL=?, PHONE_NUMBER=? WHERE EMPLOYEE_ID=?";
 String sql2="UPDATE LOCATIONS SET CITY=?, STREET_ADDRESS=?"
		   +" WHERE LOCATION_ID=(SELECT D.LOCATION_ID FROM EMPLOYEES E JOIN DEPARTMENTS D"
		   +" ON E.DEPARTMENT_ID=D.DEPARTMENT_ID WHERE EMPLOYEE_ID=?)";
 
 try{
 conn=DriverManager.getConnection(url,user,passwd);
 conn.setAutoCommit(false);
 psmt=conn.prepareStatement(sql1);
 psmt.setString(1,email);
 psmt.setString(2,phone);
 psmt.setString(3,eid);
 psmt.executeUpdate();
 psmt.clearParameters();
 psmt=conn.prepareStatement(sql2);
 psmt.setString(1,city);
 psmt.setString(2,address);
 psmt.setString(3,eid);
 psmt.executeUpdate();
 conn.commit();
 }catch(Exception e){
	 e.printStackTrace();
 }finally{
	 try {
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 }
 
 response.sendRedirect("employeelist.jsp");
 %>
</body>
</html>






