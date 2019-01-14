package br.com.iperonprev.websockets;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ServerEndpoint(value="/canal/avisos")
@Service
public class AvisosEndpoint{
	
	@Autowired
	private UsuariosDeSessao usuarios;
	
	private static Set<Session> clients = 
		    Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onMessage(Session sessao){
		clients.add(sessao);
	}
	
	public void onClose(Session session, CloseReason closeReason) {
	    usuarios.remove(session);
	    System.out.println(closeReason.getCloseCode());
	}
	
	public void send(AvisoDto aviso){
		for (Session session : clients) {
			if(session.isOpen()){
				try{
					session.getBasicRemote().sendText(aviso.toJson());
				}catch(Exception e){
					
				}
			}
		}
		
	}
}
