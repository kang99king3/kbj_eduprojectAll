package com.hk.test.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hk.test.dtos.TestDto;

//DAO 클래스 : 데이터에 접근해서 CRUD를 실행하는 객체
public class TestDao {

	
	//JDBC 구현 6단계 작성
	//1단계: 드라이버로딩
	public TestDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("1단계:드라이버로딩성공");
		} catch (ClassNotFoundException e) {
			System.out.println("1단계:드라이버로딩실패:"+getClass());
			e.printStackTrace();
		}
	}
	
	//2단계: DB연결--> Connection객체생성(url,user,pw)
	//메서드 문법: 접근제한자 + 반환타입 + 메서드명 (){ return 값;}
	//예외처리 2가지방법: try~catch(직접처리), throws~~(다른곳에서 처리: 예외던지기)
	public Connection getConnection() throws SQLException {
		Connection conn=null;
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="scott";
		String password="tiger";
		conn=DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	//3단계:쿼리준비, 4단계: 쿼리 실행 , 5단계: 쿼리 결과 받기
	
	//기능구현
	//1. 사원의 모든 정보를 조회하자!!: dto->1행의 정보저장, 여러행을 저장하려면? 
	//   List[dto,dto,dto,....]--> 여러행을 저장하려면 필통들을 가방에 넣자
	public List<TestDto> getAllList(){
		List<TestDto> list=new ArrayList<>();
		
		Connection conn=null;//DB계정에 연결할때 사용할 객체
		PreparedStatement psmt=null;//쿼리 준비할때 사용할 객체
		ResultSet rs=null;//DB결과를 받을 때 사용할 객체
		
		String sql=" SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, "
				 + " SAL, COMM, DEPTNO "  
				 + " FROM EMP_BAK ";         
		
		try {
			conn=getConnection();//DB연결됨
//			System.out.println("2단계:DB연결성공");
			
			psmt=conn.prepareStatement(sql);
//			System.out.println("3단계:쿼리준비성공");
			
			rs=psmt.executeQuery();//select문을 실행할때 사용
			//psmt.executeUpdate();//table의 데이터가 변경될때 사용--> update,insert,delete문실행할때 사용
//			System.out.println("4단계:쿼리실행성공");
//			String sql=" SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, "
//					 + " SAL, COMM, DEPTNO "  
//					 + " FROM EMP ";         
			while(rs.next()) { //rs[row,row,row,row....]
				TestDto dto=new TestDto();//필통하나 생성:row하나담는 필통임
				dto.setEmpno(rs.getInt(1));//rs에서 int형으로 1번째값을 꺼내서 dto에 저장
				dto.setEname(rs.getString(2));
				dto.setJob(rs.getString(3));
				dto.setMgr(rs.getInt(4));
				dto.setHiredate(rs.getDate(5));
				dto.setSal(rs.getInt(6));
				dto.setComm(rs.getInt(7));
				dto.setDeptno(rs.getInt(8));
				list.add(dto);
//				System.out.println(dto);//dto의 맴버필드값을 출력해서 볼수 있다. 
				//                       toString()오버라이딩->객체 안에 값을 출력
			}
//			System.out.println("5단계:쿼리결과받기성공!!");
		} catch (SQLException e) {
			System.out.println("JDBC실패:"+getClass()+":"+"getAllList()");
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return list;
	}
	
	//하나의 사원정보 조회하기: select문 where조건: 결과를 dto 하나에 담는다. 
	//파리미터: 사원번호로 하자!
	public TestDto getEmplyee(int empno) {
		TestDto dto=new TestDto();
		Connection conn=null;
		PreparedStatement psmt=null;//Statement(?파라미터기능없음), PreparedStatement
		ResultSet rs=null;
		
		String sql=" SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, "+
				   " SAL, COMM, DEPTNO " + 
				   " FROM EMP_BAK  " + 
				   " WHERE EMPNO = ? " ;
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, empno);//?에 넣어줄 값 설정 : 순서 ????? 12345
//			psmt.setString(2, dname);
			rs=psmt.executeQuery();//실행결과를 rs객체에 담는다.
			while(rs.next()) {
				int i=1;
				dto.setEmpno(rs.getInt(i++));
				dto.setEname(rs.getString(i++));
				dto.setJob(rs.getString(i++));
				dto.setMgr(rs.getInt(i++));
				dto.setHiredate(rs.getDate(i++));
				dto.setSal(rs.getInt(i++));
				dto.setComm(rs.getInt(i++));
				dto.setDeptno(rs.getInt(i++));
				System.out.println(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return dto;
	}
	
	//사원의 정보 추가하기: insert문 --> 파리미터로 추가할 값을 전달받는다.
	// 반환타입은 실행된 행의 개수를 반환--> int형반환(insert,update,delete)
	// 메서드의 반환타입은 boolean으로 정의
	public boolean addEmployee(TestDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
//		EMPNO, ENAME, JOB, MGR, SAL, DEPTNO
		String sql="INSERT INTO EMP_BAK "
				+ "VALUES(?,?,?,?,SYSDATE,?,0,?)";
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, dto.getEmpno());
			psmt.setString(2, dto.getEname());
			psmt.setString(3, dto.getJob());
			psmt.setInt(4, dto.getMgr());
			psmt.setInt(5, dto.getSal());
			psmt.setInt(6, dto.getDeptno());
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;//삼항연산자(조건?값:값)
	}
	
	//사원의 정보수정하기: update문실행, 파라미터는 수정할 값 전달
	//수정내용: SAL, COMM
	public boolean updateEmployee(int sal,int comm,int empno) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE EMP_BAK "
				 + " SET SAL=?, COMM=?"
				 + " WHERE EMPNO=? ";
		
		try {
			conn=getConnection();//2단계:연결
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, sal);
			psmt.setInt(2, comm);
			psmt.setInt(3, empno);//3단계:쿼리준비
			count=psmt.executeUpdate();//4단계:쿼리실행
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
	
	public boolean delEmployee(int empno) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql="DELETE FROM EMP_BAK WHERE EMPNO=? ";
		
		try {
			conn=getConnection();//2단계:연결
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, empno);//3단계:쿼리준비
			count=psmt.executeUpdate();//4단계:쿼리실행
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		
		return count>0?true:false;
	}
	//6단계:쿼리닫기
	public void close(ResultSet rs, PreparedStatement psmt, Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(psmt!=null) {
				psmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
//			System.out.println("6단계:DB닫기성공");
		} catch (SQLException e) {
//			System.out.println("6단계:DB닫기실패");
			e.printStackTrace();
		}
	}
}


















