package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoGuia implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7551551251182674816L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	private String DESC_descricao;
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_descricao == null) ? 0 : DESC_descricao.hashCode());
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
		TipoGuia other = (TipoGuia) obj;
		if (DESC_descricao == null) {
			if (other.DESC_descricao != null)
				return false;
		} else if (!DESC_descricao.equals(other.DESC_descricao))
			return false;
		return true;
	}
	
	
}
