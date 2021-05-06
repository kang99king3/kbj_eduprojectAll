package com.hk.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.sockjs.transport.handler.JsonpReceivingTransportHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.board.dtos.MemberDto;

import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONListAdapter;

public class EchoHandler extends TextWebSocketHandler {
	
		//로그인 한 전체 회원 저장: 전체 회원에 메시지 보낼때
		List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
		// 1대1을 위한 ID로 맵핑된 회원정보(session) 저장
		Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>();
			
		//서버에 접속이 성공 했을때
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			System.out.println("서버접속성공:afterConnectionEstablished");
			sessions.add(session);//로그인한 회원 정보를 저장한다.
			
			String senderId = getId(session);
			//ID를 키값으로 해서 회원정보 저장: 이렇게 해야 원하는 회원한테 메시지를 전달할 수 있다.
			userSessionsMap.put(senderId , session);
			System.out.println("senderId: "+senderId);
		}
		
		//소켓에 메세지를 보냈을때
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			System.out.println("서버에서 메시지받음");
//			String senderId = getId(session);
			//모든 유저에게 보낸다 - 브로드 캐스팅
//			for (WebSocketSession sess : sessions) {
//				sess.sendMessage(new TextMessage(senderNickname + ": " +  message.getPayload()));
//			}
			
			//클라이언트에서 전달되는 메시지 받기
			String msg = message.getPayload();//메시지 문자열로 변환
			ObjectMapper mapper=new ObjectMapper();//json형식의 문자열을 Map처리하기위해 생성
			Map<String, String>map = mapper.readValue(msg, Map.class);//Map으로 변환
			System.out.println(map.get("target")+"님:"+map.get("message"));
			//알림을 보낼 회원이 로그인한 상태라면
			WebSocketSession boardWriterSession = userSessionsMap.get(map.get("target"));
			if(boardWriterSession!=null) {
				TextMessage tmpMsg = new TextMessage("<div class='noti'>알림이왔어요~</div>");
				boardWriterSession.sendMessage(tmpMsg);				
			}
			//알림을 보낼 회원이 로그인한 상태가 아니라면 화면에서 DB정보를 확인하여 알려준다.
			

		}
		
		//연결 해제될때
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			//System.out.println("afterConnectionClosed " + session + ", " + status);
			userSessionsMap.remove(session.getId());
			sessions.remove(session);
		}
		
		//웹소켓 id 가져오기
		private String getId(WebSocketSession session) {
			Map<String, Object> httpSession = session.getAttributes();
			MemberDto loginUser = (MemberDto)httpSession.get("memDTO");
			
			if(loginUser == null) {
				return session.getId();
			} else {
				return loginUser.getMember_id();
			}
		}
	}
