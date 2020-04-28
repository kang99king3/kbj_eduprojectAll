package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.datasource.DataBase;
import com.hk.dtos.HkDto;

public class HkDao extends DataBase{

	//글목록조회:List<HkDto>-->글목록 여러개
	public List<HkDto> getAllList(){
		List<HkDto> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT SEQ, ID, TITLE, CONTENT, REGDATE "
				+ " FROM HKBOARD ORDER BY REGDATE DESC ";
		
		try {
			conn=getConnection();
			System.out.println("2단계:DB연결성공");
			psmt=conn.prepareStatement(sql);
			System.out.println("3단계:쿼리준비성공");
			rs=psmt.executeQuery();
			System.out.println("4단계:쿼리실행성공");
			while(rs.next()) { // rs[row,row,row...], DB는 인덱스시작이 1부터!
				HkDto dto=new HkDto();//필통하나 생성: row하나를 담는 객체
				dto.setSeq(rs.getInt(1));//row[seq]
				dto.setId(rs.getString(2));//row[id]
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				list.add(dto);
				System.out.println(dto);
			}
			System.out.println("5단계:DB결과받기성공");
		} catch (SQLException e) {
			System.out.println("JDBC실패:getAllList():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		
		return list;
	}

	//글추가하기:bolean --> insert문실행: 테이블의 row 수정 개수를 반환 (int형)
	public boolean insertBoard(HkDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
//		ResultSet rs=null;
		
		String sql=" INSERT INTO hkboard " + 
				   " values(HKBOARD_seq.nextval,?,?," + 
				   " ?,SYSDATE) ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("JDBC실패:insertBoard():"+getClass());
			e.printStackTrace();
		}finally {
			//참조타입은 초기화: null, 기본타입은 0 , 0.0 
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	
	//글상세보기:HkDto반환 -> 하나의 행을 반환
	public HkDto getBoard(int seq) {
		HkDto dto=new HkDto();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT SEQ, ID, TITLE, CONTENT,REGDATE " + 
				" FROM HKBOARD " + 
				" WHERE SEQ=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
			}
		} catch (SQLException e) {
			System.out.println("JDBC실패:getBoard():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return dto;
	}
	
	//글수정하기
	public boolean updateBoard(HkDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql= " UPDATE HKBOARD " + 
					" SET TITLE=?, " + 
					" CONTENT = ?, " + 
					" REGDATE=SYSDATE " + 
					" WHERE SEQ=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getSeq());
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("JDBC실패:updateBoard():"+getClass());
			e.printStackTrace();
		}finally {
			//참조타입은 초기화: null, 기본타입은 0 , 0.0 
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	//글삭제하기
	public boolean delBoard(int seq) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql= " DELETE FROM HKBOARD WHERE SEQ=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("JDBC실패:delBoard():"+getClass());
			e.printStackTrace();
		}finally {
			//참조타입은 초기화: null, 기본타입은 0 , 0.0 
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
}













