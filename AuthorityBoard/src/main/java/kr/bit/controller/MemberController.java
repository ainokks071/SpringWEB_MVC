package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.bit.entity.AuthVO;
import kr.bit.entity.Board;
import kr.bit.entity.Member;
import kr.bit.mapper.MemberMapper;

//Spring mvc 03 : 회원가입, 로그인 -> 권한 : 내가 작성한 글만 컨트롤 가능하게. 

@Controller
public class MemberController{	
	
	@Autowired
	private MemberMapper mapper;
	
//  DI
//	SecurityConfig에 빈 추가, 사용자 MemberController, 공급자 PasswordEncoder (인터페이스), 
	@Autowired 
	PasswordEncoder pwEncoder;

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied"; //access-denied.jsp 
	}
	
	
	@GetMapping("/totalList.do")
	public String totalList(Model model) {
		List<Board> totalList = mapper.totalList();
		System.out.println(totalList);
		model.addAttribute("member", totalList);
		return "main"; //access-denied.jsp 
	}

	
	
	//회원가입화면 
	//http://localhost:8080/mvc05/membermemberInserForm.do 요청 
	@RequestMapping("/memberInsertForm.do")
	public String memberRegister() {
		return "member/memberInsertForm"; //memberInsertForm.jsp 
	}
	
//	***** 회원가입 처리 : validation 유효성검사.
//	1. 하나라도 누락이 있을 경우(나이 제외) 회원가입실패 -> 회원가입화면으로 리다이렉트
//	2. 누락은 없는데 회원아이디 중복인경우 -> 회원가입화면으로 리다이렉트
//	3. 누락은 없는데 비밀번호 일치하지 않는 경우 -> 회원가입화면으로 리다이렉트
//	4. 누락 없고, 아이디 중복 없고, 비밀번호 일치한 경우 -> 회원가입 성공 -> 홈화면으로 리다이렉트 + 세션 객체바인딩(로그인 유지) !
//	WARN : org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver - Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported]
	@RequestMapping("/memberInsert.do")
	public String memberInsert(Member vo, String memPassword1, String memPassword2,
								RedirectAttributes rattr, HttpSession session) {
//		memIdx;   memID(String) memPassword(String) memName(String) memAge(int)
//		          memGender(String) memEmail(String) auth(String) 
//		파라메터 싹 다 수집 후,
//	    vo객체 만들어서 Member vo = new Member();
//		setter로 수집한 값 대입 : vo.setMemID(memID); -> 이 과정을 자동으로 해준다.
//		System.out.println(vo.getMemName()); -> 확인..
//		서버에서 유효성 검사해보기. validation @Valid
//		id, pwd, name, age, gender, email 하나라도 입력을 하지 않고 회원가입을 하면?

//		참고)) Builder패턴 : 편한 초기화 대체 lombok API 제공 
//		Member.builder().memID("kks").memPassword("1234").memName("kks").memAge(12).memEmail("1234").build();
		System.out.println(vo);
		List<AuthVO> authList = vo.getAuthList();
//		System.out.println(vo);
//		System.out.println(authList);
//		[AuthVO(num=0, memID=null, auth=ROLE_USER), AuthVO(num=0, memID=null, auth=ROLE_MANAGER), AuthVO(num=0, memID=null, auth=ROLE_MANAGER)]
//		사용자가 체크한 권한만 초기화 되겠지. -> num은 DB에서 auto_increment, memID는 setter로 초기화할 것 !
		
//		1. 누락 체크 
		if(vo.getMemID() == null || vo.getMemID().equals("") ||
//		   memPassword는 hidden으로, pwd1과 pwd2가 일치할 경우에만 값이 넘어온다. 일치하지 않으면 null또는 빈문자열!!				
//		   vo.getMemPassword() == null || vo.getMemPassword().equals("") ||
		   memPassword1 == null || memPassword1.equals("") ||
		   memPassword2 == null || memPassword2.equals("") ||
		   vo.getMemName() == null || vo.getMemName().equals("") ||
		   vo.getMemAge() == 0 || vo.getMemAge() > 120 ||
		   vo.getMemGender() == null || vo.getMemGender().equals("") ||
//		   															  권한체크 안했을 경우!	
		   vo.getMemEmail() == null || vo.getMemEmail().equals("") || authList.size() == 0) 
		{
//			모달 창에 띄울 메세지 객체바인딩.
			rattr.addFlashAttribute("msg1", "회원가입 실패");
			rattr.addFlashAttribute("msg2", "모든 정보를 입력해주세요.");
			return "redirect:/memberRegister.do";
		}
		
		
//		2. 누락은 없는데.... 비밀번호 일치하지 않는 경우
		if(!memPassword1.equals(memPassword2)) {
			rattr.addFlashAttribute("msg1", "회원가입 실패");
			rattr.addFlashAttribute("msg2", "비밀번호가 일치하지 않습니다.");
			return "redirect:/memberRegister.do";
		}
		
//		숙제) 아이디 중복체크 -> vo보내서 DB 조회 -> 중복이라면? -> 리다이렉트.
//		mapper.memberSelect(vo);
			
//		**새로운개념 : RedirectAttributes에 객체바인딩 !	(리다이렉트할 때 객체바인딩.)
//		다시, 회원가입 화면으로 redirect! -> 리다이렉트는 새로운 요청 (새로운 HttpServletRequest..) : 포워딩과의 차이점!!
//		redirect할 때, 오류메세지 가져가기 ? cf)forwarding은 객체 바인딩(Model) or HttpServletRequest or HttpSession 
//      "redirect:/memberRegister.do" -> memberRegiser.jsp에서 오류메세지 출력할 것!
//			addFlashAttribute() : jsp페이지에서 딱 한번만 사용할 객체바인딩 
//			memberRegiser.jsp에서 EL ${msg}로 출력해서 사용
//			누락 있으면 다시 회원가입화면으로 
		
//		나이를 입력하지 않으면 -> "?" -> 숫자로 못바꾸지!!
		
//		*** 비밀번호 암호화하기.(API) -> SecurityConfig에 @Bean 객체 설정 -> DI로 주입해주기.
//		vo.getMemPassword() : 사용자가 입력한 비밀번호. ex)1234
//		암호화된 비밀번호 반환.(1234 : $2a$10$9aHuYAGt7G55pysgvYHBbeH5QF/VmEvqUgrWNcKxFDgqstXwL0Tt2) 
		String bcryptPw = pwEncoder.encode(vo.getMemPassword());
//		DB에 저장하기 위해 setter
		vo.setMemPassword(bcryptPw);
		
//		조인된 결과 행들을 모두 조회하기 Test!(where절 사용 X )
//		모든 회원 조회 된다!
//		List<AuthVO> atAuthVOs = mapper.memberList();
//		System.out.println(atAuthVOs);

		
//		DB에 저장하기 전 memberVo : int idx = 0(기본값으로 초기화.)
		
//		1. memberVo db에 등록 
		mapper.memberInsert(vo); //가입 성공하면1, 실패하면 0 반환
		
//		AuthVO(num=0, memID=null, auth=ROLE_USER)
//		2. db 권한테이블에 회원의 권한VO 저장하기.
//		왜 반복문을 쓰는지는, 테이블의 특성을 생각해보면 알 수 있다.(1대 다의 관계 )
		for(AuthVO authVO : authList) {
//			AuthVO saveAuthVO = new AuthVO();
//			saveAuthVO.setMemID(vo.getMemID());
//			saveAuthVO.setAuth(authVO.getAuth());
			
//			트러블슈팅 : InsertForm에서 권한체크 안한 항목은 null로 들어오기 때문에, 반드시 null체크 해줘야한다!
//			### SQL: insert into member_auth(memID, auth)     values(?, ?)
//			### Cause: java.sql.SQLIntegrityConstraintViolationException: 
//			Column 'auth' cannot be null
			if(authVO.getAuth()!=null) {
				authVO.setMemID(vo.getMemID());
				mapper.authInsert(authVO);
			}
		}
//		System.out.println(authList);
		
		
//		<회원가입 성공 후, 메인화면으로 간다. >
//		1. 회원가입 성공 모달창 보여주기.(리다이렉트 객체바인딩)
		rattr.addFlashAttribute("msg1", "회원가입 성공");
//		rattr.addFlashAttribute("msg2", vo.getMemName() + "님, 환영합니다.");
		rattr.addFlashAttribute("msg2", "로그인 해주세요.");
		
		   // 회원가입이 성공하면=>로그인처리하기
//		   return "redirect:/memLoginForm.do";

//		2. 로그인 상태로 변경!(세션 객체바인딩)
		
//		vo = mapper.getMember(vo.getMemIdx()); vo에는 Idx는 없으니까(0이니까..), nullpointerexception
//		JOIN 결과 출력.
		
//		JOIN으로 조회 -> where절 -> 해당하는 
//		join된 vo 조회.
//		vo = mapper.getMemberByID(vo.getMemID());
		
		
//		DB에 저장 후, 조회된 memberVo : int idx = auto_increment
//		(!!!!!!! 중요 !!!!!!)세션 객체바인딩 하기 전, DB에서 회원 조회를 한번 해줘야한다!!
//		session.setAttribute("member", vo); 
//		main.jsp에서 ${sessionScope.member.memName}로 출력해서 사용 : ${!empty member}
		
//		홈 화면으로
		return "redirect:/memberLoginForm.do";
	}
	
	
	
	
	
	
	//로그아웃
	//http://localhost:8080/mvc03/memberLogout.do 요청 
//	@RequestMapping("/memberLogout.do")
//	public String memberLogout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/"; 
//	}
	
	
	//로그인화면..
	//http://localhost:8080/mvc03/memberLoginForm.do 요청 
	@RequestMapping("/memberLoginForm.do")
	public String memberLoginForm() {
		return "member/memberLoginForm"; 
	}
	
	
//	스프링 시큐리티 적용하면 여기로 요청되지 않는다!!
//  로그인처리..
//  http://localhost:8080/mvc05/memberLogin.do 요청
//	ajax로도 해볼 것.. ??
	@RequestMapping("/memberLogin.do")
	public String memberLogin(Member vo, HttpSession session, RedirectAttributes rattr) {
//		String memId = request.getParameter("memID");
//		String memPassword = request.getParameter("memPassword");
//		Member vo = new Member();
//		vo.setMemId(memId);
//		vo.setMemPassword(memPassword);
//		System.out.println(vo); //toString() 호출 
//		System.out.println(vo.getMemID());
//		System.out.println(vo.getMemPassword());
		
//		id나 pwd 입력하지 않으면 빈문자열"" 들어온다.(input이 있어서..) 나머지는 0또는 null
//		System.out.println(vo.getMemID());
//		System.out.println(vo.getMemPassword());
		
//		아무것도입력하지 않은 누락체크.
		if(vo.getMemID() == null || vo.getMemID().equals("") ||
		   vo.getMemPassword() == null || vo.getMemPassword().equals("")) {
			
//			리다이렉트 객체바인딩.
			rattr.addFlashAttribute("msg1", "로그인 실패");
			rattr.addFlashAttribute("msg2", "아이디와 비밀번호를 입력해주세요.");
			return "redirect:/memberLoginForm.do";
		}
		
//		id, pwd 입력 하고 로그인버튼 클릭.
		
//		id,pwd 일치하는 회원 있으면 member객체 반환 / id, pwd 일치하지 않으면 null반환
//		select where 쿼리 -> 일치하는 행 없으면 null 반환 !
		
//		로그인 시 입력한 비밀번호
		String rawPassword = vo.getMemPassword();
		
		
//			memberLogin : JOIN으로 DB에서 회원 조회 -> Member로 받아준다.
		vo = mapper.memberLogin(vo.getMemID());
//		암호화된 비밀번호.
		String encodePassword = vo.getMemPassword();
//		
		
		
//		System.out.println(member); //일치하는 회원 없으면 null
		
//		member == null or member != null
		if(vo == null || pwEncoder.matches(rawPassword, encodePassword) == false) {
//			리다이렉트 객체바인딩. -> 모달창에서 사용  
			rattr.addFlashAttribute("msg1", "로그인 실패");
			rattr.addFlashAttribute("msg2", "일치하는 회원이 없습니다. 다시 로그인해주세요.");
			return "redirect:/memberLoginForm.do";
			
		} else {
			
			
			vo = mapper.getMemberByID(vo.getMemID());
			
//			리다이렉트 객체바인딩. -> 모달창에서 사용  
			rattr.addFlashAttribute("msg1", "로그인 성공");
			rattr.addFlashAttribute("msg2", vo.getMemName()+"님, 환영합니다.");
//			첫번 째 로그인요청 -> 서버 내 쿠키 발행(세션ID) -> 클라이언트로 전달 + 세션 객체 바인딩 -> 여러 페이지에서 공유할 메모리 생성! 
			session.setAttribute("member", vo);
//			리다이렉트로 두번 째 요청 -> 각각의 jsp페이지에서 session객체에 바인딩된 데이터 추출 !
			return "redirect:/";
		}
	}
	
	
	//http://localhost:8080/mvc03/memberUpdateForm.do 요청 
//	예전 : 회원정보 수정버튼 클릭할 때, 회원의 idx 전달 -> 컨트롤러에서 받아서 특정 회원 조회 후 포워딩.
//	세션 활용하면 -> 로그인 성공 후 공유 메모리에 객체바인딩 된 회원 추출 가능 !
	@RequestMapping("/memberUpdateForm.do")
	public String memberUpdateForm() {
		return "member/memberUpdateForm"; 
	}

//WARN : org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver - Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported]
	@PostMapping("/memberUpdate.do")
	public String memberUpdate(Member vo, String memPassword1, String memPassword2,
							HttpSession session, RedirectAttributes rattr) {
		System.out.println(vo);
		List<AuthVO> authList = vo.getAuthList();
		
		if(vo.getMemID() == null || vo.getMemID().equals("") ||
//		   memPassword는 pwd1과 pwd2가 일치할 경우에만 값이 넘어온다. 일치하지 않으면 null또는 빈문자열!!				
//		   vo.getMemPassword() == null || vo.getMemPassword().equals("") ||
		   memPassword1 == null || memPassword1.equals("") ||
		   memPassword2 == null || memPassword2.equals("") ||
		   vo.getMemName() == null || vo.getMemName().equals("") ||
		   vo.getMemAge() == 0 || vo.getMemAge() >= 100 ||
		   vo.getMemGender() == null || vo.getMemGender().equals("") ||
		   vo.getMemEmail() == null || vo.getMemEmail().equals("") || authList.size() == 0)
		{
//			모달 창에 띄울 메세지 객체바인딩.
			rattr.addFlashAttribute("msg1", "회원정보 수정 실패");
			rattr.addFlashAttribute("msg2", "모든 정보를 입력해주세요.");
			return "redirect:/memberUpdateForm.do";
		}
		
//		누락은 없는데.... 비밀번호 일치하지 않는 경우
		if(!memPassword1.equals(memPassword2)) {
			rattr.addFlashAttribute("msg1", "회원정보 수정 실패");
			rattr.addFlashAttribute("msg2", "비밀번호가 일치하지 않습니다.");
			return "redirect:/memberUpdateForm.do";
		}
			
//		**새로운개념 : RedirectAttributes에 객체바인딩 !	(리다이렉트할 때 객체바인딩.)
//		다시, 회원가입 화면으로 redirect! -> 리다이렉트는 새로운 요청 (새로운 HttpServletRequest..) : 포워딩과의 차이점!!
//		redirect할 때, 오류메세지 가져가기 ? cf)forwarding은 객체 바인딩(Model) or HttpServletRequest or HttpSession 
//      "redirect:/memberRegister.do" -> memberRegiser.jsp에서 오류메세지 출력할 것!
//			addFlashAttribute() : jsp페이지에서 딱 한번만 사용할 객체바인딩 
//			memberRegiser.jsp에서 EL ${msg}로 출력해서 사용
//			누락 있으면 다시 회원가입화면으로 
		String rawPassword = vo.getMemPassword();
		String encodePassword = pwEncoder.encode(rawPassword);
		vo.setMemPassword(encodePassword);
		
//		1. 회원테이블  수정
		mapper.memberUpdate(vo);
		
		
//		ERROR 1452: 1452: Cannot add or update a child row: a foreign key constraint fails
//		### Cause: java.sql.SQLIntegrityConstraintViolationException: Column 'auth' cannot be null
//		기본키는 널이 되면 안된다!!!!!!!!!!!!!!
//	 	부모 - 자식 테이블에 데이터가 있는데, 한쪽 테이블에 삭제, 추가 하려고 하면 에러난다. 
//		mysql strict mode 해제 
		
//		2-1. 기존에 존재하는 권한 행 전부 삭제
//		회원이 한명뿐이라면, 행 삭제 시 기본키가 null이 되어 에러 발생 가능! -> null방지 데이터 넣어주었음.
//		한번에 최대 3개 행까지 삭제하겠지!
		mapper.authDelete(vo.getMemID());
		
//		2-2. 새롭게 선택된 권한 행 추가.
		for(AuthVO authVO : authList) {
			if(authVO.getAuth() !=null) {
				authVO.setMemID(vo.getMemID()); 
				mapper.authInsert(authVO);
			}
		}
//		
		
		
//		수정 후, 조회
//		vo = mapper.getMemberByID(vo.getMemID()); //조회(JOIN)
		
		rattr.addFlashAttribute("msg1", "회원정보 수정 성공");
		rattr.addFlashAttribute("msg2", vo.getMemName() + "님, 회원정보가 수정되었습니다.");
//		회원정보 수정 후, 세션 객체에 덮어쓰기 바인딩 !!  
//		session.setAttribute("member", vo); 
//		홈 화면으로
		return "redirect:/"; 
	}
	
	
	
	@RequestMapping("/memberImageForm.do")
	public String memberImageForm() {
		return "member/memberImageForm"; //memberImageForm.jsp 
	}
	
//	실제 파일 업로드 + DB에 memProfile이름 저장. 
	@RequestMapping("/memberImageInsert.do")   // 메서드의 매개변수로 주입받는 객체가 생성될 때, 기본생성자가 호출되는 것 같다.
	public String memberImageInsert(Member vo, HttpSession session,
									HttpServletRequest request, RedirectAttributes rattr) {
//		파일업로드 api : cos.jar(고전방식) -> pox.xml에 추가. -> 제공API인 MultipartRequest 객체 사용할 것.
		
//		동일한 파일명으로 업로드 시, 이름 변경하여 파일 업로드 해주는 객체
//		DefaultFileRenamePolicy renamePolicy = new DefaultFileRenamePolicy();
		
		MultipartRequest multi = null;
//		구동중인 서버의 경로 구하기 -> 업로드 폴더 경로 구하기
//		/Users/kks/dev-study/SpringWEB_MVC/SpringMVC03/src/main/webapp/resources (x)
//		/Users/kks/dev-study/SpringWEB_MVC/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVC03/resources (o)
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/upload"); //		String uploadPath = request.getRealPath("/");(deprecated)
//		업로드 파일 max 크기
		int fileMaxSize = 10 * 1024 * 1024; //10mb
		String encoding = "UTF-8";
		
		String memProfile = null;
		String oldProfile = null;
		String memID = "";
		int memIdx = 0;
		
//		request객체에 post방식으로 넘어온 데이터 들어있다.(member의 idx, profile)
		try {
//			파일 업로드 완료 !
			multi = new MultipartRequest(request, uploadPath, fileMaxSize, encoding, new DefaultFileRenamePolicy());
			
			memProfile = multi.getFilesystemName("memProfile");
//			System.out.println(memProfile); 파일 선택하지 않으면 ? -> null 찍힌다.
			
			if(memProfile == null) {
				memIdx = Integer.parseInt(multi.getParameter("memIdx"));
//				기존 사진은 지우기. 
				oldProfile = mapper.getMemberByIdx(memIdx).getMemProfile();
				new File(uploadPath + "/" + oldProfile).delete();
				
				vo.setMemIdx(memIdx);
				vo.setMemProfile(memProfile);
				mapper.imageUpdate(vo);  // DB에 memProfile null로 변경 

//				업데이트 된 회원 조회해서,
				vo = mapper.getMemberByIdx(memIdx);
//				세션객체바인딩 새롭게 
				session.setAttribute("member", vo); 
				rattr.addFlashAttribute("msg1", "프로필 사진 변경 성공");
				rattr.addFlashAttribute("msg2", "기본 이미지로 변경 되었습니다.");
				
				return "redirect:/"; 
			}
			
		} catch (IOException e) {
//			파일 크기 10mb 이상일 경우 ?
//			java.io.IOException: Posted content length of 11787364 exceeds limit of 10485760
			e.printStackTrace();
			rattr.addFlashAttribute("msg3", "파일 업로드 오류");
			rattr.addFlashAttribute("msg4", "파일의 크기는 10MB를 초과할 수 없습니다.");
//			톰캣의 server.xml에 추가 -> maxswallowSize -1 -> 한계치까지는 업로드 해본다.
//			<Connector connectionTimeout="20000" maxParameterCount="1000" port="8080" protocol="HTTP/1.1" redirectPort="8443" maxSwallowSize="-1"/>
			return "redirect:/memberImageForm.do"; 
		}
		
//		파일 이름 추출
		memProfile = multi.getFilesystemName("memProfile");
//		파일의 확장자를 대문자로 추출
		
		String ext = memProfile.substring(memProfile.lastIndexOf(".")+1).toUpperCase();
		
		if(!(ext.equals("JPEG") || ext.equals("PNG") || ext.equals("JPG") || ext.equals("GIF"))) {
			rattr.addFlashAttribute("msg5", "파일 업로드 오류");
			rattr.addFlashAttribute("msg6", "이미지 파일만 업로드할 수 있습니다.");
//			이미 업로드 된, 잘못된 확장자 파일 삭제.
			new File(uploadPath + "/" + memProfile).delete();
			
			return "redirect:/memberImageForm.do"; 
		}
		
		memIdx = Integer.parseInt(multi.getParameter("memIdx")); 
		memID = multi.getParameter("memID"); 
		
		vo.setMemIdx(memIdx);
//		vo.setMemID(memID);
		vo.setMemProfile(memProfile);
		
//		기존 사진은 지우기. 
		oldProfile = mapper.getMemberByIdx(memIdx).getMemProfile();
		new File(uploadPath + "/" + oldProfile).delete();
		
//		DB 사진 업데이트 
		mapper.imageUpdate(vo); 
		
//		회원 사진 업데이트 후에, session객체바인딩 새롭게 해줘야 리다이렉트 시, jsp파일에서 인식한다.
//		vo = mapper.getMemberByID(vo.getMemID());
		vo = mapper.getMemberByIdx(vo.getMemIdx());
		
//		세션객체바인딩 새롭게 
		session.setAttribute("member", vo); 
		rattr.addFlashAttribute("msg1", "파일 업로드 성공");
		rattr.addFlashAttribute("msg2", "성공적으로 파일이 업로드 되었습니다.");
//		홈화면으로 
		return "redirect:/"; 

	}


	
}