package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class UnidadesCenso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	@NotNull(message="Nome da unidade é obrigatório")
	private String DESC_nome;
	@ManyToOne(cascade=CascadeType.MERGE)
	@NotNull(message="Censo obrigatório")
	private Enderecos NUMR_endereco;
	private String NUMR_telefone;
	private String DESC_email;
	@ManyToOne
	private Pessoas NUMR_idDoObjetoPessoa;
	@Version
	private int versao;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_nome() {
		return DESC_nome;
	}
	public void setDESC_nome(String dESC_nome) {
		DESC_nome = dESC_nome;
	}
	public Enderecos getNUMR_endereco() {
		return NUMR_endereco;
	}
	public void setNUMR_endereco(Enderecos nUMR_endereco) {
		NUMR_endereco = nUMR_endereco;
	}
	public String getNUMR_telefone() {
		return NUMR_telefone;
	}
	public void setNUMR_telefone(String nUMR_telefone) {
		NUMR_telefone = nUMR_telefone;
	}
	public String getDESC_email() {
		return DESC_email;
	}
	public void setDESC_email(String dESC_email) {
		DESC_email = dESC_email;
	}
	public Pessoas getNUMR_idDoObjetoPessoa() {
		return NUMR_idDoObjetoPessoa;
	}
	public void setNUMR_idDoObjetoPessoa(Pessoas nUMR_idDoObjetoPessoa) {
		NUMR_idDoObjetoPessoa = nUMR_idDoObjetoPessoa;
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
		result = prime * result + ((DESC_email == null) ? 0 : DESC_email.hashCode());
		result = prime * result + ((DESC_nome == null) ? 0 : DESC_nome.hashCode());
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + ((NUMR_endereco == null) ? 0 : NUMR_endereco.hashCode());
		result = prime * result + ((NUMR_idDoObjetoPessoa == null) ? 0 : NUMR_idDoObjetoPessoa.hashCode());
		result = prime * result + ((NUMR_telefone == null) ? 0 : NUMR_telefone.hashCode());
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
		UnidadesCenso other = (UnidadesCenso) obj;
		if (DESC_email == null) {
			if (other.DESC_email != null)
				return false;
		} else if (!DESC_email.equals(other.DESC_email))
			return false;
		if (DESC_nome == null) {
			if (other.DESC_nome != null)
				return false;
		} else if (!DESC_nome.equals(other.DESC_nome))
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_endereco == null) {
			if (other.NUMR_endereco != null)
				return false;
		} else if (!NUMR_endereco.equals(other.NUMR_endereco))
			return false;
		if (NUMR_idDoObjetoPessoa == null) {
			if (other.NUMR_idDoObjetoPessoa != null)
				return false;
		} else if (!NUMR_idDoObjetoPessoa.equals(other.NUMR_idDoObjetoPessoa))
			return false;
		if (NUMR_telefone == null) {
			if (other.NUMR_telefone != null)
				return false;
		} else if (!NUMR_telefone.equals(other.NUMR_telefone))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
}
