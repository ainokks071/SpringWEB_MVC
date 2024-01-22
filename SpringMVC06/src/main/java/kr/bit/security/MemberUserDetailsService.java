package kr.bit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.bit.entity.Member;
import kr.bit.entity.MemberUser;
import kr.bit.mapper.MemberMapper;

// 2)
// SecurityConfig.java(httpsecurity)에 설정해놓은
// 로그인 처리 시 DB에서 회원을 조회하는 서비스계층. (by memID = username)

// 로그인하면 얘가 바로 실행된다고 생각하자.
// UserDetailsService : spring에서 제공해주는 인터페이스 !(loadUserByUsername 메서드가 핵심.)
public class MemberUserDetailsService implements UserDetailsService {
//	DB에서 회원 조회하기 위해 mapper 주입 
	@Autowired
	private MemberMapper memberMapper;

	//UserDetailsService의 추상 메서드 loadUserByUsername(String username) 구현 
	//로그인 폼에서, 파라미터 name을 memID는 username으로, memPW는 password로 설정해줘야 한다 ! 
	//MemberUser객체에 바인딩된 인증된 회원정보를 스프링에게 return해주면 holder메모리에 저장
	//사용자가 입력한 ID가 loadUserByUsername메서드의 전달값으로 들어간다!!	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		JOIN으로 (회원 + 권한) 조회 	username = memID
		Member member = memberMapper.memberLogin(username);
		
//		조회한 회원을 UserDetail에 객체 바인딩 필요!
		
//		MemberUser -> User -> UserDetails(MemberUser extends User, User implements UserDetails)
//		해당 ID의 회원이 있다면, MemberUser객체에 회원을 객체바인딩 !
		if(member != null) {
			return new MemberUser(member);
//			security container(security context)로 리턴하겠지? -> 객체바인딩!
			//마치 session 객체 바인딩 -> JSP에서 출력 
		} else {
			throw new UsernameNotFoundException(username + " not exist");
		}

	}
}
