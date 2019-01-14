package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DeficePessoaJuridica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NUMG_idDoObjeto = 0;
	private String DESC_codigoBarras;
	@ManyToOne
	private Orgaos REL_orgao;
	private String DESC_competencia;
	private Date DATA_vencimento;
	@Temporal(TemporalType.DATE)
	private Calendar DATA_emissao = Calendar.getInstance();
	private BigDecimal VLR_contribuicao = BigDecimal.ZERO;
	private BigDecimal VLR_juros = BigDecimal.ZERO;
	private BigDecimal VLR_total= BigDecimal.ZERO;
	private BigDecimal VLR_multa= BigDecimal.ZERO;
	@Lob
	private String DESC_observacao;
	@ManyToOne
	private FundoPrevidenciario REL_fundoPrevidenciario;
	private transient String DESC_codigoBarrasFormatado;
	private boolean FLAG_decimoTerceiro;
	private boolean FLAG_realizado;
	public boolean isFLAG_decimoTerceiro() {
		return FLAG_decimoTerceiro;
	}
	public void setFLAG_decimoTerceiro(boolean fLAG_decimoTerceiro) {
		FLAG_decimoTerceiro = fLAG_decimoTerceiro;
	}
	public boolean isFLAG_realizado() {
		return FLAG_realizado;
	}
	public void setFLAG_realizado(boolean fLAG_realizado) {
		FLAG_realizado = fLAG_realizado;
	}
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_codigoBarras() {
		return DESC_codigoBarras;
	}
	public void setDESC_codigoBarras(String dESC_codigoBarras) {
		DESC_codigoBarras = dESC_codigoBarras;
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
	public Date getDATA_vencimento() {
		return DATA_vencimento;
	}
	public void setDATA_vencimento(Date dATA_vencimento) {
		DATA_vencimento = dATA_vencimento;
	}
	public Calendar getDATA_emissao() {
		return DATA_emissao;
	}
	public void setDATA_emissao(Calendar dATA_emissao) {
		DATA_emissao = dATA_emissao;
	}
	public BigDecimal getVLR_contribuicao() {
		return VLR_contribuicao;
	}
	public void setVLR_contribuicao(BigDecimal vLR_contribuicao) {
		VLR_contribuicao = vLR_contribuicao;
	}
	public BigDecimal getVLR_juros() {
		return VLR_juros;
	}
	public void setVLR_juros(BigDecimal vLR_juros) {
		VLR_juros = vLR_juros;
	}
	public BigDecimal getVLR_total() {
		return VLR_total;
	}
	public void setVLR_total(BigDecimal vLR_total) {
		VLR_total = vLR_total;
	}
	public BigDecimal getVLR_multa() {
		return VLR_multa;
	}
	public void setVLR_multa(BigDecimal vLR_multa) {
		VLR_multa = vLR_multa;
	}
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
	}
	public FundoPrevidenciario getREL_fundoPrevidenciario() {
		return REL_fundoPrevidenciario;
	}
	public void setREL_fundoPrevidenciario(FundoPrevidenciario rEL_fundoPrevidenciario) {
		REL_fundoPrevidenciario = rEL_fundoPrevidenciario;
	}
	public String getDESC_codigoBarrasFormatado() {
		return DESC_codigoBarrasFormatado;
	}
	public void setDESC_codigoBarrasFormatado(String dESC_codigoBarrasFormatado) {
		DESC_codigoBarrasFormatado = dESC_codigoBarrasFormatado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + NUMG_idDoObjeto;
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
		DeficePessoaJuridica other = (DeficePessoaJuridica) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}
}
