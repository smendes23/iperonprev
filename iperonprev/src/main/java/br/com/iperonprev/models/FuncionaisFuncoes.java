package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class FuncionaisFuncoes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@OneToOne
	private Pessoas NUMR_idPessoas;
	private Date DATA_ingressoEnte;
	private Date DATA_egressoEnte;
	@Version
	private int NUMR_versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Pessoas getNUMR_idPessoas() {
		return NUMR_idPessoas;
	}
	public void setNUMR_idPessoas(Pessoas nUMR_idPessoas) {
		NUMR_idPessoas = nUMR_idPessoas;
	}
	public Date getDATA_ingressoEnte() {
		return DATA_ingressoEnte;
	}
	public void setDATA_ingressoEnte(Date dATA_ingressoEnte) {
		DATA_ingressoEnte = dATA_ingressoEnte;
	}
	public Date getDATA_egressoEnte() {
		return DATA_egressoEnte;
	}
	public void setDATA_egressoEnte(Date dATA_egressoEnte) {
		DATA_egressoEnte = dATA_egressoEnte;
	}
	public int getNUMR_versao() {
		return NUMR_versao;
	}
	public void setNUMR_versao(int nUMR_versao) {
		NUMR_versao = nUMR_versao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_egressoEnte == null) ? 0 : DATA_egressoEnte.hashCode());
		result = prime * result + ((DATA_ingressoEnte == null) ? 0 : DATA_ingressoEnte.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_idPessoas == null) ? 0 : NUMR_idPessoas.hashCode());
		result = prime * result + NUMR_versao;
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
		FuncionaisFuncoes other = (FuncionaisFuncoes) obj;
		if (DATA_egressoEnte == null) {
			if (other.DATA_egressoEnte != null)
				return false;
		} else if (!DATA_egressoEnte.equals(other.DATA_egressoEnte))
			return false;
		if (DATA_ingressoEnte == null) {
			if (other.DATA_ingressoEnte != null)
				return false;
		} else if (!DATA_ingressoEnte.equals(other.DATA_ingressoEnte))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_idPessoas == null) {
			if (other.NUMR_idPessoas != null)
				return false;
		} else if (!NUMR_idPessoas.equals(other.NUMR_idPessoas))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		return true;
	}
	
}
