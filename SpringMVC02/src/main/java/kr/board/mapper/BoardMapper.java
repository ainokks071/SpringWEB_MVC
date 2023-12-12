package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;

// Interface = 추상메서드 집합.
// 구현부가 없는데 어떻게 실행되는가?
// by SqlSessionFactory (스프링-마이바티스 API) : 구현 클래스 
// -> MapperInterface <-> Mapper.xml 연결, 실행

// MapperInterface : CRUD 메서드 작성 !
// CRUD 메서드 : 등록Create(Insert), 조회Read(Get), 수정Update(Modify), 삭제Delete(Remove)
// BoardMapper.xml(SQL 쿼리문 작성)파일과 연결 필요 !
//어노테이션 : Mybatis API 필요(pom.xml)
@Mapper 
public interface BoardMapper {	 
	
//	게시판 전체목록 조회
    public List<Board> boardList();

//	게시물 등록
	public void boardInsert(Board vo);

//	게시물 상세보기
	public Board boardContent(int idx);

//	게시물 삭제하기
	public void boardDelete(int idx);

//	게시물 수정하기
	public void boardUpdate(Board vo);

//	게시물 조회수 1 증가 
	@Update("update myboard set count = count + 1 where idx = #{idx}")
	public void boardCount(int idx);

     
//     public void boardInsert(Board vo);
//     
//     public Board boardContent(int idx);
//     
//     public void boardDelete(int idx);
//     
//     public void boardUpdate(Board vo);
//     
//     @Update("update myboard set count=count+1 where idx=#{idx}")
//     public void boardCount(int idx);
}
