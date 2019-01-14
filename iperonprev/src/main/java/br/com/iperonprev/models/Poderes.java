package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Poderes implements Serializable{


	private static final long serialVersionUID = 1L;@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@Column(length=60)
	private String DESC_nome;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
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
		result = prime * result + NUMG_idDoObjeto;
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
		Poderes other = (Poderes) obj;
		if (DESC_nome == null) {
			if (other.DESC_nome != null)
				return false;
		} else if (!DESC_nome.equals(other.DESC_nome))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}
	
	
}
