package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.iperonprev.constantes.DecenioEnum;
@Entity
public class Quinquenio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	@ManyToOne(cascade=CascadeType.MERGE)
	private PessoasFuncionais NUMR_pessoasFuncionais;
	@Enumerated(EnumType.STRING)
	@NotNull(message="Decênio obrigatório")
	private DecenioEnum ENUM_decenio;
	@NotNull(message="Data início obrigatória")
	private Date DATA_inicio;
	@NotNull(message="Data fim obrigatória")
	private Date DATA_fim;
	@Size(min=4, message="Número do processo deve conter pelo menos 4 carateres.")
	private String DESC_processo;
	private String DESC_observacao;
	@Version
	private int versao;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_pessoasFuncionais() {
		return NUMR_pessoasFuncionais;
	}
	public void setNUMR_pessoasFuncionais(PessoasFuncionais nUMR_pessoasFuncionais) {
		NUMR_pessoasFuncionais = nUMR_pessoasFuncionais;
	}
	public DecenioEnum getENUM_decenio() {
		return ENUM_decenio;
	}
	public void setENUM_decenio(DecenioEnum eNUM_decenio) {
		ENUM_decenio = eNUM_decenio;
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
	public String getDESC_processo() {
		return DESC_processo;
	}
	public void setDESC_processo(String dESC_processo) {
		DESC_processo = dESC_processo;
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
		result = prime * result + ((DATA_fim == null) ? 0 : DATA_fim.hashCode());
		result = prime * result + ((DATA_inicio == null) ? 0 : DATA_inicio.hashCode());
		result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
		result = prime * result + ((DESC_processo == null) ? 0 : DESC_processo.hashCode());
		result = prime * result + ((ENUM_decenio == null) ? 0 : ENUM_decenio.hashCode());
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + ((NUMR_pessoasFuncionais == null) ? 0 : NUMR_pessoasFuncionais.hashCode());
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
		Quinquenio other = (Quinquenio) obj;
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
		if (DESC_observacao == null) {
			if (other.DESC_observacao != null)
				return false;
		} else if (!DESC_observacao.equals(other.DESC_observacao))
			return false;
		if (DESC_processo == null) {
			if (other.DESC_processo != null)
				return false;
		} else if (!DESC_processo.equals(other.DESC_processo))
			return false;
		if (ENUM_decenio != other.ENUM_decenio)
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_pessoasFuncionais == null) {
			if (other.NUMR_pessoasFuncionais != null)
				return false;
		} else if (!NUMR_pessoasFuncionais.equals(other.NUMR_pessoasFuncionais))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
	
}
