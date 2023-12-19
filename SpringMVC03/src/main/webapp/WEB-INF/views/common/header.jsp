<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<!-- contextPath = mvc03 -->
<%-- [출처] ${contextPath} 설정 및 사용방법 ( ${pageContext.request.contextPath} )|작성자 Uncle Hoon --%>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${contextPath}">Spring MVC03</a>
    </div>
    <ul class="nav navbar-nav">
    					<!--contextPath = mvc03 : localhost:8080/mvc03/ 요청-->
      <li class="active"><a href="${contextPath}/">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li>
      <li><a href="#">Page 2</a></li>

      <!--localhost:8080/mvc03/board.do 요청-->
      <li><a href="board.do">게시판</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>