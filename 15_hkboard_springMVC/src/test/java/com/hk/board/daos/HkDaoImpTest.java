package com.hk.board.daos;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hk.board.dtos.HkDto;
//
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration( locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class HkDaoImpTest {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Test
	public void test() {
		System.out.println("테스트시작");
		List<HkDto> list= sqlSession.selectList("com.hk.board.boardlist");
		System.out.println("sqlSession객체:"+sqlSession);
		System.out.println("글목록개수:"+list.size());
		assertEquals("hka",list.get(0).getId());
	}

}



