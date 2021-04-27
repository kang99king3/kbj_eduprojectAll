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
<script type="text/javascript" src="http://www.mediamixad.com/ad/webad?code=1368409904"></script>
<form action="HelloServletOld.do" method="post">
	<input type="text" name="param" value="한글"/>
	<input type="submit" value="전송"/>
</form>
<a href="HelloServletOld.do?param=한글">helloServletOld요청하기</a>
<hr/>
<a href="HelloServlet.do">helloServlet요청하기</a>
<a href="HelloTest.do">helloServlet요청하기</a>
</body>
</html>





