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
public class CustMulDelServlet extends HttpServlet {
	private static final long serialVersionUID = 6554225282968841520L;
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
		sql=config.getInitParameter("muldelsql");
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
	
	String[] ids=request.getParameterValues("delck");
	int[] count=new int[ids.length];
	try{
		//--------SQL
		conn=getConnection();
		conn.setAutoCommit(false);
		psmt=conn.prepareStatement(sql);
		for(int i=0;i<ids.length; i++){
			psmt.setString(1,ids[i].trim()); 
			psmt.addBatch();
		}//
		
		//updateCustUser 실행하기
		count=psmt.executeBatch();
		conn.commit();
	}catch(SQLException se){
		try {
			conn.rollback();
		} catch (SQLException e) {
			
		}
		System.out.println(se);
	}finally{
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			
		}
		close(conn, psmt, rs);
	}
	boolean isS=true;
	for(int i=0; i<count.length; i++){
		if(count[i]!=-2){
			isS=false;
			break;
		}
	}
	if(isS){//입력에 성공하면
		out.println("<script type='text/javascript'>");
		out.println("alert('성공적으로 고객을 삭제하였습니다.');");
		out.println("location.href='./custuserlist';");
		out.println("</script>");
	}else{
		out.println("<script type='text/javascript'>");
		out.println("alert('삭제에 실패하였습니다.');");
		out.println("location.href='./custaddform.html';");
		out.println("</script>");
	}
	out.println("</body></html>");
}//

}
