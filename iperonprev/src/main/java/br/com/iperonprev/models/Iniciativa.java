package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Iniciativa implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int NUMG_idDoObejto;
	private String DESC_nome;
	public int getNUMG_idDoObejto() {
		return NUMG_idDoObejto;
	}
	public void setNUMG_idDoObejto(int nUMG_idDoObejto) {
		NUMG_idDoObejto = nUMG_idDoObejto;
	}
	public String getDESC_nome() {
		return DESC_nome;
	}
	public void setDESC_nome(String dESC_nome) {
		DESC_nome = dESC_nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_nome == null) ? 0 : DESC_nome.hashCode());
		result = prime * result + NUMG_idDoObejto;
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
		Iniciativa other = (Iniciativa) obj;
		if (DESC_nome == null) {
			if (other.DESC_nome != null)
				return false;
		} else if (!DESC_nome.equals(other.DESC_nome))
			return false;
		if (NUMG_idDoObejto != other.NUMG_idDoObejto)
			return false;
		return true;
	}
	
	
}
