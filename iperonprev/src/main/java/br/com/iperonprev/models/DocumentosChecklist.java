package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class DocumentosChecklist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String DESC_caminhoRelativo;
	private String DESC_nome;
	@ManyToOne
	private RequisitosBeneficio REL_requisitos;
	@ManyToOne
	private PessoasFuncionais REL_pessoasFuncionais;
	@Lob
	private String DESC_observacao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_caminhoRelativo() {
		return DESC_caminhoRelativo;
	}
	public void setDESC_caminhoRelativo(String dESC_caminhoRelativo) {
		DESC_caminhoRelativo = dESC_caminhoRelativo;
	}
	public String getDESC_nome() {
		return DESC_nome;
	}
	public void setDESC_nome(String dESC_nome) {
		DESC_nome = dESC_nome;
	}
	public RequisitosBeneficio getREL_requisitos() {
		return REL_requisitos;
	}
	public void setREL_requisitos(RequisitosBeneficio rEL_requisitos) {
		REL_requisitos = rEL_requisitos;
	}
	public PessoasFuncionais getREL_pessoasFuncionais() {
		return REL_pessoasFuncionais;
	}
	public void setREL_pessoasFuncionais(PessoasFuncionais rEL_pessoasFuncionais) {
		REL_pessoasFuncionais = rEL_pessoasFuncionais;
	}
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_caminhoRelativo == null) ? 0 : DESC_caminhoRelativo.hashCode());
		result = prime * result + ((DESC_nome == null) ? 0 : DESC_nome.hashCode());
		result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((REL_pessoasFuncionais == null) ? 0 : REL_pessoasFuncionais.hashCode());
		result = prime * result + ((REL_requisitos == null) ? 0 : REL_requisitos.hashCode());
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
		DocumentosChecklist other = (DocumentosChecklist) obj;
		if (DESC_caminhoRelativo == null) {
			if (other.DESC_caminhoRelativo != null)
				return false;
		} else if (!DESC_caminhoRelativo.equals(other.DESC_caminhoRelativo))
			return false;
		if (DESC_nome == null) {
			if (other.DESC_nome != null)
				return false;
		} else if (!DESC_nome.equals(other.DESC_nome))
			return false;
		if (DESC_observacao == null) {
			if (other.DESC_observacao != null)
				return false;
		} else if (!DESC_observacao.equals(other.DESC_observacao))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (REL_pessoasFuncionais == null) {
			if (other.REL_pessoasFuncionais != null)
				return false;
		} else if (!REL_pessoasFuncionais.equals(other.REL_pessoasFuncionais))
			return false;
		if (REL_requisitos == null) {
			if (other.REL_requisitos != null)
				return false;
		} else if (!REL_requisitos.equals(other.REL_requisitos))
			return false;
		return true;
	}
	
		
}
