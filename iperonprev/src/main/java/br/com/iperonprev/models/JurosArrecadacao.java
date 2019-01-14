package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JurosArrecadacao implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String competencia;
	private BigDecimal VALR_juros;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getCompetencia() {
		return competencia;
	}
	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	public BigDecimal getVALR_juros() {
		return VALR_juros;
	}
	public void setVALR_juros(BigDecimal vALR_juros) {
		VALR_juros = vALR_juros;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((VALR_juros == null) ? 0 : VALR_juros.hashCode());
		result = prime * result + ((competencia == null) ? 0 : competencia.hashCode());
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
		JurosArrecadacao other = (JurosArrecadacao) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (VALR_juros == null) {
			if (other.VALR_juros != null)
				return false;
		} else if (!VALR_juros.equals(other.VALR_juros))
			return false;
		if (competencia == null) {
			if (other.competencia != null)
				return false;
		} else if (!competencia.equals(other.competencia))
			return false;
		return true;
	}
	
}
