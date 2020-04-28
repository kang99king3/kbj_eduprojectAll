<%@page import="com.hk.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.HkDao"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function allSel(bool){ //bool은 체크여부를 받는다(true/false)
// 		alert(bool);
		var chks=document.getElementsByName("chk");//chks[chk,chk,chk...]
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=bool;//각각 체크박스에 체크여부(true/false)를 적용
		}
	}
</script>
</head>
<body>
<%--    ${list}  ${dto.id}   ${requestScope.list } --%>
<h1>글목록 조회하기</h1>
<form action="HkController.do" method="post">
<input type="hidden" name="command" value="muldel"/>
	<table border="1">
		<col width="50px">
		<col width="50px"><col width="100px"><col width="300px"><col width="200px">
		<tr>
			<th><input type="checkbox" onclick="allSel(this.checked)"/></th>
			<th>글번호</th><th>작성자</th><th>글제목</th><th>작성일</th>
		</tr>
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="5" style="text-align: center;">---작성된 글이 없습니다.---</td>
					</tr>				
				</c:when>
				<c:otherwise>
					<c:forEach items="${list}" var="dto">
						<tr>
							<td><input type="checkbox" name="chk" value="${dto.seq}"/></td>
							<td>${dto.seq}</td>
							<td>${dto.id}</td>
							<td><a href="HkController.do?command=boarddetail&seq=${dto.seq}">
							     ${fn:length(dto.title)<5?dto.title:fn:substring(dto.title,0,5)}</a></td>
							<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy년MM월dd일"/></td>
						</tr>				
					</c:forEach>
				</c:otherwise>
			</c:choose>
		<tr>
			<td colspan="5">
				<a href="HkController.do?command=addboard">글쓰기</a>
				<input type="submit" value="삭제"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>



