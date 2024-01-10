package kr.bit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.bit.mapper.MemberMapper;

@RestController
@RequestMapping("/member")
public class MemberRestController {
	
	@Autowired
	private MemberMapper mapper;
	
	@GetMapping("/dbCheck")
	public String memberDbcheck(@RequestParam("memID") String memID) {
//		일치하는 아이디 있으면, 그 아이디 반환
//		일치하는 아이디 없으면, null 반환
		String memId = mapper.memberDbcheck(memID);
		return memId;
		
		
//		String str = func(); 함수를 통해 string을 return받음
//
//		if(str.equals("a"){ 이부분에서 에러
		
//			위와 같이 변수 str을 fnc() 메서드 return 받은 값으로 지정하게 될 때, func() 메서드에서 만약 null이 리턴된다면?
//			String str객체가 없는데 equals라는 메서드로 비교하려 접근했기 때문에 NullPointerException이 발생하게 된다.
//		}
//		1) null이 발생할 것 같은 값에 대해서는 미리 예측해서 null체크를 해주기
//		여기서 중요한 점은 if문에 여러 조건을 같이 체크한다면 null체크를 먼저 하기!
//		if(str.equals("a") || str!=null){
//		} x
//		if(str!=null || str.equals("a")){
//		} o


	}

}
