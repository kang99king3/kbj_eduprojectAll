package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.datasource.DataBase;
import com.hk.dtos.LoginDto;

public class LoginDao extends DataBase{
	
	//사용자 기능
	
	//1.회원가입 기능 구현(enabled: 'Y', role:'USER')
	//insert문 작성 : boolean 타입 반환
	public boolean insertUser(LoginDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		String sql=" INSERT INTO USERINFO VALUES( "
				+  " ?,?,?,?,?,?,'Y','USER',SYSDATE)";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getPassword());
			psmt.setString(4, dto.getAddress());
			psmt.setString(5, dto.getPhone());
			psmt.setString(6, dto.getEmail());
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("jdbc실패:insertUser():"+getClass());
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	//2.로그인 기능 구현: ID와 Password를 확인해서, 해당하는 회원의 정보를 조회
	// select문작성: LoginDto 반환 
	public LoginDto getLogin(String id, String password) {
		LoginDto dto=new LoginDto();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT ID,NAME,ENABLED,ROLE "
				+ " FROM USERINFO "
				+ " WHERE ID=? AND PASSWORD=? AND ENABLED='Y' ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, password);
			rs=psmt.executeQuery();//실행된 결과(DB자체데이터)를 rs에 담는다.
			while(rs.next()) {
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setEnabled(rs.getString(3));
				dto.setRole(rs.getString(4));
			}
		} catch (SQLException e) {
			System.out.println("jdbc실패:getLogin():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return dto;
	}
	//3.나의 정보 조회 기능 구현:select문 실행:LoginDto반환:파리미터 ID로 조회
	public LoginDto getUserInfo(String id) {
		LoginDto dto=new LoginDto();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT ID,NAME,ADDRESS,PHONE,EMAIL,REGDATE "
				 + " FROM USERINFO WHERE ID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAddress(rs.getString(3));
				dto.setPhone(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setRegdate(rs.getDate(6));
				System.out.println(dto);
			}
		} catch (SQLException e) {
			System.out.println("JDBC실패:getUserInfo():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return dto;
	}
	//4.나의 정보 수정 기능 구현(address,phone,email수정):update문실행:boolean반환
	public boolean updateUserInfo(LoginDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE USERINFO SET "
				+ " ADDRESS=?, PHONE=?, EMAIL=? WHERE ID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getAddress());
			psmt.setString(2, dto.getPhone());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getId());
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("JDBC실패:updateUserInfo():"+getClass());
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	//5.회원 탈퇴 기능 구현:update문 실행:enabled='Y' ---> 'N' 수정
	public boolean delUser(String id) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE USERINFO SET "
				+ " ENABLED='N' WHERE ID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("JDBC실패:delUser():"+getClass());
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	
	//6.아이디 중복 체크 기능 구현
	public String idCheck(String id) {
		String resultId=null;//조회된 결과 ID를 저장할 변수
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT ID "
				 + " FROM USERINFO "
				 + " WHERE ID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();//실행된 결과(DB자체데이터)를 rs에 담는다.
			while(rs.next()) {
				resultId=rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("jdbc실패:idCheck():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return resultId;
	}
	
	//관리자 기능
	
	//1.전체 회원 조회(탈퇴에 대한 상태여부까지 모두 조회)
	//select문실행:List<LoginDto> 반환
	public List<LoginDto> getAllUserStatus(){
		List<LoginDto> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT ID,NAME,ADDRESS,PHONE,EMAIL,ENABLED,ROLE, REGDATE "
				+ " FROM USERINFO ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				LoginDto dto=new LoginDto();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAddress(rs.getString(3));
				dto.setPhone(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setEnabled(rs.getString(6));
				dto.setRole(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
				list.add(dto);
				System.out.println(dto);
			}
		} catch (SQLException e) {
			System.out.println("JDBC실패:getAllUserStatus():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return list;
	}
	//2.전체 회원 조회(사용중인 회원정보 모두 조회)
	public List<LoginDto> getAllUserList(){
		List<LoginDto> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT ID,NAME,ROLE, REGDATE "
				+ " FROM USERINFO WHERE ENABLED = 'Y' ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				LoginDto dto=new LoginDto();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setRole(rs.getString(3));
				dto.setRegdate(rs.getDate(4));
				list.add(dto);
				System.out.println(dto);
			}
		} catch (SQLException e) {
			System.out.println("JDBC실패:getAllUserList():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return list;
	}
	//3.회원 상세정보 조회(등급포함)
	public LoginDto getUserInfoRole(String id) {
		LoginDto dto=new LoginDto();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT ID,NAME,ROLE,REGDATE "
				 + " FROM USERINFO WHERE ID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setRole(rs.getString(3));
				dto.setRegdate(rs.getDate(4));
				System.out.println(dto);
			}
		} catch (SQLException e) {
			System.out.println("JDBC실패:getUserInfoRole():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return dto;
	}
	//4.회원 등급 변경 
	public boolean userUpdateRole(String id,String role) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		 
		String sql=" UPDATE USERINFO SET ROLE=? WHERE ID=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, role);
			psmt.setString(2, id);
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("JDBC실패:userUpdateRole():"+getClass());
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
}














