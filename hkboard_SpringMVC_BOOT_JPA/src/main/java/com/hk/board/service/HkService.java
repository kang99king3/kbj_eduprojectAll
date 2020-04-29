package com.hk.board.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<HkDto> findOne(String seq){
		List<HkDto> list=ihkDao.findAll(seq);
		return list;
	}
	
	public HkDto save(HkDto dto){
		
		return null;
	}
	
	public void deleteById(Long arg0){
		
	}
	
	
	
}




