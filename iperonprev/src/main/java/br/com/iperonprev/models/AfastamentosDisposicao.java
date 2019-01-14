package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import br.com.iperonprev.constantes.TipoOnusEnum;

@Entity
public class AfastamentosDisposicao implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne
	private PessoasFuncionais NUMR_idDoObjetoPessoasFuncionais;
	@ManyToOne
	@NotNull(message="Órgão requisitante obrigatório")
	private Orgaos NUMR_idDoObjetoOrgao;
	private String DESC_funcao;
	@Enumerated(EnumType.STRING)
	@NotNull(message="Tipo de Ônus obrigatório")
	private TipoOnusEnum ENUM_tipoOnus;
	@NotNull(message="Data da publicação obrigatória")
	private Date DATA_publicacaoDoe;
	@NotNull(message="DOE obrigatório")
	private String DESC_doe;
	private Date DATA_dataRevogacao;
	private Date DATA_dataRetorno;
	private Date NUMR_revogacaoDoe;
	private String DESC_observacao;
	@Version
	private int versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_idDoObjetoPessoasFuncionais() {
		return NUMR_idDoObjetoPessoasFuncionais;
	}
	public void setNUMR_idDoObjetoPessoasFuncionais(PessoasFuncionais nUMR_idDoObjetoPessoasFuncionais) {
		NUMR_idDoObjetoPessoasFuncionais = nUMR_idDoObjetoPessoasFuncionais;
	}
	public Orgaos getNUMR_idDoObjetoOrgao() {
		return NUMR_idDoObjetoOrgao;
	}
	public void setNUMR_idDoObjetoOrgao(Orgaos nUMR_idDoObjetoOrgao) {
		NUMR_idDoObjetoOrgao = nUMR_idDoObjetoOrgao;
	}
	public String getDESC_funcao() {
		return DESC_funcao;
	}
	public void setDESC_funcao(String dESC_funcao) {
		DESC_funcao = dESC_funcao;
	}
	public TipoOnusEnum getENUM_tipoOnus() {
		return ENUM_tipoOnus;
	}
	public void setENUM_tipoOnus(TipoOnusEnum eNUM_tipoOnus) {
		ENUM_tipoOnus = eNUM_tipoOnus;
	}
	public Date getDATA_publicacaoDoe() {
		return DATA_publicacaoDoe;
	}
	public void setDATA_publicacaoDoe(Date dATA_publicacaoDoe) {
		DATA_publicacaoDoe = dATA_publicacaoDoe;
	}
	public String getDESC_doe() {
		return DESC_doe;
	}
	public void setDESC_doe(String dESC_doe) {
		DESC_doe = dESC_doe;
	}
	public Date getDATA_dataRevogacao() {
		return DATA_dataRevogacao;
	}
	public void setDATA_dataRevogacao(Date dATA_dataRevogacao) {
		DATA_dataRevogacao = dATA_dataRevogacao;
	}
	public Date getDATA_dataRetorno() {
		return DATA_dataRetorno;
	}
	public void setDATA_dataRetorno(Date dATA_dataRetorno) {
		DATA_dataRetorno = dATA_dataRetorno;
	}
	public Date getNUMR_revogacaoDoe() {
		return NUMR_revogacaoDoe;
	}
	public void setNUMR_revogacaoDoe(Date nUMR_revogacaoDoe) {
		NUMR_revogacaoDoe = nUMR_revogacaoDoe;
	}
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
	}
	public int getVersao() {
		return versao;
	}
	public void setVersao(int versao) {
		this.versao = versao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_dataRetorno == null) ? 0 : DATA_dataRetorno.hashCode());
		result = prime * result + ((DATA_dataRevogacao == null) ? 0 : DATA_dataRevogacao.hashCode());
		result = prime * result + ((DATA_publicacaoDoe == null) ? 0 : DATA_publicacaoDoe.hashCode());
		result = prime * result + ((DESC_doe == null) ? 0 : DESC_doe.hashCode());
		result = prime * result + ((DESC_funcao == null) ? 0 : DESC_funcao.hashCode());
		result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
		result = prime * result + ((ENUM_tipoOnus == null) ? 0 : ENUM_tipoOnus.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_idDoObjetoOrgao == null) ? 0 : NUMR_idDoObjetoOrgao.hashCode());
		result = prime * result
				+ ((NUMR_idDoObjetoPessoasFuncionais == null) ? 0 : NUMR_idDoObjetoPessoasFuncionais.hashCode());
		result = prime * result + ((NUMR_revogacaoDoe == null) ? 0 : NUMR_revogacaoDoe.hashCode());
		result = prime * result + versao;
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
		AfastamentosDisposicao other = (AfastamentosDisposicao) obj;
		if (DATA_dataRetorno == null) {
			if (other.DATA_dataRetorno != null)
				return false;
		} else if (!DATA_dataRetorno.equals(other.DATA_dataRetorno))
			return false;
		if (DATA_dataRevogacao == null) {
			if (other.DATA_dataRevogacao != null)
				return false;
		} else if (!DATA_dataRevogacao.equals(other.DATA_dataRevogacao))
			return false;
		if (DATA_publicacaoDoe == null) {
			if (other.DATA_publicacaoDoe != null)
				return false;
		} else if (!DATA_publicacaoDoe.equals(other.DATA_publicacaoDoe))
			return false;
		if (DESC_doe == null) {
			if (other.DESC_doe != null)
				return false;
		} else if (!DESC_doe.equals(other.DESC_doe))
			return false;
		if (DESC_funcao == null) {
			if (other.DESC_funcao != null)
				return false;
		} else if (!DESC_funcao.equals(other.DESC_funcao))
			return false;
		if (DESC_observacao == null) {
			if (other.DESC_observacao != null)
				return false;
		} else if (!DESC_observacao.equals(other.DESC_observacao))
			return false;
		if (ENUM_tipoOnus != other.ENUM_tipoOnus)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_idDoObjetoOrgao == null) {
			if (other.NUMR_idDoObjetoOrgao != null)
				return false;
		} else if (!NUMR_idDoObjetoOrgao.equals(other.NUMR_idDoObjetoOrgao))
			return false;
		if (NUMR_idDoObjetoPessoasFuncionais == null) {
			if (other.NUMR_idDoObjetoPessoasFuncionais != null)
				return false;
		} else if (!NUMR_idDoObjetoPessoasFuncionais.equals(other.NUMR_idDoObjetoPessoasFuncionais))
			return false;
		if (NUMR_revogacaoDoe == null) {
			if (other.NUMR_revogacaoDoe != null)
				return false;
		} else if (!NUMR_revogacaoDoe.equals(other.NUMR_revogacaoDoe))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
}
