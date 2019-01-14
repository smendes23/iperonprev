package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
@Entity
public class Papel implements Serializable,GrantedAuthority{

	private static final long serialVersionUID = 1L;

	@Id
	private String name;
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}

}
