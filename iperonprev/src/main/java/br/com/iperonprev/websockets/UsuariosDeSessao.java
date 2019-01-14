package br.com.iperonprev.websockets;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
@ApplicationScoped
public class UsuariosDeSessao {

	private List<Session> sessoes = new ArrayList<>();
	
	public void add(Session sessao){
		sessoes.add(sessao);
	}
	
	public List<Session> getSessoes(){
		return this.sessoes;
	}
	
	public void remove(Session session) {
	    sessoes.remove(session);
	}
}
