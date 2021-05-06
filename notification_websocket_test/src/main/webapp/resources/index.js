//전역변수 선언-모든 홈페이지에서 사용 할 수 있게 index에 저장
   var sock = null;
   var wsUri = "ws://localhost:8090/board/echo";
   
   $(document).ready(function (){
	   connectWs();
   });
 
   function connectWs(){
   	sock = new WebSocket(wsUri);
   	//sock = new SockJS('/replyEcho');
//   	socket = sock;
 
   	//소켓 연결되면 실행
   	 sock.onopen = function() {
           console.log('info: connection opened.');
     };
 
     //메시지 받아서 출력하기
     sock.onmessage = function(evt) {
	 	var data = evt.data;
	   	console.log("ReceivMessage : " + data + "\n");
	   	
	   	//문서에 알림 출력하기
//	   	$("body").find(".noti").remove().end().append(data);
//	   	$(".noti").fadeIn();
	   	
	   	if(data!=null||data!=""){
	   		calculate();
	   	}
	   	
        //DB에서 알림에 대한 목록 불러와서 개수를 알려주거나 할 수 도 있음  
    };
 
    //소켓 종료되면 실행
    sock.onclose = function() {
      	console.log('connect close');
      	/* setTimeout(function(){conntectWs();} , 1000); */
    };
 
    //소켓 에러시 실행
    sock.onerror = function (err) {console.log('Errors : ' , err);};
 
   }
   
 //채팅시 메시지 보내기
	function send(){
		var text=$("#input");//입력창 객체
		var id=$("input[name=rdi]:checked").val();
		var msg={
				type:"board",
				target:id,
				message:text.val()
		};
		//$.ajax(): target,message등 정보를 DB에 insert하는 요청을 구현한다.
		
		//메시지를 서버로 보낸다.
		sock.send(JSON.stringify(msg));
		
//		sock.send(text.val());//입력창 객체에 텍스트를 서버로 보냄
		text.val("");//현재 입력창 "" 초기화
		text.focus();//현재입력창에 포커스(커서 위치 설정)
	}
	
	/////////////////////////////////////////////////////////////////////
	 window.onload = function () {
         if (window.Notification) {
             Notification.requestPermission();
         }
     }

     function calculate() {
         setTimeout(function () {
             notify();
         });
     }

     function notify() {
         if (Notification.permission !== 'granted') {
             alert('알림설정권한이 활성화되지 않았습니다!');
         }
         else {
             var notification = new Notification('Notification title', {
                 icon: 'http://cdn.sstatic.net/stackexchange/img/logos/so/so-icon.png',
                 body: '알림이 왔어요~~',
             });

             notification.onclick = function () {
                 window.open('http://google.com');
             };
         }
     }
	
	
	
	
	
	