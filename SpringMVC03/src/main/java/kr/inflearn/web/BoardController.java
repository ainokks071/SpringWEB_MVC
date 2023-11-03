package kr.inflearn.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inflearn.model.BoardVO;
import kr.inflearn.service.BoardService;

//servlet-context.xml에 	<context:component-scan base-package="kr.inflearn.web" />로 어노테이션 스캔 
// 웹, 프리젠테이션 계층 : POJO(Controller)의 역할? -> Service(->Mapper, DAO)연동, Data를 Model에 객체바인딩, 포워딩 
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

}
