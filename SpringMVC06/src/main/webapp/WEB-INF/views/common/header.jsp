<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!-- 권한 제외한 다른 회원의 모든 정보들 추출. -->
<c:set var="user" value="${SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<!-- 권한은 따로 추출 -->
<c:set var="auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}"/>

<script>
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
	
	/* 시큐리티 로그아웃 (SecurityConfig) */
	/* 방법 1 :ajax */
	function securityLogout(){
	  	$.ajax({
	  		url : "${contextPath}/logout",
	  		type: "post",
	  		beforeSend: function(xhr){
	              xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
	          },
	  		success : function(){
	  			location.href="${contextPath}/bye";
	  		},
	  		error : function(){ alert("error");}    		
	  	}); 
	}
	
	/* 방법 2 : js로 post요청 하기 */
	function securityLogoutPost(){
		    let f = document.createElement('form');
		    let obj;
		    obj = document.createElement('input');
		    obj.setAttribute('type', 'hidden');
		    obj.setAttribute('name', '${_csrf.parameterName}');
		    obj.setAttribute('value', '${_csrf.token}');
		
		    f.appendChild(obj);
		    f.setAttribute('method', 'post');
		    f.setAttribute('action', '${contextPath}/logout');
		    document.body.appendChild(f);
		    f.submit();
		}
	
</script>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
    					<!--contextPath = /mvc03 : localhost:8080/mvc03/ 요청-->
      <a class="navbar-brand" href="${contextPath}/main">Spring MVC06</a>
    </div>
    <ul class="nav navbar-nav">
    					<!--contextPath = /mvc03 : localhost:8080/mvc03/ 요청-->
      <li class="active"><a href="${contextPath}/main">Home</a></li>
      
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
    <%-- <c:if test="${empty sessionScope.member}"> --%>
    <!-- 로그인을 하지 않은 경우 : 시큐리티 -->
    <security:authorize access="isAnonymous()">
    <ul class="nav navbar-nav navbar-right">
      <li><a href="${contextPath}/memberInsertForm.do"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
      <li><a href="${contextPath}/memberLoginForm.do"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
    </ul>
    </security:authorize>
	<%-- </c:if> --%>
	
	<!-- 로그인을 한 경우 : session.setAttribute("member", member); -->   
    <%-- <c:if test="${!empty sessionScope.member}"> --%>
    <security:authorize access="isAuthenticated()">
     <ul class="nav navbar-nav navbar-right">
	  <li>
			    <!-- 로그인을 한 경우 : session.setAttribute("member", member);  세션 객체바인딩 꺼내오기-->   
		  	 <!-- 사진 업로드 했을 때, 회원이미지 -->
			  <%-- <c:if test="${!empty sessionScope.member.memProfile}"> --%>
			  <c:if test="${!empty user.member.memProfile}">
		    	  <div><a href="${contextPath}/memberImageForm.do"><img class="img-circle" src="${contextPath}/resources/upload/${user.member.memProfile}" style="width: 40px; height: 40px;"/></a></div> 
		 	 </c:if>
		 	 <%-- </c:if> --%>
		 	 <!-- 사진 업로드 안했을 때, 기본이미지 -->
<%-- 			  <c:if test="${empty sessionScope.member.memProfile}"> --%>
			  <c:if test="${empty user.member.memProfile}">
		    	  <div><a href="${contextPath}/memberImageForm.do"><img class="img-circle" src="${contextPath}/resources/images/basicProfile.jpeg" style="width: 40px; height: 40px;"/></a></div> 
		 	 </c:if>
<%-- 		 	 </c:if>
 --%>		  
	  </li>
	       
	  <!-- 회원 권한 보여주기(반복문 필요)  role_user-->
      <%-- <li><a> ${sessionScope.member.memName}님 환영합니다. (<c:forEach items="${sessionScope.member.authList}" var="authVO">${fn:substring(authVO.auth,5,6)} </c:forEach>)</a></li> --%>
      <li>
      	<a> ${user.member.memName}님 환영합니다. (
    	<security:authorize access="hasRole('ROLE_USER')"> USER </security:authorize>
    	<security:authorize access="hasRole('ROLE_MANAGER')"> MANAGER </security:authorize>
    	<security:authorize access="hasRole('ROLE_ADMIN')"> ADMIN </security:authorize> 
    	)
      		<%--<c:forEach items="${user.member.authList}" var="authVO">
       			<c:if test="${authVO.auth == 'ROLE_USER'}"> USER </c:if>
      			<c:if test="${authVO.auth == 'ROLE_MANAGER'}"> MANAGER </c:if>
      			<c:if test="${authVO.auth == 'ROLE_ADMIN'}"> ADMIN </c:if> --%> 
      	</a>
      </li>
      
      <li><a href="${contextPath}/memberUpdateForm.do"><span class="glyphicon glyphicon-wrench"></span> 회원정보 수정</a></li>
      <li><a href="${contextPath}/memberImageForm.do"><span class="glyphicon glyphicon-picture"></span> 프로필사진 수정 </a></li>
      <!-- <li><a href="javascript:securityLogout()"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li> -->
      <li><a href="javascript:securityLogout()"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
     </ul>
     </security:authorize>
<%--     </c:if>--%>
  </div>
</nav>