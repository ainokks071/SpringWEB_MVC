package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;

// Interface = 추상메서드 집합.
// 구현부가 없는데 어떻게 실행되는가?
/* by SqlSessionFactory (스프링-마이바티스 API) 
 * -> MapperInterface <-> Mapper.xml 연결, 실행
*/

// CRUD 메서드 : 등록Create(Insert), 조회Read(Get), 수정Update(Modify), 삭제Delete(Remove)
// BoardMapper.xml(SQL 쿼리문 작성)파일과 연결 필요 !
@Mapper //- Mybatis API 필요
public interface BoardMapper {	 
	
     public List<Board> getList();
     
     public void boardInsert(Board vo);
     
     public Board boardContent(int idx);
     
     public void boardDelete(int idx);
     
     public void boardUpdate(Board vo);
     
     @Update("update myboard set count=count+1 where idx=#{idx}")
     public void boardCount(int idx);
}