package kr.or.ddit.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


//프로퍼티에서 runtime 체크
@ServerEndpoint("/wsServer")
public class WsServer {
	//세션 객체의 값을 어딘가에 가지고 있으면 모두에게 메시지를 보낼 수 있다
	static List<Session> sessions = new ArrayList<Session>();
	
	//어노테이션 말고 상속도 가능
	//새로운 클라이언트의 요청이 들어왔을 때
	//Session <- 웹소켓 세션!
	@OnOpen
	public void onOpen(Session session){
		//System.out.println("onOpen 실행완료 ");
		//System.out.println("onOpen : " + session.getId());	//호출되는 web 페이지(client.jsp)마다 다른 사용자로 인식 
		sessions.add(session);
	}
	
	//클라이언트가 메시지를 보냈을때 서버가 회신하는 메서드 
	@OnMessage
	public void onMessage(String message, Session session) throws IOException{
		//System.out.println("onMessage : "+ message);
		
		//채팅
		for(Session s : sessions){
			s.getBasicRemote().sendText(s.getId()+" : "+message);
		}	
		
		//세션 객체를 통해서 클라이언트에게 메시지를 보낼 수 있다.
		//session.getBasicRemote().sendText("Server's hello world");
	}
	
	//세션 정리(연결종료된 session)
	@OnClose
	public void onClose(Session session){
		for(Session s : sessions){
			if(session.getId().equals(s.getId())){
				sessions.remove(s);
			}
		}
	}
}
