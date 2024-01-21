package kr.bit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.bit.entity.Board;
import kr.bit.mapper.BoardMapper;

// REST server 의의 : localhost8080/mvc02/board/all -> view 없이 only 데이터만 보여줌 -> 가져다가 사용 !
// 클라이언트가 서버에 자원을 요청한다. 주소를 보다 명확히. -> 
// @RestController : URL + 요청방식(GET, POST, PUT, DELETE) -> 자원의 주소
// -> ajax통신을 기본 전제로 함, @ResponseBody(JSON 데이터 형태로 응답하겠다.)
// -> 따라서, @RestController가 있으면, @ResponseBody 생략 가능

// 	http://localhost:8080/mvc03 '/board'
@RestController
@RequestMapping("/board")
public class BoardRestController {
	
	//의존성 주입 	
	@Autowired
	private BoardMapper mapper;
	// 	http://localhost:8080/mvc02/board '/all'
	@GetMapping("/all")
	public List<Board> boardList() {
//		List<Board> boardList = mapper.boardList();
		List<Board> boardList = mapper.boardList2();
//		- String리턴 -> JSP 포워딩 or 리다이렉트. 
		
//		- 객체(VO, DTO, List...) 리턴 -> 클라이언트에게 JSON 데이터 형식으로 리턴하겠다. @ResponseBody : ajax로 응답
//		- JSON(JavaScript Object Notation) 데이터 포맷 : 중괄호 객체(BoardVO) 정보
// 		  배열 : [{"id":1,"tit":"게시판","cont":"게시판","writ":"선생님","date":"23","count":0}, {}, {}, ...]    
//		  준비물 1. pom.xml에 jackson-databind API 추가(from mvn repo)
//							-> 객체를 JSON 데이터 포맷으로 변환해주는 API	
//							-> 클라이언트와 서버는 객체가 아니라 문자열로 데이터를 주고받기 때문에 변환 필요 !
//			   2. @ResponseBody 어노테이션 -> jackson-databind API 동작 
//		의의 : 순수하게 Data만을 클라이언트에 응답하고, 각 클라이언트에 맞게 View를 제작해라 -> REST SERVER, REST API..
		return boardList;
	}
	
//	parameter 수집 		/* title=111 & content=111 & writer=111 & memID = kimks071 형태 */
//	insert는 왜 @RequestBody 없지 ? -> 에러..
	@PostMapping("/new")
	public void boardInsert(Board vo) {
		mapper.boardInsert(vo);
	}
	
//	@RequestBody : JSON 형태로 넘어오는 데이터 받기(여러 개의 데이터 넘어올 떄 !!)
	@PutMapping("/update")
	public void boardUpdate(@RequestBody Board vo) {
		mapper.boardUpdate(vo);
	}
	
	@DeleteMapping("/{idx}")
	public void boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
	}

	
	@PutMapping("/{idx}")
	public void boardCount(@PathVariable("idx") int idx) {
		mapper.boardCount(idx);
	}
	
	@GetMapping("/{idx}")
	public Board boardContent(@PathVariable("idx") int idx) {
		Board vo = mapper.boardContent(idx);
		return vo;
	}
	
//	@GetMapping("/boardContent.do")
//	public Board boardContent(@RequestParam("idx") int idx) {
//		mapper.boardCount(idx);
//		Board vo = mapper.boardContent(idx);
//		return vo;
//	}
	
	
	
	
	


}
