<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- <%request.setCharacterEncoding("utf-8"); %> --%>
<%-- <%response.setContentType("text/html; charset=utf-8"); %> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>새글 작성하기</h1>
<form action="HkController.do" method="post">
	<input type="hidden" name="command" value="addinsert"/>
	<table border="1">
		<tr>
			<th>작성자(ID)</th>
			<td><input type="text" name="id"/></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td><input type="text" name="title"/></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td><textarea rows="10" cols="60" name="content"></textarea> </td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="글등록"/>	
				<a href="HkController.do?command=boardlist">글목록</a>		
			</td>
		</tr>
	</table>
</form>
</body>
</html>









