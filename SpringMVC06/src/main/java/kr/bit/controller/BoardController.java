package kr.bit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//Spring mvc 03 : 회원가입, 로그인 -> 권한 : 내가 작성한 글만 컨트롤 가능하게. 


//Controller는 업무단위로 작성 (ex. BoardController, MemberController, ProductController ... )
//@annotation : 컨트롤러의 역할을 한다는 것을 인식시켜줌, 인식표. -> servlet-context.xml의 scan에 의해 인식 !
//new BoardController(); by scan -> SpringContainer에 bean생성 !
@Controller
public class BoardController{	
	
	//http://localhost:8080/mvc03/board.do 요청 
	@RequestMapping("/board.do")
	public String board() {
		return "board/board"; 
		// views/board/board.jsp			
		//포워딩 한 번 하고 끝! -> url 변경 X : http://localhost:8080/mvc03/board.do 유지
	}
	
	@RequestMapping("/memberDetail.do/{memID}")
	public String memberDetail(@PathVariable String memID) {
		return "board/board"; 
		// views/board/board.jsp			
		//포워딩 한 번 하고 끝! -> url 변경 X : http://localhost:8080/mvc03/board.do 유지
	}
	

// RestController를 사용하기 전, 기존의 Controller
	
//	@Autowired
//	private BoardMapper mapper;
//	
//	@RequestMapping("/boardList.do")
//	public @ResponseBody List<Board> boardList() {
//		List<Board> boardList = mapper.boardList();
//		return boardList;
//	}
//	
//	@PostMapping("/boardInsert.do")
//	public @ResponseBody void boardInsert(Board vo) {
//		mapper.boardInsert(vo);
//	}
//	
//	@GetMapping("/boardDelete.do")
//	public @ResponseBody void boardDelete(@RequestParam("idx") int idx) {
//		mapper.boardDelete(idx);
//	}
//
//	@PostMapping("/boardUpdate.do")
//	public @ResponseBody void boardUpdate(Board vo) {
//		mapper.boardUpdate(vo);
//	}
//	
//	@GetMapping("/boardCount.do")
//	public @ResponseBody void boardCount(@RequestParam("idx") int idx) {
//		mapper.boardCount(idx);
//	}
//	
//	@GetMapping("/boardContent.do")
//	public @ResponseBody Board boardContent(@RequestParam("idx") int idx) {
//		Board vo = mapper.boardContent(idx);
//		return vo;
//	}
//	@GetMapping("/boardContent.do")
//	public @ResponseBody Board boardContent(@RequestParam("idx") int idx) {
//		mapper.boardCount(idx);
//		Board vo = mapper.boardContent(idx);
//		return vo;
//	}
	
	
}