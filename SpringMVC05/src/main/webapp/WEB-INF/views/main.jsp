<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>MVC 05</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
   <script type="text/javascript">
   /*  회원가입 성공 후, 리다이렉트시 한번 뿌려줄 데이터 객체바인딩
		rattr.addFlashAttribute("msg1", "회원가입 성공");
		rattr.addFlashAttribute("msg2", vo.getMemName()+"님, 환영합니다."); */
		
   /*  로그인 성공 후, 리다이렉트시 한번 뿌려줄 데이터 객체바인딩
		rattr.addFlashAttribute("msg1", "로그인 성공");
		rattr.addFlashAttribute("msg2", vo.getMemName()+"님, 환영합니다."); */
	$(document).ready(function() {
	  /* msg1이 비어있지 않다는 것은, 회원가입 성공한 것. */
		if(${!empty msg1}) {
			$("#modalTitle").text("${msg1}");
			$("#modalContent").html("<span style='color: green;'> ${msg2} </span>");
			$("#myModal").modal("show");
		}
	});  
		
		
		
		
		
   </script>
  
</head>
<body>

<!-- 상단 nav바 -->
<jsp:include page="common/header.jsp"/>     

 <div class="container">
  <h3>Spring MVC 05</h3>
  <h4> <c:if test="${!empty sessionScope.member}"> ${member.memName}님 환영합니다.</c:if></h4>
  <h4> <c:if test="${empty sessionScope.member}"> 로그인 해주세요 !</c:if></h4>
  
  <p>게시판 + 회원관리 : 권한 -> 회원가입, 로그인 기능 구현</p>
</div>
 
<div class="container">
  <div class="panel panel-default">
  
    <div>
       <img src="${contextPath}/resources/images/background.jpeg" style="width: 100%; height: 380px;"/>
    </div>
    
    <div class="panel-body"> <!-- 탭 메뉴 -->
    
		<ul class="nav nav-tabs">
		  <li class="active"><a data-toggle="tab" href="#home">Home</a></li>
		  <li><a data-toggle="tab" href="#menu1">게시판</a></li>
		  <li><a data-toggle="tab" href="#menu2">공지사항</a></li>
		</ul>		
		
		<div class="tab-content">
		  <div id="home" class="tab-pane fade in active">
		    <h3>HOME</h3>
		    <p>Some content.</p>
		  </div>
		  
		  <div id="menu1" class="tab-pane fade">
		    <h3>게시판</h3>
		    <p>Some content in menu 1.</p>
		  </div>
		  
		  <div id="menu2" class="tab-pane fade">
		    <h3>공지사항</h3>
		    <p>Some content in menu 2.</p>
		  </div>
		</div>
				
    </div>
    
    <div class="panel-footer"> ainokks071@gmail.com </div>
    
  </div>
  
</div>








<!-- 모달창 -->
<jsp:include page="common/modal.jsp"/>
<!-- 위의 자바스크립트에서 id로 접근 -->
<!-- <div id="myModal" class="modal fade" role="dialog">
<h4 id="modalTitle" class="modal-title">모달 창 제목 뿌려주기</h4>
<p id="modalContent"> 모달 내용 뿌려주기</p> -->
 </body>
</html>
