package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> boardSelect(){
		
		List<BoardVo> boardList = sqlSession.selectList("board.select");
		
		return boardList;
	}
	
	public BoardVo boardSeletone(int no) {
		
		BoardVo boardVo = sqlSession.selectOne("board.selectone", no);
		
		return boardVo;
	}
	
	public int boardUpdate(BoardVo boardVo) {
		
		int count = sqlSession.update("board.update", boardVo);
		
		return count;
	}
	
	public int boardDelete(int no) {
		
		int count = sqlSession.delete("board.delete", no);
		
		return count;
	}
	
	public int boardInsert(BoardVo boardVo) {
		
		int count = sqlSession.insert("board.insert", boardVo);
		
		return count;
	}
}
