-- test DB
-- member(회원) table

-- 테이블 생성 
create table tb_board (
 idx int not null auto_increment, --게시글의 고유 인덱스, insert시 자동증가입력
 title varchar(100) not null, -- 게시글 제목 
 contents varchar(4000) not null, -- 게시글 내용 
 count int, -- 게시글의 조회수 
 writer varchar(30) not null, -- 게시글 작성자 
 indate datetime default now() not null, -- 게시글 등록일 : insert시 현재시각 자동입력 
 primary key(idx)
);

-- 테이블 삭제  
drop table tb_board;


--select(조회, 검색)
select * from tb_board;

--insert(저장)
insert into tb_board(title, contents, count, writer)
values('ㅎㅇ', 'ㅎㅇ', 0, '김경수'); 

--update(수정)
update member set age=45, phone='010-1111-0000' where id='kimks071';

--delete(삭제)
delete from member where id='admin';
delete from member;