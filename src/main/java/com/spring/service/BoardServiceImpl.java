package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.board.BoardDAO;
import com.spring.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired	//필드(멤버) 이름 위에 사용하면 객체 컨텐츠 주입됨
	private BoardDAO boardDAO;
	
	@Override
	public void insertBoard(BoardVO vo) { //글 등록 서비스
		boardDAO.insertBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList() {	//글 목록 서비스
		return boardDAO.getBoardList();
	}

}
