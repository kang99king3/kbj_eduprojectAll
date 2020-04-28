package com.hk.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/FileController01.do")
public class FileController01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		MultipartRequest multi=null;
		
		//파일을 저장하기 위한 경로 설정
		//1.상대경로
//		String saveDirectory=request.getSession().getServletContext().getRealPath("upload");
		//2.절대경로
		System.out.println(request.getServletContext().getRealPath("upload"));
		System.out.println(request.getSession().getServletContext().getRealPath("upload"));
		String saveDirectory="D:/workspace_20191217_web/13_fileupload_download_MVC2"
				+ "/WebContent/upload";
		
		//파일을 업로드할때 최대 파일 사이즈 설정 (10MB로 설정)
		//1byte --> 1024byte --> 1kb ---> 1024kb ---> 1MB 
		int maxPostSize=1024*1024*10; 
		
		try {
			//객체생성과 동시에 파일은 업로드 실행
			//객체(요청객체, 저장경로,최대업로드사이즈,인코딩, 중복된 파일명 존재시 리네임 설정하는 객체)
			multi=new MultipartRequest(request, saveDirectory, maxPostSize,
					 "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//파일의 원본이름 구하기
		String fileName=multi.getOriginalFileName("filename");
		response.sendRedirect("fileupload.jsp?filename="+fileName);
	}

}





