package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.config.SqlMapConfig;
import com.hk.datasource.DataBase;
import com.hk.dtos.AnsDto;

public class AnsDao extends SqlMapConfig{
	
	private String nameSpance="com.hk.ansboard.";
	
	//글목록조회
	public List<AnsDto> getAllList(){
		List<AnsDto> list=null;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			list=sqlSession.selectList(nameSpance+"getAllList");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return list;
	}
	//새글추가
	public boolean insertBoard(AnsDto dto) {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.insert(nameSpance+"insertBoard", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count>0?true:false;
	}
	//글상세보기
	public AnsDto getBoard(int seq) {
		AnsDto dto=null;
		SqlSession sqlSession=null;
		//다이나믹 쿼리를 사용할 경우  map에 담아서 전달한다.
		Map<String, Integer> map=new HashMap<>();
		map.put("seq", seq);
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			dto=sqlSession.selectOne(nameSpance+"getAllList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}

		return dto;
	}
	//글수정하기: seq, title, content
	public boolean ansBoardUpdate(AnsDto dto) {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.update(
					nameSpance+"ansUpdateBoard", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	//글삭제하기(여러글삭제와 같이구현)
	public boolean mulDel(String[] seqs) {
		int count=0;
		SqlSession sqlSession=null;
		Map<String, String[]>map=new HashMap<>();
		map.put("seqs", seqs);
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.update(nameSpance+"muldel", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
	//조회수
	public boolean readCount(int seq) {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.update(nameSpance+"readCount", seq);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	//답글달기: update문/insert문 실행---> 트랜젝션 처리가 필요
	public boolean replyBoard(AnsDto dto) {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			//transaction처리: autocommit=false 설정
			sqlSession=getSqlSessionFactory().openSession(false);//transaction처리
			sqlSession.update(nameSpance+"replyUpdate",dto);
			count=sqlSession.insert(nameSpance+"replyInsert", dto);
			sqlSession.commit();//정상실행됐다면 commit실행--> DB에 반영//transaction처리
		} catch (Exception e) {
			sqlSession.rollback();//transaction처리
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
}















