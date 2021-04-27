package com.hk.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.board.daos.ICalDao;
import com.hk.board.dtos.CalDto;

@Service
public class CalService implements ICalService{

	@Autowired
	private ICalDao calDao;
	
	@Override
	public boolean insertCalBoard(CalDto dto) {
		return calDao.insertCalBoard(dto);
	}

	@Override
	public List<CalDto> calBoardList(String id, String yyyyMMdd) {
		return calDao.calBoardList(id, yyyyMMdd);
	}

	@Override
	public CalDto calBoardDetail(int seq) {
		return calDao.calBoardDetail(seq);
	}

	@Override
	public boolean calBoardUpdate(CalDto dto) {
		return calDao.calBoardUpdate(dto);
	}

	@Override
	public boolean calMuldel(String[] seqs) {
		return calDao.calMuldel(seqs);
	}

	@Override
	public int calBoardCount(String id, String yyyyMMdd) {
		return calDao.calBoardCount(id, yyyyMMdd);
	}

	@Override
	public List<CalDto> getCalViewList(String id, String yyyyMM) {
		return calDao.getCalViewList(id, yyyyMM);
	}

}




