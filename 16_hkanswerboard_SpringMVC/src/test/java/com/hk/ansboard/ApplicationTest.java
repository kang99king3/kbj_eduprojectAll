package com.hk.ansboard;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hk.ansboard.dtos.AnsDto;
import com.hk.ansboard.service.IAnsService;

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
	IAnsService ansService;
	
	MockMvc mock;
	
	@Test
	public void test3() throws Exception {
		//mock 객체를 활용하여 가상의 클라이언트 요청--> 컨트롤러 테스트
		this.mock=MockMvcBuilders.webAppContextSetup(wac).build();
		//boardlist.do를 요청하여 상태정보 구하기
		MvcResult result= mock.perform(MockMvcRequestBuilders.get("/boardlist.do"))
		                     .andExpect(MockMvcResultMatchers.status().isOk())
		                     .andReturn();
		//상태정보중 성공상태의 값 200 코드 구하기
		System.out.println("요청상태(200):"+HttpStatus.OK.value());
		//성공시 200코드와 같은지 비교 테스트
		assertEquals(HttpStatus.OK.value(),
				result.getResponse().getStatus());
	
	}
	
	
	@Test
	public void test2() {
		//service객체 구현 후 테스트하기
//		boolean isS=ansService.replyBoard(
//				new AnsDto(2,"hk","답글kkkk","제목"));
//		System.out.println("성공여부:"+isS);
		
		for (AnsDto dto: ansService.getAllList()) {
			System.out.println(dto);
		}
	}
	

	@Test
	public void test() {
		//myBatis 연결이 잘 되었는지 테스트
		System.out.println("sqlSession생성:"+sqlSession);
		//쿼리를 작성해서 잘 실행되는지 테스트
		List<AnsDto> list=sqlSession.selectList("com.hk.ansboard.getAllList");
		System.out.println("게시글개수:"+(list==null?"결과없음":list.size()));
		
//		AnsDto dto=sqlSession.selectOne("com.hk.ansboard.getAllList",35);
		System.out.println(list.get(0).getSeq());
		//assert메서드 활용해서 쿼리 실행 예상결과값이 일치하는지 테스트
		assertEquals("hk", list.get(7).getId());
	}

}









