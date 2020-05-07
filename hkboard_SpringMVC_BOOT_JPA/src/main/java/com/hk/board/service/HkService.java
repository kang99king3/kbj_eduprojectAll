package com.hk.board.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hk.board.daos.IHkDao;
import com.hk.board.dtos.HkDto;

@Service
public class HkService{
	@Autowired
	private IHkDao ihkDao;

	public List<HkDto> findAll(){
		
		return ihkDao.findAll();
	}
	
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




