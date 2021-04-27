<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function chatWin(){
		open("home.do?id=${sessionScope.userId}","채팅장"," resizable=no,width=450px,height=600px");
	}
</script>
</head>
<body>
<h1>채팅프로젝트</h1>
<h2>채팅시작하기</h2>
<span><b>${sessionScope.userId}</b>님 채팅을 시작합니다.</span>
<button id="chatStart_btn" onclick="chatWin()">클릭</button>
</body>
</html>

