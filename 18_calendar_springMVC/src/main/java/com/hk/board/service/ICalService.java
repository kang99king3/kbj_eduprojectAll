package com.hk.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hk.board.dtos.CalDto;

public interface ICalService {

	//일정추가하기:ID, TITLE, CONTENT, MDATE
	public boolean insertCalBoard(CalDto dto);
	
	//일정목록조회하기: ID, yyyyMMdd
	public List<CalDto> calBoardList(String id, String yyyyMMdd);
	
	//일정 상세내용 조회:SEQ
	public CalDto calBoardDetail(int seq);
	
	//일정 수정하기: SEQ,TITLE,CONTENT,MDATE
	public boolean calBoardUpdate(CalDto dto);

	//일정삭제하기: SEQ
	public boolean calMuldel(String[] seqs);

	//일정개수구하기: ID, yyyyMMdd
	public int calBoardCount(String id, String yyyyMMdd);
	
	//달력에 일정 보여주기: ID, yyyyMM
	public List<CalDto> getCalViewList(String id, String yyyyMM);
}
