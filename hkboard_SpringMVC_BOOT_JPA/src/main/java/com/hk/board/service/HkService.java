package com.hk.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hk.board.daos.IHkDao;
import com.hk.board.dtos.HkDto;

@Service
public class HkService{
	@Autowired
	private IHkDao ihkDao;

	//글목록조회
	public List<HkDto> findAll(){
		
		return ihkDao.findAll();
	}
	//글목록: 정렬 및 페이징처리
	public List<HkDto> findAllP(int pnum){
		//Pageable객체는 new하지 않고 PageRequest.of()로 구한다. : of(페이지번호,row개수,정렬,대상)
		Pageable pageable = PageRequest.of(pnum, 10,Direction.DESC,"regdate");
		Page<HkDto> page = ihkDao.findAll(pageable);
		// Page객체에서 List객체를 얻어오려면 getContent()사용
		return page.getContent();
	}   
	
	//page개수를 구하는 메서드
	public int pCount(int size) {
		return (int)ihkDao.count()/size;
	}
	
	//상세보기
	public HkDto findDetail(int seq){
		Optional<HkDto> list=ihkDao.findById(seq);
		HkDto dto=null;
		if(list.isPresent()) {
			dto=list.get();
		}
		return dto;
	}
	
	public HkDto save(HkDto dto){
		return ihkDao.save(dto);
	}
	
	public void deleteById(int seq){
		ihkDao.deleteById(seq);
	}
	
	@Transactional
	public void muldel(String[] seqs) {
		
		for (int i = 0; i < seqs.length; i++) {
			ihkDao.deleteById(Integer.parseInt(seqs[i]));			
		}
	}
	
	
//	public void deleteInBatch(Iterable<HkDto> dto) {
//		ihkDao.deleteInBatch(dto);
//	}
	
	
	
}




