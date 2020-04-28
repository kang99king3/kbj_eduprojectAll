package com.hk.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.board.daos.HkDaoImp;
import com.hk.board.daos.IHkDao;
import com.hk.board.dtos.HkDto;

@Service
public class HkService implements IHkService{

	@Autowired
	private IHkDao hkDaoImp;
	
	@Override
	public List<HkDto> getAllList() {
		return hkDaoImp.getAllList();
	}

	@Override
	public boolean insertBoard(HkDto dto) {
		return hkDaoImp.insertBoard(dto);
	}

	@Override
	public HkDto getBoard(int seq) {
		return hkDaoImp.getBoard(seq);
	}

	@Override
	public boolean updateBoard(HkDto dto) {
		return hkDaoImp.updateBoard(dto);
	}

	@Override
	public boolean delBoard(int seq) {
		return hkDaoImp.delBoard(seq);
	}

	@Override
	public boolean mulDel(String[] seqs) {
		return hkDaoImp.mulDel(seqs);
	}

}
