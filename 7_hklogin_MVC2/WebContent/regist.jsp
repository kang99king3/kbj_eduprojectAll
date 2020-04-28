<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html; charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//page가 로딩되면 함수를 실행시킨다.  : 로딩되는걸 이벤트로 인식 --> onload이벤트
	window.onload=function(){
		var pwInputs=document.querySelectorAll("input[type=password]");
		//pwInputs[1]에서 커서가 나가면 실행해라
		pwInputs[1].onblur=function(){
			if(pwInputs[0].value!=pwInputs[1].value){
				alert("패스워드를 확인하세요!!");
				pwInputs[0].value="";
				pwInputs[1].value="";
				pwInputs[0].focus();
			}
		}
		
		//아이디 중복체크를 먼저 진행해서 아이디를 결정하고 다음 입력값 진행 처리
		var inputs=document.querySelectorAll("input[name]");
		for (var i = 2; i < inputs.length; i++) {
			inputs[i].onfocus=function(){
				if(inputs[1].className=='n'){
					alert("아이디중복체크를 먼저하세요");
					inputs[1].focus();
				}
			}
		}
		
		
		//form태그에서 submit전송 이벤트가 발생하는지 감지--> 패스워드 일치하는 확인 실행
		var form=document.getElementsByTagName("form")[0];//[form][0]
		form.onsubmit=function(){
			var pwInputs=document.querySelectorAll("input[type=password]");
// 			alert(pwInputs[0].value+":"+pwInputs[1].value);
			if(pwInputs[0].value!=pwInputs[1].value){
				alert("패스워드를 확인하세요!!");
				pwInputs[0].value="";
				pwInputs[1].value="";
				pwInputs[0].focus();
				return false;//유효하지 않은 값이 존재하면 submit전송기능 취소
			}
		}
		
	}
	
	function idChk(){
		//입력된 아이디값 구하기
		var id=document.getElementsByName("id")[0].value;
		//window.open("url","title","브라우저의 속성설정")
		open("LoginController.do?command=idChk&id="+id,
			 "", 
			 "width=300px,height=300px");
	}
	
	
</script>
</head>
<body>
<h1>회원가입</h1>
<form action="LoginController.do" method="post">
	<input type="hidden" name="command" value="insertuser"/>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="id" required="required" class="n"/>
				<input type="button" value="중복체크" onclick="idChk()"/>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" required="required"/></td>
		</tr>
		<tr>
			<th>패스워드</th>
			<td><input type="password" name="password" required="required"/></td>
		</tr>
		<tr>
			<th>패스워드확인</th>
			<td><input type="password" name="password2" required="required"/></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="address" required="required"/></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="tel" name="phone" required="required"/></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="email" name="email" required="required"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="가입완료"/>
				<input type="button" value="메인" 
				onclick="location.href='index.jsp'"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>













