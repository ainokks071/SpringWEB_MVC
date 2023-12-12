package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

//Controller는 업무단위로 작성 (ex. BoardController, MemberController, ProductController ... )
//@annotation : 컨트롤러의 역할을 한다는 것을 인식시켜줌, 인식표. -> servlet-context.xml의 scan에 의해 인식 !
@Controller
public class BoardController{	// new BoardController(); by scan -> SpringContainer에 bean생성 !
	
//	@Autowired : 의존성주입, SpringContainer에 미리 만들어져있는 객체 사용 ! 
	@Autowired
	private BoardMapper mapper;
	
// HandlerMapping(스프링 내장) : 클라이언트의 요청(/boardList.do) <-> 실행 메서드 연결 ! by FrontController
// ex ) 클라이언트의 요청 => localhost:8080/mvc01/boardList.do : 게시판 전체목록 조회 요청 
// 요청 URL과 실행되는 메서드 mapping : HandlerMapping(스프링 내장)	
	
//	localhost:8080/mvc01/boardList.do
// 	게시판 전체 목록 조회 
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
	
//	게시판 글쓰기 화면
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm";
	}
	
//	게시판 글쓰기 화면에서 글 작성 후, 등록버튼 클릭 
//	parameter name : title, content, writer 파라메터수집 자동으로 해준다.
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) {
		mapper.boardInsert(vo);
		return "redirect:/boardList.do";
	}
	
//	게시판 전체 목록에서 제목 클릭하여 상세화면 조회 + 게시물 조회수 1 증가
	@GetMapping("/boardContent.do")
	public String boardContent(@RequestParam("idx") int idx, Model model) {
//		vo.setCount(vo.getCount() + 1);  ->  count db에서 변경 X 
//		순서 1 : update 쿼리 필요 -> 게시물 조회수 1 증가
		mapper.boardCount(idx);
		
//		순서 2 : 변경된 vo 조회 
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo);
		return "boardContent";
	}

//	상세화면에서 삭제하기 버튼 클릭 
//	@PathVariable을 사용하기 위해서는 web.xml에서 *.do를 /로 바꿔줘야 한다 !!
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	
//	상세화면에서 수정하기 버튼 클릭하여 수정하기 페이지 조회
//	수정하기 페이지로..
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo);
		return "boardUpdateForm";
	}
	
// 	수정하기 페이지에서 글 수정 후 등록버튼클릭 
	@PostMapping("/boardUpdate.do")
	public String boardUpdateForm(Board vo) {
		mapper.boardUpdate(vo);
		return "redirect:/boardList.do";
	}
	
	

}