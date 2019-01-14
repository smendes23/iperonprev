package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Municipios implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@Column(length=100)
	private String DESC_nome;
	@ManyToOne
	private Estados NUMR_idDoObjetoEstado;
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
	public Estados getNUMR_idDoObjetoEstado() {
		return NUMR_idDoObjetoEstado;
	}
	public void setNUMR_idDoObjetoEstado(Estados nUMR_idDoObjetoEstado) {
		NUMR_idDoObjetoEstado = nUMR_idDoObjetoEstado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_nome == null) ? 0 : DESC_nome.hashCode());
		result = prime * result + ((NUMR_idDoObjetoEstado == null) ? 0 : NUMR_idDoObjetoEstado.hashCode());
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
		Municipios other = (Municipios) obj;
		if (DESC_nome == null) {
			if (other.DESC_nome != null)
				return false;
		} else if (!DESC_nome.equals(other.DESC_nome))
			return false;
		if (NUMR_idDoObjetoEstado == null) {
			if (other.NUMR_idDoObjetoEstado != null)
				return false;
		} else if (!NUMR_idDoObjetoEstado.equals(other.NUMR_idDoObjetoEstado))
			return false;
		return true;
	}
	
	
}
