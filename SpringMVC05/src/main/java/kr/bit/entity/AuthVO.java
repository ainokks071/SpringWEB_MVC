package kr.bit.entity;

import lombok.Data;

@Data
public class AuthVO {
	private int num; // 일련번호
	private String memID; //회원 아이디 FK
	private String auth; // 회원권한(3가지 부여 , ROLE_USER, ROLE_MANAGER, ROLE_ADMIN)
}