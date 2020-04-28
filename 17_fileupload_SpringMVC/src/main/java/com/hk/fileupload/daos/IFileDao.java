package com.hk.fileupload.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.fileupload.dtos.FileDto;

public interface IFileDao {

	//파일업로드를 통해 파일정보를 DB에 저장하는 기능
		public boolean insertFileInfo(FileDto dto) ;
		
		//파일정보목록을 보여주는 기능
		public List<FileDto> getFileList();
		
		//파일 다운로드를 위해 파일 하나의 정보를 가져오는 기능
		//저장된 파일명으로 찾아서 원본파일명으로 다운로드 응답
		public FileDto getFileInfo(int seq) ;
}
