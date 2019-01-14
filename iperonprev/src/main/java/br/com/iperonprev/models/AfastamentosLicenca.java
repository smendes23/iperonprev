package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AfastamentosLicenca implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@Size(min=4,message="Número do processo deve conter!")
	private String NUMR_processo;
	@ManyToOne
	private PessoasFuncionais NUMR_idDoObjetoFuncional;
	@ManyToOne
	@NotNull(message="Tipo de licença obrigatória")
	private TipoLicenca NUMR_tipoLicenca;
	private int FLAG_contribuicao;
	@NotNull(message="Data início obrigatória")
	private Date DATA_inicioLicenca;
	private Date DATA_fimLicenca;
	private String DESC_doe;
	private Date DATA_publicacaoDoe;
	private Date DATA_revogacao;
	@Lob
	private String DESC_observacao;
	private transient int NUMR_qtdDias;
	@Version
	private int versao;
	
	public int getNUMR_qtdDias() {
		return NUMR_qtdDias;
	}
	public void setNUMR_qtdDias(int nUMR_qtdDias) {
		NUMR_qtdDias = nUMR_qtdDias;
	}
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getNUMR_processo() {
		return NUMR_processo;
	}
	public void setNUMR_processo(String nUMR_processo) {
		NUMR_processo = nUMR_processo;
	}
	public PessoasFuncionais getNUMR_idDoObjetoFuncional() {
		return NUMR_idDoObjetoFuncional;
	}
	public void setNUMR_idDoObjetoFuncional(PessoasFuncionais nUMR_idDoObjetoFuncional) {
		NUMR_idDoObjetoFuncional = nUMR_idDoObjetoFuncional;
	}
	public TipoLicenca getNUMR_tipoLicenca() {
		return NUMR_tipoLicenca;
	}
	public void setNUMR_tipoLicenca(TipoLicenca nUMR_tipoLicenca) {
		NUMR_tipoLicenca = nUMR_tipoLicenca;
	}
	public int getFLAG_contribuicao() {
		return FLAG_contribuicao;
	}
	public void setFLAG_contribuicao(int fLAG_contribuicao) {
		FLAG_contribuicao = fLAG_contribuicao;
	}
	public Date getDATA_inicioLicenca() {
		return DATA_inicioLicenca;
	}
	public void setDATA_inicioLicenca(Date dATA_inicioLicenca) {
		DATA_inicioLicenca = dATA_inicioLicenca;
	}
	public Date getDATA_fimLicenca() {
		return DATA_fimLicenca;
	}
	public void setDATA_fimLicenca(Date dATA_fimLicenca) {
		DATA_fimLicenca = dATA_fimLicenca;
	}
	public String getDESC_doe() {
		return DESC_doe;
	}
	public void setDESC_doe(String dESC_doe) {
		DESC_doe = dESC_doe;
	}
	public Date getDATA_publicacaoDoe() {
		return DATA_publicacaoDoe;
	}
	public void setDATA_publicacaoDoe(Date dATA_publicacaoDoe) {
		DATA_publicacaoDoe = dATA_publicacaoDoe;
	}
	public Date getDATA_revogacao() {
		return DATA_revogacao;
	}
	public void setDATA_revogacao(Date dATA_revogacao) {
		DATA_revogacao = dATA_revogacao;
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
		result = prime * result + ((DATA_fimLicenca == null) ? 0 : DATA_fimLicenca.hashCode());
		result = prime * result + ((DATA_inicioLicenca == null) ? 0 : DATA_inicioLicenca.hashCode());
		result = prime * result + ((DATA_publicacaoDoe == null) ? 0 : DATA_publicacaoDoe.hashCode());
		result = prime * result + ((DATA_revogacao == null) ? 0 : DATA_revogacao.hashCode());
		result = prime * result + ((DESC_doe == null) ? 0 : DESC_doe.hashCode());
		result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
		result = prime * result + FLAG_contribuicao;
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_idDoObjetoFuncional == null) ? 0 : NUMR_idDoObjetoFuncional.hashCode());
		result = prime * result + ((NUMR_processo == null) ? 0 : NUMR_processo.hashCode());
		result = prime * result + ((NUMR_tipoLicenca == null) ? 0 : NUMR_tipoLicenca.hashCode());
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
		AfastamentosLicenca other = (AfastamentosLicenca) obj;
		if (DATA_fimLicenca == null) {
			if (other.DATA_fimLicenca != null)
				return false;
		} else if (!DATA_fimLicenca.equals(other.DATA_fimLicenca))
			return false;
		if (DATA_inicioLicenca == null) {
			if (other.DATA_inicioLicenca != null)
				return false;
		} else if (!DATA_inicioLicenca.equals(other.DATA_inicioLicenca))
			return false;
		if (DATA_publicacaoDoe == null) {
			if (other.DATA_publicacaoDoe != null)
				return false;
		} else if (!DATA_publicacaoDoe.equals(other.DATA_publicacaoDoe))
			return false;
		if (DATA_revogacao == null) {
			if (other.DATA_revogacao != null)
				return false;
		} else if (!DATA_revogacao.equals(other.DATA_revogacao))
			return false;
		if (DESC_doe == null) {
			if (other.DESC_doe != null)
				return false;
		} else if (!DESC_doe.equals(other.DESC_doe))
			return false;
		if (DESC_observacao == null) {
			if (other.DESC_observacao != null)
				return false;
		} else if (!DESC_observacao.equals(other.DESC_observacao))
			return false;
		if (FLAG_contribuicao != other.FLAG_contribuicao)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_idDoObjetoFuncional == null) {
			if (other.NUMR_idDoObjetoFuncional != null)
				return false;
		} else if (!NUMR_idDoObjetoFuncional.equals(other.NUMR_idDoObjetoFuncional))
			return false;
		if (NUMR_processo == null) {
			if (other.NUMR_processo != null)
				return false;
		} else if (!NUMR_processo.equals(other.NUMR_processo))
			return false;
		if (NUMR_tipoLicenca == null) {
			if (other.NUMR_tipoLicenca != null)
				return false;
		} else if (!NUMR_tipoLicenca.equals(other.NUMR_tipoLicenca))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
	
}
