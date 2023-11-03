package kr.inflearn.service;

import java.util.List;

import kr.inflearn.model.BoardVO;


// 비즈니스 계층

public interface BoardService {
	
//	게시물 리스트 조회 
	public List<BoardVO> getList(); 

//	게시물 등록 
	public void register(BoardVO boardVO);
	
//	특정 게시물 상세보기 
	public BoardVO get(int idx);
	
//	게시물 삭제 
	public int remove(int idx);
	
//	게시물 수정 
	public int modify(BoardVO boardVO);
}
