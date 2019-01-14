package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LicencaMaternidade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne
	private PessoasFuncionais NUMR_pessoafuncional;
	private String NUMR_processo;
	private Date DATA_analiseProcesso;
	private Date DATA_concessao;
	private Date DATA_revisao;
	private Date DATA_cessacao;
	private int NUMR_baseCalculo;
	private BigDecimal NUMR_valorBeneficio;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_pessoafuncional() {
		return NUMR_pessoafuncional;
	}
	public void setNUMR_pessoafuncional(PessoasFuncionais nUMR_pessoafuncional) {
		NUMR_pessoafuncional = nUMR_pessoafuncional;
	}
	public String getNUMR_processo() {
		return NUMR_processo;
	}
	public void setNUMR_processo(String nUMR_processo) {
		NUMR_processo = nUMR_processo;
	}
	public Date getDATA_analiseProcesso() {
		return DATA_analiseProcesso;
	}
	public void setDATA_analiseProcesso(Date dATA_analiseProcesso) {
		DATA_analiseProcesso = dATA_analiseProcesso;
	}
	public Date getDATA_concessao() {
		return DATA_concessao;
	}
	public void setDATA_concessao(Date dATA_concessao) {
		DATA_concessao = dATA_concessao;
	}
	public Date getDATA_revisao() {
		return DATA_revisao;
	}
	public void setDATA_revisao(Date dATA_revisao) {
		DATA_revisao = dATA_revisao;
	}
	public Date getDATA_cessacao() {
		return DATA_cessacao;
	}
	public void setDATA_cessacao(Date dATA_cessacao) {
		DATA_cessacao = dATA_cessacao;
	}
	public int getNUMR_baseCalculo() {
		return NUMR_baseCalculo;
	}
	public void setNUMR_baseCalculo(int nUMR_baseCalculo) {
		NUMR_baseCalculo = nUMR_baseCalculo;
	}
	public BigDecimal getNUMR_valorBeneficio() {
		return NUMR_valorBeneficio;
	}
	public void setNUMR_valorBeneficio(BigDecimal nUMR_valorBeneficio) {
		NUMR_valorBeneficio = nUMR_valorBeneficio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_analiseProcesso == null) ? 0 : DATA_analiseProcesso.hashCode());
		result = prime * result + ((DATA_cessacao == null) ? 0 : DATA_cessacao.hashCode());
		result = prime * result + ((DATA_concessao == null) ? 0 : DATA_concessao.hashCode());
		result = prime * result + ((DATA_revisao == null) ? 0 : DATA_revisao.hashCode());
		result = prime * result + NUMR_baseCalculo;
		result = prime * result + ((NUMR_pessoafuncional == null) ? 0 : NUMR_pessoafuncional.hashCode());
		result = prime * result + ((NUMR_processo == null) ? 0 : NUMR_processo.hashCode());
		result = prime * result + ((NUMR_valorBeneficio == null) ? 0 : NUMR_valorBeneficio.hashCode());
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
		LicencaMaternidade other = (LicencaMaternidade) obj;
		if (DATA_analiseProcesso == null) {
			if (other.DATA_analiseProcesso != null)
				return false;
		} else if (!DATA_analiseProcesso.equals(other.DATA_analiseProcesso))
			return false;
		if (DATA_cessacao == null) {
			if (other.DATA_cessacao != null)
				return false;
		} else if (!DATA_cessacao.equals(other.DATA_cessacao))
			return false;
		if (DATA_concessao == null) {
			if (other.DATA_concessao != null)
				return false;
		} else if (!DATA_concessao.equals(other.DATA_concessao))
			return false;
		if (DATA_revisao == null) {
			if (other.DATA_revisao != null)
				return false;
		} else if (!DATA_revisao.equals(other.DATA_revisao))
			return false;
		if (NUMR_baseCalculo != other.NUMR_baseCalculo)
			return false;
		if (NUMR_pessoafuncional == null) {
			if (other.NUMR_pessoafuncional != null)
				return false;
		} else if (!NUMR_pessoafuncional.equals(other.NUMR_pessoafuncional))
			return false;
		if (NUMR_processo == null) {
			if (other.NUMR_processo != null)
				return false;
		} else if (!NUMR_processo.equals(other.NUMR_processo))
			return false;
		if (NUMR_valorBeneficio == null) {
			if (other.NUMR_valorBeneficio != null)
				return false;
		} else if (!NUMR_valorBeneficio.equals(other.NUMR_valorBeneficio))
			return false;
		return true;
	}
	
	
}
