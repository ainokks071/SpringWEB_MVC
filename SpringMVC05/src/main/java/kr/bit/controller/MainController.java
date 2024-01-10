package kr.bit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
//	localhost:8080/mvc05/
	@RequestMapping("/")
	public String main() {
		return "main"; //main.jsp
	}
	
}
