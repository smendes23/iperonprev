package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bancos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto = 0;
	private Integer NUMR_codigo;
	private String DESC_banco;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Integer getNUMR_codigo() {
		return NUMR_codigo;
	}
	public void setNUMR_codigo(Integer nUMR_codigo) {
		NUMR_codigo = nUMR_codigo;
	}
	public String getDESC_banco() {
		return DESC_banco;
	}
	public void setDESC_banco(String dESC_banco) {
		DESC_banco = dESC_banco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_banco == null) ? 0 : DESC_banco.hashCode());
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + ((NUMR_codigo == null) ? 0 : NUMR_codigo.hashCode());
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
		Bancos other = (Bancos) obj;
		if (DESC_banco == null) {
			if (other.DESC_banco != null)
				return false;
		} else if (!DESC_banco.equals(other.DESC_banco))
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_codigo == null) {
			if (other.NUMR_codigo != null)
				return false;
		} else if (!NUMR_codigo.equals(other.NUMR_codigo))
			return false;
		return true;
	}
	
}
