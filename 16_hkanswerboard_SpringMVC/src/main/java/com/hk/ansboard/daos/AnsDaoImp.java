package com.hk.ansboard.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.hk.ansboard.dtos.AnsDto;

//객체를 xml에 등록하지 않고 사용하기 위해 servlet-context.xml에
// <component-scan>를 사용하여 설정
// @Component ----> service나 dao등의 클래스에 동일하게 선언함
// @Controller, @Service, @Repository로 선언한 클래스들을 객체생성시켜준다.

@Repository
public class AnsDaoImp implements IAnsDao{

	private String namespace="com.hk.ansboard.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<AnsDto> getAllList() {
		return sqlSession.selectList(namespace+"getAllList");
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		int count=sqlSession.insert(namespace+"insertBoard", dto);
		return count>0?true:false;
	}

	@Override
	public AnsDto getBoard(int seq) {
		return sqlSession.selectOne(namespace+"getAllList", seq);
	}

	@Override
	public boolean ansBoardUpdate(AnsDto dto) {
		int count=sqlSession.update(namespace+"ansUpdateBoard", dto);
		return count>0?true:false;
	}

	@Override
	public boolean mulDel(String[] seqs) {
		Map<String, String[]> map=new HashMap<>();
		map.put("seqs", seqs);
		int count=sqlSession.update(namespace+"muldel", map);
		return count>0?true:false;
	}

	@Override
	public boolean readCount(int seq) {
		int count=sqlSession.update(namespace+"readCount", seq);
		return count>0?true:false;
	}

	@Override
	public int replyBoardUpdate(AnsDto dto) {
		return sqlSession.update(namespace+"replyUpdate", dto);
	}

	@Override
	public int replyBoardInsert(AnsDto dto) {
		return sqlSession.insert(namespace+"replyInsert", dto);
	}

}



