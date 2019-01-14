package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RealizadoPessoaJuridica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String DESC_codigoBarras;
	private transient String DESC_codigoBarrasFormatado;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_codigoBarras() {
		return DESC_codigoBarras;
	}
	public void setDESC_codigoBarras(String dESC_codigoBarras) {
		DESC_codigoBarras = dESC_codigoBarras;
	}
	public String getDESC_codigoBarrasFormatado() {
		return DESC_codigoBarrasFormatado;
	}
	public void setDESC_codigoBarrasFormatado(String dESC_codigoBarrasFormatado) {
		DESC_codigoBarrasFormatado = dESC_codigoBarrasFormatado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_codigoBarras == null) ? 0 : DESC_codigoBarras.hashCode());
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
		RealizadoPessoaJuridica other = (RealizadoPessoaJuridica) obj;
		if (DESC_codigoBarras == null) {
			if (other.DESC_codigoBarras != null)
				return false;
		} else if (!DESC_codigoBarras.equals(other.DESC_codigoBarras))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}
	
}
