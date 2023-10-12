<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--부트스트랩5  -->
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9' crossorigin='anonymous'>
<!--부트스트랩3, jquery  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>


<div class="container">
  <h2>업로드가 완료되었습니다.</h2>
  <div class="panel panel-default">
    <div class="panel-heading">Panel Heading</div>
    
    <div class="panel-body">
			<table class = 'table table-borderd table-hover'>
						<tr>
							<th>아이디</th>
							<td>${map.id}</td>
						</tr>
						<tr>
							<th>이름</th>
							<td>${map.name}</td>
						</tr>
						<c:forEach var="fileNameList"  items="${map.fileNameList}">
							<tr>
								<td>${fileNameList}</td>
								<td>다운로드</td>
							</tr>
						</c:forEach>
			</table>
	</div>
    
    <div class="panel-footer">Panel Footer</div>
  </div>
</div>




</body>
</html>