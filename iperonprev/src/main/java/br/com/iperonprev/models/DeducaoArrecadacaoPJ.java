package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.iperonprev.constantes.TipoBeneficioDeducaoEnum;

@Entity
public class DeducaoArrecadacaoPJ implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	@Enumerated(EnumType.STRING)
	private TipoBeneficioDeducaoEnum ENUM_tipoDeducao;
	private BigDecimal VAL_deducao;
	private String DESC_competencia;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public TipoBeneficioDeducaoEnum getENUM_tipoDeducao() {
		return ENUM_tipoDeducao;
	}
	public void setENUM_tipoDeducao(TipoBeneficioDeducaoEnum eNUM_tipoDeducao) {
		ENUM_tipoDeducao = eNUM_tipoDeducao;
	}
	public BigDecimal getVAL_deducao() {
		return VAL_deducao;
	}
	public void setVAL_deducao(BigDecimal vAL_deducao) {
		VAL_deducao = vAL_deducao;
	}
	public String getDESC_competencia() {
		return DESC_competencia;
	}
	public void setDESC_competencia(String dESC_competencia) {
		DESC_competencia = dESC_competencia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
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
		DeducaoArrecadacaoPJ other = (DeducaoArrecadacaoPJ) obj;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		return true;
	}

}
