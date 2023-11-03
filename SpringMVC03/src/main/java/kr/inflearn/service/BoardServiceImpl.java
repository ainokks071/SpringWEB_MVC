package kr.inflearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inflearn.mapper.BoardMapper;
import kr.inflearn.model.BoardVO;

//servlet-context.xml에 	<context:component-scan base-package="kr.inflearn.service" />로 어노테이션 스캔
//비즈니스(서비스) 계층
@Service
public class BoardServiceImpl implements BoardService {
	
//	Service에서 Mapper 사용, 접근 by 의존성 주입 
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardVO> getList() {
		return boardMapper.getList();
	}

	@Override
	public void register(BoardVO boardVO) {
		boardMapper.insert(boardVO);
	}

	@Override
	public BoardVO get(int idx) {
		return boardMapper.read(idx);
	}
	
	@Override
	public int remove(int idx) {
		return boardMapper.delete(idx);
	}

	@Override
	public int modify(BoardVO boardVO) {
		return boardMapper.update(boardVO);
	}

}
