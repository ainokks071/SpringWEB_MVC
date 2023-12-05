package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

//Controller는 업무단위로 작성 (ex. BoardController, MemberController, ProductController ... )
//어노테이션 -> 컨트롤러의 역할을 한다는 것을 인식시켜줌, 인식표.
@Controller
public class BoardController{	// new BoardController();
	@Autowired
	private BoardMapper mapper;
	
	// HandlerMapping(스프링 내장) : 클라이언트의 요청(/boardList.do) <-> 실행 메서드 연결 ! 
	
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		List<Board> list=mapper.getList();
		model.addAttribute("list", list);
		return "boardList"; // /WEB-INF/views/boardList.jsp(by ViewResolver(스프링 내장)) -> forward
	}
	
	
//	@GetMapping("/boardForm.do")
//	public String boardForm() {
//		return "boardForm"; // /WEB-INF/views/boardForm.jsp -> forward
//	}
//	@PostMapping("/boardInsert.do")
//	public String boardInsert(Board vo) { // title, content, writer => 파라메터수집(Board)
//		mapper.boardInsert(vo); // 등록		
//		return "redirect:/boardList.do"; // redirect
//	}
//	@GetMapping("/boardContent.do")
//	public String boardContent(int idx, Model model) { // ?idx=6
//		Board vo=mapper.boardContent(idx);
//		// 조회수 증가
//		mapper.boardCount(idx);
//		model.addAttribute("vo", vo); // ${vo.idx}...
//		return "boardContent"; // boardContent.jsp
//	}
//	@GetMapping("/boardDelete.do/{idx}")
//	public String boardDelete(@PathVariable("idx") int idx) { // ?idx=6
//		mapper.boardDelete(idx); //삭제		
//		return "redirect:/boardList.do";
//	}	
//	@GetMapping("/boardUpdateForm.do/{idx}")
//	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
//		Board vo=mapper.boardContent(idx);
//		model.addAttribute("vo", vo);		
//		return "boardUpdate"; // boardUpdate.jsp
//	}
//	@PostMapping("/boardUpdate.do")
//	public String boardUpdate(Board vo) { // idx, title, content
//		mapper.boardUpdate(vo); // 수정		
//		return "redirect:/boardList.do";
//	}
}