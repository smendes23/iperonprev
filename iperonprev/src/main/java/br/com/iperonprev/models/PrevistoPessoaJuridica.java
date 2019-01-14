package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.iperonprev.constantes.FundoPrevidenciarioEnum;
import br.com.iperonprev.constantes.TipoGuiaEnum;

@Entity
public class PrevistoPessoaJuridica implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NUMG_idDoObjeto = 0;
	@ManyToOne
	private Orgaos REL_orgao;
	private String DESC_competencia;
	@Enumerated(EnumType.STRING)
	private FundoPrevidenciarioEnum ENUM_fundoPrevidenciario;
	@Enumerated(EnumType.STRING)
	private TipoGuiaEnum ENUM_tipoSegurado;
	private BigDecimal VLR_previsto = BigDecimal.ZERO;
	private BigDecimal VLR_salarioMaternidade = BigDecimal.ZERO;
	private BigDecimal VLR_auxilioDoenca = BigDecimal.ZERO;
	@Transient
	private BigDecimal VLR_total = BigDecimal.ZERO;
	@Lob
	private String DESC_observacao;
	@Version
	private int NUMR_versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Orgaos getREL_orgao() {
		return REL_orgao;
	}
	public void setREL_orgao(Orgaos rEL_orgao) {
		REL_orgao = rEL_orgao;
	}
	public String getDESC_competencia() {
		return DESC_competencia;
	}
	public void setDESC_competencia(String dESC_competencia) {
		DESC_competencia = dESC_competencia;
	}
	public FundoPrevidenciarioEnum getENUM_fundoPrevidenciario() {
		return ENUM_fundoPrevidenciario;
	}
	public void setENUM_fundoPrevidenciario(FundoPrevidenciarioEnum eNUM_fundoPrevidenciario) {
		ENUM_fundoPrevidenciario = eNUM_fundoPrevidenciario;
	}
	public TipoGuiaEnum getENUM_tipoSegurado() {
		return ENUM_tipoSegurado;
	}
	public void setENUM_tipoSegurado(TipoGuiaEnum eNUM_tipoSegurado) {
		ENUM_tipoSegurado = eNUM_tipoSegurado;
	}
	public BigDecimal getVLR_previsto() {
		return VLR_previsto;
	}
	public void setVLR_previsto(BigDecimal vLR_previsto) {
		VLR_previsto = vLR_previsto;
	}
	public BigDecimal getVLR_salarioMaternidade() {
		return VLR_salarioMaternidade;
	}
	public void setVLR_salarioMaternidade(BigDecimal vLR_salarioMaternidade) {
		VLR_salarioMaternidade = vLR_salarioMaternidade;
	}
	public BigDecimal getVLR_auxilioDoenca() {
		return VLR_auxilioDoenca;
	}
	public void setVLR_auxilioDoenca(BigDecimal vLR_auxilioDoenca) {
		VLR_auxilioDoenca = vLR_auxilioDoenca;
	}
	public BigDecimal getVLR_total() {
		return VLR_total;
	}
	public void setVLR_total(BigDecimal vLR_total) {
		VLR_total = vLR_total;
	}
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
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
		result = prime * result + ((DESC_competencia == null) ? 0 : DESC_competencia.hashCode());
		result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
		result = prime * result + ((ENUM_fundoPrevidenciario == null) ? 0 : ENUM_fundoPrevidenciario.hashCode());
		result = prime * result + ((ENUM_tipoSegurado == null) ? 0 : ENUM_tipoSegurado.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + NUMR_versao;
		result = prime * result + ((REL_orgao == null) ? 0 : REL_orgao.hashCode());
		result = prime * result + ((VLR_auxilioDoenca == null) ? 0 : VLR_auxilioDoenca.hashCode());
		result = prime * result + ((VLR_previsto == null) ? 0 : VLR_previsto.hashCode());
		result = prime * result + ((VLR_salarioMaternidade == null) ? 0 : VLR_salarioMaternidade.hashCode());
		result = prime * result + ((VLR_total == null) ? 0 : VLR_total.hashCode());
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
		PrevistoPessoaJuridica other = (PrevistoPessoaJuridica) obj;
		if (DESC_competencia == null) {
			if (other.DESC_competencia != null)
				return false;
		} else if (!DESC_competencia.equals(other.DESC_competencia))
			return false;
		if (DESC_observacao == null) {
			if (other.DESC_observacao != null)
				return false;
		} else if (!DESC_observacao.equals(other.DESC_observacao))
			return false;
		if (ENUM_fundoPrevidenciario != other.ENUM_fundoPrevidenciario)
			return false;
		if (ENUM_tipoSegurado != other.ENUM_tipoSegurado)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		if (REL_orgao == null) {
			if (other.REL_orgao != null)
				return false;
		} else if (!REL_orgao.equals(other.REL_orgao))
			return false;
		if (VLR_auxilioDoenca == null) {
			if (other.VLR_auxilioDoenca != null)
				return false;
		} else if (!VLR_auxilioDoenca.equals(other.VLR_auxilioDoenca))
			return false;
		if (VLR_previsto == null) {
			if (other.VLR_previsto != null)
				return false;
		} else if (!VLR_previsto.equals(other.VLR_previsto))
			return false;
		if (VLR_salarioMaternidade == null) {
			if (other.VLR_salarioMaternidade != null)
				return false;
		} else if (!VLR_salarioMaternidade.equals(other.VLR_salarioMaternidade))
			return false;
		if (VLR_total == null) {
			if (other.VLR_total != null)
				return false;
		} else if (!VLR_total.equals(other.VLR_total))
			return false;
		return true;
	}
	
	
	
	
}
