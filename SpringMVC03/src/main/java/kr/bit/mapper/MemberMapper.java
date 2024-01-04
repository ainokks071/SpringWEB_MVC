package kr.bit.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.bit.entity.Member;

@Mapper
public interface MemberMapper {
	

//	아이디 중복체크
	public String memberDbcheck(String memID);

//	회원 가입 
	public int memberInsert(Member vo); //회원가입 insert 성공하면 1, 실패하면 0

//	로그인 체크
	public Member memberLogin(Member vo);

//	회원 정보 수정 
	public void memberUpdate(Member vo);

//	사진 등록 
	public void imageUpdate(Member vo);

//	회원 한명 조회 
	public Member getMember(int memIdx);

}
