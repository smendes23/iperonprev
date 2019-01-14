package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RemessaPessoaJuridica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NUMG_idDoObjeto = 0;
	private String DESC_codigoBarras;
	@OneToOne
	private Orgaos REL_orgao;
	private String DESC_competencia;
	private Date DATA_vencimento;
	@Temporal(TemporalType.DATE)
	private Calendar DATA_emissao = Calendar.getInstance();
	private BigDecimal VLR_segurado = BigDecimal.ZERO;
	private BigDecimal VLR_jurosSegurado= BigDecimal.ZERO;
	private BigDecimal VLR_multaSegurado= BigDecimal.ZERO;
	private BigDecimal VLR_patronal= BigDecimal.ZERO;
	private BigDecimal VLR_jurosPatronal= BigDecimal.ZERO;
	private BigDecimal VLR_multaPatronal= BigDecimal.ZERO;
	@Lob
	private String DESC_observacao;
	@ManyToOne
	private FundoPrevidenciario REL_fundoPrevidenciario;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	private List<DeducaoArrecadacaoPJ> REL_deducao = new ArrayList<>();
	private transient String DESC_codigoBarrasFormatado;
	/* 0 = Previsto, 1=Realizado*/
	private int BOL_pago;
	
	public int getBOL_pago() {
		return BOL_pago;
	}
	public void setBOL_pago(int bOL_pago) {
		BOL_pago = bOL_pago;
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
	public BigDecimal getVLR_segurado() {
		return VLR_segurado;
	}
	public void setVLR_segurado(BigDecimal vLR_segurado) {
		VLR_segurado = vLR_segurado;
	}
	public BigDecimal getVLR_jurosSegurado() {
		return VLR_jurosSegurado;
	}
	public void setVLR_jurosSegurado(BigDecimal vLR_jurosSegurado) {
		VLR_jurosSegurado = vLR_jurosSegurado;
	}
	public BigDecimal getVLR_multaSegurado() {
		return VLR_multaSegurado;
	}
	public void setVLR_multaSegurado(BigDecimal vLR_multaSegurado) {
		VLR_multaSegurado = vLR_multaSegurado;
	}
	public BigDecimal getVLR_patronal() {
		return VLR_patronal;
	}
	public void setVLR_patronal(BigDecimal vLR_patronal) {
		VLR_patronal = vLR_patronal;
	}
	public BigDecimal getVLR_jurosPatronal() {
		return VLR_jurosPatronal;
	}
	public void setVLR_jurosPatronal(BigDecimal vLR_jurosPatronal) {
		VLR_jurosPatronal = vLR_jurosPatronal;
	}
	public BigDecimal getVLR_multaPatronal() {
		return VLR_multaPatronal;
	}
	public void setVLR_multaPatronal(BigDecimal vLR_multaPatronal) {
		VLR_multaPatronal = vLR_multaPatronal;
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
		RemessaPessoaJuridica other = (RemessaPessoaJuridica) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}
}
