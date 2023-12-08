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
  /*
  $(document).ready(function() {      });
  이것은 일종의 이벤트 핸들러로, 문서가 완전히 로드되기 전에 JavaScript 코드가 실행되는 것을 방지하여 안정성을 높이고, 
  JavaScript의 jQuery 라이브러리를 사용하여 문서 객체 모델(DOM)이 준비되었을 때 실행될 함수를 정의하는 부분.
  이 부분은 일반적으로 HTML 문서의 로딩이 끝나면 실행되는 코드를 정의하는데 사용. 
  여기서는 jQuery를 이용하여 문서가 준비되면 실행할 함수를 정의하는 것.
  $(document).ready() 함수는 jQuery에서 사용되며, 문서 객체 모델(DOM)이 완전히 로드되고 준비될 때까지 기다린 후에 함수를 실행.
  이는 일반적으로 웹 페이지가 모든 HTML 요소, 스타일, 이미지 및 기타 자원을 로드하고 브라우저가 준비되었음을 확인하는데 사용됩니다.
  페이지의 요소에 대한 조작이나 상호작용을 보장. 이를 통해 사용자 경험이 향상되고, 코드 실행이 확실하게 보장됩니다.
  */
  
/* 	$(document).ready(function() {
  		$('#regBtn').click(function() {
  			location.href="<c:url value='/boardRegister.do' />";
  		});
  	}); 
 	아래는 람다식으로 바꿨을때 !
*/
	$(document).ready(function() {
  		$('#regBtn').click(()=>{
							/* GET방식으로 요청 : 등록페이지 띄워라. */
  			location.href="<c:url value='/boardRegister.do' />";
  		});
  	});


  
  </script>
</head>
<body>
 
<div class="container">
  <h2>게시물 목록</h2>
  <div class="panel panel-default">
    <div class="panel-heading">
    	Spring WEB MVC 게시판 만들기
    	<input type="button" id="regBtn" value="게시물 등록하기" class="btn btn-xs btn-primary pull-right" /> 
    </div>
    <div class="panel-body">
    
    	<div class="table-responsive">          
			  <table class="table table-hover">
			    <thead>
			      <tr>
			        <th>게시물 번호</th>
			        <th>제 목</th>
			        <th>조회수</th>
			        <th>등록자</th>
			        <th>등록일</th>
			      </tr>
			    </thead>
			    <tbody>
			     <c:forEach var="vo" items="${boardList}">
				      <tr>
				        <td>${vo.idx}</td>
				        							<!-- get방식 @RequestParam  -->
				        <td> <a href="<c:url value='/boardContents.do?idx=${vo.idx}' />" >${vo.title}</a> </td>
				        <td>${vo.count}</td>
				        <td>${vo.writer}</td>
				        <!-- 	private Date indate; " Date타입 <=> fmt:formatDate " -->
				        <td><fmt:formatDate value="${vo.indate}" pattern="yyyy-MM-dd" /></td>
				      </tr>
			     </c:forEach> 
			    </tbody>
			  </table>
  		</div>
    
    </div>
    <div class="panel-footer">Email : ainokks071@gmail.com</div>
  </div>
</div>

</body>
</html>