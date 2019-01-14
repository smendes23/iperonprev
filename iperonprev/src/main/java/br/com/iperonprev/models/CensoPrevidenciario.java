package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import br.com.iperonprev.constantes.TipoRecadastramentoEnum;

@Entity
public class CensoPrevidenciario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne
	private PessoasFuncionais NUMR_pessoasFuncionais;
	@ManyToOne
	private UnidadesCenso NUMR_unidade;
	private boolean FLAG_pendente;
	private Date DATA_recadastramento;
	private String NUMR_recadastramento;
	private String DESC_atendente;
	private Integer tipoBeneficiario;
	@Enumerated(EnumType.STRING)
	private TipoRecadastramentoEnum ENUM_tipoRecadastramento;
	@Lob
	private String DESC_observacao;
	@ManyToOne
	private DadosCenso NUMR_idCenso;
	@Version
	private int versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_pessoasFuncionais() {
		return NUMR_pessoasFuncionais;
	}
	public void setNUMR_pessoasFuncionais(PessoasFuncionais nUMR_pessoasFuncionais) {
		NUMR_pessoasFuncionais = nUMR_pessoasFuncionais;
	}
	public UnidadesCenso getNUMR_unidade() {
		return NUMR_unidade;
	}
	public void setNUMR_unidade(UnidadesCenso nUMR_unidade) {
		NUMR_unidade = nUMR_unidade;
	}
	public boolean isFLAG_pendente() {
		return FLAG_pendente;
	}
	public void setFLAG_pendente(boolean fLAG_pendente) {
		FLAG_pendente = fLAG_pendente;
	}
	public Date getDATA_recadastramento() {
		return DATA_recadastramento;
	}
	public void setDATA_recadastramento(Date dATA_recadastramento) {
		DATA_recadastramento = dATA_recadastramento;
	}
	public String getNUMR_recadastramento() {
		return NUMR_recadastramento;
	}
	public void setNUMR_recadastramento(String nUMR_recadastramento) {
		NUMR_recadastramento = nUMR_recadastramento;
	}
	public String getDESC_atendente() {
		return DESC_atendente;
	}
	public void setDESC_atendente(String dESC_atendente) {
		DESC_atendente = dESC_atendente;
	}
	public Integer getTipoBeneficiario() {
		return tipoBeneficiario;
	}
	public void setTipoBeneficiario(Integer tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}
	public TipoRecadastramentoEnum getENUM_tipoRecadastramento() {
		return ENUM_tipoRecadastramento;
	}
	public void setENUM_tipoRecadastramento(TipoRecadastramentoEnum eNUM_tipoRecadastramento) {
		ENUM_tipoRecadastramento = eNUM_tipoRecadastramento;
	}
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
	}
	public DadosCenso getNUMR_idCenso() {
		return NUMR_idCenso;
	}
	public void setNUMR_idCenso(DadosCenso nUMR_idCenso) {
		NUMR_idCenso = nUMR_idCenso;
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
		result = prime * result + ((DATA_recadastramento == null) ? 0 : DATA_recadastramento.hashCode());
		result = prime * result + ((DESC_atendente == null) ? 0 : DESC_atendente.hashCode());
		result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
		result = prime * result + ((ENUM_tipoRecadastramento == null) ? 0 : ENUM_tipoRecadastramento.hashCode());
		result = prime * result + (FLAG_pendente ? 1231 : 1237);
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_idCenso == null) ? 0 : NUMR_idCenso.hashCode());
		result = prime * result + ((NUMR_pessoasFuncionais == null) ? 0 : NUMR_pessoasFuncionais.hashCode());
		result = prime * result + ((NUMR_recadastramento == null) ? 0 : NUMR_recadastramento.hashCode());
		result = prime * result + ((NUMR_unidade == null) ? 0 : NUMR_unidade.hashCode());
		result = prime * result + ((tipoBeneficiario == null) ? 0 : tipoBeneficiario.hashCode());
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
		CensoPrevidenciario other = (CensoPrevidenciario) obj;
		if (DATA_recadastramento == null) {
			if (other.DATA_recadastramento != null)
				return false;
		} else if (!DATA_recadastramento.equals(other.DATA_recadastramento))
			return false;
		if (DESC_atendente == null) {
			if (other.DESC_atendente != null)
				return false;
		} else if (!DESC_atendente.equals(other.DESC_atendente))
			return false;
		if (DESC_observacao == null) {
			if (other.DESC_observacao != null)
				return false;
		} else if (!DESC_observacao.equals(other.DESC_observacao))
			return false;
		if (ENUM_tipoRecadastramento != other.ENUM_tipoRecadastramento)
			return false;
		if (FLAG_pendente != other.FLAG_pendente)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_idCenso == null) {
			if (other.NUMR_idCenso != null)
				return false;
		} else if (!NUMR_idCenso.equals(other.NUMR_idCenso))
			return false;
		if (NUMR_pessoasFuncionais == null) {
			if (other.NUMR_pessoasFuncionais != null)
				return false;
		} else if (!NUMR_pessoasFuncionais.equals(other.NUMR_pessoasFuncionais))
			return false;
		if (NUMR_recadastramento == null) {
			if (other.NUMR_recadastramento != null)
				return false;
		} else if (!NUMR_recadastramento.equals(other.NUMR_recadastramento))
			return false;
		if (NUMR_unidade == null) {
			if (other.NUMR_unidade != null)
				return false;
		} else if (!NUMR_unidade.equals(other.NUMR_unidade))
			return false;
		if (tipoBeneficiario == null) {
			if (other.tipoBeneficiario != null)
				return false;
		} else if (!tipoBeneficiario.equals(other.tipoBeneficiario))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
	
}
