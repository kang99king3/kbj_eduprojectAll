package com.hk.board.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.board.dtos.CalDto;

@Repository
public class CalDao implements ICalDao{

	private String namespace="com.hk.board.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public boolean insertCalBoard(CalDto dto) {
		int count=sqlSession.insert(namespace+"insertcal", dto);
		return count>0?true:false;
	}

	@Override
	public List<CalDto> calBoardList(String id, String yyyyMMdd) {
		Map<String, String>map=new HashMap<String, String>();
		map.put("id", id);
		map.put("yyyyMMdd", yyyyMMdd);
		return sqlSession.selectList(namespace+"calboardlist", map);
	}

	@Override
	public CalDto calBoardDetail(int seq) {
		return sqlSession.selectOne(namespace+"caldetail", seq);
	}

	@Override
	public boolean calBoardUpdate(CalDto dto) {
		int count=sqlSession.update(namespace+"calupdate", dto);
		return count>0?true:false;
	}

	@Override
	public boolean calMuldel(String[] seqs) {
		Map<String, String[]>map=new HashMap<>();
		map.put("seqs", seqs);
		int count=sqlSession.delete(namespace+"calmuldel", map);
		return count>0?true:false;
	}

	@Override
	public int calBoardCount(String id, String yyyyMMdd) {
		Map<String, String>map=new HashMap<>();
		map.put("id", id);
		map.put("yyyyMMdd", yyyyMMdd);
		return sqlSession.selectOne(namespace+"calcount", map);
	}

	@Override
	public List<CalDto> getCalViewList(String id, String yyyyMM) {
		Map<String, String>map=new HashMap<>();
		map.put("id", id);
		map.put("yyyyMM", yyyyMM);
		return sqlSession.selectList(namespace+"calviewlist", map);
	}
	
}
