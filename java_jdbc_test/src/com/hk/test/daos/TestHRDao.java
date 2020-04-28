package com.hk.test.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.hk.test.dtos.TestDto;
import com.hk.test.dtos.TestHRDto;

public class TestHRDao {

	public TestHRDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1단계:드라이버로딩성공");
		} catch (ClassNotFoundException e) {
			System.out.println("1단계:드라이버로딩실패");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="hr";
		String password="hr";
		return DriverManager.getConnection(url, user, password);
	}
	
	//전체 조회하기: select문 실행 --> 여러행 반환 --> list[dto,dto...]
	public List<TestHRDto> getHRList(){
		List<TestHRDto> list=new ArrayList<>();
		
		//배열 : 여러값을 저장해서 관리 --> 저장 길이 설정
		//List, Set, Map --> 저장 길이 설정 안해도 되요
		//List 저장 방식: 인덱스가 생성(순서가 있음), 값이 중복 가능 , 배열과 비슷한 구조
		//제네릭: 저장할때 미리 타입을 정하는 것
//		List<String> list2=new ArrayList();
//		list2.add("안녕");//String타입
//		String s=list2.get(0);
		
		//List 입장에서 보면 값을 저장해야되는데 어떤값이 저장될지 모름
//		Object obj="안녕";
//		Object obj2=new TestDto();
//		[Object,Object,Object,Object,Object..]
		
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT EMPLOYEE_ID,LAST_NAME,EMAIL,PHONE_NUMBER, HIRE_DATE, "
				+ " SALARY, DEPARTMENT_ID "
				+ " FROM EMPLOYEES ";
		
		try {
			conn=getConnection();
			System.out.println("2단계:DB연결성공");
			psmt=conn.prepareStatement(sql);
			System.out.println("3단계:쿼리준비성공");
			rs=psmt.executeQuery();
			System.out.println("4단계:쿼리실행성공");
			while(rs.next()) {
				TestHRDto dto=new TestHRDto();
				dto.setEmployee_id(rs.getInt(1));
				dto.setLast_name(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setPhone_number(rs.getString(4));
				dto.setHire_date(rs.getDate(5));
				dto.setSalary(rs.getInt(6));
				dto.setDepartment_id(rs.getInt(7));
				list.add(dto);
			}
			System.out.println("5단계:쿼리결과받기성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return list;
	}
	
	//부서번호50번에 해당하는 사원조회(여러행이 반환될 수 있음)--> list[dto,dto...]
	//전달받을 파라미터는 부서번호
	public List<TestHRDto> getDeptEmpList(int deptno){
		List<TestHRDto> list=new ArrayList<>();

		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT EMPLOYEE_ID,LAST_NAME,EMAIL,PHONE_NUMBER, HIRE_DATE, "
				+ " SALARY, DEPARTMENT_ID "
				+ " FROM EMPLOYEES WHERE DEPARTMENT_ID = ? ";
		
		try {
			conn=getConnection();
			System.out.println("2단계:DB연결성공");
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, deptno);
			System.out.println("3단계:쿼리준비성공");
			rs=psmt.executeQuery();
			System.out.println("4단계:쿼리실행성공");
			while(rs.next()) {
				TestHRDto dto=new TestHRDto();
				dto.setEmployee_id(rs.getInt(1));
				dto.setLast_name(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setPhone_number(rs.getString(4));
				dto.setHire_date(rs.getDate(5));
				dto.setSalary(rs.getInt(6));
				dto.setDepartment_id(rs.getInt(7));
				list.add(dto);
			}
			System.out.println("5단계:쿼리결과받기성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return list;
	}
	public void close(ResultSet rs, PreparedStatement psmt, Connection conn) {
		try {
			if(rs!=null) {// null이 아니면 쓰고 있다는 의미이므로 닫아준다
				rs.close();
			}
			if(psmt!=null){
				psmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
			System.out.println("6단계:DB닫기 성공");
		} catch (SQLException e) {
			System.out.println("6단계:DB닫기 실패");
			e.printStackTrace();
		}
	}
}







