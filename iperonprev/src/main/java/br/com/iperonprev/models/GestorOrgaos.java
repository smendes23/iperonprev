package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class GestorOrgaos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	@ManyToOne(cascade=CascadeType.MERGE)
	private PessoasFuncionais pessoasFuncionais;
	@ManyToOne(cascade=CascadeType.MERGE)
	private Orgaos orgaos;
	private Date DATA_inicio;
	private Date DATA_fim;
	@Version
	private int NUMR_versao;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getPessoasFuncionais() {
		return pessoasFuncionais;
	}
	public void setPessoasFuncionais(PessoasFuncionais pessoasFuncionais) {
		this.pessoasFuncionais = pessoasFuncionais;
	}
	public Orgaos getOrgaos() {
		return orgaos;
	}
	public void setOrgaos(Orgaos orgaos) {
		this.orgaos = orgaos;
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
		result = prime * result + ((DATA_fim == null) ? 0 : DATA_fim.hashCode());
		result = prime * result + ((DATA_inicio == null) ? 0 : DATA_inicio.hashCode());
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + NUMR_versao;
		result = prime * result + ((orgaos == null) ? 0 : orgaos.hashCode());
		result = prime * result + ((pessoasFuncionais == null) ? 0 : pessoasFuncionais.hashCode());
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
		GestorOrgaos other = (GestorOrgaos) obj;
		if (DATA_fim == null) {
			if (other.DATA_fim != null)
				return false;
		} else if (!DATA_fim.equals(other.DATA_fim))
			return false;
		if (DATA_inicio == null) {
			if (other.DATA_inicio != null)
				return false;
		} else if (!DATA_inicio.equals(other.DATA_inicio))
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		if (orgaos == null) {
			if (other.orgaos != null)
				return false;
		} else if (!orgaos.equals(other.orgaos))
			return false;
		if (pessoasFuncionais == null) {
			if (other.pessoasFuncionais != null)
				return false;
		} else if (!pessoasFuncionais.equals(other.pessoasFuncionais))
			return false;
		return true;
	}
}
