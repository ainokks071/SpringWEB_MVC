package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

//Controller는 업무단위로 작성 (ex. BoardController, MemberController, ProductController ... )
//@annotation : 컨트롤러의 역할을 한다는 것을 인식시켜줌, 인식표. -> servlet-context.xml의 scan에 의해 인식 !
@Controller
public class BoardController{	// new BoardController(); by scan -> SpringContainer의 bean생성 !
	
//	@Autowired : 의존성주입, 컨테이너에 미리 만들어져있는 객체 사용 ! 
	@Autowired
	private BoardMapper mapper;
	
// HandlerMapping(스프링 내장) : 클라이언트의 요청(/boardList.do) <-> 실행 메서드 연결 ! by FrontController
// ex ) 클라이언트의 요청 => localhost:8080/mvc01/boardList.do : 게시판 전체목록 조회 요청 
// 요청 URL과 실행되는 메서드 mapping : HandlerMapping(스프링 내장)	
	
//	localhost:8080/mvc01/boardList.do
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		
		//MapperInterface의 메서드 사용 ! -> 게시글 리스트 조회, 
		//MapperInterface의 구현 클래스 = SqlSessionFactoryBean : SQL CRUD메서드 사용 가능 by root-context.xml
		List<Board> list = mapper.getList();
		
		//객체바인딩 : Model객체, 객체바인딩 전용 객체 
		model.addAttribute("list", list);
		
		// 포워딩 or 리다이렉트 		
		// /WEB-INF/views/boardList.jsp(by ViewResolver(스프링 내장) : at servlet-context.xml)
		//	-> forwarding -> View(JSP)페이지에서, EL태그 ${}로 데이터 출력 !
		return "boardList"; 
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