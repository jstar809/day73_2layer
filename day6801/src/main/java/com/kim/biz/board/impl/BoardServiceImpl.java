package com.kim.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.kim.biz.board.BoardService;
import com.kim.biz.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO2 boardDAO;
	@Override
	public void insertBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO selectOneBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDAO.selectOneBoard(vo);
	}

	@Override
	public List<BoardVO> selectAllBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		System.out.println("main 이 실행된거야");
		return boardDAO.selectAllBoard(vo);
	}

}
