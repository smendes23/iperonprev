package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Enderecos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne(fetch=FetchType.EAGER,cascade=javax.persistence.CascadeType.ALL)
	private Municipios NUMR_idDoObjetoMunicipio;
	private String DESC_bairro;
	@Column(length=8)
	private String NUMR_cep;
	@OneToOne
	private TipoLogradouro NUMR_tipoLogradouro;
	@Column(length=100)
	private String DESC_logradouro;
	@Column(length=6)
	private String DESC_numero;
	@Lob
	private String DESC_observacao;
	/*@Version
	private int versao;*/
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Municipios getNUMR_idDoObjetoMunicipio() {
		return NUMR_idDoObjetoMunicipio;
	}
	public void setNUMR_idDoObjetoMunicipio(Municipios nUMR_idDoObjetoMunicipio) {
		NUMR_idDoObjetoMunicipio = nUMR_idDoObjetoMunicipio;
	}
	public String getDESC_bairro() {
		return DESC_bairro;
	}
	public void setDESC_bairro(String dESC_bairro) {
		DESC_bairro = dESC_bairro;
	}
	public String getNUMR_cep() {
		return NUMR_cep;
	}
	public void setNUMR_cep(String nUMR_cep) {
		NUMR_cep = nUMR_cep;
	}
	public TipoLogradouro getNUMR_tipoLogradouro() {
		return NUMR_tipoLogradouro;
	}
	public void setNUMR_tipoLogradouro(TipoLogradouro nUMR_tipoLogradouro) {
		NUMR_tipoLogradouro = nUMR_tipoLogradouro;
	}
	public String getDESC_logradouro() {
		return DESC_logradouro;
	}
	public void setDESC_logradouro(String dESC_logradouro) {
		DESC_logradouro = dESC_logradouro;
	}
	public String getDESC_numero() {
		return DESC_numero;
	}
	public void setDESC_numero(String dESC_numero) {
		DESC_numero = dESC_numero;
	}
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
	}
	/*public int getVersao() {
		return versao;
	}
	public void setVersao(int versao) {
		this.versao = versao;
	}*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_bairro == null) ? 0 : DESC_bairro.hashCode());
		result = prime * result + ((DESC_logradouro == null) ? 0 : DESC_logradouro.hashCode());
		result = prime * result + ((DESC_numero == null) ? 0 : DESC_numero.hashCode());
		result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_cep == null) ? 0 : NUMR_cep.hashCode());
		result = prime * result + ((NUMR_idDoObjetoMunicipio == null) ? 0 : NUMR_idDoObjetoMunicipio.hashCode());
		result = prime * result + ((NUMR_tipoLogradouro == null) ? 0 : NUMR_tipoLogradouro.hashCode());
//		result = prime * result + versao;
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
		Enderecos other = (Enderecos) obj;
		if (DESC_bairro == null) {
			if (other.DESC_bairro != null)
				return false;
		} else if (!DESC_bairro.equals(other.DESC_bairro))
			return false;
		if (DESC_logradouro == null) {
			if (other.DESC_logradouro != null)
				return false;
		} else if (!DESC_logradouro.equals(other.DESC_logradouro))
			return false;
		if (DESC_numero == null) {
			if (other.DESC_numero != null)
				return false;
		} else if (!DESC_numero.equals(other.DESC_numero))
			return false;
		if (DESC_observacao == null) {
			if (other.DESC_observacao != null)
				return false;
		} else if (!DESC_observacao.equals(other.DESC_observacao))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_cep == null) {
			if (other.NUMR_cep != null)
				return false;
		} else if (!NUMR_cep.equals(other.NUMR_cep))
			return false;
		if (NUMR_idDoObjetoMunicipio == null) {
			if (other.NUMR_idDoObjetoMunicipio != null)
				return false;
		} else if (!NUMR_idDoObjetoMunicipio.equals(other.NUMR_idDoObjetoMunicipio))
			return false;
		if (NUMR_tipoLogradouro == null) {
			if (other.NUMR_tipoLogradouro != null)
				return false;
		} else if (!NUMR_tipoLogradouro.equals(other.NUMR_tipoLogradouro))
			return false;
		/*if (versao != other.versao)
			return false;*/
		return true;
	}
	
}
