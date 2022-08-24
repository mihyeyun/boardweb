package com.spring.service;

import java.util.List;

import com.spring.board.BoardVO;

public interface BoardService {
	//글 등록
	public void insertBoard(BoardVO vo);
	
	//글 목록
	public List<BoardVO> getBoardList();
}
