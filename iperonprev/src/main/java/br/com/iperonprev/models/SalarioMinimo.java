package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SalarioMinimo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int NUMR_ano;
	private Date Dt_vigencia;
	private BigDecimal NUMR_valor;
	public int getNUMR_ano() {
		return NUMR_ano;
	}
	public void setNUMR_ano(int nUMR_ano) {
		NUMR_ano = nUMR_ano;
	}
	public Date getDt_vigencia() {
		return Dt_vigencia;
	}
	public void setDt_vigencia(Date dt_vigencia) {
		Dt_vigencia = dt_vigencia;
	}
	public BigDecimal getNUMR_valor() {
		return NUMR_valor;
	}
	public void setNUMR_valor(BigDecimal nUMR_valor) {
		NUMR_valor = nUMR_valor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Dt_vigencia == null) ? 0 : Dt_vigencia.hashCode());
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
		SalarioMinimo other = (SalarioMinimo) obj;
		if (Dt_vigencia == null) {
			if (other.Dt_vigencia != null)
				return false;
		} else if (!Dt_vigencia.equals(other.Dt_vigencia))
			return false;
		return true;
	}
	
}
