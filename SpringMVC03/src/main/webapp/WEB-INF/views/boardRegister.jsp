<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- jstl c태그 사용 선언 : 반복문 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  </script>
</head>
<body>
<div class="container">
  <h2>메인 화면</h2>
  <div class="panel panel-default">
    <div class="panel-heading"> 게시물 등록하기 </div>
    <div class="panel-body">
		    <form action="<c:url value='/boardRegister.do' />" method="post" >
		    
			    <div class="form-group">
			      <label for="title">제 목</label>
			      <input type="text" class="form-control" name="title">
			    </div>
			    
			    <div class="form-group">
			      <label for="contents">내 용</label>
			      <textarea class="form-control" rows="3" name="contents"></textarea>
			    </div>
			    
			    <div class="form-group">
			      <label for="writer">작성자</label>
			      <input type="text" class="form-control" name="writer">
			    </div>
			    
			    <button type="submit" class="btn btn-default">등록</button>
			    <button type="reset" class="btn btn-default">취소</button>
			    
		  </form>
    </div>
    <div class="panel-footer">Email : ainokks071@gmail.com</div>
  </div>
</div></body>
</html>