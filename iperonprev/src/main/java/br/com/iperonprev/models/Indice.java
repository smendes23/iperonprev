package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Indice implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String NUMR_mesAno;
	private BigDecimal VALR_indice;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="portaria_NUMG_idDoObjeto")
	private Portaria portaria;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getNUMR_mesAno() {
		return NUMR_mesAno;
	}
	public void setNUMR_mesAno(String nUMR_mesAno) {
		NUMR_mesAno = nUMR_mesAno;
	}
	public BigDecimal getVALR_indice() {
		return VALR_indice;
	}
	public void setVALR_indice(BigDecimal vALR_indice) {
		VALR_indice = vALR_indice;
	}
	public Portaria getPortaria() {
		return portaria;
	}
	public void setPortaria(Portaria portaria) {
		this.portaria = portaria;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NUMR_mesAno == null) ? 0 : NUMR_mesAno.hashCode());
		result = prime * result + ((VALR_indice == null) ? 0 : VALR_indice.hashCode());
		result = prime * result + ((portaria == null) ? 0 : portaria.hashCode());
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
		Indice other = (Indice) obj;
		if (NUMR_mesAno == null) {
			if (other.NUMR_mesAno != null)
				return false;
		} else if (!NUMR_mesAno.equals(other.NUMR_mesAno))
			return false;
		if (VALR_indice == null) {
			if (other.VALR_indice != null)
				return false;
		} else if (!VALR_indice.equals(other.VALR_indice))
			return false;
		if (portaria == null) {
			if (other.portaria != null)
				return false;
		} else if (!portaria.equals(other.portaria))
			return false;
		return true;
	}
	
}
