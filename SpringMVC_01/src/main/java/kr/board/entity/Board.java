package kr.board.entity;

import lombok.Data;

//entity = domain
//Lombok API : getter(), setter(), toString() 메서드 코드 다이어트 라이브러리. 사용법 : @Data
@Data
public class Board {
	  private int idx; // 번호
	  private String title; // 제목
	  private String content; // 내용
	  private String writer; // 작성자
	  private String indate; // 작성일
	  private int count; // 조회수
	  // setter , getter 
}
