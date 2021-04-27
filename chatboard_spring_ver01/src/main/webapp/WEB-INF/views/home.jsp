<%@page import="org.springframework.web.socket.WebSocketSession"%>
<%@page contentType="text/html; charset=utf-8" language="java" %>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<style type="text/css">
		table{ border-collapse: collapse;}
		#chatForm{
			height: 400px; width: 300px; 
			vertical-align: top;
			overflow: auto;
		}
		#chatUserlist{vertical-align: top;}
		.me,.you{margin-top: 10px;}
		.me>div,.you>div{width: 120px;border-radius: 5px; 
			font-size: 13px; padding: 4px;
		}
		.me>div{background-color: yellow;}
		.you>div{background-color:#CEF6F5;}
	 	.me{float:right;} 
		.you{margin-left: 0px;}
		.me+div{clear: both;}
		img{width: 50px; height: 50px;}
	</style>
</head>
<body onbeforeunload="chatRoomClose()">
<!-- <body> -->
<script type="text/javascript">
	
	
	
	var ws;//웹소켓 객체 변수
// 	var wsUri = "ws://192.168.4.166:8090/chatboard_spring_ver01/chatboard";
// 	var wsUri = "ws://localhost:8090/chatboard_spring_ver01/chatboard";
	var wsUri = "ws://localhost:8090/chat/chatboard";
	function openSocket(){
		//웹소켓이 생성 되어 있다면 다시 객체를 생성하지 않는다.
		 if(ws!=null && ws.readyState!=WebSocket.CLOSED){
//              writeResponse("WebSocket is already opened.");
             return;
         }
		//웹소켓 객체 생성: uri에 해당 websockethandler 연결
		ws=new WebSocket(wsUri);
		
		//웹소켓 연결되면 실행
		ws.onopen=function(event){
// 			alert("채팅실행");
		}
		
		//웹소켓 서버에서 메시지 받으면 실행
		ws.onmessage=function(event){
			userListView();
			$("#chatForm").append(event.data);
			$("#chatForm").scrollTop($("#chatForm").prop('scrollHeight'));
		}
		
		//웹소켓 연결 종료되면 실행
		ws.onclose=function(){
			$.ajax({
				url:"chatOut.do",
				async:false
			});
			ws=null;//웹소켓 연결 끊고 null로 초기화
// 			alert("채팅종료");
		}
	}
	
	//채팅시 메시지 보내기
	function send(){
		var text=$("#input");//입력창 객체
// 		var msg={
// 				type:"chat",
// 				target:"hk",
// 				message:text.val()
// 		};
// 		ws.send(JSON.stringify(msg));

		ws.send(text.val());//입력창 객체에 텍스트를 서버로 보냄
		text.val("");//현재 입력창 "" 초기화
		text.focus();//현재입력창에 포커스(커서 위치 설정)
	}
	
	//채팅참여자 아이디 모두 보여주기
	function userListView(){
		$.ajax({
			url:"userlistview.do",
			async:false,
			dataType:"json",
			method:"post",
			success:function(obj){
				var ulist=obj.userlist;//채팅참여자리스트
				var chatUserlist="";
				for (var i = 0; i < ulist.length; i++) {
					chatUserlist+="<p>"+ulist[i]+"</p>";
				}
				$("#chatUserlist").html(chatUserlist);
			}
		});
	}
	//채팅창을 닫으면 소켓 연결 끊고, 서버에서도 연결 끊는다(chatOut.do)
	function chatRoomClose(){
		
		//클라이언트 웹소켓 종료
		ws.close();
		//종료할 웹소켓 객체 삭제
		$.ajax({
			url:"chatOut.do",
			async:false
		});
	
		//채팅창이 닫히면 메인창에 클릭 버튼 활성화
// 		opener.document.getElementById("chatStart_btn")
// 		.disabled="";
	}
	
	$(function(){
		
		//채팅창이 열리면 메인창에 클릭 버튼 비활성화
// 		opener.document.getElementById("chatStart_btn")
// 		.disabled="disabled";
		
		//창이 로드되면 웹소켓 연결
		openSocket();
		
		//새로고침 막기 : F5키 (116)
		$(document).keydown(function(e){
			var key=(e)?e.keyCode:event.keyCode;
			  if (key == 116) {
		            if (e) {
		                e.preventDefault();
		            } else {
		                event.keyCode = 0;
		                event.returnValue = false;
		            }
			  }
		});
		
		//입력창에 키보드 키가 up되는 이벤트 발생시 입력창의 상태가 ""이면 버튼비활성화 아니면 활성화
		//---> 입력창에 글자를 입력할때만 보내기 버튼을 클릭할 수 있도록 한다.
		$("#input").keyup(function() {
			if($("#input").val()==""){
				$(".chat_btn").attr("disabled","disabled");
			}else{
				$(".chat_btn").removeAttr("disabled");
			}
		});
		
		//입력창에 글을 입력하고 엔터키를 누르면 "보내기"버튼의 'disabled'속성을 확인하여 클릭실행
		$("#input").keypress(function(e){//키를 누르면
			if(event.keyCode==13){//누른 키가 엔터키인지 확인하고
				
				if($('.chat_btn').attr("disabled")==undefined){//버튼이 활성화되어있으면
					$('.chat_btn').trigger("click");//버튼을 클릭한다.
				}
				return false;//엔터키를 누르기 때문에 엔터의 기본기능인 줄바꿈현상을 막는다
			}
		});
	});
</script>
<table border="1">
	<tr class="chatTr">
		<td class="chatTd" id="chatHeader" colspan="2">
			<img src="resources/profile.png"/>${sessionScope.userId}
		</td>
	</tr>
	<tr class="chatTr">
		<td class="chatTd">
			<div id="chatForm"></div>
		</td>
		<td class="chatTd" id="chatUserlist"></td>
	</tr>
	<tr class="chatTr">
		<td class="chatTd">
			<textarea rows="3" cols="40" id="input"></textarea>
		</td>
		<td class="chatTd">
			<button disabled="disabled"  class="chat_btn" onclick="send()">보내기</button>
		</td>
	</tr>
</table>

</body>
</html>
