package com.hk.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.CalDto;

public class CalDao extends SqlMapConfig{

	private String nameSpace="com.hk.calboard.";
	
	//일정추가하기: insert문실행
	public boolean insertCal(CalDto dto) {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.insert(nameSpace+"insertCal", dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
	//일정목록 조회하기:select문실행 , 파라미터: id,yyyyMMdd
	public List<CalDto> getCalList(String id, String yyyyMMdd){
		List<CalDto> list=null;
		SqlSession sqlSession=null;
		
		//두개이상의 값을 담을때 map에 담아서 전달한다. calMapper.xml로
		Map<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("yyyyMMdd", yyyyMMdd);
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			list=sqlSession.selectList(nameSpace+"getCalList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	//일정 삭제하기: delete문실행 , 파라미터: String []  [seq,seq,seq...]
	public boolean mulDelCal(String [] seqs) {
		int count=0;
		SqlSession sqlSession=null;
		Map<String, String[]> map=new HashMap<>();
		map.put("seqs", seqs);
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.delete(nameSpace+"muldel", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
	//일정상세보기: select문 실행 , 파라미터: seq
	public CalDto getCalDetail(int seq) {
		CalDto dto=null;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			dto=sqlSession.selectOne(nameSpace+"calDetail", seq);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return dto;
	}
	
	//일정수정하기: update문실행 , 파라미터: seq,title,content,mdate
	public boolean updateCal(CalDto dto) {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.insert(nameSpace+"calUpdate", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
		
		//일정개수확인: select문 실행 , 파라미터: id, yyyyMMdd
	public int getCalCount(String id, String yyyyMMdd) {
		int count=0;
		SqlSession sqlSession=null;
		Map<String, String>map=new HashMap<>();
		map.put("id", id);
		map.put("yyyyMMdd", yyyyMMdd);
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.selectOne(nameSpace+"calCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count;
	}
	
	//달력에서 날마다 일정 3개이하씩 보여주는 기능 : select문 ,파라미터: id,yyyyMM
	public List<CalDto> getCalViewList(String id, String yyyyMM){
		List<CalDto> list=null;
		SqlSession sqlSession=null;
		Map<String, String>map=new HashMap<>();
		map.put("id", id);
		map.put("yyyyMM", yyyyMM);
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			list=sqlSession.selectList(nameSpace+"calViewList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return list;
	}
}



















