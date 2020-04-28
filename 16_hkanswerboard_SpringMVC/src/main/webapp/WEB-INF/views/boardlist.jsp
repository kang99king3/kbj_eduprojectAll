<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function allSel(bool){
		var chks=document.getElementsByName("seq");//[checkbox,checkbox..]
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=bool;
		}
		
// 		$("input[name=seq]").prop("checked",bool);
	}
	
	$(function(){
		//내용 미리보기 ajax구현
		//hover()메서드를 사용해서 마우스 커서가 올라갈 때 서블릿 요청을 해서 해당 글의 내용을 가져오기
		$("td>a").hover(function(){
			var seq=$(this).parent().prev().prev().text();
			$.ajax({
				url:"contentAjax.do",  //요청URL
				data:{"seq":seq}, //서버쪽으로 보낼 데이터
				dataType:"json",          //서버에서 받게 될 데이터 타입 정의
				method:"post",      //전송방식 정의
				success:function(obj){//서버통신에 성공했다면 기능실행(obj는 전달된 데이터 받기)
					//주요코드작성
// 					alert(obj.dto.content);//obj{"dto":{seq:3,id:hk,content:"내용"}}
// 					alert(obj["dto"]["content"]);

//					json(dto)로 받았을때
					$("textarea[name=preContent]").val(obj.dto.content);
											//    .val(obj["dto"]["content"])
					//text로 전달받았을때
// 					$("textarea[name=preContent]").val(obj);
				},
				error:function(){
// 					alert("서버통신실패!!");
				}
			});
		},function(){
			$("textarea[name=preContent]").val("");
		});
		
		
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
		
		//체크박스 처리: 체크가 하나라도 안되면 전체선택체크박스 해제, 모두 선택되면 체크
		var chks=document.getElementsByName("seq");
		for (var i = 0; i < chks.length; i++) {
			chks[i].onclick=function(){
				var checkedObjs=document.querySelectorAll("input[name=seq]:checked");
				if(checkedObjs.length==chks.length){
					document.getElementsByName("all")[0].checked=true;
				}else{
					document.getElementsByName("all")[0].checked=false;
				}
			}
		}
	})
</script>
</head>
<body>
<jsp:useBean id="util" class="com.hk.utils.Util"/>
<div id="container" style="height: auto;">
<h1>글목록보기</h1>
<textarea rows="2" cols="40" name="preContent"></textarea>
<form action="muldel.do" method="post">
<table  class="table table-striped">
	<tr>
		<th><input type="checkbox" name="all" onclick="allSel(this.checked)"/></th>
		<th>번호</th>
		<th>작성자</th>
		<th style="width:300px;">제 목</th>
		<th>작성일</th>
		<th>refer</th>
		<th>step</th>
		<th>depth</th>
		<th>조회수</th>
		<th>delflag</th>
	</tr>
	<c:choose>
		<c:when test="${empty list}">
			<tr>
				<td colspan="10">---작성된 글이 없습니다.---</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td><input type="checkbox" name="seq" value="${dto.seq}"/></td>
					<td>${dto.seq}</td>
					<td>${dto.id}</td>
					<c:choose>
						<c:when test="${dto.delflag=='Y'}">
							<td>---삭제된 글입니다.---</td>
						</c:when>
						<c:otherwise>
							<td>
							<jsp:setProperty property="arrowNbsp" name="util" value="${dto.depth}"/>
							<jsp:getProperty property="arrowNbsp" name="util"/>
							<a href="boarddetail.do?seqparam=${dto.seq}">${dto.title}</a></td>
						</c:otherwise>
					</c:choose>
					<td><f:formatDate pattern="yyyy-MM-dd" value="${dto.regdate}"/>  </td>
					<td>${dto.refer}</td>
					<td>${dto.step}</td>
					<td>${dto.depth}</td>
					<td>${dto.readcount}</td>
					<td>${dto.delflag}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="10">
			<button onclick="location.href='addForm.do'" type="button" class="btn btn-default" aria-label="Left Align">
			  <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			    글추가
			</button>
			<button type="submit" class="btn btn-default" aria-label="Left Align">
			  <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
			    글삭제
			</button>
<!-- 			<input type="button" value="글추가" -->
<!-- 			 onclick="location.href='AnsController.do?command=addForm'"/> -->
<!-- 			<input type="submit" value="글삭제"/>  -->
		</td>
	</tr>
</table>
</form>
</div>
<jsp:include page="footer.jsp" />	

</body>
</html>















