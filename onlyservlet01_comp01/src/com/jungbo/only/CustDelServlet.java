package com.jungbo.only;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustDelServlet extends HttpServlet {

	private static final long serialVersionUID = 5058882855094577085L;
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
		sql=config.getInitParameter("delsql");
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
	private boolean isNull(String str){
		return str==null || str.trim().equals("");
	}
public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("euc-kr");
	response.setContentType("text/html; charset=euc-kr");
	PrintWriter out=response.getWriter();
	out.println("<html><head>");;
	out.println("<title>CustUser Lists</title>");
	out.println("</head><body>");
	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	int count=0;
	try{
		//--------�Ķ���� �ޱ�
		String id=request.getParameter("id");
		if(isNull(id)){
			response.sendRedirect("./index.html");
		}//
		//--------SQL
		conn=getConnection();
		psmt=conn.prepareStatement(sql);
		//���� �Ķ���� ������ ?�� �ֱ� 
		psmt.setString(1,id); 
		count=psmt.executeUpdate();
	}catch(SQLException se){
		System.out.println(se);
	}finally{
		close(conn, psmt, rs);
	}

	if(count>=1){//�Է¿� �����ϸ�
		out.println("<script type='text/javascript'>");
		out.println("alert('���������� ���� �����Ͽ����ϴ�.');");
		out.println("location.href='./custuserlist';");
		out.println("</script>");
	}else{
		out.println("<script type='text/javascript'>");
		out.println("alert('�� ������ �����Ͽ����ϴ�.');");
		out.println("location.href='./custaddform.html';");
		out.println("</script>");
	}
	out.println("</body></html>");
}//
}
