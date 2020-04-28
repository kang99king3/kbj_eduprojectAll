package com.hk.fileupload.daos;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.fileupload.dtos.FileDto;

@Repository
public class FileDaoImp implements IFileDao{

	private String namespace="com.hk.fileupload.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public boolean insertFileInfo(FileDto dto) {
		int count=sqlSession.insert(namespace+"fileinsert", dto);
		return count>0?true:false;
	}

	@Override
	public List<FileDto> getFileList() {
		return sqlSession.selectList(namespace+"filelist");
	}

	@Override
	public FileDto getFileInfo(int seq) {
		return sqlSession.selectOne(namespace+"getfile",seq);
	}

}
