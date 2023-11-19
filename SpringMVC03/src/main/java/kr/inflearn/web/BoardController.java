package kr.inflearn.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.inflearn.model.BoardVO;
import kr.inflearn.service.BoardService;

//servlet-context.xml
//<context:component-scan base-package="kr.inflearn.web" />
//어노테이션 스캔 + 컨테이너에 객체bean생성

// 웹, 프리젠테이션 계층
//POJO(Controller)의 역할? -> Service(->Mapper, DAO)연동, Data를 Model에 객체바인딩, 포워딩 
@Controller
public class BoardController {
	
	@Autowired 
	private BoardService boardService;
	
// http://localhost:8080/board/boardList.do
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
//		1. Service 연동해서 데이터 CRUD하기.
		List<BoardVO> boardList = boardService.getList();
//		2. Model에 객체바인딩(model : 객체바인딩 전용 클래스)
		model.addAttribute("boardList", boardList);
//		3. 포워딩 
		return "boardList";
	}
	
	/* "동일한 요청 url" -> get, post 구분시키는 방법 ! */
	
//	@RequestMapping(value = "/boardRegister.do", method=RequestMethod.GET)
//	public String boardRegister() {
//		return "boardRegister";
//	}
	
//	단순히, 게시물 등록페이지 띄워라
	@GetMapping("/boardRegister.do")
	public String boardRegisterGET() {
		return "boardRegister";
	}
	
//	@RequestMapping(value = "/boardRegister.do", method = RequestMethod.POST)
//	public String boardRegisterPOST() {
//		return "redirect:/boardList.do";
//	}
	
//	실제로 게시물 작성 후 등록! 
	@PostMapping("/boardRegister.do")
	public String boardRegisterPOST(BoardVO boardVO) {
		boardService.register(boardVO);
		return "redirect:/boardList.do";
	}
	
	@RequestMapping(value = "/boardContents.do", method=RequestMethod.GET)
	public String boardContents(@RequestParam("idx") int idx, Model model) {
		BoardVO boardVO = boardService.get(idx, "contents");
		model.addAttribute("boardVO", boardVO);
		return "boardContents";
	}
	
	@GetMapping("/boardModify.do")
	public String boardModifyGET(@RequestParam("idx") int idx, Model model) {
		BoardVO boardVO = boardService.get(idx, "notContents");
		model.addAttribute("boardVO", boardVO);
		return "boardModify";
	}
	
	@PostMapping("/boardModify.do")
	public String boardModifyPOST(BoardVO boardVO) {
		boardService.modify(boardVO);
		return "redirect:/boardList.do";
	}
	
	@RequestMapping(value = "/boardRemove.do", method = RequestMethod.GET)
	public String boardRemove(@RequestParam("idx") int idx) {
		boardService.remove(idx);
		return "redirect:/boardList.do";
	}

}
