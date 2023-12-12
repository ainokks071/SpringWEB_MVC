<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Spring mvc 02 : main.jsp 하나로 1)게시물전체목록 2)게시물등록 3)상세보기 4)수정하기화면 HOW??? --> 
     
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC02</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
  	/* JQuery 시작 문법 : main.jsp 실행되면 아래 함수가 실행된다. */
  	$(document).ready(function() {
  		boardList();
  	});
  	
  	/* 서버와 비동기 통신 함수 */
  	function boardList() {
  		/* JQuery에서 제공해주는 ajax 함수 */
  		$.ajax({
  			/* 서버의 controller로 요청 -> 넘어온 JSON데이터를 콜백함수로 전달 */
  			url : "boardList.do",
  			type : "get",
  			dataType : "json",
  			success : makeView,
  			error : function() {
  				alert("error");
  			}
  		});
  	}
  	
	/* 콜백함수 */  	/* data=[{   },{    },{    },,, ] */
  	function makeView(data) {
/*   		alert(data); */
  		var listHtml="<table class='table table-bordered'>";
  	    listHtml+="<tr>";
  	    listHtml+="<td>번호</td>";
  	    listHtml+="<td>제목</td>";
  	    listHtml+="<td>작성자</td>";
  	    listHtml+="<td>작성일</td>";
  	    listHtml+="<td>조회수</td>";
  	    listHtml+="</tr>";
  	    
  	    /*JQuery 반복문  */
  	    /* index   0      1      2  */
  	    /* data=[{   }, {   }, {   }, ...] */ 
 		/* obj={"idx":5,"title":"게시판"~~ } */
  	    $.each(data, function(index, obj){
  	    	listHtml+="<tr>";
  	    	listHtml+="<td>"+obj.idx+"</td>";
  	    	listHtml+="<td>"+obj.title+"</td>";
  	        listHtml+="<td>"+obj.writer+"</td>";
  	        listHtml+="<td>"+obj.indate+"</td>";
  	        listHtml+="<td>"+obj.count+"</td>";
  	        listHtml+="</tr>";  	        	 
  	    	} );    	 
  	    
  	   listHtml+="<tr>";
 	   listHtml+="<td colspan='5'>";
 	   listHtml+="<button class='btn btn-primary btn-sm' onclick='goForm()'>글쓰기</button>";
 	   listHtml+="</td>";
 	   listHtml+="</tr>";
 	   listHtml+="</table>";
  	    	 
  	   $("#viewList").html(listHtml);
	}
	
  	function goForm(){
	    $("#viewList").css("display","none");  // 감추고
	    $("#writeForm").css("display","block");// 보이고
	}
  	
  	function goList(){
	    $("#viewList").css("display","block");  // 보이고
	    $("#writeForm").css("display","none");// 감추고
	}
  	
  	function goInsert() {
  		$.ajax({
  			url : "boardInsert.do",
  			data : ""
  			type : "post",
  			success : makeView,
  			error : function() {
  				alert("error");
  			}
  		});
  	}
  
  </script>
</head>
<body>
 
<div class="container">
  <h2>SpringMVC02</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body" id="viewList" style="display: block"></div>
    <div class="panel-body" id="writeForm" style="display: none">
	 <form>
      <table class="table">
         <tr>
           <td>제목</td>
           <td><input type="text" id="title" name="title" class="form-control"/></td>
         </tr>
         <tr>
           <td>내용</td>
           <td><textarea rows="7" class="form-control" id="content" name="content"></textarea> </td>
         </tr>
         <tr>
           <td>작성자</td>
           <td><input type="text" id="writer" name="writer" class="form-control"/></td>
         </tr>
         <tr>
           <td colspan="2" align="center">
               <button type="button" class="btn btn-success btn-sm" onclick="goInsert()">등록</button>
               <button type="reset" class="btn btn-warning btn-sm" id="fclear">취소</button>
<!--                <a href="/mvc02"> 리스트 </a>-->				
				<button type="button" class="btn btn-info btn-sm" onclick="goList()">리스트</button>
 		   </td>
         </tr>
      </table>
     </form>	    
    </div>
    <div class="panel-footer">ainokks071@gmail.com</div>
  </div>
</div>

</body>
</html>