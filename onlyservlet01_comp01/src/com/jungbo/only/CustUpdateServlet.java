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

public class CustUpdateServlet  extends HttpServlet {

	private static final long serialVersionUID = 8632210818951469253L;
	String driver;
	String url;
	String user;
	String passwd;
	String sql;
	public void init(ServletConfig config) throws ServletException {
		//------web xml conf. 읽기
		driver=config.getServletContext().getInitParameter("driver");
		passwd=config.getServletContext().getInitParameter("passwd");
		user=config.getServletContext().getInitParameter("user");
		url=config.getServletContext().getInitParameter("url");
		sql=config.getInitParameter("updatesql");
		//-----dirver loading
		try {
			Class.forName(driver);
			System.out.println(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("1/6 로딩 실패");
		}
	}//
	
	public Connection getConnection() throws SQLException{
		Connection conn=null;
		
		try {
			conn=DriverManager.getConnection(url,user,passwd);
		} catch (SQLException e) {
			System.out.println("2/6 연결 실실패");
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
		out.println("<html><head>");
		out.println("<title>CustUser Lists</title>");
		out.println("</head><body>");
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		int count=0;
		try{
			//--------파라메터 받기
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			if(isNull(id) ||isNull(name)||isNull(address) ){
				response.sendRedirect("./index.html");
			}//
			//--------SQL
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			//받은 파라메터 쿼리의 ?에 넣기 
			psmt.setString(1,name.trim()); 
			psmt.setString(2,address.trim()); 
			psmt.setString(3,id.trim()); 
			//updateCustUser 실행하기
			count=psmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se);
		}finally{
			close(conn, psmt, rs);
		}
		if(count>=1){//입력에 성공하면
			out.println("<script type='text/javascript'>");
			out.println("alert('성공적으로 정보를  수정하였습니다.');");
			out.println("location.href='./custuserlist';");
			out.println("</script>");
		}else{
			out.println("<script type='text/javascript'>");
			out.println("alert('정보 수정에 실패하였습니다.');");
			out.println("location.href='./custaddform.html';");
			out.println("</script>");
		}
		out.println("</body></html>");
	}//

}