package com.hk.board;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hk.board.dtos.UserDto;
import com.hk.board.service.UserService;



//RunWith: spring에서 junit 사용하기 위한 설정
//contextConfiguration: spring bean 설정 파일의 위치를 지정할 때 사용
//@WebAppConfiguration: 컨트롤 및 웹환경에 사용되는 빈들을 자동으로 등록 생성해줌
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class ApplicationTest {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Autowired
    protected WebApplicationContext wac;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	MockMvc mock;
	
//	@Test
//	public void test3() throws Exception {
//		//mock 객체를 활용하여 가상의 클라이언트 요청--> 컨트롤러 테스트
//		this.mock=MockMvcBuilders.webAppContextSetup(wac).build();
//		//boardlist.do를 요청하여 상태정보 구하기
//		MvcResult result= mock.perform(MockMvcRequestBuilders.get("/boardlist.do"))
//		                     .andExpect(MockMvcResultMatchers.status().isOk())
//		                     .andReturn();
//		//상태정보중 성공상태의 값 200 코드 구하기
//		System.out.println("요청상태(200):"+HttpStatus.OK.value());
//		//성공시 200코드와 같은지 비교 테스트
//		assertEquals(HttpStatus.OK.value(),
//				result.getResponse().getStatus());
//	
//	}
	
	
//	@Test
//	public void test() {
//		boolean isS=userService.insertUser(
//				new UserDto("kbj6","6347","빼루","주소","010-1234-1586",
//							"test@email7.com","USER",null,"N",null)
//				);
//		System.out.println("회원가입:"+isS);
//	}
	
//	@Test
//	public void test() {
//		userService.loadUserByUsername("kbj");
//	}

	@Test
	public void test3() {
		String password="6347";
//		String encodePw=passwordEncoder.encode(password);
//		System.out.println(passwordEncoder.matches(password, encodePw));
	
		UserDto dto=(UserDto)userService.loadUserByUsername("kbj6");
		System.out.println("비밀번호:"+dto.getPassword());
		System.out.println(passwordEncoder.matches(password, dto.getPassword()));
	}
	
}









