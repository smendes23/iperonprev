package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.FundoPrevidenciarioEnum;

@Entity
public class Financeiro implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int NUMG_idDoObjeto;
	@ManyToOne
	private PessoasFuncionais NUMR_idDoObjetoFuncional;
	private String NUMR_competencia;
	private int NUMR_folha;
	@Enumerated(EnumType.STRING)
	private DecisaoEnum FLAG_decimoTerceiro;
	private BigDecimal VALR_remunBruta;
	private BigDecimal VALR_remunLiquida;
	private BigDecimal VALR_contribuicaoIperon;
	@Enumerated(EnumType.STRING)
	private transient FundoPrevidenciarioEnum ENUM_fundoPrevidenciario;
	private double NUMR_aliquotaSegurado;
	private BigDecimal VALR_contribSegurado;
	private BigDecimal VALR_devolSegurado;
	private double NUMR_aliquotaPatronal;
	private BigDecimal VALR_contribPatronal;
	private BigDecimal VALR_devolPatronal;
	private transient BigDecimal VALR_indice;
	private transient BigDecimal VALR_remuneracaoBase;
	private transient BigDecimal VALR_remuneracaoMedia;
	private transient BigDecimal VALR_oitentaMaiores;
	private BigDecimal VALR_jurosSegurado;
	private BigDecimal VALR_multaSegurado;
	private BigDecimal VALR_jurosPatronal;
	private BigDecimal VALR_multaPatronal;
	private boolean BOL_contribuicaoInvalida;
	@Version
	private int NUMR_versao;
	
	public boolean isBOL_contribuicaoInvalida() {
		return BOL_contribuicaoInvalida;
	}
	public void setBOL_contribuicaoInvalida(boolean bOL_contribuicaoInvalida) {
		BOL_contribuicaoInvalida = bOL_contribuicaoInvalida;
	}
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_idDoObjetoFuncional() {
		return NUMR_idDoObjetoFuncional;
	}
	public void setNUMR_idDoObjetoFuncional(PessoasFuncionais nUMR_idDoObjetoFuncional) {
		NUMR_idDoObjetoFuncional = nUMR_idDoObjetoFuncional;
	}
	public String getNUMR_competencia() {
		return NUMR_competencia;
	}
	public void setNUMR_competencia(String nUMR_competencia) {
		NUMR_competencia = nUMR_competencia;
	}
	public int getNUMR_folha() {
		return NUMR_folha;
	}
	public void setNUMR_folha(int nUMR_folha) {
		NUMR_folha = nUMR_folha;
	}
	public DecisaoEnum getFLAG_decimoTerceiro() {
		return FLAG_decimoTerceiro;
	}
	public void setFLAG_decimoTerceiro(DecisaoEnum fLAG_decimoTerceiro) {
		FLAG_decimoTerceiro = fLAG_decimoTerceiro;
	}
	public BigDecimal getVALR_remunBruta() {
		return VALR_remunBruta;
	}
	public void setVALR_remunBruta(BigDecimal vALR_remunBruta) {
		VALR_remunBruta = vALR_remunBruta;
	}
	public BigDecimal getVALR_remunLiquida() {
		return VALR_remunLiquida;
	}
	public void setVALR_remunLiquida(BigDecimal vALR_remunLiquida) {
		VALR_remunLiquida = vALR_remunLiquida;
	}
	public BigDecimal getVALR_contribuicaoIperon() {
		return VALR_contribuicaoIperon;
	}
	public void setVALR_contribuicaoIperon(BigDecimal vALR_contribuicaoIperon) {
		VALR_contribuicaoIperon = vALR_contribuicaoIperon;
	}
	public FundoPrevidenciarioEnum getENUM_fundoPrevidenciario() {
		return ENUM_fundoPrevidenciario;
	}
	public void setENUM_fundoPrevidenciario(FundoPrevidenciarioEnum eNUM_fundoPrevidenciario) {
		ENUM_fundoPrevidenciario = eNUM_fundoPrevidenciario;
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
	public int getNUMR_versao() {
		return NUMR_versao;
	}
	public void setNUMR_versao(int nUMR_versao) {
		NUMR_versao = nUMR_versao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (BOL_contribuicaoInvalida ? 1231 : 1237);
		result = prime * result + ((ENUM_fundoPrevidenciario == null) ? 0 : ENUM_fundoPrevidenciario.hashCode());
		result = prime * result + ((FLAG_decimoTerceiro == null) ? 0 : FLAG_decimoTerceiro.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		long temp;
		temp = Double.doubleToLongBits(NUMR_aliquotaPatronal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(NUMR_aliquotaSegurado);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((NUMR_competencia == null) ? 0 : NUMR_competencia.hashCode());
		result = prime * result + NUMR_folha;
		result = prime * result + ((NUMR_idDoObjetoFuncional == null) ? 0 : NUMR_idDoObjetoFuncional.hashCode());
		result = prime * result + NUMR_versao;
		result = prime * result + ((VALR_contribPatronal == null) ? 0 : VALR_contribPatronal.hashCode());
		result = prime * result + ((VALR_contribSegurado == null) ? 0 : VALR_contribSegurado.hashCode());
		result = prime * result + ((VALR_contribuicaoIperon == null) ? 0 : VALR_contribuicaoIperon.hashCode());
		result = prime * result + ((VALR_devolPatronal == null) ? 0 : VALR_devolPatronal.hashCode());
		result = prime * result + ((VALR_devolSegurado == null) ? 0 : VALR_devolSegurado.hashCode());
		result = prime * result + ((VALR_jurosPatronal == null) ? 0 : VALR_jurosPatronal.hashCode());
		result = prime * result + ((VALR_jurosSegurado == null) ? 0 : VALR_jurosSegurado.hashCode());
		result = prime * result + ((VALR_multaPatronal == null) ? 0 : VALR_multaPatronal.hashCode());
		result = prime * result + ((VALR_multaSegurado == null) ? 0 : VALR_multaSegurado.hashCode());
		result = prime * result + ((VALR_remunBruta == null) ? 0 : VALR_remunBruta.hashCode());
		result = prime * result + ((VALR_remunLiquida == null) ? 0 : VALR_remunLiquida.hashCode());
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
		Financeiro other = (Financeiro) obj;
		if (BOL_contribuicaoInvalida != other.BOL_contribuicaoInvalida)
			return false;
		if (ENUM_fundoPrevidenciario != other.ENUM_fundoPrevidenciario)
			return false;
		if (FLAG_decimoTerceiro != other.FLAG_decimoTerceiro)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (Double.doubleToLongBits(NUMR_aliquotaPatronal) != Double.doubleToLongBits(other.NUMR_aliquotaPatronal))
			return false;
		if (Double.doubleToLongBits(NUMR_aliquotaSegurado) != Double.doubleToLongBits(other.NUMR_aliquotaSegurado))
			return false;
		if (NUMR_competencia == null) {
			if (other.NUMR_competencia != null)
				return false;
		} else if (!NUMR_competencia.equals(other.NUMR_competencia))
			return false;
		if (NUMR_folha != other.NUMR_folha)
			return false;
		if (NUMR_idDoObjetoFuncional == null) {
			if (other.NUMR_idDoObjetoFuncional != null)
				return false;
		} else if (!NUMR_idDoObjetoFuncional.equals(other.NUMR_idDoObjetoFuncional))
			return false;
		if (NUMR_versao != other.NUMR_versao)
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
		if (VALR_contribuicaoIperon == null) {
			if (other.VALR_contribuicaoIperon != null)
				return false;
		} else if (!VALR_contribuicaoIperon.equals(other.VALR_contribuicaoIperon))
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
		if (VALR_remunBruta == null) {
			if (other.VALR_remunBruta != null)
				return false;
		} else if (!VALR_remunBruta.equals(other.VALR_remunBruta))
			return false;
		if (VALR_remunLiquida == null) {
			if (other.VALR_remunLiquida != null)
				return false;
		} else if (!VALR_remunLiquida.equals(other.VALR_remunLiquida))
			return false;
		return true;
	}
}
