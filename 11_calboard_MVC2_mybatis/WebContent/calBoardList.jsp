<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function allSel(bool){
		var chks=document.getElementsByName("seq");//[checkbox,checkbox..]
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=bool;
		}
		
	//		$("input[name=seq]").prop("checked",bool);
	}
	
	$(function(){
		//form태그에서 submit이벤트가 발생하면 함수실행
		$("form").submit(function(){       
			var bool=true;
			var count=$(this).find("input[name=seq]:checked").length;
			if(count==0){
				alert("최소하나이상 체크해야 됩니다.!!");
				bool=false;
			}
			return bool;
		});
	})
</script>
</head>
<body>
<jsp:useBean id="util" class="com.hk.utils.Util" />
<h1>일정목록조회</h1>
<form action="CalController.do" method="post">
	<input type="hidden" name="command" value="muldel"/>
	<table border="1">
		<col width="50px">
		<col width="50px">
		<col width="200px">
		<col width="300px">
		<col width="200px">
		<tr>
			<th><input type="checkbox" name="all" onclick="allSel(this.checked)"/></th>
			<th>번호</th>
			<th>일정</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		<c:choose>
			<c:when test="${empty list}">
				<tr><td colspan="5">---작성된 일정이 없습니다.---</td></tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="dto" items="${list}">
					<tr>
						<td><input type="checkbox" name="seq" value="${dto.seq}"/></td>
						<td>${dto.seq}</td>
						<td>
							<jsp:setProperty property="toDates" name="util" value="${dto.mdate}"/>
							<jsp:getProperty property="toDates" name="util"/>
						</td>
						<td>
							<a href="CalController.do?command=calDetail&seq=${dto.seq}">${dto.title}</a>
						</td>
						<td><f:formatDate pattern="yyyy-MM-dd" value="${dto.regdate}"/> </td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="5">
				<input type="submit" value="삭제"/>
				<input type="button" value="달력보기"
				 onclick="location.href='CalController.do?command=calendar'"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>











