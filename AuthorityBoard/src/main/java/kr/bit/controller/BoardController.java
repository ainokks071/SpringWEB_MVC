package kr.bit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController{	
	
	@RequestMapping("/board.do")
	public String board() {
		return "board/board"; //board.jsp
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