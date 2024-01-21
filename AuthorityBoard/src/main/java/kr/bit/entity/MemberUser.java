package kr.bit.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class MemberUser extends User{

	public MemberUser(String username, String password, 
					Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	private Member member;
	
//Collection<? extends GrantedAuthority> authorities
//지네릭 타입이 GrantedAuthority의 자식타입만 가능
//객체 바인딩 !!(member에는 로그인 한 회원의 정보가 들어있다.)
	public MemberUser(Member member) {
//		부모인 User : memID, memPass, memAuth 정보 초기화 !
		super(member.getMemID(), member.getMemPassword(), 
		      //스트림을 이용하여, List<AuthVO> -> Collection<GrantedAuthority>로 변경 !
			  member.getAuthList().stream()
			  .map(AuthVO -> new SimpleGrantedAuthority(AuthVO.getAuth()))
			  .collect(Collectors.toList()));
		
//		나머지 정보 : email, name, profile, gender 등은 자식객체인 MemberUser에 초기화 
		this.member = member;
		
		
//		따라서, MemberUser객체는 부모인 User(memID, memPass, memAuth)와 자신의 필드인 member(모든 정보O) 사용 가능 !! 
	}

}
