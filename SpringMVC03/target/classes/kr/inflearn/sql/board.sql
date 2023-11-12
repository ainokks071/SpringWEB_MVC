-- test DB

-- board(게시판) table

-- 게시판 테이블 생성 
create table tb_board (
 idx int not null auto_increment, -- 게시물의 고유번호, insert시 자동증가입력
 title varchar(100) not null, -- 게시물의 제목 
 contents varchar(4000) not null, -- 게시물의 내용 
 count int, -- 게시물의 조회수 
 writer varchar(30) not null, -- 게시물의 작성자 
 indate datetime default now() not null, -- 게시물의 등록일 : insert시 현재시각 자동입력 
 primary key(idx)
);

-- 테이블 삭제  
drop table tb_board;


--select(조회, 검색)
select * from tb_board;

--insert(저장)
insert into tb_board(title, contents, count, writer)
values('글제목', '글내용', 1, '김경수'); 

--update(수정)
update member set age=45, phone='010-1111-0000' where id='kimks071';

--delete(삭제)
delete from tb_board;