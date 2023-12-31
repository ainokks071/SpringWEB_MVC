package kr.narp.myapp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

// @(어노테이션 : 전처리) -> SpringContainer에 미리 객체(bean)가 생성되어 관리를 해준다.
@Controller
public class MemberController {
	// 스프링 컨테이너에 bean으로 등록되어있는 DAO 가져다 쓴다. DI(의존성 주입) : 의존관계 느슨하게..
	// (cf. DAO는 root-context.xml에 기술된대로 component scan에 의해 빈으로 등록되어있다.)
	// autowired : 의존성 주입. new 사용 X. 외부로부터 주입받는다.
	// new 사용 X : MemberDAO dao = new MemberDAO();
	@Autowired
	private MemberDAO dao;
	
	// Model(스프링에서 제공해주는 객체바인딩역할 클래스) = HttpServletRequest 
	// 메서드의 매개변수로 선언해놓으면 자동으로 주입받는다. 
	@RequestMapping("/memberList.do")
	public String memberList(Model model) {
		List<MemberVO> list = dao.memberList();
		// model객체에 객체바인딩.
		model.addAttribute("list", list);
		// ViewResolver에 의해 자동으로 ~.jsp 붙여짐.
		return "memberList";
	}
	
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {
		return "memberRegister";
	}
	
	// 메서드의 매개변수로 설정해놓으면 자동으로 객체 주입. 
	@RequestMapping("/memberInsert.do")
	public String memberInsert(MemberVO vo) { //파라메터수집, post방식
		// 파라메터수집 자동으로 처리해준다.(getParameter x) SpringContainer에 VO객체 이미 존재.	기본생성자 호출.		
		dao.memberInsert(vo);
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberDelete.do")
	public String memberDelete(@RequestParam("num") int num) { //파라메터수집, get방식
		dao.memberDelete(num);
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberContent.do")
	public String memberContent(Model model, int num) {
		MemberVO vo = dao.memberContent(num);
		model.addAttribute("vo", vo);
		return "memberContent";
	}
	
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(MemberVO vo) {
		dao.memberUpdate(vo);
		return "redirect:/memberList.do";
	}
	
}
