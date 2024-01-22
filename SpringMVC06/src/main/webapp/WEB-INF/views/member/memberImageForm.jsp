<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<!-- 권한은 따로 추출 -->
<c:set var="auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}"/>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC04</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
	$(document).ready(function() {
			  /* 파일 업로드 성공 시 모달창  */
			if(${!empty msg1}) {
				$("#modalTitle").text("${msg1}");
				$("#modalContent").html("<span style='color: green;'> ${msg2} </span>");
				$("#myModal").modal("show");
			}
			  /* 용량 초과 파일 업로드 시 모달창  */
			if(${!empty msg3}) {
				$("#modalTitle").text("${msg3}");
				$("#modalContent").html("<span style='color: red;'> ${msg4} </span>");
				$("#myModal").modal("show");
			}
			  /* 이미지 확장자 아닐 시 모달창  */
			if(${!empty msg5}) {
				$("#modalTitle").text("${msg5}");
				$("#modalContent").html("<span style='color: red;'> ${msg6} </span>");
				$("#myModal").modal("show");
			}
		});  
  </script>
</head>
<body>
 
<jsp:include page="../common/header.jsp"></jsp:include>
 
<div class="container">
  <h2>SpringMVC04</h2>
  <div class="panel panel-default">
    <div class="panel-heading">회원 이미지 등록 화면</div>
    <div class="panel-body">
    							<!--multipart(request객체 body?) : 파일이름(문자열데이터) + 파일(바이너리데이터)-->
    							<!--파일 넘어간다고 서버에게 알려줌.-->
    							<!--스프링시큐리티가 적용되면, 모든 POST방식에는 CSRF 토큰을 넘겨줘야한다.   -->
    							<!-- enctype="multipart/form-data" 일 때는, 쿼리스트링 방식으로 토큰 넘겨줌  -->
      <form action="${contextPath}/memberImageInsert.do?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
      	<!--로그인 한 회원의 idx값 넘어간다.  -->
         <input type="hidden" name="memIdx" value="${user.member.memIdx}"/>
         <input type="hidden" name="memID" value="${user.member.memID}"/>
         
         <table class="table table-bordered" style="text-align: center; border: 1px solid #dddddd;">
           
           <tr>
             <td style="width: 110px; vertical-align: middle;">아이디</td>
             <td>${user.member.memID}</td>
           </tr>
           
           <tr>
             <td style="width: 110px; vertical-align: middle;">사진 업로드</td>
             <td colspan="2">
               <span class="btn btn-default"> <!-- 파일 첨부.  -->
                 이미지를 업로드하세요.<input type="file" name="memProfile" />
               </span>
             </td>            
           </tr>
                 
           <tr>
             <td colspan="2" style="text-align: left;">
                <input type="submit" class="btn btn-primary btn-sm pull-right" value="등록"/>
             </td>             
           </tr>
         </table>
         
      </form> 
    </div>
    <div class="panel-footer">ainokks071@gmail.com</div>
  </div>
</div>

<jsp:include page="../common/modal.jsp"/>

</body>
</html>