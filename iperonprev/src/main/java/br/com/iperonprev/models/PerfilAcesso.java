package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PerfilAcesso implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String nomePerfil;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomePerfil() {
		return nomePerfil;
	}
	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nomePerfil == null) ? 0 : nomePerfil.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilAcesso other = (PerfilAcesso) obj;
		if (id != other.id)
			return false;
		if (nomePerfil == null) {
			if (other.nomePerfil != null)
				return false;
		} else if (!nomePerfil.equals(other.nomePerfil))
			return false;
		return true;
	}
}
