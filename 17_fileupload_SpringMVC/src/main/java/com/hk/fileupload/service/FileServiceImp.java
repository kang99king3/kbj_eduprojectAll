package com.hk.fileupload.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hk.fileupload.daos.FileDaoImp;
import com.hk.fileupload.daos.IFileDao;
import com.hk.fileupload.dtos.FileDto;

@Service
public class FileServiceImp implements IFileService{

	@Autowired
	private IFileDao fileDao;
	
	//서비스에서 파일업로드 작업을 하고 파일정보를 DB에 저장하는 작업도 구현  
	@Override
	public boolean fileUpload(HttpServletRequest request) {
		
		MultipartHttpServletRequest multi=
				(MultipartHttpServletRequest)request;
		
		//요청파일 가져오기
		MultipartFile multiFile=multi.getFile("filename");
	
		//---DB에 파일정보를 저장하기 위한 코드 작성
		//1.요청한 파일에서 원본파일 이름 구하기: originName구하기
		String originName=multiFile.getOriginalFilename();
		//2.UUID객체를 통해서 파일명을 랜덤하게 32자리고 생성--> 저장할 파일명
		//  12345671-12345671-12345671-12345671--> "-"만 제거해서 32자리 만들기
		String createUUid=UUID.randomUUID().toString()
				        .replaceAll("-", "");
		String storedName=createUUid
				         +originName.substring(originName.indexOf("."));
		                //"32자리.txt"
		//3.파일사이즈 구하기
		int fileSize=(int)multiFile.getSize();
		
		//4.파일객체구하기
		String path="D:/workspace_20191217_web/"
				+ "17_fileupload_SpringMVC/src/main/webapp/upload/";
		File f=new File(path+storedName);
		
		boolean isS=false;
		try {
			multiFile.transferTo(f);//파일객체의 경로대로 업로드한다.
			//파일정보 DB에 저장하기(관련 내용 1,2,3번 내용)
			isS=fileDao.insertFileInfo(
					new FileDto(0,originName,storedName,fileSize,null,null));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isS;
	}

	@Override
	public List<FileDto> getFileList() {
		return fileDao.getFileList();
	}

	@Override
	public FileDto getFileInfo(int seq) {
		return fileDao.getFileInfo(seq);
	}

}
