<%@page import="java.util.Map"%>
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
	
	function rowCount1(count){
		
		location.href="HkController.do?command=boardlist&pnum=1&rcount="+count;
		
	}
	
// 	function test(num){
// 		var selRow=document.getElementById("rowCount").value;
// 		var url="HkController.do?command=boardlist&pnum="+num+"&rcount="+selRow;
// 		location.href=url;
// 	}
</script>
<style type="text/css">
	a{text-decoration: none;}
	
</style>
</head>
<body>
<%
	//request 스코프에 객체를 저장하면 모두 Object 타입이 된다. --> casting --> List로 변환
	List<HkDto> list=(List<HkDto>)request.getAttribute("list");
// 	int pcount=(Integer) request.getAttribute("pcount");
	Map<String,Integer> map=(Map<String,Integer>)request.getAttribute("pMap");
%>
<h1>글목록 조회하기</h1>
<form action="HkController.do" method="post">
<select id="rowCount" onchange="rowCount1(this.value)">
	<option value="10" ${sessionScope.rcount=="10"?"selected":""}>10</option>
	<option value="20" ${sessionScope.rcount=="20"?"selected":""}>20</option>
	<option value="30" ${sessionScope.rcount=="30"?"selected":""}>30</option>
</select>
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
						<td><a href="HkController.do?command=boarddetail&seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
						<td><%=dto.getRegdate()%></td>
					</tr>
					<%
				}
			}
		%>
		
		<tr>
			<td colspan="5" style="text-align: center;">
				<a href="HkController.do?command=boardlist&pnum=<%=map.get("prePageNum")%>">◀</a>
				<%
				for(int i=map.get("startPage") ;i<=map.get("endPage");i++){
						%>
						<a href="HkController.do?command=boardlist&pnum=<%=i%>"><%=i%></a>
						<%
					}
				%>
				<a href="HkController.do?command=boardlist&pnum=<%=map.get("nextPageNum")%>">▶</a>
			</td>
		</tr>

	<!-- 여기는 row개수를 설정해서 구현하는 요소 시작-->	
<!-- 		<tr> -->
<!-- 			<td colspan="5" style="text-align: center;" id="pageTd"> -->
<%-- 				<a onclick="test(<%=map.get("prePageNum")%>)" href="#">◀</a> --%>
				<%
// 				for(int i=map.get("startPage") ;i<=map.get("endPage");i++){
						%>
<%-- 						<a onclick="test(<%=i%>)" href="#"><%=i%></a> --%>
						<%
// 					}
				%>
<%-- 				<a onclick="test(<%=map.get("nextPageNum")%>)" href="#">▶</a> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
		<!-- 여기는 row개수를 설정해서 구현하는 요소 종료-->	
		
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



