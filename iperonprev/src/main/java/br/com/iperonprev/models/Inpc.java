package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inpc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private BigDecimal VALR_inpc;
	private int NUMR_mesAno;
	
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public BigDecimal getVALR_inpc() {
		return VALR_inpc;
	}
	public void setVALR_inpc(BigDecimal vALR_inpc) {
		VALR_inpc = vALR_inpc;
	}
	public int getNUMR_mesAno() {
		return NUMR_mesAno;
	}
	public void setNUMR_mesAno(int nUMR_mesAno) {
		NUMR_mesAno = nUMR_mesAno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + NUMR_mesAno;
		result = prime * result + ((VALR_inpc == null) ? 0 : VALR_inpc.hashCode());
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
		Inpc other = (Inpc) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_mesAno != other.NUMR_mesAno)
			return false;
		if (VALR_inpc == null) {
			if (other.VALR_inpc != null)
				return false;
		} else if (!VALR_inpc.equals(other.VALR_inpc))
			return false;
		return true;
	}
	
	
}
