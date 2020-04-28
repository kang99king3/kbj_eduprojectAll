<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<jsp:include page="header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="container">
<h1>게시글 수정하기</h1>
<form action="ansboardUpdate.do" method="post">
<input type="hidden" name="seq" value="${dto.seq}"/>
<table class="table table-striped">
	<tr>
		<th>작성자</th>
		<td>${dto.id}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input class="form-control" type="text" name="title" value="${dto.title}"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea class="form-control" rows="10" cols="60" name="content">${dto.content}</textarea> </td>
	</tr>
	<tr>
		<td colspan="2">
			<input  type="submit" value="수정완료"/>
		</td>
	</tr>
</table>
</form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>