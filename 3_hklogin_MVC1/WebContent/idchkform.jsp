<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	onload=function(){
		//현재 페이지를 열어준 부모페이지.문서.name속성값이"id"인 요소.값 을 구함
		var id=opener.document.getElementsByName("id")[0].value;
		document.getElementById("id").textContent=id;
	}
	
	function confirmId(resultId){
		//조회된 아이디가 null인지 아닌지에 따라 true/false로 변환
		var isS=resultId=='null'?true:false;
		
		var parentPageInputId=opener.document.getElementsByName("id")[0];

		if(isS){
			opener.document.getElementsByName("name")[0].focus();
			parentPageInputId.setAttribute("class", "y");//y를 설정해서 아이디 사용가능을 표시
		}else{
			parentPageInputId.setAttribute("class", "n");//n을 설정해서 아이디 중복체크 다시하도록 표시
			parentPageInputId.value="";
			parentPageInputId.focus();
		}
		
		window.self.close();// 나 자신의 창을 닫는다 close(): 창을 닫아주는 기능
	}
</script>
</head>
<%
	String resultId=(String)request.getAttribute("resultId");
%>
<body>
<table border="1">
	<tr>
		<td id="id"></td>
	</tr>
	<tr>
		<td><%=resultId==null?"사용가능합니다.":"중복된 아이디입니다." %></td>
	</tr>
	<tr>
		<td>
			<button onclick="confirmId('<%=resultId%>')">확인</button>
		</td>
	</tr>
</table>
</body>
</html>









