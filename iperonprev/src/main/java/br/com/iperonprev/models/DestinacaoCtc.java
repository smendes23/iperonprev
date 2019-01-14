package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DestinacaoCtc implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@OneToOne
	private Orgaos REL_orgaos;
	private Date DATA_inicio;
	private Date DATA_fim;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Orgaos getREL_orgaos() {
		return REL_orgaos;
	}
	public void setREL_orgaos(Orgaos rEL_orgaos) {
		REL_orgaos = rEL_orgaos;
	}
	public Date getDATA_inicio() {
		return DATA_inicio;
	}
	public void setDATA_inicio(Date dATA_inicio) {
		DATA_inicio = dATA_inicio;
	}
	public Date getDATA_fim() {
		return DATA_fim;
	}
	public void setDATA_fim(Date dATA_fim) {
		DATA_fim = dATA_fim;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		DestinacaoCtc other = (DestinacaoCtc) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}
	
}
