package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Carreiras implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	@Column(length=60)
	private String DESC_nome;
	private Date DATA_criacao;
	private Date DATA_extincao;
	@Version
	private int NUMR_versao;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_nome() {
		return DESC_nome;
	}
	public void setDESC_nome(String dESC_nome) {
		DESC_nome = dESC_nome;
	}
	public Date getDATA_criacao() {
		return DATA_criacao;
	}
	public void setDATA_criacao(Date dATA_criacao) {
		DATA_criacao = dATA_criacao;
	}
	public Date getDATA_extincao() {
		return DATA_extincao;
	}
	public void setDATA_extincao(Date dATA_extincao) {
		DATA_extincao = dATA_extincao;
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
		result = prime * result + ((DATA_criacao == null) ? 0 : DATA_criacao.hashCode());
		result = prime * result + ((DATA_extincao == null) ? 0 : DATA_extincao.hashCode());
		result = prime * result + ((DESC_nome == null) ? 0 : DESC_nome.hashCode());
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
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
		Carreiras other = (Carreiras) obj;
		if (DATA_criacao == null) {
			if (other.DATA_criacao != null)
				return false;
		} else if (!DATA_criacao.equals(other.DATA_criacao))
			return false;
		if (DATA_extincao == null) {
			if (other.DATA_extincao != null)
				return false;
		} else if (!DATA_extincao.equals(other.DATA_extincao))
			return false;
		if (DESC_nome == null) {
			if (other.DESC_nome != null)
				return false;
		} else if (!DESC_nome.equals(other.DESC_nome))
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		return true;
	}
	public Carreiras(Integer nUMG_idDoObjeto, String dESC_nome, Date dATA_criacao, Date dATA_extincao,
			int nUMR_versao) {
		super();
		NUMG_idDoObjeto = nUMG_idDoObjeto;
		DESC_nome = dESC_nome;
		DATA_criacao = dATA_criacao;
		DATA_extincao = dATA_extincao;
		NUMR_versao = nUMR_versao;
	}
	public Carreiras() {
		super();
	}
	
	
}
