package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DocumentoFuncional implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String DESC_caminhoRelativo;
	private String DESC_nome;
	@ManyToOne
	private PessoasFuncionais REL_funcional;
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
	
	public PessoasFuncionais getREL_funcional() {
		return REL_funcional;
	}
	public void setREL_funcional(PessoasFuncionais rEL_funcional) {
		REL_funcional = rEL_funcional;
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
		DocumentoFuncional other = (DocumentoFuncional) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}
}
