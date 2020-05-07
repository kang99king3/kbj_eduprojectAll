package com.hk.board.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hk.board.dtos.HkDto;

@Repository
public interface IHkDao extends JpaRepository<HkDto, Integer> {
	//JpaRepository<Dto타입,pk값의 타입>
	
	//글목록조회
	public List<HkDto> findAll();
	
	@Override
	public Page<HkDto> findAll(Pageable request);
	
	//글상세보기
//	@Query("select seq, id,title,content,regdate from hkboard where seq=?1")
	public Optional<HkDto> findById(int seq);
	
	//글추가/글수정
	public <S extends HkDto> S save(S dto);
	//글삭제
	public void deleteById(int seq) ;
	
	
//	public void deleteInBatch(Iterable<HkDto> dto);
	
}
