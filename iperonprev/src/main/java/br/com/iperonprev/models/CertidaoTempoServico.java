package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class CertidaoTempoServico implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private int NUMR_ano;
	private int NUMR_certidao;
	@ManyToOne
	private PessoasFuncionais REL_funcional = new PessoasFuncionais();
	private String DESC_numeroCertidao = new String();
	@ManyToOne
	private Orgaos REL_orgaoExpedidor = new Orgaos();
	private String DESC_lotacao = new String();
	private transient List<Averbacao> LIST_Averbacao = new ArrayList<>();
	@Lob
	private String DESC_anotacoes = new String();
	private String DESC_fonteInformacao = new String();
	private Date DATA_inicioPeriodoApuracao;
	private Date DATA_fimPeriodoApuracao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public int getNUMR_ano() {
		return NUMR_ano;
	}
	public void setNUMR_ano(int nUMR_ano) {
		NUMR_ano = nUMR_ano;
	}
	public int getNUMR_certidao() {
		return NUMR_certidao;
	}
	public void setNUMR_certidao(int nUMR_certidao) {
		NUMR_certidao = nUMR_certidao;
	}
	public PessoasFuncionais getREL_funcional() {
		return REL_funcional;
	}
	public void setREL_funcional(PessoasFuncionais rEL_funcional) {
		REL_funcional = rEL_funcional;
	}
	public String getDESC_numeroCertidao() {
		return DESC_numeroCertidao;
	}
	public void setDESC_numeroCertidao(String dESC_numeroCertidao) {
		DESC_numeroCertidao = dESC_numeroCertidao;
	}
	public Orgaos getREL_orgaoExpedidor() {
		return REL_orgaoExpedidor;
	}
	public void setREL_orgaoExpedidor(Orgaos rEL_orgaoExpedidor) {
		REL_orgaoExpedidor = rEL_orgaoExpedidor;
	}
	public String getDESC_lotacao() {
		return DESC_lotacao;
	}
	public void setDESC_lotacao(String dESC_lotacao) {
		DESC_lotacao = dESC_lotacao;
	}
	public List<Averbacao> getLIST_Averbacao() {
		return LIST_Averbacao;
	}
	public void setLIST_Averbacao(List<Averbacao> lIST_Averbacao) {
		LIST_Averbacao = lIST_Averbacao;
	}
	public String getDESC_anotacoes() {
		return DESC_anotacoes;
	}
	public void setDESC_anotacoes(String dESC_anotacoes) {
		DESC_anotacoes = dESC_anotacoes;
	}
	public String getDESC_fonteInformacao() {
		return DESC_fonteInformacao;
	}
	public void setDESC_fonteInformacao(String dESC_fonteInformacao) {
		DESC_fonteInformacao = dESC_fonteInformacao;
	}
	public Date getDATA_inicioPeriodoApuracao() {
		return DATA_inicioPeriodoApuracao;
	}
	public void setDATA_inicioPeriodoApuracao(Date dATA_inicioPeriodoApuracao) {
		DATA_inicioPeriodoApuracao = dATA_inicioPeriodoApuracao;
	}
	public Date getDATA_fimPeriodoApuracao() {
		return DATA_fimPeriodoApuracao;
	}
	public void setDATA_fimPeriodoApuracao(Date dATA_fimPeriodoApuracao) {
		DATA_fimPeriodoApuracao = dATA_fimPeriodoApuracao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_fimPeriodoApuracao == null) ? 0 : DATA_fimPeriodoApuracao.hashCode());
		result = prime * result + ((DATA_inicioPeriodoApuracao == null) ? 0 : DATA_inicioPeriodoApuracao.hashCode());
		result = prime * result + ((DESC_anotacoes == null) ? 0 : DESC_anotacoes.hashCode());
		result = prime * result + ((DESC_fonteInformacao == null) ? 0 : DESC_fonteInformacao.hashCode());
		result = prime * result + ((DESC_lotacao == null) ? 0 : DESC_lotacao.hashCode());
		result = prime * result + ((DESC_numeroCertidao == null) ? 0 : DESC_numeroCertidao.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + NUMR_ano;
		result = prime * result + NUMR_certidao;
		result = prime * result + ((REL_funcional == null) ? 0 : REL_funcional.hashCode());
		result = prime * result + ((REL_orgaoExpedidor == null) ? 0 : REL_orgaoExpedidor.hashCode());
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
		CertidaoTempoServico other = (CertidaoTempoServico) obj;
		if (DATA_fimPeriodoApuracao == null) {
			if (other.DATA_fimPeriodoApuracao != null)
				return false;
		} else if (!DATA_fimPeriodoApuracao.equals(other.DATA_fimPeriodoApuracao))
			return false;
		if (DATA_inicioPeriodoApuracao == null) {
			if (other.DATA_inicioPeriodoApuracao != null)
				return false;
		} else if (!DATA_inicioPeriodoApuracao.equals(other.DATA_inicioPeriodoApuracao))
			return false;
		if (DESC_anotacoes == null) {
			if (other.DESC_anotacoes != null)
				return false;
		} else if (!DESC_anotacoes.equals(other.DESC_anotacoes))
			return false;
		if (DESC_fonteInformacao == null) {
			if (other.DESC_fonteInformacao != null)
				return false;
		} else if (!DESC_fonteInformacao.equals(other.DESC_fonteInformacao))
			return false;
		if (DESC_lotacao == null) {
			if (other.DESC_lotacao != null)
				return false;
		} else if (!DESC_lotacao.equals(other.DESC_lotacao))
			return false;
		if (DESC_numeroCertidao == null) {
			if (other.DESC_numeroCertidao != null)
				return false;
		} else if (!DESC_numeroCertidao.equals(other.DESC_numeroCertidao))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_ano != other.NUMR_ano)
			return false;
		if (NUMR_certidao != other.NUMR_certidao)
			return false;
		if (REL_funcional == null) {
			if (other.REL_funcional != null)
				return false;
		} else if (!REL_funcional.equals(other.REL_funcional))
			return false;
		if (REL_orgaoExpedidor == null) {
			if (other.REL_orgaoExpedidor != null)
				return false;
		} else if (!REL_orgaoExpedidor.equals(other.REL_orgaoExpedidor))
			return false;
		return true;
	}
}
