<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>새글추가하기</h1>
<form action="AnsController.do" method="post">
<input type="hidden" name="command" value="ansAddBoard"/>
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
				<input  type="submit" value="글등록"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>
<jsp:include page="footer.jsp" />








