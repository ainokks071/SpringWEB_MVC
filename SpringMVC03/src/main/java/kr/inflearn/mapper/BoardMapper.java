package kr.inflearn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.inflearn.model.BoardVO;

//	영속 계층 : 과거DAO -> [ MapperInterface로 교체 : Mapper.xml or 어노테이션(@)에 기술되어 있는 SQL 쿼리 수행 ]

/*
 	* [ MapperInterface와 Mapper.xml 연결방법 ]
 	* 
 	* 1. BoardMapper.xml에 mapper namespace작성, 메서드명 = id명 일치시켜주기
 	* 2. pom.xml에 mybatis API 작성 -> 위의 import org.apache.ibatis.annotations.Mapper; @Mapper어노테이션 사용가능
 	* 3. root-context.xml에 <mybatis:scan base-package="kr.inflearn.mapper" /> 작성 
 	     -> @Mapper 어노테이션을 인식하여, 최종적으로 MapperInterface와 Mapper.xml파일을 연결해준다 !! 
 	     + 컨테이너에 객체bean 생성 -->	
 	* 4. DB작업은 SQL SessionFactory가 수행.
*/

// 영속 계층 
@Mapper
public interface BoardMapper {
	
//	게시물 리스트 조회 
	public List<BoardVO> getList(); 

//	게시물 등록 
	public void insert(BoardVO boardVO);
	
//	특정 게시물 상세보기  
	public BoardVO read(int idx);
	
//	게시물 삭제 
	public int delete(int idx);
	
//	게시물 수정 
	public int update(BoardVO boardVO);

//	게시물 조회수 올리기
	public int count(int idx);
	
}
