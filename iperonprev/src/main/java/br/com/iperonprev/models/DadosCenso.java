package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class DadosCenso implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	private String DESC_descricao;
	private Date DATA_inicio;
	private Date DATA_fim;
	@Version
	private int versao;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_descricao() {
		return DESC_descricao;
	}
	public void setDESC_descricao(String dESC_descricao) {
		DESC_descricao = dESC_descricao;
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
	public int getVersao() {
		return versao;
	}
	public void setVersao(int versao) {
		this.versao = versao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_fim == null) ? 0 : DATA_fim.hashCode());
		result = prime * result + ((DATA_inicio == null) ? 0 : DATA_inicio.hashCode());
		result = prime * result + ((DESC_descricao == null) ? 0 : DESC_descricao.hashCode());
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + versao;
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
		DadosCenso other = (DadosCenso) obj;
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
		if (DESC_descricao == null) {
			if (other.DESC_descricao != null)
				return false;
		} else if (!DESC_descricao.equals(other.DESC_descricao))
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
}
