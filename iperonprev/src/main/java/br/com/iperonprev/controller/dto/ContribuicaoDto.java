package br.com.iperonprev.controller.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.iperonprev.models.PessoasFuncionais;

public class ContribuicaoDto implements Serializable {
	

	private int NUMG_idDoObjeto = 0;
	private PessoasFuncionais NUMR_idPessoasFuncionais;
	private String DESC_competencia;
	private BigDecimal VALR_contribuicaoPrevidenciaria = BigDecimal.ZERO;;
	private double NUMR_aliquotaSegurado = 0;
	
	private BigDecimal VALR_contribSegurado = BigDecimal.ZERO;
	private BigDecimal VALR_devolSegurado = BigDecimal.ZERO;
	private double NUMR_aliquotaPatronal = 0;
	private BigDecimal VALR_contribPatronal = BigDecimal.ZERO;;
	private BigDecimal VALR_devolPatronal= BigDecimal.ZERO;
	private transient BigDecimal VALR_indice = BigDecimal.ZERO;;
	private transient BigDecimal VALR_remuneracaoBase = BigDecimal.ZERO;;
	private transient BigDecimal VALR_remuneracaoMedia = BigDecimal.ZERO;;
	private transient BigDecimal VALR_oitentaMaiores = BigDecimal.ZERO;;
	private BigDecimal VALR_jurosSegurado = BigDecimal.ZERO;
	private BigDecimal VALR_multaSegurado = BigDecimal.ZERO;
	private BigDecimal VALR_jurosPatronal = BigDecimal.ZERO;
	private BigDecimal VALR_multaPatronal = BigDecimal.ZERO;
	private boolean BOL_contribuicaoInvalida;
	
	
	public ContribuicaoDto() {}
	public ContribuicaoDto(int NUMG_idDoObjeto,String DESC_competencia,PessoasFuncionais NUMR_idPessoasFuncionais,BigDecimal vALR_contribuicaoPrevidenciaria) {
		this.NUMG_idDoObjeto = NUMG_idDoObjeto;
		this.DESC_competencia = DESC_competencia;
		this.NUMR_idPessoasFuncionais = NUMR_idPessoasFuncionais;
		this.VALR_contribuicaoPrevidenciaria = vALR_contribuicaoPrevidenciaria;
	}
	public ContribuicaoDto(
			String dESC_competencia,
			BigDecimal vALR_contribuicaoPrevidenciaria,
			double nUMR_aliquotaSegurado, 
			BigDecimal vALR_contribSegurado,
			BigDecimal vALR_devolSegurado,
			double nUMR_aliquotaPatronal, 
			BigDecimal vALR_contribPatronal,
			BigDecimal vALR_devolPatronal) {
		DESC_competencia = dESC_competencia;
		VALR_contribuicaoPrevidenciaria = vALR_contribuicaoPrevidenciaria;
		NUMR_aliquotaSegurado = nUMR_aliquotaSegurado;
		VALR_contribSegurado = vALR_contribSegurado;
		VALR_devolSegurado = vALR_devolSegurado;
		NUMR_aliquotaPatronal = nUMR_aliquotaPatronal;
		VALR_contribPatronal = vALR_contribPatronal;
		VALR_devolPatronal = vALR_devolPatronal;

	}
	
	
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_idPessoasFuncionais() {
		return NUMR_idPessoasFuncionais;
	}
	public void setNUMR_idPessoasFuncionais(PessoasFuncionais nUMR_idPessoasFuncionais) {
		NUMR_idPessoasFuncionais = nUMR_idPessoasFuncionais;
	}
	public String getDESC_competencia() {
		return DESC_competencia;
	}
	public void setDESC_competencia(String dESC_competencia) {
		DESC_competencia = dESC_competencia;
	}
	public BigDecimal getVALR_contribuicaoPrevidenciaria() {
		return VALR_contribuicaoPrevidenciaria;
	}
	public void setVALR_contribuicaoPrevidenciaria(BigDecimal vALR_contribuicaoPrevidenciaria) {
		VALR_contribuicaoPrevidenciaria = vALR_contribuicaoPrevidenciaria;
	}
	public double getNUMR_aliquotaSegurado() {
		return NUMR_aliquotaSegurado;
	}
	public void setNUMR_aliquotaSegurado(double nUMR_aliquotaSegurado) {
		NUMR_aliquotaSegurado = nUMR_aliquotaSegurado;
	}
	public BigDecimal getVALR_contribSegurado() {
		return VALR_contribSegurado;
	}
	public void setVALR_contribSegurado(BigDecimal vALR_contribSegurado) {
		VALR_contribSegurado = vALR_contribSegurado;
	}
	public BigDecimal getVALR_devolSegurado() {
		return VALR_devolSegurado;
	}
	public void setVALR_devolSegurado(BigDecimal vALR_devolSegurado) {
		VALR_devolSegurado = vALR_devolSegurado;
	}
	public double getNUMR_aliquotaPatronal() {
		return NUMR_aliquotaPatronal;
	}
	public void setNUMR_aliquotaPatronal(double nUMR_aliquotaPatronal) {
		NUMR_aliquotaPatronal = nUMR_aliquotaPatronal;
	}
	public BigDecimal getVALR_contribPatronal() {
		return VALR_contribPatronal;
	}
	public void setVALR_contribPatronal(BigDecimal vALR_contribPatronal) {
		VALR_contribPatronal = vALR_contribPatronal;
	}
	public BigDecimal getVALR_devolPatronal() {
		return VALR_devolPatronal;
	}
	public void setVALR_devolPatronal(BigDecimal vALR_devolPatronal) {
		VALR_devolPatronal = vALR_devolPatronal;
	}
	public BigDecimal getVALR_indice() {
		return VALR_indice;
	}
	public void setVALR_indice(BigDecimal vALR_indice) {
		VALR_indice = vALR_indice;
	}
	public BigDecimal getVALR_remuneracaoBase() {
		return VALR_remuneracaoBase;
	}
	public void setVALR_remuneracaoBase(BigDecimal vALR_remuneracaoBase) {
		VALR_remuneracaoBase = vALR_remuneracaoBase;
	}
	public BigDecimal getVALR_remuneracaoMedia() {
		return VALR_remuneracaoMedia;
	}
	public void setVALR_remuneracaoMedia(BigDecimal vALR_remuneracaoMedia) {
		VALR_remuneracaoMedia = vALR_remuneracaoMedia;
	}
	public BigDecimal getVALR_oitentaMaiores() {
		return VALR_oitentaMaiores;
	}
	public void setVALR_oitentaMaiores(BigDecimal vALR_oitentaMaiores) {
		VALR_oitentaMaiores = vALR_oitentaMaiores;
	}
	public BigDecimal getVALR_jurosSegurado() {
		return VALR_jurosSegurado;
	}
	public void setVALR_jurosSegurado(BigDecimal vALR_jurosSegurado) {
		VALR_jurosSegurado = vALR_jurosSegurado;
	}
	public BigDecimal getVALR_multaSegurado() {
		return VALR_multaSegurado;
	}
	public void setVALR_multaSegurado(BigDecimal vALR_multaSegurado) {
		VALR_multaSegurado = vALR_multaSegurado;
	}
	public BigDecimal getVALR_jurosPatronal() {
		return VALR_jurosPatronal;
	}
	public void setVALR_jurosPatronal(BigDecimal vALR_jurosPatronal) {
		VALR_jurosPatronal = vALR_jurosPatronal;
	}
	public BigDecimal getVALR_multaPatronal() {
		return VALR_multaPatronal;
	}
	public void setVALR_multaPatronal(BigDecimal vALR_multaPatronal) {
		VALR_multaPatronal = vALR_multaPatronal;
	}
	public boolean isBOL_contribuicaoInvalida() {
		return BOL_contribuicaoInvalida;
	}
	public void setBOL_contribuicaoInvalida(boolean bOL_contribuicaoInvalida) {
		BOL_contribuicaoInvalida = bOL_contribuicaoInvalida;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (BOL_contribuicaoInvalida ? 1231 : 1237);
		result = prime * result + ((DESC_competencia == null) ? 0 : DESC_competencia.hashCode());
		long temp;
		temp = Double.doubleToLongBits(NUMR_aliquotaPatronal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(NUMR_aliquotaSegurado);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((NUMR_idPessoasFuncionais == null) ? 0 : NUMR_idPessoasFuncionais.hashCode());
		result = prime * result + ((VALR_contribPatronal == null) ? 0 : VALR_contribPatronal.hashCode());
		result = prime * result + ((VALR_contribSegurado == null) ? 0 : VALR_contribSegurado.hashCode());
		result = prime * result
				+ ((VALR_contribuicaoPrevidenciaria == null) ? 0 : VALR_contribuicaoPrevidenciaria.hashCode());
		result = prime * result + ((VALR_devolPatronal == null) ? 0 : VALR_devolPatronal.hashCode());
		result = prime * result + ((VALR_devolSegurado == null) ? 0 : VALR_devolSegurado.hashCode());
		result = prime * result + ((VALR_jurosPatronal == null) ? 0 : VALR_jurosPatronal.hashCode());
		result = prime * result + ((VALR_jurosSegurado == null) ? 0 : VALR_jurosSegurado.hashCode());
		result = prime * result + ((VALR_multaPatronal == null) ? 0 : VALR_multaPatronal.hashCode());
		result = prime * result + ((VALR_multaSegurado == null) ? 0 : VALR_multaSegurado.hashCode());
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
		ContribuicaoDto other = (ContribuicaoDto) obj;
		if (BOL_contribuicaoInvalida != other.BOL_contribuicaoInvalida)
			return false;
		if (DESC_competencia == null) {
			if (other.DESC_competencia != null)
				return false;
		} else if (!DESC_competencia.equals(other.DESC_competencia))
			return false;
		if (Double.doubleToLongBits(NUMR_aliquotaPatronal) != Double.doubleToLongBits(other.NUMR_aliquotaPatronal))
			return false;
		if (Double.doubleToLongBits(NUMR_aliquotaSegurado) != Double.doubleToLongBits(other.NUMR_aliquotaSegurado))
			return false;
		if (NUMR_idPessoasFuncionais == null) {
			if (other.NUMR_idPessoasFuncionais != null)
				return false;
		} else if (!NUMR_idPessoasFuncionais.equals(other.NUMR_idPessoasFuncionais))
			return false;
		if (VALR_contribPatronal == null) {
			if (other.VALR_contribPatronal != null)
				return false;
		} else if (!VALR_contribPatronal.equals(other.VALR_contribPatronal))
			return false;
		if (VALR_contribSegurado == null) {
			if (other.VALR_contribSegurado != null)
				return false;
		} else if (!VALR_contribSegurado.equals(other.VALR_contribSegurado))
			return false;
		if (VALR_contribuicaoPrevidenciaria == null) {
			if (other.VALR_contribuicaoPrevidenciaria != null)
				return false;
		} else if (!VALR_contribuicaoPrevidenciaria.equals(other.VALR_contribuicaoPrevidenciaria))
			return false;
		if (VALR_devolPatronal == null) {
			if (other.VALR_devolPatronal != null)
				return false;
		} else if (!VALR_devolPatronal.equals(other.VALR_devolPatronal))
			return false;
		if (VALR_devolSegurado == null) {
			if (other.VALR_devolSegurado != null)
				return false;
		} else if (!VALR_devolSegurado.equals(other.VALR_devolSegurado))
			return false;
		if (VALR_jurosPatronal == null) {
			if (other.VALR_jurosPatronal != null)
				return false;
		} else if (!VALR_jurosPatronal.equals(other.VALR_jurosPatronal))
			return false;
		if (VALR_jurosSegurado == null) {
			if (other.VALR_jurosSegurado != null)
				return false;
		} else if (!VALR_jurosSegurado.equals(other.VALR_jurosSegurado))
			return false;
		if (VALR_multaPatronal == null) {
			if (other.VALR_multaPatronal != null)
				return false;
		} else if (!VALR_multaPatronal.equals(other.VALR_multaPatronal))
			return false;
		if (VALR_multaSegurado == null) {
			if (other.VALR_multaSegurado != null)
				return false;
		} else if (!VALR_multaSegurado.equals(other.VALR_multaSegurado))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ID Funcional: "+NUMR_idPessoasFuncionais.getNUMG_idDoObjeto()+" - "+
				"Competência: "+DESC_competencia+" - "+
				"Base de Contribuição: "+VALR_contribuicaoPrevidenciaria+" - "+
				"Segurado: "+VALR_contribSegurado+" - "+
				"Alíquota Segurado: "+NUMR_aliquotaSegurado+" - "+
				"Patronal: "+VALR_contribPatronal+" - "+
				"Alíquota Patronal: "+NUMR_aliquotaPatronal;
	}
	
	
}
