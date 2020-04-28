package com.hk.board.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.hk.board.dtos.HkDto;

//dao클래스에는 @Repository를 선언한다.
@Repository
public class HkDaoImp implements IHkDao{

	private String nameSpace="com.hk.board.";
	
	//xml에 등록되어 있는 bean을 스프링컨테이너가 객체생성해서 주입해준다.
	//주입해줄 위치를 알려주는 이정표 같은 역할
	//등록된 객체의 타입과 같은 타입을 찾아서 넣어준다.
//	@Resource
//	@Qualifier
	@Autowired
	private SqlSessionTemplate sqlSession;

//	@Autowired
//	private HKDto dto;
	
	@Override
	public List<HkDto> getAllList() {
		return sqlSession.selectList(nameSpace+"boardlist");
	}

	@Override
	public boolean insertBoard(HkDto dto) {
		int count=sqlSession.insert(nameSpace+"insertBoard", dto);
		return count>0?true:false;
	}

	@Override
	public HkDto getBoard(int seq) {
		return sqlSession.selectOne(nameSpace+"getBoard",seq);
	}

	@Override
	public boolean updateBoard(HkDto dto) {
		int count=sqlSession.update(nameSpace+"updateBoard", dto);
		return count>0?true:false;
	}

	@Override
	public boolean delBoard(int seq) {
		int count=sqlSession.delete(nameSpace+"delBoard", seq);
		return count>0?true:false;
	}
	
	@Override
	public boolean mulDel(String[] seqs) {
		Map<String, String[]>map=new HashMap<>();
		map.put("seqs", seqs);
		int count=sqlSession.delete(nameSpace+"muldel", map);
		return count>0?true:false;
	}

}



