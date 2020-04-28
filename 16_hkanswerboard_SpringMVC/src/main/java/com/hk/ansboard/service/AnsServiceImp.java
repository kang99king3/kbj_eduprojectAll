package com.hk.ansboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.ansboard.daos.IAnsDao;
import com.hk.ansboard.dtos.AnsDto;

@Service
public class AnsServiceImp implements IAnsService{

	// service객체는 controller로 부터 받은 요청에 대한
	// dao 메서드를 실행시킨다.
	@Autowired
	private IAnsDao ansDao;
	
	@Override
	public List<AnsDto> getAllList() {
		return ansDao.getAllList();
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		return ansDao.insertBoard(dto);
	}

	@Override
	public AnsDto getBoard(int seq) {
		return ansDao.getBoard(seq);
	}

	@Override
	public boolean ansBoardUpdate(AnsDto dto) {
		return ansDao.ansBoardUpdate(dto);
	}

	@Override
	public boolean mulDel(String[] seqs) {
		return ansDao.mulDel(seqs);
	}

	@Override
	public boolean readCount(int seq) {
		return ansDao.readCount(seq);
	}

	public void testBoard() {
		
	}
	
//	@Transactional
	@Override
	public boolean replyBoard(AnsDto dto) {
		int count=0;
		ansDao.replyBoardUpdate(dto);//update문 실행
		count=ansDao.replyBoardInsert(dto);//insert문실행
		return count>0?true:false;
	}

}
