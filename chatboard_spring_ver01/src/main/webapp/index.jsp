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
<h1>로그인</h1>
<div>
	<form action="login.do" method="post">
		<label>아이디:</label><input type="text" name="id"/>
		<input type="submit" value="로그인"/>	
	</form>
</div>
</body>
</html>