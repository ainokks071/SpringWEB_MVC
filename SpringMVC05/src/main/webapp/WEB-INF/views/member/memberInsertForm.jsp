<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
    
<!DOCTYPE html>
<html>
<head>
  <title>Spring MVC05</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  
/*  회원정보 입력누락으로 리다이렉트시, 한번 뿌려줄 데이터 객체바인딩
    rattr.addFlashAttribute("msg1", "회원가입 실패");
	rattr.addFlashAttribute("msg2", "모든 정보를 입력해주세요."); */
	$(document).ready(function() {
	  /* 회원가입화면에서 누락 있으면 실행 */
	  
	  /* msg1이 비어 있지 않다 = 회원가입 시 누락이 있었다. */
		if(${!empty msg1}) {
			$("#modalTitle").text("${msg1}"); //"회원가입 실패"
			$("#modalContent").html("<span style='color: red;'> ${msg2} </span>"); //"모든 정보를 입력해주세요."
			$("#myModal").modal("show");
		}
	});  
  
  
     /* id 중복확인 : ajax 비동기 */
	function idDuplicatedCk() {
    	 /* 사용자가 입력한 아이디 값 가져오기 */
    	var memID = $("#memID").val();
    	
    	if(memID == '') {
    		/* 아무것도 입력하지 않고, 중복체크할 시. */
    		alert("아이디를 입력하세요");
    		return;
    	}
    	
    	/* 입력을 하긴 했다. */
		$.ajax({
			url : "${contextPath}/member/dbCheck",
			type : "get",
			data : {"memID":memID},
  			success : function(id) {
  				/* 중복이 아닐 경우 -> '' 빈문자열 반환
  				   중복일 경우 -> 'ID' 해당 ID 반환 */
				if(id == '') {
				/* alert("중복이 아닙니다.");*/
				
				/* 아래 모달창 */
 				/* $("#idDbMsg").text("중복이 아닙니다. 사용 가능한 아이디입니다."); */
 				$("#modalTitle").text("ID 중복 확인");
 				$("#modalContent").html("<span style='color: green;'> 사용 가능한 아이디입니다.</span>");
 				$("#myModal").modal("show");
 				
				} else {
				/* $("#idDbMsg").text("중복된 아이디입니다.");*/
				$("#modalTitle").text("ID 중복 확인");
 				$("#modalContent").html("<span style='color: red;'> 중복된 아이디입니다.</span>");
 				$("#myModal").modal("show");
  				}
  			},	
			error : function() { alert("error"); }
		});
	}
  
  /* onkeyup에 의해 input창에 키보드를 눌렀다 뗄 때마다 실행되는 함수 */
	function passwordCheck() {
		var pwd1 = $("#memPassword1").val();
		var pwd2 = $("#memPassword2").val();
		
		/* pwd2 비밀번호확인 input이 비어있으면(= pwd1만 입력하고 있을때는) 실행X */
		
		/* pwd2 비밀번호확인 input이 입력되어 있거나, 입력하고 있을 때 실행 */
		if(pwd2 != '') {
			if(pwd1 != pwd2) {
				$("#passMessage").text("비밀번호가 일치하지않습니다.");
				$("#passMessage").css("color", "red");
				
				/* 일치하면, 일치 메세지 보여주고, hidden -> memPassword에 비밀번호값 대입! */
			} else {
				$("#passMessage").text("비밀번호가 일치합니다.");
				$("#passMessage").css("color", "green");
				
				/* pwd1과 pwd2가 일치한다면, hidden -> memPassword에 비밀번호값 대입! 하여 전송 */
				$("#memPassword").val(pwd1);
			}
			
		} 
	}
  
  /* 회원가입 등록버튼 클릭 시! */
  function registerCheck() {
	  /* 나이는 int인데, 아무것도 입력하지 않고 서버로 넘어가면 -> null이 된다. 따라서 에러 발생 !!
	     프론트단에서 해결해주는 게 좋겠다. */
	  /* 숙제) 나이뿐만 아니라, 모든 정보 체크해볼 것! */
	  
	  /* 누락 체크 */
	  var memID = $("#memID").val();
	  var memPassword1 = $("#memPassword1").val();
	  var memPassword2 = $("#memPassword2").val();
	  var memName = $("#memName").val();
	  var memAge = $("#memAge").val();
	  var memEmail = $("#memEmail").val();
	  
	  /* 체크박스에 체크하지 않으면 false반환 ! */
	  var auth1 = $('input:checkbox[id="auth1"]').is(":checked");
	  var auth2 = $('input:checkbox[id="auth2"]').is(":checked");
	  var auth3 = $('input:checkbox[id="auth3"]').is(":checked");
/* 	  var auth1 = $("#auth1").val();
	  var auth2 = $("#auth2").val();
	  var auth3 = $("#auth3").val();   */
	  /* 1. 하나라도 누락 있을 시.. 누락 체크 */
	  if(memID == '' || memPassword1 == '' || memPassword2 == '' || memName == '' || memAge == '' ||
	     memAge == 0 || memAge > 120 || memEmail == '' ||
	    (auth1 == false && auth2 == false && auth3 == false)) {
		  
		$("#modalTitle").text("회원가입 실패"); //"회원가입 실패"
		$("#modalContent").html("<span style='color: red;'> 모든 정보를 입력해주세요. </span>"); //"모든 정보를 입력해주세요."
		$("#myModal").modal("show");
		  /* 아래의 전송함수 실행 안되게 하기 위해 return false 사용. */
		  return false;
	  }
	  
	  /* 2. 누락은 없지만, 비밀번호 다를 시.. */
	  if(memPassword1 != memPassword2) {
			$("#modalTitle").text("회원가입 실패"); //"회원가입 실패"
			$("#modalContent").html("<span style='color: red;'> 비밀번호가 일치하지 않습니다. 다시 입력해주세요. </span>"); //"모든 정보를 입력해주세요."
			$("#myModal").modal("show");
			  /* 아래의 전송함수 실행 안되게 하기 위해 return false 사용. */
			  return false;
	  }
	  
	  /* 3. 아이디 중복되면, 전송 불가. */
		$.ajax({
			url : "${contextPath}/member/dbCheck",
			type : "get",
			data : {"memID":memID},
  			success : function(id) {
  				/* 중복일 경우 -> 'ID' 해당 ID 반환 */
				if(id != '') {
					/* 아래 모달창 */
	 				/* $("#idDbMsg").text("중복이 아닙니다. 사용 가능한 아이디입니다."); */
	 				$("#modalTitle").text("회원가입 실패");
	 				$("#modalContent").html("<span style='color: red;'> 중복된 아이디입니다. 다시 입력해주세요.</span>");
	 				$("#myModal").modal("show");
	 				/* if문 바로 밖 함수를 빠져나간다. 따라서, submit()를 else문 안에 감싸줘야한다. */
	 				return false;
	 				
				} else {
					
					/* 모든 검증절차 통과하면, 서버로 전송(/memberInsert.do) */
					/* 자바스크립트로 폼 전송하기 frm은 form의 name으로 내가 설정해준 것.*/
					document.frm.submit();
				}
  			},	
			error : function() { alert("error"); }
		});
  }
  </script>
</head>

<!-- 헤더 -->
<jsp:include page="../common/header.jsp"></jsp:include>

<body>
<div class="container">
  <h2>SpringMVC05</h2>
  <div class="panel panel-default">
    <div class="panel-heading">회원가입화면</div>
    
    <!-- input의 name = MemberVO의 field ! -> memID, memPassword, ..., authList[0].auth -->
    <div class="panel-body" id="writeForm" >
	  <form name="frm" action="${contextPath}/memberInsert.do" method="post">
		<!--스프링시큐리티가 적용되면, 모든 POST방식에는 CSRF 토큰을 넘겨줘야한다.   -->
	 	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	 	  <input type="hidden" id="memPassword" name="memPassword" value=""/>
		  <input type="hidden" id="memProfile" name="memProfile" value=""/>
	  
          <table class="table table-bordered" style="text-align: center; border: 1px solid #dddddd;">
           <tr>
             <td style="width: 110px; vertical-align: middle;">아이디</td>
             <td><input type="text" id="memID" name="memID" class="form-control" maxlength="20" placeholder="아이디를 입력하세요."/></td>
             <td style="width: 110px;"><button type="button" class="btn btn-primary btn-sm" onclick="idDuplicatedCk()">중복확인</button></td>
           </tr>
           
           <tr>
             <td style="width: 110px; vertical-align: middle;">비밀번호</td>
             															<!--input창에 키보드 입력할 때마다 함수 호출  -->
             <td colspan="2"><input type="password" id="memPassword1" name="memPassword1" onkeyup="passwordCheck()" class="form-control" maxlength="20" placeholder="비밀번호를 입력하세요."/></td>            
           </tr>
           <tr>
             <td style="width: 110px; vertical-align: middle;">비밀번호확인</td>
             										<!-- onkeyup : 키보드를 눌렀다 떼었을 때, check()로 위에 입력한 비밀번호와 입력하는지 확인  -->
             <td colspan="2"><input type="password" id="memPassword2" name="memPassword2" onkeyup="passwordCheck()" class="form-control" maxlength="20" placeholder="비밀번호를 확인하세요."/></td>            
           </tr>
           
            <tr>
             <td style="width: 110px; vertical-align: middle;">사용자 이름</td>
             <td colspan="2"><input type="text" id="memName" name="memName" class="form-control" maxlength="20" placeholder="이름을 입력하세요."/></td>            
           </tr>
           <tr>
             <td style="width: 110px; vertical-align: middle;">나이</td>
             <td colspan="2"><input type="number" id="memAge" name="memAge" class="form-control" maxlength="20" placeholder="나이를 입력하세요."/></td>            
           </tr>
           <tr>
             <td style="width: 110px; vertical-align: middle;">성별</td>
             <td colspan="2">
                <div class="form-group" style="text-align: center; margin: 0 auto;">
                    <div class="btn-group" data-toggle="buttons">
                       <label class="btn btn-primary active">
                         <input type="radio" name="memGender" autocomplete="off" value="남자" checked/>남자
                       </label>
                        <label class="btn btn-primary">
                         <input type="radio" name="memGender" autocomplete="off" value="여자"/>여자
                       </label>
                    </div>                    
                </div>
             </td>            
           </tr> 
           
           <tr>
             <td style="width: 110px; vertical-align: middle;">이메일</td>
             <td colspan="2"><input type="text" id="memEmail" name="memEmail" class="form-control" maxlength="20" placeholder="이메일을 입력하세요."/></td>            
           </tr>
           
           <!-- 권한체크박스추가 -->
           <tr>
             <td style="width: 110px; vertical-align: middle;">사용자 권한</td>
             <td colspan="2">
             						<!-- MemberVO : List<AuthVO> authList, AuthVO : String auth; -->
                <input type="checkbox" id="auth1" name="authList[0].auth" value="ROLE_USER"> ROLE_USER
	            <input type="checkbox" id="auth2" name="authList[1].auth" value="ROLE_MANAGER"> ROLE_MANAGER
	            <input type="checkbox" id="auth3" name="authList[2].auth" value="ROLE_ADMIN"> ROLE_ADMIN           
            </tr>
           
           <tr>
             <td colspan="3" style="text-align: left;">
                <span id="passMessage" style="color: red"> </span>   <input type="button" class="btn btn-primary btn-sm pull-right" onclick="registerCheck()" value="등록" />
             </td>             
           </tr>
           
         </table>
         
      </form>    
    </div>
    
    
    
<jsp:include page="../common/modal.jsp"/>
<!-- <h4 id="modalTitle">모달 창 제목 뿌려주기</h4>
	  <p id="modalContent">모달 내용 뿌려주기</p>    						-->
        
        
        
    <div class="panel-footer">ainokks071@gmail.com</div>
  </div>
</div>


</body>



</html>