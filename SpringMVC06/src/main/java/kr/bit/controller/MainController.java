package kr.bit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.bit.mapper.MemberMapper;

@Controller
public class MainController {
	
	@Autowired
	MemberMapper memberMapper;
	
	//로그인 시 시큐리티에서 루트경로("/")를 요청.
	@RequestMapping("/")
	public String hi(RedirectAttributes rttrs) {
		rttrs.addFlashAttribute("msg1", "안녕하세요");
		return "redirect:/main";
	}
	
	//로그아웃 시 시큐리티에서 ("/bye")를 요청.
	@RequestMapping("/bye")
	public String bye(RedirectAttributes rttrs) {
		rttrs.addFlashAttribute("msg2", "안녕히가세요");
		return "redirect:/main";
	}
	
	//메인화면("/main")으로 이동
	@RequestMapping("/main")
	public String main() {
		return "main"; //main.jsp
	}
	
	
}
