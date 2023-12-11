<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
     
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC01</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 
<div class="container">
  <h2>SpringMVC01</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD CONTENT</div>
    <div class="panel-body">
	<table class="table">
          <tr>
            <td>제목</td>
            <td>${vo.title}</td>
          </tr>
          <tr>
            <td>내용</td>
<%--        <td style="white-space: pre-line;">${vo.content}</td> --%>
			<!-- 줄바꿈 -->	
			<td>${fn:replace(vo.content, newLineChar, "<br>")}</td>
		  </tr>
          <tr>
            <td>작성자</td>
            <td>${vo.writer}</td>
          </tr>
          <tr>
            <td>조회수</td>
            <td>${vo.count}</td>
          </tr>
          <tr>
          	<td>작성일</td>
          	<!--날짜형식 변경 2023-12-09 22:25:22 공백을 기준으로 앞(0) 뒤(1) -->
          	<td>${fn:split(vo.indate, " ")[0]}</td>
          </tr>
          <tr>
           <td colspan="2" align="center">
           <!--QueryString 방식으로 넘기기-->
<%--       <a href="boardUpdateForm.do?idx=${vo.idx}" class="btn btn-primary btn-sm">수정화면으로</a> --%>

           <!--PathVariable 방식으로 넘기기-->
           	<a href="boardUpdateForm.do/${vo.idx}" class="btn btn-primary btn-sm">수정화면으로</a>
           	<a href="boardDelete.do/${vo.idx}" class="btn btn-warning btn-sm">삭제하기</a>
           	<a href="boardList.do" class="btn btn-info btn-sm">목록으로</a>
           </td>
          </tr>
       </table>    

	</div>
    <div class="panel-footer">ainokks071@gmail.com</div>
  </div>
</div>

</body>
</html>