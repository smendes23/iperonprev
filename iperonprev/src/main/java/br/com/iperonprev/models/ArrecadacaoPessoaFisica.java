package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.iperonprev.constantes.TipoGuiaEnum;

@Entity
public class ArrecadacaoPessoaFisica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NUMG_idDoObjeto = 0;
	private String DESC_codigoBarras;
	@ManyToOne
	private PessoasFuncionais REL_funcional;
	private String DESC_competencia;
	private Date DATA_vencimento;
	@Temporal(TemporalType.DATE)
	private Calendar DATA_emissao = Calendar.getInstance();
	@Enumerated(EnumType.STRING)
	private TipoGuiaEnum ENUM_tipoGuia;
	private BigDecimal VLR_contribuicao = BigDecimal.ZERO;
	private BigDecimal VLR_juros = BigDecimal.ZERO;
	private BigDecimal VLR_total= BigDecimal.ZERO;
	private BigDecimal VLR_multa= BigDecimal.ZERO;
	private boolean FLAG_decimoTerceiro;
	private boolean FLAG_realizado;
	@Lob
	private String DESC_observacao;
	@ManyToOne
	private FundoPrevidenciario REL_fundoPrevidenciario;
	private transient String DESC_codigoBarrasFormatado;
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
	public PessoasFuncionais getREL_funcional() {
		return REL_funcional;
	}
	public void setREL_funcional(PessoasFuncionais rEL_funcional) {
		REL_funcional = rEL_funcional;
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
	public TipoGuiaEnum getENUM_tipoGuia() {
		return ENUM_tipoGuia;
	}
	public void setENUM_tipoGuia(TipoGuiaEnum eNUM_tipoGuia) {
		ENUM_tipoGuia = eNUM_tipoGuia;
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
		result = prime * result + ((DATA_emissao == null) ? 0 : DATA_emissao.hashCode());
		result = prime * result + ((DATA_vencimento == null) ? 0 : DATA_vencimento.hashCode());
		result = prime * result + ((DESC_codigoBarras == null) ? 0 : DESC_codigoBarras.hashCode());
		result = prime * result + ((DESC_competencia == null) ? 0 : DESC_competencia.hashCode());
		result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
		result = prime * result + ((ENUM_tipoGuia == null) ? 0 : ENUM_tipoGuia.hashCode());
		result = prime * result + (FLAG_decimoTerceiro ? 1231 : 1237);
		result = prime * result + (FLAG_realizado ? 1231 : 1237);
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((REL_funcional == null) ? 0 : REL_funcional.hashCode());
		result = prime * result + ((REL_fundoPrevidenciario == null) ? 0 : REL_fundoPrevidenciario.hashCode());
		result = prime * result + ((VLR_contribuicao == null) ? 0 : VLR_contribuicao.hashCode());
		result = prime * result + ((VLR_juros == null) ? 0 : VLR_juros.hashCode());
		result = prime * result + ((VLR_multa == null) ? 0 : VLR_multa.hashCode());
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
		ArrecadacaoPessoaFisica other = (ArrecadacaoPessoaFisica) obj;
		if (DATA_emissao == null) {
			if (other.DATA_emissao != null)
				return false;
		} else if (!DATA_emissao.equals(other.DATA_emissao))
			return false;
		if (DATA_vencimento == null) {
			if (other.DATA_vencimento != null)
				return false;
		} else if (!DATA_vencimento.equals(other.DATA_vencimento))
			return false;
		if (DESC_codigoBarras == null) {
			if (other.DESC_codigoBarras != null)
				return false;
		} else if (!DESC_codigoBarras.equals(other.DESC_codigoBarras))
			return false;
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
		if (ENUM_tipoGuia != other.ENUM_tipoGuia)
			return false;
		if (FLAG_decimoTerceiro != other.FLAG_decimoTerceiro)
			return false;
		if (FLAG_realizado != other.FLAG_realizado)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (REL_funcional == null) {
			if (other.REL_funcional != null)
				return false;
		} else if (!REL_funcional.equals(other.REL_funcional))
			return false;
		if (REL_fundoPrevidenciario == null) {
			if (other.REL_fundoPrevidenciario != null)
				return false;
		} else if (!REL_fundoPrevidenciario.equals(other.REL_fundoPrevidenciario))
			return false;
		if (VLR_contribuicao == null) {
			if (other.VLR_contribuicao != null)
				return false;
		} else if (!VLR_contribuicao.equals(other.VLR_contribuicao))
			return false;
		if (VLR_juros == null) {
			if (other.VLR_juros != null)
				return false;
		} else if (!VLR_juros.equals(other.VLR_juros))
			return false;
		if (VLR_multa == null) {
			if (other.VLR_multa != null)
				return false;
		} else if (!VLR_multa.equals(other.VLR_multa))
			return false;
		if (VLR_total == null) {
			if (other.VLR_total != null)
				return false;
		} else if (!VLR_total.equals(other.VLR_total))
			return false;
		return true;
	}
	
}
