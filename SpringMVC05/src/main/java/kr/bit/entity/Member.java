package kr.bit.entity;

import java.util.List;

import lombok.Data;

@Data
public class Member {
	
	  private int memIdx; //PK
	  private String memID;  
	  private String memPassword;
	  private String memName;
	  private int memAge; 
	  private String memGender;
	  private String memEmail;
	  private String memProfile; 
	  
	  private List<AuthVO> authList; 
	  //authList[0].auth, authList[1].auth, authList[2].auth

}
