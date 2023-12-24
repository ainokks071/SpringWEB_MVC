<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
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
  <h3>Spring MVC 03</h3>
  	<!-- 로그인을 한 경우 : session.setAttribute("member", member);  세션 객체바인딩 꺼내오기-->   
  <p> <c:if test="${!empty member}"> ${member.memName}님 환영합니다.</c:if></p>
  <p> <c:if test="${empty member}"> 로그인 해주세요 !</c:if></p>
  
  <p>게시판 + 회원관리 : 권한 -> 회원가입, 로그인 기능 구현</p>
</div>

<!-- 모달창 -->
<jsp:include page="common/modal.jsp"/>
<!-- 위의 자바스크립트에서 id로 접근 -->
<!-- <div id="myModal" class="modal fade" role="dialog">
<h4 id="modalTitle" class="modal-title">모달 창 제목 뿌려주기</h4>
<p id="modalContent"> 모달 내용 뿌려주기</p> -->
 </body>
</html>
