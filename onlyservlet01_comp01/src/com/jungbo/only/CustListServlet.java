package com.jungbo.only;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustListServlet extends HttpServlet {
	private static final long serialVersionUID = -742219224855176087L;
	String driver;
	String url;
	String user;
	String passwd;
	String sql;
	public void init(ServletConfig config) throws ServletException {
		//------web xml conf. �б�
		driver=config.getServletContext().getInitParameter("driver");
		passwd=config.getServletContext().getInitParameter("passwd");
		user=config.getServletContext().getInitParameter("user");
		url=config.getServletContext().getInitParameter("url");
		sql=config.getInitParameter("listsql");
		//-----dirver loading
		try {
			Class.forName(driver);
			System.out.println(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("1/6 �ε� ����");
		}
	}//
	
	public Connection getConnection() throws SQLException{
		Connection conn=null;
		
		try {
			conn=DriverManager.getConnection(url,user,passwd);
		} catch (SQLException e) {
			System.out.println("2/6 ���� �ǽ���");
			throw e;
		}
		return conn;
	}//
	private void close(Connection conn, Statement psmt, ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if(psmt!=null){
			try {
				psmt.close();
			} catch (SQLException e) {
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}//  

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess( request,  response);
	}
  public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("euc-kr");
	response.setContentType("text/html; charset=euc-kr");
	PrintWriter out=response.getWriter();
	out.println("<html>");
	out.println("<head>");
	out.println("<title>CustUser Lists</title>");
	out.println("<script type='text/javascript'>");
	out.println("function deletechecks(e){");
	out.println("	var x=document.getElementsByName('delck');");
	out.println("	var lng=x.length;");
	out.println("	for(i=0;i<lng;++i){");
	out.println("		x(i).checked=e;");
	out.println("	}");
	out.println("}");
	out.println("</script>");
	out.println("</head>");
	out.println("<body>");
	out.println("<center>");
	out.println("<form action='./muldelcustuser' method='post'>");
	out.println("<table width='700' border='0' cellpadding='0' cellspacing='0'>");
	out.println("<col width='100'><col width='300'><col width='300'>");
	out.println("<tr><td height='2' bgcolor='#0000ff' colspan='3'></td></tr>");
	out.println("<tr bgcolor='#F6F6F6'>");
	out.println("	<td bgcolor='YELLOW' align='center'>");
	out.println("	<input type='checkbox' name='alldel'  " +
			" onclick='deletechecks(this.checked)'/>");
	out.println("	</td>");
	out.println("	<td>�� �� ��</td>");
	out.println("	<td>��     ��</td>");
	out.println("</tr>");
	out.println("<tr><td height='1' bgcolor='#0000ff' colspan='3'></td></tr>");
	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	int count=0;
try{
conn=getConnection();
psmt=conn.prepareStatement(sql);
rs=psmt.executeQuery();
while(rs.next()){
	count++;
	out.println("<tr bgcolor='#F6F6D6'>");
	out.println("  <td align='center' bgcolor='yellow'>");
	out.println("  <input type='checkbox' name='delck' value='"+rs.getString("ID")+"'/>");
	out.println("  </td>");
	out.println("  <td>"+rs.getString("ID")+"</td>");
	out.println("  <td><a href='./getcustuser?id="+rs.getString("ID")+"'>"+rs.getString("NAME")+"</a></td>");
	out.println("</tr>");
	out.println("<tr><td height='1' bgcolor='#c0c0c0' colspan='3'></td></tr>");
}
}catch(SQLException se){
	
}finally{
	close(conn, psmt, rs);
}
	if(count==0){//�������� ������
	out.println("<tr bgcolor='#F6F6D6'>");
	out.println("<td colspan='3' align='center'>�� ����Ʈ�� �������� �ʽ��ϴ�.</td></tr>");	
	}
	out.println("	<tr>");
	out.println("		<td align='center' height='1' bgcolor='#c0c0c0' colspan='3'>");
	out.println("		<input type='submit'  value='����������'/>");
	out.println("		</td>");
	out.println("	</tr>");
	out.println("<tr><td height='2' bgcolor='#0000ff' colspan='3'></td></tr>");
	out.println("<tr bgcolor='#F6F6D6'>");
	out.println("<td colspan='3'><a href='custaddform.html'>���߰��ϱ�</a></td>");
	out.println("</tr></table></form><br/>");
	out.println("<a href='index.html'>Home</a><br/>");
	out.println("</center></body></html>");
 }//
  @Override
public void destroy() {
	// TODO Auto-generated method stub
	super.destroy();
}
}//






