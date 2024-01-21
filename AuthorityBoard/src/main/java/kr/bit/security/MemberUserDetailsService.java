package kr.bit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.bit.entity.Member;
import kr.bit.entity.MemberUser;
import kr.bit.mapper.MemberMapper;


// SecurityConfig.java(httpsecurity)의 로그인, 로그아웃 처리 시 DB에서 회원을 조회하는 서비스계층. (by memID = username)
// 로그인하면 얘가 바로 실행된다고 생각하자.
public class MemberUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;

	//UserDetailsService의 추상 메서드 loadUserByUsername(String username) 구현 
	//로그인 폼에서, 파라미터 name을 ID는 username으로, PW는 password로 설정해줘야 한다 ! 
	//MemberUser객체에 바인딩된 인증된 회원정보를 스프링에게 return해주면 holder메모리에 저장 	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		JOIN으로 회원 + 권한 조회 	username = memID
		Member member = memberMapper.memberLogin(username);
		
//		MemberUser -> User -> UserDetails(MemberUser extends User, User implements UserDetails)
//		해당 ID의 회원이 있다면, MemberUser객체에 회원을 객체바인딩 !
		if(member != null) {
			return new MemberUser(member); //마치 session 객체 바인딩 -> JSP에서 출력 
		} else {
			throw new UsernameNotFoundException(username + " not exist");
		
	}

	}
}
