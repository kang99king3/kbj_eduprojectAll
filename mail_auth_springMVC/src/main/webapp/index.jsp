<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<h1>회원가입</h1>
<form action="regist.do" method="post">
	<table border="1">
		<tr>
			<th>이메일</th>
			<td><input type="email" name="email"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="이메일인증"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>





