package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class RevisaoInvalidez implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private Date DATA_ultimaRevisao;
	private Date DATA_proximaRevisao;
	@ManyToOne
	private AposentadoriaPorInvalidez NUMR_aposentadoriaInvalidez;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Date getDATA_ultimaRevisao() {
		return DATA_ultimaRevisao;
	}
	public void setDATA_ultimaRevisao(Date dATA_ultimaRevisao) {
		DATA_ultimaRevisao = dATA_ultimaRevisao;
	}
	public Date getDATA_proximaRevisao() {
		return DATA_proximaRevisao;
	}
	public void setDATA_proximaRevisao(Date dATA_proximaRevisao) {
		DATA_proximaRevisao = dATA_proximaRevisao;
	}
	public AposentadoriaPorInvalidez getNUMR_aposentadoriaInvalidez() {
		return NUMR_aposentadoriaInvalidez;
	}
	public void setNUMR_aposentadoriaInvalidez(AposentadoriaPorInvalidez nUMR_aposentadoriaInvalidez) {
		NUMR_aposentadoriaInvalidez = nUMR_aposentadoriaInvalidez;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_proximaRevisao == null) ? 0 : DATA_proximaRevisao.hashCode());
		result = prime * result + ((DATA_ultimaRevisao == null) ? 0 : DATA_ultimaRevisao.hashCode());
		result = prime * result + ((NUMR_aposentadoriaInvalidez == null) ? 0 : NUMR_aposentadoriaInvalidez.hashCode());
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
		RevisaoInvalidez other = (RevisaoInvalidez) obj;
		if (DATA_proximaRevisao == null) {
			if (other.DATA_proximaRevisao != null)
				return false;
		} else if (!DATA_proximaRevisao.equals(other.DATA_proximaRevisao))
			return false;
		if (DATA_ultimaRevisao == null) {
			if (other.DATA_ultimaRevisao != null)
				return false;
		} else if (!DATA_ultimaRevisao.equals(other.DATA_ultimaRevisao))
			return false;
		if (NUMR_aposentadoriaInvalidez == null) {
			if (other.NUMR_aposentadoriaInvalidez != null)
				return false;
		} else if (!NUMR_aposentadoriaInvalidez.equals(other.NUMR_aposentadoriaInvalidez))
			return false;
		return true;
	}
	
	
}
