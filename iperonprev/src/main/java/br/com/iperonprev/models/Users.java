package br.com.iperonprev.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Users implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	@Id
	private String matricula;
	private String senha;
	private String nome;
	private boolean contaHabilitada;
	private boolean redefinirSenha;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Roles> roles;
	@OneToOne
	private PerfilAcesso perfil;
	
	public boolean isRedefinirSenha() {
		return redefinirSenha;
	}

	public void setRedefinirSenha(boolean redefinirSenha) {
		this.redefinirSenha = redefinirSenha;
	}

	public PerfilAcesso getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilAcesso perfil) {
		this.perfil = perfil;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isContaHabilitada() {
		return contaHabilitada;
	}

	public void setContaHabilitada(boolean contaHabilitada) {
		this.contaHabilitada = contaHabilitada;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles();
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.matricula;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.contaHabilitada;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
