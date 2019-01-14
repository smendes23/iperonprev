package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class NivelFuncional implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	
	private String DESC_nivel;

	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}

	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}

	public String getDESC_nivel() {
		return DESC_nivel;
	}

	public void setDESC_nivel(String dESC_nivel) {
		DESC_nivel = dESC_nivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_nivel == null) ? 0 : DESC_nivel.hashCode());
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
		NivelFuncional other = (NivelFuncional) obj;
		if (DESC_nivel == null) {
			if (other.DESC_nivel != null)
				return false;
		} else if (!DESC_nivel.equals(other.DESC_nivel))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}

	
}
