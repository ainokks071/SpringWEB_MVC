<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<c:set var="auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}"/>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>Board</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="${contextPath}/resources/js/board.js" type="text/javascript"></script>
</head>
<body>
 
 <!-- header -->
<jsp:include page="../common/header.jsp"/>
 
<div class="container">
  <h2>회원 게시판</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    
    <!-- 위에 javascript 함수로 이 div에 내용물 넣어줌. -->
    <div class="panel-body" id="viewList" style="display: block"> viewList </div>
    
    <!-- 존재는 하지만, 글쓰기 form은 처음에는 보이지 않음  -->
    <div class="panel-body" id="writeForm" style="display: none">
	<!-- <form action="boardInsert.do" method="post"> : 화면 전환되어버림 !-->
	<!-- form태그를 반복문 안에 작성할 수 없는 이유 : name의 값이 계속 변한다. -->
	 <form id="frm">
	 															<!--로그인 한 사용자의 아이디 넘어간다. -->
      <input type="hidden" id="memID" name="memID" value="${user.member.memID}" />
      <table class="table">
         <tr>
           <td>제목</td>
           <td><input type="text" id="title" name="title" class="form-control"/></td>
         </tr>
         
         <tr>
           <td>내용</td>
           <td><textarea rows="7" class="form-control" id="content" name="content"></textarea> </td>
         </tr>
         
         <tr>
           <td>작성자</td>
           <td><input type="text" id="writer" name="writer" class="form-control" value="${user.member.memID}" readonly/></td>
         </tr>
         
         <tr>
           <td colspan="2" align="center">
               <button type="button" class="btn btn-success btn-sm" onclick="boardInsert()">등록</button>
               <button type="reset" class="btn btn-warning btn-sm" id="formClear">취소</button>
<!--                <a href="/mvc02"> 리스트 </a>-->				
				<button type="button" class="btn btn-info btn-sm" onclick="closeForm()">리스트로</button>
 		   </td>
         </tr>
         
      </table>
     </form>	
    </div>
    <div class="panel-footer">ainokks071@gmail.com</div>
  </div>
</div>

</body>
</html>