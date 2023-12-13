package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

//Controller는 업무단위로 작성 (ex. BoardController, MemberController, ProductController ... )
//@annotation : 컨트롤러의 역할을 한다는 것을 인식시켜줌, 인식표. -> servlet-context.xml의 scan에 의해 인식 !
//new BoardController(); by scan -> SpringContainer에 bean생성 !
@Controller
public class BoardController{	
	
	@Autowired
	private BoardMapper mapper;
	
	// 	http://localhost:8080/mvc02/
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/boardList.do")
	public @ResponseBody List<Board> boardList() {
		List<Board> boardList = mapper.boardList();
//		- String리턴 -> JSP 포워딩 or 리다이렉트. 
		
//		- 객체(VO, DTO, List...) 리턴 -> 클라이언트에게 JSON 데이터 형식으로 리턴하겠다.
//		- JSON(JavaScript Object Notation) 데이터 포맷 : 중괄호 객체(BoardVO) 정보
// 		  배열 : [{"id":1,"tit":"게시판","cont":"게시판","writ":"선생님","date":"23","count":0}, {}, {}, ...]    
//		  준비물 1. pom.xml에 jackson-databind API 추가(from mvn repo)
//							-> 객체를 JSON 데이터 포맷으로 변환해주는 API	
//							-> 클라이언트와 서버는 객체가 아니라 문자열로 데이터를 주고받기 때문에 변환 필요 !
//			   2. @ResponseBody 어노테이션 -> jackson-databind API 동작 
//		의의 : 순수하게 Data만을 클라이언트에 응답하고, 각 클라이언트에 맞게 View를 제작해라 -> REST SERVER, REST API..
		return boardList;
	}
	
//	parameter 수집 		/* title=111 & content=111 & writer=111 형태 */
	@PostMapping("/boardInsert.do")
	public @ResponseBody void boardInsert(Board vo) {
		mapper.boardInsert(vo);
	}

	@RequestMapping("/boardContent.do")
	public @ResponseBody Board boardContent(int idx) {
		Board board= mapper.boardContent(idx);
//		- String리턴 -> JSP 포워딩 or 리다이렉트. 
		
//		- 객체(VO, DTO, List...) 리턴 -> 클라이언트에게 JSON 데이터 형식으로 리턴하겠다.
//		- JSON(JavaScript Object Notation) 데이터 포맷 : 중괄호 객체(BoardVO) 정보
// 		  배열 : [{"id":1,"tit":"게시판","cont":"게시판","writ":"선생님","date":"23","count":0}, {}, {}, ...]    
//		  준비물 1. pom.xml에 jackson-databind API 추가(from mvn repo)
//							-> 객체를 JSON 데이터 포맷으로 변환해주는 API	
//							-> 클라이언트와 서버는 객체가 아니라 문자열로 데이터를 주고받기 때문에 변환 필요 !
//			   2. @ResponseBody 어노테이션 -> jackson-databind API 동작 
//		의의 : 순수하게 Data만을 클라이언트에 응답하고, 각 클라이언트에 맞게 View를 제작해라 -> REST SERVER, REST API..
		return board;
	}
	
	
	
	
	
	
	
}