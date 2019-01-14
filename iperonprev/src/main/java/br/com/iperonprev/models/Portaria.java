package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Portaria implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	private int NUMG_idDoObjeto;
	private String DESC_competencia;
	private String DESC_descricao;
	@OneToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER,mappedBy="portaria")
	private List<Indice> NUMR_indice;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_competencia() {
		return DESC_competencia;
	}
	public void setDESC_competencia(String dESC_competencia) {
		DESC_competencia = dESC_competencia;
	}
	public String getDESC_descricao() {
		return DESC_descricao;
	}
	public void setDESC_descricao(String dESC_descricao) {
		DESC_descricao = dESC_descricao;
	}
	public List<Indice> getNUMR_indice() {
		return NUMR_indice;
	}
	public void setNUMR_indice(List<Indice> nUMR_indice) {
		NUMR_indice = nUMR_indice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_competencia == null) ? 0 : DESC_competencia.hashCode());
		result = prime * result + ((DESC_descricao == null) ? 0 : DESC_descricao.hashCode());
		result = prime * result + ((NUMR_indice == null) ? 0 : NUMR_indice.hashCode());
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
		Portaria other = (Portaria) obj;
		if (DESC_competencia == null) {
			if (other.DESC_competencia != null)
				return false;
		} else if (!DESC_competencia.equals(other.DESC_competencia))
			return false;
		if (DESC_descricao == null) {
			if (other.DESC_descricao != null)
				return false;
		} else if (!DESC_descricao.equals(other.DESC_descricao))
			return false;
		if (NUMR_indice == null) {
			if (other.NUMR_indice != null)
				return false;
		} else if (!NUMR_indice.equals(other.NUMR_indice))
			return false;
		return true;
	}
}
