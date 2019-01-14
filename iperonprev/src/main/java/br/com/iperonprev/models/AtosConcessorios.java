package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AtosConcessorios  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@OneToOne
	private PessoasFuncionais REL_pessoasFuncionais;
	private String NUMR_ato;
	private Date DATA_publicacao;
	private Date DATA_retroativa;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getREL_pessoasFuncionais() {
		return REL_pessoasFuncionais;
	}
	public void setREL_pessoasFuncionais(PessoasFuncionais rEL_pessoasFuncionais) {
		REL_pessoasFuncionais = rEL_pessoasFuncionais;
	}
	public String getNUMR_ato() {
		return NUMR_ato;
	}
	public void setNUMR_ato(String nUMR_ato) {
		NUMR_ato = nUMR_ato;
	}
	public Date getDATA_publicacao() {
		return DATA_publicacao;
	}
	public void setDATA_publicacao(Date dATA_publicacao) {
		DATA_publicacao = dATA_publicacao;
	}
	public Date getDATA_retroativa() {
		return DATA_retroativa;
	}
	public void setDATA_retroativa(Date dATA_retroativa) {
		DATA_retroativa = dATA_retroativa;
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
		AtosConcessorios other = (AtosConcessorios) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}
}
