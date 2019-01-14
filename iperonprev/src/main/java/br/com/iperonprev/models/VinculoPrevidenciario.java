package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
@Entity
public class VinculoPrevidenciario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private int NUMR_codigo;
	private String DESC_descricao;
	@Version
	private int versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public int getNUMR_codigo() {
		return NUMR_codigo;
	}
	public void setNUMR_codigo(int nUMR_codigo) {
		NUMR_codigo = nUMR_codigo;
	}
	public String getDESC_descricao() {
		return DESC_descricao;
	}
	public void setDESC_descricao(String dESC_descricao) {
		DESC_descricao = dESC_descricao;
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
		result = prime * result + ((DESC_descricao == null) ? 0 : DESC_descricao.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + NUMR_codigo;
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
		VinculoPrevidenciario other = (VinculoPrevidenciario) obj;
		if (DESC_descricao == null) {
			if (other.DESC_descricao != null)
				return false;
		} else if (!DESC_descricao.equals(other.DESC_descricao))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_codigo != other.NUMR_codigo)
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
}
