package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> exeBoardList(){
		
		List<BoardVo> boardList = boardDao.boardSelect();
		
		return boardList;
	}
	
	public BoardVo exeBoardRead(int no) {
		
		BoardVo boardVo = boardDao.boardSeletone(no);
		
		return boardVo;
	}
	
	public int exeBoardModify(BoardVo boardVo) {
		
		int count = boardDao.boardUpdate(boardVo);
		
		return count;
	}
	
	public int exeBoardDelete(int no) {
		
		int count = boardDao.boardDelete(no);
		
		return count;
	}
	
	public int exeBoardwrite(BoardVo boardVo) {
		
		int count = boardDao.boardInsert(boardVo);
		
		return count;
	}
	
}
