package kr.inflearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inflearn.mapper.BoardMapper;
import kr.inflearn.model.BoardVO;

//servlet-context.xml
//<context:component-scan base-package="kr.inflearn.service" />
//어노테이션 스캔 + 컨테이너에 객체bean생성

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
	public BoardVO get(int idx, String mode) {
//		고객의 요구사항 : 게시물 상세보기 클릭하면 조회수 1증가 !
//		컨트롤러 변경 X -> 서비스계층(비즈니스레이어)에서 구현할 것 !
//		상세보기 클릭했을 때만 조회수 1 증가, 수정하기 클릭했을 때는 조회수 변화 없어야 하므로!
		if(mode.equals("contents")) {
			boardMapper.count(idx);
		}
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
