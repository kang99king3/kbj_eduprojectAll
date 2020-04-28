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

public class CustDetailServlet extends HttpServlet {

	private static final long serialVersionUID = -5581757997721323939L;
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
		sql=config.getInitParameter("selectsql");
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
			if(isNull(id)){
				response.sendRedirect("./index.html");
			}//
			out.println("<center>");
			out.println("<div id='Content'>");
			out.println("<table width='600' border='0' cellpadding='0' cellspacing='0'>");
			out.println("<col width='200'><col width='400'>");
			out.println("<tr><td height='2' bgcolor='#0000ff' colspan='2'></td></tr>");
			//--------SQL
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			//받은 파라메터 쿼리의 ?에 넣기 
			psmt.setString(1,id); 
			rs=psmt.executeQuery();
			while(rs.next()){
				count++;
				out.println(" <tr bgcolor='#F6F6F6'>");
				out.println(" <td>아 이 디</td>");
				out.println(" <td>"+rs.getString("ID")+"</td>");
				out.println(" </tr>");
				out.println("<tr><td height='1' bgcolor='#0000ff' colspan='2'></td></tr>");
				out.println("<tr bgcolor='#F6F6F6'>");
				out.println(" <td>이      름</td>");
				out.println(" <td>"+rs.getString("NAME")+"</td>");
				out.println(" </tr>");
				out.println("<tr><td height='1' bgcolor='#0000ff' colspan='2'></td></tr>");
				out.println("<tr bgcolor='#F6F6F6'>");
				out.println(" <td>주      소</td>");
				out.println(" <td>"+rs.getString("ADDRESS")+"</td>");
				out.println(" </tr>");
				out.println("<tr><td height='1' bgcolor='#0000ff' colspan='2'></td></tr>");
				out.println("<tr bgcolor='#F6F6F6'>");
				out.println(" <td>고 객 정 보 변 경</td>");
				out.println(" <td>");
				out.println("<form action='./custbfupdate' method='post'>");
				out.println("      <input type='hidden' name='id' value='"+rs.getString("ID")+"'/>");
				out.println("      <input type='submit'  value='고객정보변경'/>");
				out.println("  </form>");
				out.println("</td>");
				out.println(" </tr>");
				out.println("<tr><td height='1' bgcolor='#0000ff' colspan='2'></td></tr>");
				out.println("<tr bgcolor='#F6F6F6'>");
				out.println(" <td>삭      제</td>");
				out.println(" <td>");
				out.println("<form action='./delcustuser' method='post'>");
				out.println("      <input type='hidden' name='id' value='"+rs.getString("ID")+"'/>");
				out.println("    <input type='submit'  value='고객정보삭제'/>");
				out.println("</form>");
				out.println("</td>");
				out.println(" </tr>");
				out.println("<tr><td height='1' bgcolor='#0000ff' colspan='2'></td></tr>");
				out.println("</table>");
				out.println("<br/>");
				out.println("<a href='index.html'>Home</a><br/>");
			}
		}catch(SQLException se){
			System.out.println(se);
		}finally{
			close(conn, psmt, rs);
		}
		out.println("</div>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}//

}
