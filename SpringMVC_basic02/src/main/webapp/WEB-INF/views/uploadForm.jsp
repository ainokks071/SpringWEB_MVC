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

<script type="text/javascript">

var cnt = 1;
function fileAdd() {
	$("#d_file").append("<br> <input type='file' name='file"+cnt+"' />");
	cnt++;
}

</script>

</head>
<body>

<div class="container">
  <h2>다중 파일 업로드 </h2>
  <div class="panel panel-default">
    <div class="panel-heading">스프링을 이용한 다중 파일 업로드 구현 </div>
    <div class="panel-body">
    	<!-- form -->
		<form class="form-horizontal" action="<c:url value='/uploadFiles.do' />" method="post" enctype="multipart/form-data">
		
			<div class="form-group">
		   		<label class="control-label col-sm-2" for="id">아이디 : </label>
		  	    <div class="col-sm-10">
		     	  <input type="text" class="form-control" id="id" name="id" placeholder="Enter id" style="width:30%" >
		        </div>
		    </div>
		    
		    <div class="form-group">
		       <label class="control-label col-sm-2" for="name">이름 :</label>
		       <div class="col-sm-10">
		         <input type="text" class="form-control" id="name" name="name" placeholder="Enter name" style="width:30%">
		       </div>
		    </div>
		    
		    <div class="form-group">
		       <label class="control-label col-sm-2" for="">파일추가 :</label>
		       <div class="col-sm-10">
		         <input type="button" value="파일추가" onclick="fileAdd()">
		         <div id="d_file"> </div>
		       </div>
		    </div>
		    
		    <div class="form-group">
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary">업로드</button>
		      </div>
		    </div>
		    
		 </form>
    
    </div>
    <div class="panel-footer">Panel Footer</div>
  </div>
</div>

</body>
</html>