package com.hk.fileupload;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hk.fileupload.dtos.FileDto;
import com.hk.fileupload.service.IFileService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private IFileService fileService;
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/fileform.do", method = RequestMethod.GET)
	public String fileForm(Locale locale, Model model) {
		logger.info("파일업로드폼으로 이동 {}.", locale);
		return "fileupload";
	}
	
	@RequestMapping(value = "/fileupload.do", method = RequestMethod.POST)
	public String fileupload(HttpServletRequest request, Locale locale, Model model) {
		logger.info("파일업로드하기 {}.", locale);
		boolean isS=fileService.fileUpload(request);
		if(isS) {
			logger.info("파일업로드성공");
			return "redirect:filelist.do";
		}else {
			logger.info("파일업로드실패");
			return "fileupload";
		}
	}
	
	@RequestMapping(value = "/filelist.do", method = RequestMethod.GET)
	public String fileList(Locale locale, Model model) {
		logger.info("파일목록보기 이동 {}.", locale);
		List<FileDto>list=fileService.getFileList();
		model.addAttribute("list", list);
		return "filelist";
	}
	
	@RequestMapping(value = "/download.do", method = RequestMethod.GET)
	public void download(HttpServletResponse response ,@RequestParam("seq") int seq, Locale locale, Model model) throws IOException {
		logger.info("파일다운로드 {}.", locale);
		//파일의 원본이름을 구하기 위해 요청한 seq에 해당하는 파일 상세정보구하기
		FileDto dto=fileService.getFileInfo(seq);
		String originName=dto.getOrigin_fname();//원본파일명:사용자측에서 필요
		String storedName=dto.getStored_fname();//저장파일명:서버측에서 필요
		
		//저장된 파일 읽어오기
		String path="D:/workspace_20191217_web/"
				+ "17_fileupload_SpringMVC/src/main/webapp/upload/";
		byte[] fileByte=FileUtils.readFileToByteArray(
				new File(path+storedName));
		
		//읽어들인 파일정보를 화면에 다운로드할 수 있게 설정
		response.setContentType("application/octet-stream");
		
		//attachment는 첨부파일의미(팝업창), filename은 다운받게 될 파일의 이름 지정함
		response.setHeader("Content-Disposition",
				"attachmant; fileName='"
		       +URLEncoder.encode(originName,"UTF-8")+"';");
		
		response.setHeader("Content-Transfer-Encoding", "binary");
		//실제 파일을 내보내는 작업
		response.getOutputStream().write(fileByte);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
}










