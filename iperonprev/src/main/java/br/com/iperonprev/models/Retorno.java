package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Retorno implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String codigoBarras;
	@OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
	private Financeiro financeiro;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Financeiro getFinanceiro() {
		return financeiro;
	}
	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((codigoBarras == null) ? 0 : codigoBarras.hashCode());
		result = prime * result + ((financeiro == null) ? 0 : financeiro.hashCode());
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
		Retorno other = (Retorno) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (codigoBarras == null) {
			if (other.codigoBarras != null)
				return false;
		} else if (!codigoBarras.equals(other.codigoBarras))
			return false;
		if (financeiro == null) {
			if (other.financeiro != null)
				return false;
		} else if (!financeiro.equals(other.financeiro))
			return false;
		return true;
	}
}
