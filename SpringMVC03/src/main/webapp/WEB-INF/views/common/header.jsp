<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
    					<!--contextPath = /mvc03 : localhost:8080/mvc03/ 요청-->
      <a class="navbar-brand" href="${contextPath}">Spring MVC03</a>
    </div>
    <ul class="nav navbar-nav">
    					<!--contextPath = /mvc03 : localhost:8080/mvc03/ 요청-->
      <li class="active"><a href="${contextPath}">Home</a></li>
      
      <li><a href="#">Page 1</a></li>
      
      <li><a href="#">Page 2</a></li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 3 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 3-1</a></li>
          <li><a href="#">Page 3-2</a></li>
          <li><a href="#">Page 3-3</a></li>
        </ul>
      </li>

      <!--localhost:8080/mvc03/board.do 요청-->
      <li><a href="${contextPath}/board.do">게시판</a></li>
    </ul>
    
    <!-- 로그인을 하지 않은 경우 : session -->
    <c:if test="${empty sessionScope.member}">
    <ul class="nav navbar-nav navbar-right">
      <li><a href="${contextPath}/memberInsertForm.do"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
      <li><a href="${contextPath}/memberLoginForm.do"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
    </ul>
	</c:if>
	
	<!-- 로그인을 한 경우 : session.setAttribute("member", member); -->   
    <c:if test="${!empty sessionScope.member}">
     <ul class="nav navbar-nav navbar-right">
	  <li>
			    <!-- 로그인을 한 경우 : session.setAttribute("member", member);  세션 객체바인딩 꺼내오기-->   
		  <c:if test="${!empty sessionScope.member}">
		  	 <!-- 사진 업로드 했을 때, 회원이미지 -->
			  <c:if test="${!empty sessionScope.member.memProfile}">
		    	  <div><a href="${contextPath}/memberImageForm.do"><img class="img-circle" src="${contextPath}/resources/upload/${sessionScope.member.memProfile}" style="width: 40px; height: 40px;"/></a></div> 
		 	 </c:if>
		 	 <!-- 사진 업로드 안했을 때, 기본이미지 -->
			  <c:if test="${empty sessionScope.member.memProfile}">
		    	  <div><a href="${contextPath}/memberImageForm.do"><img class="img-circle" src="${contextPath}/resources/images/basicProfile.jpeg" style="width: 40px; height: 40px;"/></a></div> 
		 	 </c:if>
		  </c:if>
	  </li>     
      <li><a> ${sessionScope.member.memName}님 환영합니다. </a></li>
      <li><a href="${contextPath}/memberUpdateForm.do"><span class="glyphicon glyphicon-wrench"></span> 회원정보 수정</a></li>
      <li><a href="${contextPath}/memberImageForm.do"><span class="glyphicon glyphicon-picture"></span> 프로필사진 수정 </a></li>
      <li><a href="${contextPath}/memberLogout.do"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
     </ul>
    </c:if>
  </div>
</nav>