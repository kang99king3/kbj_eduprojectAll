package com.hk.ansboard.service;

import java.util.List;

import com.hk.ansboard.dtos.AnsDto;

public interface IAnsService {

	//글목록조회
		public List<AnsDto> getAllList();
		//새글추가
		public boolean insertBoard(AnsDto dto) ;
		//글상세보기
		public AnsDto getBoard(int seq) ;
		//글수정하기: seq, title, content
		public boolean ansBoardUpdate(AnsDto dto) ;
		//글삭제하기(여러글삭제와 같이구현)
		public boolean mulDel(String[] seqs) ;
		//조회수
		public boolean readCount(int seq) ;
		//답글달기: update문/insert문 실행---> 트랜젝션 처리가 필요
		public boolean replyBoard(AnsDto dto) ;//service에서 실행할 메서드
		public void testBoard();
}






