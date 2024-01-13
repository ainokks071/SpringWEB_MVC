<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />

     <!-- pannel template  -->
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC05</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  	<script type="text/javascript">
	   /*  로그인 실패 후, 리다이렉트시 "한번" 뿌려줄 데이터 객체바인딩
			rattr.addFlashAttribute("msg1", "로그인 실패");
			rattr.addFlashAttribute("msg2", "아이디와 비밀번호를 확인해주세요."); */
		$(document).ready(function() {
		  /* msg1이 비어있지 않다는 것은, 로그인에 실패한 것. */
			if(${!empty msg1}) {
				$("#modalTitle").text("${msg1}");
				$("#modalContent").html("<span style='color: red;'> ${msg2} </span>");
				$("#myModal").modal("show");
			}
		});  
	</script>
  
  
</head>
<body>
 
<jsp:include page="../common/header.jsp"></jsp:include>
 
<div class="container">
  <h2>SpringMVC05</h2>
  <div class="panel panel-default">
    <div class="panel-heading">로그인 화면</div>
    <div class="panel-body">
      <form action="${contextPath}/memberLogin.do" method="post">
      	<!--스프링시큐리티가 적용되면, 모든 POST방식에는 CSRF 토큰을 넘겨줘야한다.   -->
      	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
         <table class="table table-bordered" style="text-align: center; border: 1px solid #dddddd;">
           <tr>
             <td style="width: 110px; vertical-align: middle;">아이디</td>
             <td><input type="text" id="memID" name="memID" class="form-control" maxlength="20" placeholder="아이디를 입력하세요."/></td>
           </tr>
           <tr>
             <td style="width: 110px; vertical-align: middle;">비밀번호</td>
             <td colspan="2"><input type="password" id="memPassword" name="memPassword" class="form-control" maxlength="20" placeholder="비밀번호를 입력하세요."/></td>            
           </tr>      
           <tr>
             <td colspan="2" style="text-align: left;">
             <!-- ajax로도 해볼 것 ! -->
                <input type="submit" class="btn btn-primary btn-sm pull-right" value="로그인"/>
             </td>             
           </tr>
         </table>
      </form> 
    </div>
    <div class="panel-footer">ainokks071@gmail.com</div>
  </div>
</div>

<!-- 모달창 -->
<jsp:include page="../common/modal.jsp"/>
<!-- 위의 자바스크립트에서 id로 접근 -->
<!-- <div id="myModal" class="modal fade" role="dialog">
<h4 id="modalTitle" class="modal-title">모달 창 제목 뿌려주기</h4>
<p id="modalContent"> 모달 내용 뿌려주기</p> -->

</body>
</html>