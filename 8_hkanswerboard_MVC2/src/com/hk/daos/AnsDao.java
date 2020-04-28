package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.datasource.DataBase;
import com.hk.dtos.AnsDto;

public class AnsDao extends DataBase{
	
	
	//글목록조회
	public List<AnsDto> getAllList(){
		List<AnsDto> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		StringBuffer sb=new StringBuffer();
		sb.append(" SELECT SEQ,ID,TITLE,CONTENT,REGDATE,REFER,STEP,DEPTH, ");
			    sb.append(" READCOUNT,DELFLAG ");
			    sb.append(" FROM ANSWERBOARD ");
			    sb.append(" ORDER BY REFER,STEP ");
			    
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sb.toString());
			rs=psmt.executeQuery();
			while(rs.next()) {
				int i=1;
				AnsDto dto=new AnsDto();
				dto.setSeq(rs.getInt(i++));
				dto.setId(rs.getString(i++));
				dto.setTitle(rs.getString(i++));
				dto.setContent(rs.getString(i++));
				dto.setRegdate(rs.getDate(i++));
				dto.setRefer(rs.getInt(i++));
				dto.setStep(rs.getInt(i++));
				dto.setDepth(rs.getInt(i++));
				dto.setReadcount(rs.getInt(i++));
				dto.setDelflag(rs.getString(i++));
				list.add(dto);
				System.out.println(dto);
			}
		} catch (SQLException e) {
			System.out.println("JDBC실패:getAllList():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}    
		return list;
	}
	//새글추가
	public boolean insertBoard(AnsDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" INSERT INTO ANSWERBOARD " + 
				   " VALUES (ANSWERBOARD_SEQ.NEXTVAL,?,?,?,SYSDATE " + 
				   "        ,(SELECT NVL(MAX(REFER)+1,0) FROM ANSWERBOARD) " + 
				   "        ,0,0,0,'N') ";
				
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
			close(null, psmt, conn);
		}		
		return count>0?true:false;
	}
	//글상세보기
	public AnsDto getBoard(int seq) {
		AnsDto dto=new AnsDto();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		StringBuffer sb=new StringBuffer();
		sb.append(" SELECT SEQ,ID,TITLE,CONTENT,REGDATE, ");
			    sb.append(" READCOUNT ");
			    sb.append(" FROM ANSWERBOARD ");
			    sb.append(" WHERE SEQ=? ");
			    
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sb.toString());
			psmt.setInt(1, seq);
			rs=psmt.executeQuery();
			while(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				dto.setReadcount(rs.getInt(6));
				System.out.println(dto);
			}
		} catch (SQLException e) {
			System.out.println("JDBC실패:getBoard():"+getClass());
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}	    
		return dto;
	}
	//글수정하기: seq, title, content
	public boolean ansBoardUpdate(AnsDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		String sql=" UPDATE ANSWERBOARD SET TITLE=?,CONTENT=?"
				+ " , REGDATE=SYSDATE WHERE SEQ=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getSeq());
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("JDBC실패:ansBoardUpdate():"+getClass());
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	//글삭제하기(여러글삭제와 같이구현)
	public boolean mulDel(String[] seqs) {
		boolean isS=true;
		int [] count=null;//쿼리의 실행결과를 담을 배열선언
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE ANSWERBOARD SET DELFLAG='Y' WHERE SEQ=? ";
		
		try {
			conn=getConnection();
			conn.setAutoCommit(false);
			psmt=conn.prepareStatement(sql);
			for (int i = 0; i < seqs.length; i++) {
				psmt.setString(1, seqs[i]);
				psmt.addBatch();
			}
			count=psmt.executeBatch();//[-2,-2,-2,-2,-2]
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close(null, psmt, conn);
			//실패여부를 확인해서 true/false반환하려고 하는 코드
			for (int i = 0; i < count.length; i++) {
				if(count[i]!=-2) {
					isS=false;
					break;
				}
			}
		}
		return isS;
	}
	//조회수
	public boolean readCount(int seq) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" UPDATE ANSWERBOARD SET READCOUNT=READCOUNT+1 "  
				  +" WHERE SEQ=? ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("JDBC실패:readCount():"+getClass());
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	//답글달기: update문/insert문 실행---> 트랜젝션 처리가 필요
	public boolean replyBoard(AnsDto dto) {
		int count=0;
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		//부모의 step보타 큰 글들을 구해서 step+1을 하자!!
		String sql1=" UPDATE ANSWERBOARD SET STEP=STEP+1 " + 
				" WHERE REFER=(SELECT REFER FROM ANSWERBOARD WHERE SEQ=?) " + 
				" AND STEP > (SELECT STEP FROM ANSWERBOARD WHERE SEQ=?) ";
		
		//답글추가: 부모의 refer, 부모의 step+1, 부모의 depth+1
		String sql2=" INSERT INTO ANSWERBOARD " + 
				" VALUES (ANSWERBOARD_SEQ.nextval,?,?,?,SYSDATE " + 
				"       ,(SELECT REFER FROM ANSWERBOARD WHERE SEQ=?) " + 
				"       ,(SELECT STEP FROM ANSWERBOARD WHERE SEQ=?)+1 " + 
				"       ,(SELECT DEPTH FROM ANSWERBOARD WHERE SEQ=?)+1 " + 
				"       ,0,'N')";
		
		try {
			conn=getConnection();
			conn.setAutoCommit(false);
			psmt=conn.prepareStatement(sql1);//update문 준비
			psmt.setInt(1, dto.getSeq());
			psmt.setInt(2, dto.getSeq());
			psmt.executeUpdate();//update문 실행
			
			psmt=conn.prepareStatement(sql2);//insert문 준비
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, dto.getSeq());
			psmt.setInt(5, dto.getSeq());
			psmt.setInt(6, dto.getSeq());
			count=psmt.executeUpdate();//insert문실행
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.setAutoCommit(true);
				close(null, psmt, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count>0?true:false;
	}
	
}















