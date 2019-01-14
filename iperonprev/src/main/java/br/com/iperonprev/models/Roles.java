package br.com.iperonprev.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Roles implements GrantedAuthority{

	public Roles(){}
	
	public Roles(String nome) {
		super();
		this.nome = nome;
	}

	private static final long serialVersionUID = 1L;
	@Id
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
