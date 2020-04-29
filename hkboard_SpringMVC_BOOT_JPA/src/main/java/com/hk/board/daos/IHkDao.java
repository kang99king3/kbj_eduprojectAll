package com.hk.board.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hk.board.dtos.HkDto;

@Repository
public interface IHkDao extends JpaRepository<HkDto, Long> {
	//JpaRepository<Dto타입,pk값의 타입>

	public List<HkDto> findAll();
	
	@Query("select seq, id,title,content,regdate from hkboard where seq=?1")
	public List<HkDto> findAll(String seq);
	
	public HkDto save(HkDto dto) ;
	
	public void deleteById(Long arg0) ;
	
}
