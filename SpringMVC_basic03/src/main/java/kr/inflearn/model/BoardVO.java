package kr.inflearn.model;

import java.util.Date;

public class BoardVO {
// ** Board 테이블의 컬럼명과 일치
//create table tb_board (
// idx int not null auto_increment, --게시글의 고유 인덱스, insert시 자동증가입력
// title varchar(100) not null, -- 게시글 제목 
// contents varchar(4000) not null, -- 게시글 내용 
// count int, -- 게시글의 조회수 
// writer varchar(30) not null, -- 게시글 작성자 
// indate datetime default now() not null, -- 게시글 등록일 : insert시 현재시각 자동입력 
// primary key(idx)
//);
	private int idx;
	private String title;
	private String contents;
	private int count;
	private String writer; 
	private Date indate;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	
	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", title=" + title + ", contents=" + contents + ", count=" + count + ", writer="
				+ writer + ", indate=" + indate + "]";
	} 
	
	
	
}
