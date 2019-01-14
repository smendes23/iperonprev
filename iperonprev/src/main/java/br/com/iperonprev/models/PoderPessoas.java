package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class PoderPessoas implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto = 0;
	@OneToOne
	private Orgaos REL_orgao;
	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoas NUMR_idDoObjetoPessoa;
	private Date DATA_inicioMandato;
	private Date DATA_fimMandato;
	@Version
	private int NUMR_versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Orgaos getREL_orgao() {
		return REL_orgao;
	}
	public void setREL_orgao(Orgaos rEL_orgao) {
		REL_orgao = rEL_orgao;
	}
	public Pessoas getNUMR_idDoObjetoPessoa() {
		return NUMR_idDoObjetoPessoa;
	}
	public void setNUMR_idDoObjetoPessoa(Pessoas nUMR_idDoObjetoPessoa) {
		NUMR_idDoObjetoPessoa = nUMR_idDoObjetoPessoa;
	}
	public Date getDATA_inicioMandato() {
		return DATA_inicioMandato;
	}
	public void setDATA_inicioMandato(Date dATA_inicioMandato) {
		DATA_inicioMandato = dATA_inicioMandato;
	}
	public Date getDATA_fimMandato() {
		return DATA_fimMandato;
	}
	public void setDATA_fimMandato(Date dATA_fimMandato) {
		DATA_fimMandato = dATA_fimMandato;
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
		PoderPessoas other = (PoderPessoas) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}
}
