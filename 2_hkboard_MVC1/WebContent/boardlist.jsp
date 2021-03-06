<%@page import="com.hk.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<%
	//request 스코프에 객체를 저장하면 모두 Object 타입이 된다. --> casting --> List로 변환
	List<HkDto> list=(List<HkDto>)request.getAttribute("list");
	
%>
<h1>글목록 조회하기</h1>
<form action="hkcontroller.jsp" method="post">
<input type="hidden" name="command" value="muldel"/>
	<table border="1">
		<col width="50px">
		<col width="50px"><col width="100px"><col width="300px"><col width="200px">
		<tr>
			<th><input type="checkbox" onclick="allSel(this.checked)"/></th>
			<th>글번호</th><th>작성자</th><th>글제목</th><th>작성일</th>
		</tr>
		<%  
			if(list==null||list.size()==0){
				%>
				<tr>
					<td colspan="5" style="text-align: center;">---적성된 글이 없습니다.---</td>
				</tr>
				<%
			}else{
			
				//향상된 for문
				for(HkDto dto:list){
					%>
					<tr>
						<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>"/></td>
						<td><%=dto.getSeq()%></td>
						<td><%=dto.getId()%></td>
						<td><a href="hkcontroller.jsp?command=boarddetail&seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
						<td><%=dto.getRegdate()%></td>
					</tr>
					<%
				}
			}
		%>
		<tr>
			<td colspan="5">
				<a href="hkcontroller.jsp?command=addboard">글쓰기</a>
				<input type="submit" value="삭제"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>



