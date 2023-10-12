package kr.bit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.bit.model.MemberVO;

//인터페이스 : 추상메서드 집합 (기능 정의만! 구현X)
// DAO -> MapperInterface로 교체 : mapper.xml과 연결되어 있다. how? (메서드 이름 = xml의 "id", namespace참조.)
// SqlSessionFactory + SqlSession 객체 불러와서 작업. 
// root-context.xml의 <mybatis:scan base-package="kr.bit.mapper" />에 의해 MapperInterface 이용 가능.

// mapper.xml파일 이용하지않고 메서드마다 (어노테이션+쿼리)를 작성해도 된다. 
//  -> 이때에는 mapper.xml파일을 삭제하거나 다른 패키지로 이동시켜야함. 

// @Mapper는 생략 가능.

@Mapper
public interface MemberMapper {

//	전체 회원 조회 기능 
	@Select("select * from member")
	public List<MemberVO> memberList();
	
//	회원 가입 기능 
	@Insert("insert into member(id, pass, name, age, email, phone)\n"
			+ "	 values(\n"
			+ "	 #{id},\n"
			+ "	 #{pass},\n"
			+ "	 #{name},\n"
			+ "	 #{age},\n"
			+ "	 #{email},\n"
			+ "	 #{phone}\n"
			+ "	 )")
	public int memberInsert(MemberVO vo);
	
//	회원 삭제 기능 
	@Delete("delete from member where num=#{num}")
	public int memberDelete(int num);
	
//	특정 회원 조회 기능 
	@Select("select * from member where num=#{num}")
	public MemberVO memberContent(int num);
	
//	회원 수정 기능 
	@Update("update member set age=#{age}, phone=#{phone}, email=#{email} where num=#{num}")
	public int memberUpdate(MemberVO vo);
	
//	public int memberFileInsert(MemberVO vo);
//	public String memberLogin(MemberVO vo);
//	public boolean memberDbcheck(String id);
//	public int memberFileDelete(int num);
//	public int memberFileUpdate(MemberVO vo);
}
