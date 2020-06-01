package com.hk.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.hk.board.MailHandler;
import com.hk.board.TempKey;
import com.hk.board.daos.IMailDao;

@Service
public class MailService implements IMailService{

	@Autowired
	private IMailDao mailDao;
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Override
	public void create(String usermail) throws Exception {
//		dao.create(vo); // 회원가입 DAO
		String key = new TempKey().getKey(10, false); // 인증키 생성
		mailDao.createAuthkey(usermail, key); // 인증키 DB저장

		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[홈페이지 이메일 인증]"); // 메일제목
		sendMail.setText("인증번호:"+key); // 메일내용
		sendMail.setFrom("보낸사람메일주소", "보낸이이름"); //도착한 메일에 보여질 보낸사람 주소와 이름
		sendMail.setTo(usermail); // 받는이
		sendMail.send();
		System.out.println("이메일로 인증키를 보낸다 인증번호:"+key);
	}

	
}
