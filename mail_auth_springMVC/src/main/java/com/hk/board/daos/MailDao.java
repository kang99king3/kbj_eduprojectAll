package com.hk.board.daos;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MailDao implements IMailDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace="com.hk.mail.";
	@Override
	public void createAuthkey(String userEmail, String authKey) {
		// TODO Auto-generated method stub
		Map<String,String>map=new HashMap<>();
		map.put("usereMail", userEmail);
		map.put("authKey", authKey);
		
//		sqlSession.insert(namespace+"createAuthKey",map);
		System.out.println("인증키를 DB에 저장합니다.");
	}
	
	
	
}
