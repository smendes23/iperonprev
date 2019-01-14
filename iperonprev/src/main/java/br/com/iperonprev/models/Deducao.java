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

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.TipoDeducaoEnum;

@Entity
public class Deducao implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne
	private PessoasFuncionais NUMR_pessoasFuncionais;
	@Enumerated(EnumType.STRING)
	@NotNull(message="Tipo de dedução obrigatória")
	private TipoDeducaoEnum ENUM_tipoDeducao;
	@NotNull(message="Data início obrigatória")
	private Date DATA_inicio;
	private Date DATA_fim;
	private int NUMR_qtdDias;
	private String DESC_observacoes;
	@Enumerated(EnumType.STRING)
	private DecisaoEnum ENUM_compensasaoDeducao;
	@Version
	private int NUMR_versao;
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
	public TipoDeducaoEnum getENUM_tipoDeducao() {
		return ENUM_tipoDeducao;
	}
	public void setENUM_tipoDeducao(TipoDeducaoEnum eNUM_tipoDeducao) {
		ENUM_tipoDeducao = eNUM_tipoDeducao;
	}
	public Date getDATA_inicio() {
		return DATA_inicio;
	}
	public void setDATA_inicio(Date dATA_inicio) {
		DATA_inicio = dATA_inicio;
	}
	public Date getDATA_fim() {
		return DATA_fim;
	}
	public void setDATA_fim(Date dATA_fim) {
		DATA_fim = dATA_fim;
	}
	public int getNUMR_qtdDias() {
		return NUMR_qtdDias;
	}
	public void setNUMR_qtdDias(int nUMR_qtdDias) {
		NUMR_qtdDias = nUMR_qtdDias;
	}
	public String getDESC_observacoes() {
		return DESC_observacoes;
	}
	public void setDESC_observacoes(String dESC_observacoes) {
		DESC_observacoes = dESC_observacoes;
	}
	public DecisaoEnum getENUM_compensasaoDeducao() {
		return ENUM_compensasaoDeducao;
	}
	public void setENUM_compensasaoDeducao(DecisaoEnum eNUM_compensasaoDeducao) {
		ENUM_compensasaoDeducao = eNUM_compensasaoDeducao;
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
		result = prime * result + ((DATA_fim == null) ? 0 : DATA_fim.hashCode());
		result = prime * result + ((DATA_inicio == null) ? 0 : DATA_inicio.hashCode());
		result = prime * result + ((DESC_observacoes == null) ? 0 : DESC_observacoes.hashCode());
		result = prime * result + ((ENUM_compensasaoDeducao == null) ? 0 : ENUM_compensasaoDeducao.hashCode());
		result = prime * result + ((ENUM_tipoDeducao == null) ? 0 : ENUM_tipoDeducao.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_pessoasFuncionais == null) ? 0 : NUMR_pessoasFuncionais.hashCode());
		result = prime * result + NUMR_qtdDias;
		result = prime * result + NUMR_versao;
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
		Deducao other = (Deducao) obj;
		if (DATA_fim == null) {
			if (other.DATA_fim != null)
				return false;
		} else if (!DATA_fim.equals(other.DATA_fim))
			return false;
		if (DATA_inicio == null) {
			if (other.DATA_inicio != null)
				return false;
		} else if (!DATA_inicio.equals(other.DATA_inicio))
			return false;
		if (DESC_observacoes == null) {
			if (other.DESC_observacoes != null)
				return false;
		} else if (!DESC_observacoes.equals(other.DESC_observacoes))
			return false;
		if (ENUM_compensasaoDeducao != other.ENUM_compensasaoDeducao)
			return false;
		if (ENUM_tipoDeducao != other.ENUM_tipoDeducao)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_pessoasFuncionais == null) {
			if (other.NUMR_pessoasFuncionais != null)
				return false;
		} else if (!NUMR_pessoasFuncionais.equals(other.NUMR_pessoasFuncionais))
			return false;
		if (NUMR_qtdDias != other.NUMR_qtdDias)
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		return true;
	}
}
