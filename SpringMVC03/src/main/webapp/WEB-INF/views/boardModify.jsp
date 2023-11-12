<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- jstl c태그 사용 선언 : 반복문 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- jstl fmt태그 사용 선언 : 날짜 형식 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
  
  $(document).ready(()=>{
	$('#list').click(()=>{
		location.href="<c:url value='/boardList.do' />";
		});
	$('#remove').click(()=>{
		location.href="<c:url value='/boardRemove.do?idx=" + ${boardVO.idx} + "' />";
		});
  });
  
  </script>
</head>
<body>
 
<div class="container">
  <h2>게시물 수정 페이지</h2>
  <div class="panel panel-default">
    <div class="panel-heading">
    	Spring WEB MVC 게시판 만들기
    </div>
    <div class="panel-body">
    
      <form action="<c:url value='/boardModify.do' />" method="post" >
      
	    <div class="form-group">
			<label for="title">번 호</label>
			<input type="text" class="form-control" id="idx" name="idx" value="${boardVO.idx}" readonly="readonly">
		</div>
		
	    <div class="form-group">
			<label for="title">제 목</label>
			<input type="text" class="form-control" name="title" value="${boardVO.title}">
		</div>
				    
		<div class="form-group">
			<label for="contents">내 용</label>
			<textarea class="form-control" rows="3" name="contents">${boardVO.contents}</textarea>
		</div>
				    
		<div class="form-group">
			<label for="writer">작성자</label>
			<input type="text" class="form-control" name="writer" value="${boardVO.writer}" readonly="readonly">
		</div>
		
		<div class="form-group">
			<label for="count">조회수</label>
			<input type="text" class="form-control" value="${boardVO.count}" readonly="readonly">
		</div>
		
    	<button type="submit" class="btn btn-primary">수정하기</button>
    	<!-- type="button" 없으면 삭제 안됨.. -->
    	<button type="button" id="remove" class="btn btn-default btn-danger">삭제하기</button>
    	<button type="button" id="list" class="btn btn-info">리스트 보기</button>
    	
      </form>	
    	
    </div>
    <div class="panel-footer">Email : ainokks071@gmail.com</div>
  </div>
</div>

</body>
</html>