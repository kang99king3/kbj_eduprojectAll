<jsp:include page="header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function updateForm(seq){
		location.href="AnsController.do?command=updateForm&seq="+seq;
	}
	function delBoard(seq){
		location.href="AnsController.do?command=muldel&seq="+seq;
	}
	function replyForm(){
		$("#replyForm").show();
		var replyPosition=$("#replyForm").offset().top;//div의 상단위치를 구함
		$("#container").animate({
			"scrollTop":replyPosition
		},1000);
		//animate({css속성값정의},지연시간,easing)
	}
</script>
<style type="text/css">
	#replyForm{display: none;}
	#container{overflow: auto; height: 500px;}
</style>

</head>
<body>
<div id="container">
<h1>게시글 상세보기</h1>
<table class="table table-striped">
	<tr>
		<th>작성자</th>
		<td>${dto.id}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${dto.title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea class="form-control" rows="10" cols="60" readonly="readonly">${dto.content}</textarea> </td>
	</tr>
	<tr>
		<td colspan="2">
			<button class="btn btn-success" onclick="updateForm(${dto.seq})">수정</button>
			<button class="btn btn-success" onclick="delBoard(${dto.seq})">삭제</button>
			<button class="btn btn-success" onclick="replyForm()">답글</button>
		</td>
	</tr>
</table>
<div id="replyForm">
	<h1>답글추가하기</h1>
	<form action="AnsController.do" method="post">
		<input type="hidden" name="command" value="replyBoard"/>
		<input type="hidden" name="seq" value="${dto.seq}"/>
		<table class="table table-striped">
			<tr>
				<th>아이디</th>
				<td><input class="form-control" type="text" name="id" required="required"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input class="form-control" type="text" name="title" required="required"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea class="form-control" rows="10" cols="60" name="content" required="required"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="답글등록"/>
				</td>
			</tr>
		</table>
	</form>
	</div>
</div>

</body>
</html>
<jsp:include page="footer.jsp"/>





