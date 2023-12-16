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
  	/* $(document).ready() : JQuery 시작 문법 : main.jsp 실행되면 아래 함수가 실행된다. */
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
  	
	/* 콜백함수 */  	/* JSON data=[{   },{    },{    },,, ] */
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
  	    /* JSON data=[{   }, {   }, {   }, ...] */ 
 		/* obj={"idx":5,"title":"게시판"~~ } */
  	    $.each(data, function(index, board){
  	    	listHtml+="<tr>";
  	    	listHtml+="<td>"+board.idx+"</td>";
  	    	
  	    	/* 제목을 클릭하면 글 번호가 boardContent()로 넘어감 */
  	    	/* 비동기통신 X, 단순히 숨겨져있던 form을 보여주거나 가리기 */
  	    	/* listHtml+="<td id='boardTitle"+board.idx+"'><a href='javascript:boardContent(" + board.idx + ")'>" + board.title + "</a></td>"; */
  	    	listHtml+="<td id='boardTitle"+board.idx+"'><button onclick='boardCount(" + board.idx + ")'>" + board.title + "</button></td>";
  	        listHtml+="<td>"+board.writer+"</td>";
  	        listHtml+="<td>"+board.indate+"</td>";
  	        listHtml+="<td id='boardCount"+board.idx+"'>"+board.count+"</td>";
  	        listHtml+="</tr>";
  	        
  	        /* 이미 숨겨져있는 글 내용 content!! -> tr태그의 고유 id로 접근해서 diplay -> block*/
            listHtml+="<tr id='content" + board.idx + "' style='display: none'>";
    	    listHtml+="<td>내용</td>";
    	    listHtml+="<td colspan='4'>";
    	    listHtml+="<textarea id='textArea"+board.idx+"' readonly rows='7' class='form-control'>" + board.content + "</textarea>";
    	    listHtml+="<br/>";
 	 	    listHtml+="<span id='updateBtn"+board.idx+"'><button class='btn btn-primary btn-sm' onclick='boardUpdateForm("+board.idx+")'>수정화면</button></span>&nbsp;"; /* nbsp; : 공백 */
	 	    listHtml+="<button class='btn btn-warning btn-sm' onclick='boardDelete("+board.idx+")'>삭제</button>&nbsp;";
	 	    listHtml+="<button class='btn btn-info btn-sm' onclick='boardList()'>닫기</button>";
    	    listHtml+="</td>";
    	    listHtml+="</tr>";
/*     	    listHtml+="<tr style='display:block'>";
    	    listHtml+="<td><button onclick='boardDelete(" + board.idx + ")'>삭제</button></td>";
    	    listHtml+="</tr>"; */
  	    	} );    	 
  	    
	  	   listHtml+="<tr>";
	 	   listHtml+="<td colspan='5'>";
	 	   /* 비동기통신 X, 단순히 숨겨져있던 form을 보여주거나 가리기 */
	 	   listHtml+="<button class='btn btn-primary btn-sm' onclick='openForm()'>글쓰기</button>";
	 	   listHtml+="<button class='btn btn-warning btn-sm' onclick='closeForm()'>닫기</button>";
	 	   listHtml+="</td>";
	 	   listHtml+="</tr>";
	 	   listHtml+="</table>";
	
	 	   $("#viewList").html(listHtml);
     	   //boardList() 호출 시, 항상 글쓰기 form은 감추고 리스트 보여준다.
	 	   closeForm();
	}
	
	/* 닫혀있는 글의 제목 클릭하면 -> DB에 조회수 1증가 -> DB에서 글 조회 -> text()로 조회수 갱신 -> 글 open */
	function boardCount(idx) {
	  if($("#content" + idx).css("display")=="none") {
  		$.ajax({
  			url : "boardCount.do",
  			type : "get",
  			data : {"idx":idx},
  			success : function() {
  				$.ajax({
  		  			url : "boardContent.do",
  		  			type : "get",
  		  			data : {"idx":idx},
  		  	  		dataType : "json",
					success : function(vo) {
							$("#boardCount"+vo.idx).text(vo.count);
				 	    	$("#content" + vo.idx).css("display","table-row");
					},	
  		  			error : function() { alert("error"); }
  				});
			},
  			error : function() { alert("error"); }
  		});
  	/* 글이 열려있다면, 글 닫아줌 */	
	  } else {
	    $("#content" + idx).css("display","none");
		$("#textA" + vo.idx).attr("readonly", true);
	  }
	}
	
/* 	function boardContent(data) {
  		$.ajax({ 
  			url : "boardContent.do",
  			type : "get",
  			data : {"idx":idx},
  			success : function(vo) {
 */
 				/* 넘어온 idx에 해당하는 가려져있던 글의 상세내용을 보여줌, 비동기통신 X */
				/* tr태그의 id로 접근해서 보여주기  */
		 	    /* $("#c" + idx).css("display","block"); : colspan 안먹힘 : tr접근 시..*/
/* 		 	      if($("#content" + idx).css("display")=="none") {
			 	    $("#content" + idx).css("display","table-row");
		 	    } else {
			 	    $("#content" + idx).css("display","none");
			 	    $("#textA" + idx).attr("readonly", true);
		 	    }
  				},
  			error : function() { alert("error"); }
  		});
	}
 */	
	
  	function openForm(){
 	    $("#writeForm").css("display","block");
	}
  	
  	function closeForm(){
 	    $("#writeForm").css("display","none");
	}
  	  	
	function boardInsert() {
		/* 사용자가 입력한 form의 값을 하나씩 가져오기  val() <-> text() <-> html() 
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val(); */
		
		/* serialize() : form의 입력 데이터 모두 가져오기 : 직렬화(모든 데이터를 한 줄로 직렬화) */
		/* title=111 & content=111 & writer=111 형태 */
		var fData = $("#frm").serialize();
/* 		alert(fData); */		
  		$.ajax({ 
  			url : "boardInsert.do",
  			type : "post",
  			data : fData,
  			success : boardList,
  			error : function() {
  				alert("error");
  			}
  		});
  		/* boardList(); ( X ) */
  		
  		/* form태그에 입력된 값 초기화 방법 2가지 */
  		/* 방법 1
		$("#title").val("");
		$("#content").val("");
		$("#writer").val(""); */
		/* 방법 2 : reset button 클릭 이벤트 */
		$("#formClear").trigger("click");
  	}
	
		function boardDelete(idx) {
			/* alert(idx); */
	  		$.ajax({
	  			url : "boardDelete.do",
	  			type : "get",
	  			data : {"idx": idx},
	  			success : boardList,
	  			error : function() { alert("error"); }
	  		});
	  	}
		
		/* 수정화면버튼 클릭 : 1)textarea readonly false로 변경 2) 제목 input으로 변경 3)수정하기버튼 변경   */
		function boardUpdateForm(idx) {
			/* 1)textarea readonly false로 변경 */
			/* attr = attribute 속성 */
	 	    $("#textArea" + idx).attr("readonly", false);
	 	    
	 	   /* 2) 제목 input으로 변경 */
	 	   var titleValue = $("#boardTitle" + idx).text();
	 	   var newInput = "<input type='text' id='newTitle"+idx+"' class='form-control' value='"+titleValue+"' />";
	 	   $("#boardTitle" + idx).html(newInput);
			
	 	    /* 3)수정하기버튼 변경 */
	 	   var updateBtn = "<button class='btn btn-primary btn-sm' onclick='boardUpdate("+idx+")'> 수정하기 </button>";
	 	   $("#updateBtn"+idx).html(updateBtn);
	 	   }
		
		function boardUpdate(idx) {
			/* var fData = $("#formUpdate"+idx).serialize(); */
			var newTitle = $("#newTitle"+idx).val();
			var newContent = $("#textArea"+idx).val();
			
			$.ajax({ 
			url : "boardUpdate.do",
			type : "post",
			data : {"idx" : idx, "title" : newTitle, "content" : newContent},
			success : boardList,
			error : function() { alert("error"); }
		    });
		}
  
  </script>
</head>
<body>
 
<div class="container">
  <h2>SpringMVC02</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    
    <!-- 위에 javascript 함수로 이 div에 내용물 넣어줌. -->
    <div class="panel-body" id="viewList" style="display: block"> viewList </div>
    
    <!-- 존재는 하지만, 글쓰기 form은 처음에는 보이지 않음  -->
    <div class="panel-body" id="writeForm" style="display: none">
	<!-- <form action="boardInsert.do" method="post"> : 화면 전환되어버림 !-->
	 <form id="frm">
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
               <button type="button" class="btn btn-success btn-sm" onclick="boardInsert()">등록</button>
               <button type="reset" class="btn btn-warning btn-sm" id="formClear">취소</button>
<!--                <a href="/mvc02"> 리스트 </a>-->				
				<button type="button" class="btn btn-info btn-sm" onclick="closeForm()">리스트로</button>
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