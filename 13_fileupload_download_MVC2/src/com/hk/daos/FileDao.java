package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.datasource.DataBase;
import com.hk.dtos.FileDto;

public class FileDao extends DataBase{

	//파일업로드를 통해 파일정보를 DB에 저장하는 기능
	public boolean insertFileInfo(FileDto dto) {
		int count=0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		String sql=" INSERT INTO FILEBOARD VALUES "
				+ " (FILEBOARD_SEQ.NEXTVAL,?,?,?,SYSDATE,'N') ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, dto.getOrigin_fname());
			psmt.setString(2, dto.getStored_fname());
			psmt.setInt(3, dto.getFile_size());
			
			count=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, psmt, conn);
		}
		return count>0?true:false;
	}
	
	//파일정보목록을 보여주는 기능
	public List<FileDto> getFileList(){
		List<FileDto> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT SEQ, ORIGIN_FNAME, STORED_FNAME, FILE_SIZE,"
				+ " F_REGDATE, F_DELFLAG "
				+ " FROM FILEBOARD ORDER BY F_REGDATE DESC ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {  // java객체 <------DB에 객체 : myBatis가 ORM을 구현
				FileDto dto=new FileDto();
				dto.setSeq(rs.getInt(1));
				dto.setOrigin_fname(rs.getString(2));
				dto.setStored_fname(rs.getString(3));
				dto.setFile_size(rs.getInt(4));
				dto.setF_regdate(rs.getDate(5));
				dto.setF_delflag(rs.getString(6));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return list;
	}
	
	//파일 다운로드를 위해 파일 하나의 정보를 가져오는 기능
	//저장된 파일명으로 찾아서 원본파일명으로 다운로드 응답
	public FileDto getFileInfo(int seq) {
		FileDto dto=new FileDto();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql=" SELECT SEQ, ORIGIN_FNAME, STORED_FNAME, FILE_SIZE,"
				+ " F_REGDATE, F_DELFLAG "
				+ " FROM FILEBOARD WHERE SEQ=?";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs=psmt.executeQuery();
			while(rs.next()) {  // java객체 <------DB에 객체 : myBatis가 ORM을 구현

				dto.setSeq(rs.getInt(1));
				dto.setOrigin_fname(rs.getString(2));
				dto.setStored_fname(rs.getString(3));
				dto.setFile_size(rs.getInt(4));
				dto.setF_regdate(rs.getDate(5));
				dto.setF_delflag(rs.getString(6));
	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}
		return dto;
	}
}

















