package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Orgaos  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	private String DESC_nome;
	@Column(length=25)
	private String DESC_sigla;
	private String DESC_razaoSocial;
	@Column(length=20)
	private String DESC_cnpj;
	@ManyToOne
	private NaturezasJuridicas NUMR_idDoObjetoNatJuridica;
	@ManyToOne
	private Poderes NUMR_idDoObjetoPoder;
	@Column(length=100)
	private String DESC_email;
	@ManyToOne
	private EntesFederados NUMR_idDoObjetoEnteFederado;
	private boolean NUMR_unidadeGestora;
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
	public String getDESC_sigla() {
		return DESC_sigla;
	}
	public void setDESC_sigla(String dESC_sigla) {
		DESC_sigla = dESC_sigla;
	}
	public String getDESC_razaoSocial() {
		return DESC_razaoSocial;
	}
	public void setDESC_razaoSocial(String dESC_razaoSocial) {
		DESC_razaoSocial = dESC_razaoSocial;
	}
	public String getDESC_cnpj() {
		return DESC_cnpj;
	}
	public void setDESC_cnpj(String dESC_cnpj) {
		DESC_cnpj = dESC_cnpj;
	}
	public NaturezasJuridicas getNUMR_idDoObjetoNatJuridica() {
		return NUMR_idDoObjetoNatJuridica;
	}
	public void setNUMR_idDoObjetoNatJuridica(NaturezasJuridicas nUMR_idDoObjetoNatJuridica) {
		NUMR_idDoObjetoNatJuridica = nUMR_idDoObjetoNatJuridica;
	}
	public Poderes getNUMR_idDoObjetoPoder() {
		return NUMR_idDoObjetoPoder;
	}
	public void setNUMR_idDoObjetoPoder(Poderes nUMR_idDoObjetoPoder) {
		NUMR_idDoObjetoPoder = nUMR_idDoObjetoPoder;
	}
	public String getDESC_email() {
		return DESC_email;
	}
	public void setDESC_email(String dESC_email) {
		DESC_email = dESC_email;
	}
	public EntesFederados getNUMR_idDoObjetoEnteFederado() {
		return NUMR_idDoObjetoEnteFederado;
	}
	public void setNUMR_idDoObjetoEnteFederado(EntesFederados nUMR_idDoObjetoEnteFederado) {
		NUMR_idDoObjetoEnteFederado = nUMR_idDoObjetoEnteFederado;
	}
	public boolean isNUMR_unidadeGestora() {
		return NUMR_unidadeGestora;
	}
	public void setNUMR_unidadeGestora(boolean nUMR_unidadeGestora) {
		NUMR_unidadeGestora = nUMR_unidadeGestora;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_cnpj == null) ? 0 : DESC_cnpj.hashCode());
		result = prime * result + ((DESC_email == null) ? 0 : DESC_email.hashCode());
		result = prime * result + ((DESC_nome == null) ? 0 : DESC_nome.hashCode());
		result = prime * result + ((DESC_razaoSocial == null) ? 0 : DESC_razaoSocial.hashCode());
		result = prime * result + ((DESC_sigla == null) ? 0 : DESC_sigla.hashCode());
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + ((NUMR_idDoObjetoEnteFederado == null) ? 0 : NUMR_idDoObjetoEnteFederado.hashCode());
		result = prime * result + ((NUMR_idDoObjetoNatJuridica == null) ? 0 : NUMR_idDoObjetoNatJuridica.hashCode());
		result = prime * result + ((NUMR_idDoObjetoPoder == null) ? 0 : NUMR_idDoObjetoPoder.hashCode());
		result = prime * result + (NUMR_unidadeGestora ? 1231 : 1237);
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
		Orgaos other = (Orgaos) obj;
		if (DESC_cnpj == null) {
			if (other.DESC_cnpj != null)
				return false;
		} else if (!DESC_cnpj.equals(other.DESC_cnpj))
			return false;
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
		if (DESC_razaoSocial == null) {
			if (other.DESC_razaoSocial != null)
				return false;
		} else if (!DESC_razaoSocial.equals(other.DESC_razaoSocial))
			return false;
		if (DESC_sigla == null) {
			if (other.DESC_sigla != null)
				return false;
		} else if (!DESC_sigla.equals(other.DESC_sigla))
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_idDoObjetoEnteFederado == null) {
			if (other.NUMR_idDoObjetoEnteFederado != null)
				return false;
		} else if (!NUMR_idDoObjetoEnteFederado.equals(other.NUMR_idDoObjetoEnteFederado))
			return false;
		if (NUMR_idDoObjetoNatJuridica == null) {
			if (other.NUMR_idDoObjetoNatJuridica != null)
				return false;
		} else if (!NUMR_idDoObjetoNatJuridica.equals(other.NUMR_idDoObjetoNatJuridica))
			return false;
		if (NUMR_idDoObjetoPoder == null) {
			if (other.NUMR_idDoObjetoPoder != null)
				return false;
		} else if (!NUMR_idDoObjetoPoder.equals(other.NUMR_idDoObjetoPoder))
			return false;
		if (NUMR_unidadeGestora != other.NUMR_unidadeGestora)
			return false;
		return true;
	}
}
