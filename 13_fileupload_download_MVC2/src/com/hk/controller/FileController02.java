package com.hk.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.daos.FileDao;
import com.hk.dtos.FileDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/FileController02.do")
public class FileController02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String command=request.getParameter("command");
		
		FileDao dao=new FileDao();
		
		if(command==null||command.equals("")) { //업로드 요청(multipart로 요청)
			MultipartRequest multi=null;
			
			//파일경로설정
			String saveDirectory="D:/workspace_20191217_web/13_fileupload_download_MVC2"
					+ "/WebContent/upload";
			
			//파일사이즈설정
			int maxPostSize=1024*1024*10;
			
			try {
				//해당 경로에 파일 업로드 실행
				multi=new MultipartRequest(request, saveDirectory, maxPostSize,
						"utf-8",new DefaultFileRenamePolicy());
				
				//파일의 정보를 DB에 저장하는 작업
				multi.getParameter("title");//파일이 아닌 다른 파라미터들 받을때 
				
				//1.원본파일명 구함
				String origin_fname=multi.getOriginalFileName("filename");
				
				//2.저장할 파일명 구함(UUID ----> 32자리 랜덤으로 생성)
				String stored_fname=createUUID()
						 +origin_fname.substring(origin_fname.lastIndexOf("."));
				          //test.txt ---> .의 인덱스는 4  "test.txt".substring(4)
							// 32자리.txt 변환
				//3.파일사이즈 구하기: length() --> long타입으로 반환(형변환 필요)
				int file_size=(int)multi.getFile("filename").length();
				
				//4.DB에 정보 저장하기
				boolean isS=dao.insertFileInfo(new FileDto(0, origin_fname, stored_fname, file_size, null, null));
				
				//getOriginalFileName("filename"): 원본파일명
				//getFilesystemName("filename"): 실제로 저장된 파일명
				//--> policy객체가 중복되는 파일명을 재정의한다.
				
				//5.저장된 파일의 이름을 storedName으로 바꾼다.
				File oldFile=new File(saveDirectory+"/"+multi.getFilesystemName("filename"));
				File newFile=new File(saveDirectory+"/"+stored_fname);
				
				oldFile.renameTo(newFile);//old---> new로 파일명 바뀜
				
				if(isS) {
					response.sendRedirect("fileupload.jsp");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(command.equals("downloadlist")) {//다운목록 조회
			List<FileDto> list=dao.getFileList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("filelist.jsp").forward(request, response);
		
		}else if(command.equals("download")) {//다운로드하기
			int seq=Integer.parseInt(request.getParameter("seq"));
			//다운로드 요청 파일 정보를 DB에서 가져오기
			FileDto dto=dao.getFileInfo(seq);
			
			//파일경로설정
			String saveDirectory="D:/workspace_20191217_web/13_fileupload_download_MVC2"
					+ "/WebContent/upload";
			
			String filePath=saveDirectory+"/"+dto.getStored_fname();
			
			File file=new File(filePath);
			
			//java가 한번에 읽을 수 있는 양의 크기만큼 배열을 생성
			byte[] b=new byte[(int)file.length()];
			//10byte---> [0,0,0,0,0,0,0,0,0,0]
			
			//브라우저로 응답할 때 설정값 초기화
			response.reset();
			
			//다운로드하는 파일의 형식을 모른다면 octet-steam , 
			//예) application/msword
			response.setContentType("application/octet-steam");
			
			//한글인코딩 : 한글파일 이름이 깨지는 것을 방지
			String encoding=new String(dto.getOrigin_fname().getBytes("utf-8"),"8859_1");
			
			//파일의 다운로드 버튼을 클릭했을 때 다운로드 저장화면이 나오도록 처리
			response.setHeader("Content-Disposition", "attachment; filename="+encoding);
		
			FileInputStream in =null;//파일을 읽어들이기 위한 객체(입력)
			ServletOutputStream out=null;//내보내기 위한 객체(출력)
			
			try {
				//파일을 읽어들이기 위한 파이프를 준비한다.
				in=new FileInputStream(file);
				//출력을 위한 파이프를 준비한다.
				out=response.getOutputStream();
				
				//읽어들이는 값을 저장할 변수선언
				int numRead=0;
				
				// read()를 통해 파일 읽어들이기
				while((numRead=in.read(b,0,b.length))!=-1) {
					//write()를 통해 파일 출력하기
					out.write(b,0,numRead);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				out.flush();//남은 데이터가 있으면 모두 다 밀어내서 내보내기
				out.close();//out객체 닫기
				in.close();//in객체 닫기
			}
		}
		
	}
	
	//랜덤한 값 32자리 생성하는 메서드 
	public String createUUID() {
		//"12345678-12345678-12345678-12345678"
		return UUID.randomUUID().toString().replaceAll("-", "");//"-"제거하고 32자리로 만듬
	}

}






