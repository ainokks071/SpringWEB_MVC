	
	/* ajax POST CSRF 토큰 */
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
  
  	/* $(document).ready() : JQuery 시작 문법 :board.jsp 실행되면 아래 함수가 실행된다. */
  	$(document).ready(function() {
  		boardList();
  	});
  	
  	/* 서버와 비동기 통신 함수 */
  	function boardList() {
  		/* JQuery에서 제공해주는 ajax 함수 */
  		$.ajax({
  			/* RestController로 요청 -> 넘어온 JSON데이터를 콜백함수로 전달 */
  			url : "board/all",
			/* 요청방식 : get */
  			type : "get",
			/* 응답 받을 데이터 타입 : json */
  			dataType : "json",
			/* 콜백함수 */
  			success : makeView,
  			error : function() {
  				alert("error");
  			}
  		});
  	}
  	
	/* 콜백함수 */  	/* list가 변환된 JSON data=[{   },{    },{    },,, ] */
  	function makeView(list) {
/*   		alert(data); */
 
  	    var listHtml="<table class='table table-bordered'>";
	  	    listHtml+="<tr>";
	  	    listHtml+="<td>번호</td>";
	  	    listHtml+="<td>제목</td>";
	  	    listHtml+="<td>작성자</td>";
	  	    listHtml+="<td>작성일</td>";
	  	    listHtml+="<td>조회수</td>";
	  	    listHtml+="</tr>";
  	    
  	    /*JQuery 반복문 : boardList에서 board 하나씩 추출 */
  	    /* index   0      1      2  */
  	    /* JSON data=[{   }, {   }, {   }, ...] */ 
 		/* obj={"idx":5,"title":"게시판"~~ } */
  	    $.each(list, function(index, board){
  	    	// 반복문 내부에 form태그 안되는 이유 : input의 name속성 값이 board.idx에 따라 변한다.
  	    	// ex) <input type='text' name='title1234'> 이런식으로.. 
  	    	// BoardVO의 idx, title, content와 일치 X -> 불가능 !!! -> 파라메터 하나씩 받아주자.
  	    	//listHtml+="<form id='newFrm"+board.idx+"'>";
  	    	
  	    	listHtml+="<tr>";
 	  	    listHtml+="<td>"+board.idx+"</td>";
  	    	/* 제목을 클릭하면 글 번호가 boardContent()로 넘어감 */
  	    	listHtml+="<td id='boardTitle"+board.idx+"'><a href='javascript:boardContent(" + board.idx + ")'>" + board.title + "</a></td>";
  	    	/* listHtml+="<td id='boardTitle"+board.idx+"'><button onclick='boardContent(" + board.idx + ")'>" + board.title + "</button></td>"; */
  	        listHtml+="<td><a href='javascript:memberDetail(" + board.memID + ")'>" + board.writer + "</a></td>";
  	        
  	        /* indate = 2023-12-18 12:34:33 문자열 !! -> 자바스크립트 문자열 자르기 함수 구글링 */
  	        /* 문자열 자르기 방법1 substr(시작위치, 길이); */
  	        /* listHtml+="<td>"+board.indate.substr(0, 10)+"</td>"; */
  	        /* 문자열 자르기 방법2 split(' ')[0] : 2023-12-18 19:12:33 -> 공백을 기준으로 앞[0], 뒤[1] */
  	        listHtml+="<td>"+board.indate.split(" ")[0]+"</td>";
  	        listHtml+="<td id='boardCount"+board.idx+"'>"+board.count+"</td>";
  	        listHtml+="</tr>";
  	        
  	        /* 상세페이지 */
  	        /* 이미 숨겨져있는 글 내용 content!! -> tr태그의 고유 id로 접근해서 diplay -> block*/
            listHtml+="<tr id='content" + board.idx + "' style='display: none'>";
/* 	            if("${user.member.memID}" == board.memID) {
	    		    listHtml+="<td><img src='${contextPath}/resources/upload/${user.member.memProfile}'></td>";
	       	     } else {
	        		listHtml+="<td><img src='${contextPath}/resources/images/basicProfile.jpeg'></td>";
	            } */
    	    listHtml+="<td>내용</td>";
    	    listHtml+="<td colspan='4'>";
    	    /* listHtml+="<textarea id='textArea"+board.idx+"' readonly rows='7' class='form-control'>" + board.content + "</textarea>"; */
    	    listHtml+="<textarea id='textArea"+board.idx+"' readonly rows='7' class='form-control'></textarea>";
	    	listHtml+="<br/>";
	    	
	    	/* 본인이 작성한 글이면 수정, 삭제버튼 클릭 가능 */
	  	    if("${user.member.memID}" == board.memID) {
		 	 	listHtml+="<span id='updateBtn"+board.idx+"'><button class='btn btn-primary btn-sm' onclick='boardUpdateForm("+board.idx+")'>수정화면</button></span>&nbsp;"; /* nbsp; : 공백 */
			 	listHtml+="<button class='btn btn-warning btn-sm' onclick='boardDelete("+board.idx+")'>삭제</button>&nbsp;";
		 	    listHtml+="<button class='btn btn-info btn-sm' onclick='boardList()'>리스트로</button>";
	  	    } else {
		 	 	listHtml+="<span id='updateBtn"+board.idx+"'><button disabled class='btn btn-primary btn-sm' onclick='boardUpdateForm("+board.idx+")'>수정화면</button></span>&nbsp;"; /* nbsp; : 공백 */
			 	listHtml+="<button disabled class='btn btn-warning btn-sm' onclick='boardDelete("+board.idx+")'>삭제</button>&nbsp;";
		 	    listHtml+="<button class='btn btn-info btn-sm' onclick='boardList()'>리스트로</button>";
	  	    }
    	    listHtml+="</td>";
    	    listHtml+="</tr>";
/*     	    listHtml+="<tr style='display:block'>";
    	    listHtml+="<td><button onclick='boardDelete(" + board.idx + ")'>삭제</button></td>";
    	    listHtml+="</tr>"; */
  	    	});
  	       
  	       /* 로그인 한 회원만 글쓰기 보여주기. */		
	  	    if("${user.member}" != null) {
		  	   listHtml+="<tr>";
		 	   listHtml+="<td colspan='5'>";
		 	   /* 글쓰기 버튼 비동기통신 X, 단순히 숨겨져있던 form을 보여주거나 가리기 */
		 	   /* 글쓰기 버튼은 로그인을 했을 때만 보여준다. */
		 	   listHtml+="<button class='btn btn-primary btn-sm' onclick='openForm()'>글쓰기</button>";
		 	   listHtml+="<button class='btn btn-warning btn-sm' onclick='closeForm()'>닫기</button>";
		 	   listHtml+="</td>";
		 	   listHtml+="</tr>";
  	       }
  	       
	 	   listHtml+="</table>";
	
	 	   $("#viewList").html(listHtml);
     	   //boardList() 호출 시, 항상 글쓰기 form은 감추고 리스트 보여준다.
	 	   closeForm();
	}
	
	
	
	/* 수정화면버튼 클릭 : 1)textarea readonly false로 변경 2) 제목 input으로 변경 3)수정하기버튼 변경   */
	function boardUpdateForm(idx) {
		/* 1)textarea readonly false로 변경 */
		/* attr = attribute 속성 */
 	    $("#textArea" + idx).attr("readonly", false);
 	    
 	   /* 2) 제목 input으로 변경 */
 	   var titleValue = $("#boardTitle" + idx).text(); //text() : board.title 값 가져오기 
 	   var newInput = "<input type='text' id='newTitle"+idx+"' class='form-control' value='"+titleValue+"' />";
 	   $("#boardTitle" + idx).html(newInput);
		
 	    /* 3)수정하기버튼 변경 */
	 var updateBtn = "<button class='btn btn-primary btn-sm' onclick='boardUpdate("+idx+")'> 수정하기 </button>";
 	   $("#updateBtn"+idx).html(updateBtn);
 	   }
		
	/* 방법 1 중첩으로 ajax 작성  */
	/* 닫혀있는 글의 제목 클릭하면 -> DB에 조회수 1증가 -> DB에서 글 조회 -> text()로 조회수 갱신 -> 글 open */
	function boardContent(idx) {
		
		
		
		
		
		
	  if($("#content" + idx).css("display")=="none") {
  		$.ajax({
  			url : "board/" + idx,
  			type : "put",
  			beforeSend: function(xhr){ 
  				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
  				},
  			success : function() {
  				$.ajax({
  		  			url : "board/" + idx,
  		  			type : "get",
  		  	  		dataType : "json",
					success : function(vo) {
							/* textArea에 값 넣어주기 ! */
					        $("#textArea"+vo.idx).val(vo.content);
					        /* 조회수에 값 넣어주기 ! */
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
	
	
/* 	방법 2: Controller에서 한번에 처리 : boardContent.do로 요청 -> 조회수 증가 + 갱신된 글 조회 한번에 처리
	function boardContent2(idx) {
	  if($("#content" + idx).css("display")=="none") {
  		$.ajax({
  			url : "boardContent.do", ===> 조회수 증가 + 갱신된 글 조회 한번에 처리
  			type : "get",
  			data : {"idx":idx},
  			dataType : "json",
  			success : function(vo) {
					        $("#textArea"+vo.idx).val(vo.content);
							$("#boardCount"+vo.idx).text(vo.count);
				 	    	$("#content" + vo.idx).css("display","table-row");
					},	
  		  	error : function() { alert("error"); }
  			});
	  } else {
	    $("#content" + idx).css("display","none");
		$("#textA" + vo.idx).attr("readonly", true);
	  }
	} */
	
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
		/* + 회원ID까지 -> title=111 & content=111 & writer=111 & memID=kimks071 */
		var fData = $("#frm").serialize();
/* 		alert(fData); */		
  		$.ajax({
  			url : "board/new",
  			type : "post",
  			data : fData,
  			beforeSend: function(xhr){ 
  				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
  				},
  			success : boardList,
  			error : function() {
  				alert("error");
  			}
  		});
  		/* 콜백함수로 boardList() 호출해주지 않으면 ? -> DB에 저장된 게시글 갱신되지 않겠지.  */
  		
  		/* form태그에 입력된 값 초기화 방법 2가지 */
  		/* 방법 1
		$("#title").val("");
		$("#content").val("");
		$("#writer").val(""); */
		/* 방법 2 : reset button 클릭 이벤트 발생시키기 */
		$("#formClear").trigger("click");
  	}
	
		/* 여러 개의 데이터를 넘길때 주의  */
		
		/* 1. JSON 형태로 변경해서 보낸다. contentType, JSON.stringify() 이용
		   2. RestController에서는, 받을 때 @RequestBody 이용 !
		*/
		function boardUpdate(idx) {
			var newTitle = $("#newTitle"+idx).val();
			var newContent = $("#textArea"+idx).val();
			
			$.ajax({ 
				url : "board/update",
				type : "put",
				beforeSend: function(xhr){ 
	  				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
	  				},
				contentType : 'application/json;charset=utf-8',
				data : JSON.stringify({"idx" : idx, "title" : newTitle, "content" : newContent}),
				success : boardList,
				error : function() { alert("error"); }
		    });
		}
		
		function boardDelete(idx) {
			/* alert(idx); */
	  		$.ajax({
	  			url : "board/" + idx,
	  			type : "delete",
	  			beforeSend: function(xhr){ 
	  				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
	  				},
	  			success : boardList,
	  			error : function() { alert("error"); }
	  		});
	  	}

		function memberDetail(memID) {
			location.href='memberDetail.do?'+memID;
		}
		
		
		/* 파라메터 한번에 수집하기 테스트 : 불가 
		function boardUpdate(idx) {
			var fData = $("#newFrm"+idx).serialize();
			$.ajax({ 
				url : "boardUpdate.do",
				type : "post",
				data : fData,
				success : boardList,
				error : function() { alert("error"); }
			});
		} */	 	 
			
