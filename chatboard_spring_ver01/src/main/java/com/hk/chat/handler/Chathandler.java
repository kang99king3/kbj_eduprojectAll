package com.hk.chat.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.hk.chat.HomeController;

public class Chathandler extends TextWebSocketHandler{

	private static final Logger logger = LoggerFactory.getLogger(Chathandler.class);
	
	//채팅 접속한 사용자들(WebSocketSession)을 담아 둘 List 생성
	private List<WebSocketSession>sessionList=new ArrayList<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("채팅시작");
		
		sessionList.add(session);//채팅을 시작하면 생성된 웹소켓 객체를 List에 추가한다.
		//세션에서 아이디를 구한다
		String userId=(String)session.getAttributes().get("userId");
		
		//채팅참여중인 모든 사용자(sessionList)에 메시지를 보낸다.
		for (WebSocketSession ws : sessionList) {
			ws.sendMessage(new TextMessage("<div>---"+userId+"님이 입장하셨습니다.---</div>"));
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("메시지보냄");
		String userId=(String)session.getAttributes().get("userId");
		
		//클라이언트로부터 전달된 메시지를 모두에게 전송하기 위해
		for (WebSocketSession ws : sessionList) {
			String myId="";//자신의 메시지인지 구별을 위한 변수 선언
			if(userId.equals((String)ws.getAttributes().get("userId"))) {
				myId="me";//내꺼인 경우 
			}else {
				myId="you";//니꺼인 경우
			}
			
			//각각의 채팅 참여자에게 메시지 전달
			//<div class='myId'> 페이지에서 css 처리를 위한 선언
			// 내꺼이면 오른쪽에 메시지 출력, 니꺼면 오른쪽에 출력하기 위한 처리
			ws.sendMessage(new TextMessage(
							"<div class='"+myId+"' >"
							+   "<span>"+userId+"</span>"
							+   "<div>"
							+      message.getPayload()
							+   "</div>"
							+"</div>")
							);
		}

	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("채팅종료");
		
		//채팅을 종료하면 사용자 웹소켓객체를 모아둔 sessionList에서 해당 웹소켓객체를 삭제
		sessionList.remove(session);//웹소켓 해당 객체 삭제
		
		String userId=(String)session.getAttributes().get("userId");
		
		for (WebSocketSession ws : sessionList) {
			ws.sendMessage(new TextMessage("<div>---"+userId+"님이 퇴장하셨습니다.---</div>"));
		}
	}
	
	 @Override
     public void handleTransportError(WebSocketSession session,
                  Throwable exception) throws Exception {
           this.logger.error("web socket error!", exception);

     }

     @Override
     public boolean supportsPartialMessages() {
           this.logger.info("call method!"); 
           return super.supportsPartialMessages();
     }
}
