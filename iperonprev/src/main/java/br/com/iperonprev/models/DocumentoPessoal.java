package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DocumentoPessoal implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String DESC_caminhoRelativo;
	private String DESC_nome;
	@ManyToOne
	private Pessoas REL_pessoa;
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
	public Pessoas getREL_pessoa() {
		return REL_pessoa;
	}
	public void setREL_pessoa(Pessoas rEL_pessoa) {
		REL_pessoa = rEL_pessoa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_caminhoRelativo == null) ? 0 : DESC_caminhoRelativo.hashCode());
		result = prime * result + ((DESC_nome == null) ? 0 : DESC_nome.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((REL_pessoa == null) ? 0 : REL_pessoa.hashCode());
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
		DocumentoPessoal other = (DocumentoPessoal) obj;
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
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (REL_pessoa == null) {
			if (other.REL_pessoa != null)
				return false;
		} else if (!REL_pessoa.equals(other.REL_pessoa))
			return false;
		return true;
	}
	
}
