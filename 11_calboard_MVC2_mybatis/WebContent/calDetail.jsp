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
<jsp:useBean id="util" class="com.hk.utils.Util"/>
<h1>일정상세보기</h1>
<table border="1">
		<tr>
			<th>아이디</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>일정</th>
			<td><jsp:setProperty value="${dto.mdate}" property="toDates" name="util"/>
				<jsp:getProperty property="toDates" name="util"/>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea readonly="readonly" rows="10" cols="60">${dto.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="updateForm()"/>
				<input type="button" value="삭제" onclick="delBoard()"/>
				<input type="button" value="목록" onclick="calList()"/>
				<input type="button" value="달력" onclick="calendar()"/>
			</td>
		</tr>
</table>
<script type="text/javascript">
	function updateForm(){ //일정수정폼으로 이동하기
		location.href="CalController.do?command=calUpdateForm&seq=${dto.seq}";
	}
	function delBoard(){  //일정삭제하기
		if(confirm("정말 삭제할라고??")){
			location.href="CalController.do?command=muldel&seq=${dto.seq}";			
		}
	}
	
	function calList(){   //일정목록으로 이동하기
		location.href="CalController.do?${sessionScope.calListQuery}";
	}
	
	function calendar(){  //달력으로 이동하기
		location.href='CalController.do?command=calendar';
	}
</script>
</body>
</html>








