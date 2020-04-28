<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	String eid=request.getParameter("eid");
	   Connection conn=null;
	   PreparedStatement psmt=null;
    try{
    Class.forName("oracle.jdbc.driver.OracleDriver");
    String url="jdbc:oracle:thin:@localhost:1521:xe";
    String user="hr";
    String passwd="hr";
    conn=DriverManager.getConnection(url,user,passwd);
    
    	/* �������� ���� �ؼ� ���õ� ���̺��� ������ ������Ų��.
    	   ������ foreign key�� �����Ѵ�
    	String sql1=" ALTER TABLE DEPARTMENTS DROP CONSTRAINT EMP_MANAGER_FK";
    	 cascade �ɼ��� ����Ͽ� foreign key �缳�� �Ѵ�. 
    	String sql2=" ALTER TABLE DEPARTMENTS "
  	      +" ADD CONSTRAINT EMP_MANAGER_FK FOREIGN KEY(DEPARTMENT_ID) "
            +" REFERENCES EMPLOYEES(EMPLOYEE_ID) ON DELETE CASCADE ";
    	 job_history ���̺��� ��Ȱ��ȭ �����ش�.
    	String sql3=" ALTER TABLE JOB_HISTORY "
    	           +" DISABLE CONSTRAINT JHIST_DEPT_FK";*/
    
    
    String sql="DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID=?";
    
    psmt=conn.prepareStatement(sql);
    psmt.setString(1,eid);
    psmt.executeUpdate();

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