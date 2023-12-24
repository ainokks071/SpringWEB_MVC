package kr.bit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.bit.entity.Member;
import kr.bit.mapper.MemberMapper;

//Spring mvc 03 : 회원가입, 로그인 -> 권한 : 내가 작성한 글만 컨트롤 가능하게. 

@Controller
public class MemberController{	
	
	@Autowired
	private MemberMapper mapper;
	
	
	private Member member;
	
	
	
//	회원가입화면 
	//http://localhost:8080/mvc03/memberRegister.do 요청 
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {
		return "member/memberRegister"; //memberRegiser.jsp 
	}
	
//	***** 회원가입 처리 : validation 유효성검사.
//	1. 하나라도 누락이 있을 경우(나이 제외) 회원가입실패 -> 회원가입화면으로 리다이렉트
//	2. 누락은 없는데 회원아이디 중복인경우 -> 회원가입화면으로 리다이렉트
//	3. 누락은 없는데 비밀번호 일치하지 않는 경우 -> 회원가입화면으로 리다이렉트
//	4. 누락 없고, 아이디 중복 없고, 비밀번호 일치한 경우 -> 회원가입 성공 -> 홈화면으로 리다이렉트 + 세션 객체바인딩(로그인 유지) !
	
	@PostMapping("/memberInsert.do")
	public String memberInsert(Member vo, String memPassword1, String memPassword2,
								RedirectAttributes rattr, HttpSession session) {
//		memIdx;   memID(String) memPassword(String) memName(String) memAge(int)
//		          memGender(String) memEmail(String) memProfile(String)  
//		파라메터 싹 다 수집 후,
//	    vo객체 만들어서 Member vo = new Member();
//		세터로 수집한 값 대입vo.setMemID(memID); -> 이 과정을 자동으로 해준다.
//		System.out.println(vo.getMemName()); -> 확인..
//		서버에서 유효성 검사해보기. validation @Valid
//		id, pwd, name, age, gender, email 하나라도 입력을 하지 않고 회원가입을 하면?
//		단, 나이는 입력하자...
		if(vo.getMemID() == null || vo.getMemID().equals("") ||

//		   memPassword는 pwd1과 pwd2가 일치할 경우에만 값이 넘어온다. 일치하지 않으면 null또는 빈문자열!!				
//		   vo.getMemPassword() == null || vo.getMemPassword().equals("") ||
		   memPassword1 == null || memPassword1.equals("") ||
		   memPassword2 == null || memPassword2.equals("") ||
		   vo.getMemName() == null || vo.getMemName().equals("") ||
		   vo.getMemAge() == 0 || vo.getMemAge() >= 100 ||
		   vo.getMemGender() == null || vo.getMemGender().equals("") ||
		   vo.getMemEmail() == null || vo.getMemEmail().equals("") )
		{
//			모달 창에 띄울 메세지 객체바인딩.
			rattr.addFlashAttribute("msg1", "회원가입 실패");
			rattr.addFlashAttribute("msg2", "모든 정보를 입력해주세요.");
			return "redirect:/memberRegister.do";
		}
		
//		누락은 없는데.... 비밀번호 일치하지 않는 경우
		if(!memPassword1.equals(memPassword2)) {
			rattr.addFlashAttribute("msg1", "회원가입 실패");
			rattr.addFlashAttribute("msg2", "비밀번호가 일치하지 않습니다.");
			return "redirect:/memberRegister.do";
		}
			
//		**새로운개념 : RedirectAttributes에 객체바인딩 !	(리다이렉트할 때 객체바인딩.)
//		다시, 회원가입 화면으로 redirect! -> 리다이렉트는 새로운 요청 (새로운 HttpServletRequest..) : 포워딩과의 차이점!!
//		redirect할 때, 오류메세지 가져가기 ? cf)forwarding은 객체 바인딩(Model) or HttpServletRequest or HttpSession 
//      "redirect:/memberRegister.do" -> memberRegiser.jsp에서 오류메세지 출력할 것!
//			addFlashAttribute() : jsp페이지에서 딱 한번만 사용할 객체바인딩 
//			memberRegiser.jsp에서 EL ${msg}로 출력해서 사용
//			누락 있으면 다시 회원가입화면으로 
		
//		나이를 입력하지 않으면 -> "?" -> 숫자로 못바꾸지!!
		
		
//		여기까지 왔으면, 누락없이 회원가입 성공한 것.
		mapper.memberInsert(vo); //가입 성공하면1, 실패하면 0 반환
		
//		<회원가입 성공 후, 메인화면으로 간다. >
//		1. 회원가입 성공 모달창 보여주기.(리다이렉트 객체바인딩)
		rattr.addFlashAttribute("msg1", "회원가입 성공");
		rattr.addFlashAttribute("msg2", vo.getMemName() + "님, 환영합니다.");
		
//		2. 로그인 상태로 변경!(세션 객체바인딩)
		session.setAttribute("member", vo); 
//		main.jsp에서 ${member.memName}로 출력해서 사용 : ${!empty member}
		
//		홈 화면으로
		return "redirect:/"; 
	}
	
	//로그아웃
	//http://localhost:8080/mvc03/memberLogout.do 요청 
	@RequestMapping("/memberLogout.do")
	public String memberLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/"; 
	}
	
	
	//로그인화면..
	//http://localhost:8080/mvc03/memberLoginForm.do 요청 
	@RequestMapping("/memberLoginForm.do")
	public String memberLoginForm(HttpSession session) {
		return "member/memberLoginForm"; 
	}
	
	//로그인처리..
	//http://localhost:8080/mvc03/memberLogin.do 요청 
	@RequestMapping("/memberLogin.do")
	public String memberLogin(Member vo, HttpSession session, RedirectAttributes rattr) {

//		id나 pwd 입력하지 않으면 빈문자열 들어온다.
//		System.out.println(vo.getMemID());
//		System.out.println(vo.getMemPassword());
		
//		아무것도입력하지 않은 누락체크도 한번 해볼 것.
		
		
		
//		id,pwd 일치하는 회원 있으면 member객체 반환 / id, pwd 일치하지 않으면 null반환
		member = mapper.memberLogin(vo);
//		System.out.println(member); null
		
//		member == null or member != null
		if(member == null) {
			
			rattr.addFlashAttribute("msg1", "로그인 실패");
			rattr.addFlashAttribute("msg2", "아이디와 비밀번호를 확인해주세요.");
			
			return "redirect:/memberLoginForm.do";
			
		} else {
			
			rattr.addFlashAttribute("msg1", "로그인 성공");
			rattr.addFlashAttribute("msg2", member.getMemName()+"님, 환영합니다.");

			session.setAttribute("member", member);
			return "redirect:/";
		}
		
	}
	
}