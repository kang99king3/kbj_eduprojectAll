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
import org.apache.ibatis.session.SqlSessionFactory;

import com.hk.config.SqlMapConfig;
import com.hk.datasource.DataBase;
import com.hk.dtos.HkDto;

public class HkDao extends SqlMapConfig{

	private String nameSpace="com.hk.board.";
	
	//글목록조회:List<HkDto>-->글목록 여러개
//	public List<HkDto> getAllList(){
//		List<HkDto> list=new ArrayList<>();
//		SqlSession sqlSession=null;
//		
//		try {
//			//SqlSessionFactory객체 구함
//			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
//			//SqlSessionFactory객체로부터 SqlSession객체를 구해온다.
//			//이때 openSession(true)로 실행하면 autocommit-> true 설정
//			sqlSession=sqlSessionFactory.openSession(true);
//			//selectList(쿼리ID)실행하면 결과를 List로 반환한다.
//			list=sqlSession.selectList(nameSpace+"boardlist");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			sqlSession.close();
//		}
//		
//		return list;
//	}
	
	//-----------------------------------------
	//페이징처리를 위한 글목록 조회 메서드
//	public List<HkDto> getAllList(String pnum){
//		List<HkDto> list=new ArrayList<>();
//		SqlSession sqlSession=null;
//		
//		try {
//			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
//			sqlSession=sqlSessionFactory.openSession(true);
//			list=sqlSession.selectList(nameSpace+"boardlist",pnum);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			sqlSession.close();
//		}
//		return list;
//	}
//	
//	//글목록의 페이지수 구하기
//	public int getPCount() {
//		int pcount=0;
//		SqlSession sqlSession=null;
//		
//		try {
//			sqlSession=getSqlSessionFactory().openSession(true);
//			pcount=sqlSession.selectOne(nameSpace+"pcount");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			sqlSession.close();
//		}
//		return pcount;
//	}
	
	//-----------------------------------------
	//페이징처리를 위한 글목록 조회 메서드
		public List<HkDto> getAllList(String pnum,String rcount){
			List<HkDto> list=new ArrayList<>();
			SqlSession sqlSession=null;
			Map<String, String>map=new HashMap<>();
			map.put("pnum", pnum);
			map.put("rcount", rcount);
			try {
				SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
				sqlSession=sqlSessionFactory.openSession(true);
				list=sqlSession.selectList(nameSpace+"boardlist",map);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return list;
		}
		
		//글목록의 페이지수 구하기
		public int getPCount(String rcount) {
			int pcount=0;
			SqlSession sqlSession=null;
			
			try {
				sqlSession=getSqlSessionFactory().openSession(true);
				pcount=sqlSession.selectOne(nameSpace+"pcount",rcount);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return pcount;
		}
	//글추가하기:bolean --> insert문실행: 테이블의 row 수정 개수를 반환 (int형)
	public boolean insertBoard(HkDto dto) {
		int count=0;
		SqlSession sqlSession=null;
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			//               insert(실행할쿼리명,전달할 파라미터)
			count=sqlSession.insert(nameSpace+"insertBoard", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
	//글상세보기:HkDto반환 -> 하나의 행을 반환
	public HkDto getBoard(int seq) {
		HkDto dto=null;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			dto=sqlSession.selectOne(nameSpace+"getBoard", seq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return dto;
	}
	
	//글수정하기
	public boolean updateBoard(HkDto dto) {
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.update(nameSpace+"updateBoard",dto);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	//글삭제하기
	public boolean delBoard(int seq) {
		int count=0;
		SqlSession sqlSession=null;
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.delete(nameSpace+"delBoard", seq);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
	//글 여러개 삭제하기: delete문 실행 ---> 여러개가 실행
	//batch개념: 동일한 작업을 한번에 실행하는 개념
	//  1+2  , 3+4 , 6+10 ......  실행!!
	/*
	 * delete from hkboard where seq=15
	 * delete from hkboard where seq=16
	 * delete from hkboard where seq=17
	 */
	//파리미터 seqs는 chks를 뜻함
	public boolean mulDel(String[] seqs) {
		int count=0;
		SqlSession sqlSession=null;
		//다이나믹 쿼리 사용시 파라미터는 반드시 Map에 담아서 전달한다.
		Map<String, String[]>map=new HashMap<>();
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
}














