package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class GerenciamentoConvocacao implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoas NUMR_idPessoas;
	private Date DATA_envio;
	private Date DATA_recebimento;
	@Version
	private int versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Pessoas getNUMR_idPessoas() {
		return NUMR_idPessoas;
	}
	public void setNUMR_idPessoas(Pessoas nUMR_idPessoas) {
		NUMR_idPessoas = nUMR_idPessoas;
	}
	public Date getDATA_envio() {
		return DATA_envio;
	}
	public void setDATA_envio(Date dATA_envio) {
		DATA_envio = dATA_envio;
	}
	public Date getDATA_recebimento() {
		return DATA_recebimento;
	}
	public void setDATA_recebimento(Date dATA_recebimento) {
		DATA_recebimento = dATA_recebimento;
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
		result = prime * result + ((DATA_envio == null) ? 0 : DATA_envio.hashCode());
		result = prime * result + ((DATA_recebimento == null) ? 0 : DATA_recebimento.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_idPessoas == null) ? 0 : NUMR_idPessoas.hashCode());
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
		GerenciamentoConvocacao other = (GerenciamentoConvocacao) obj;
		if (DATA_envio == null) {
			if (other.DATA_envio != null)
				return false;
		} else if (!DATA_envio.equals(other.DATA_envio))
			return false;
		if (DATA_recebimento == null) {
			if (other.DATA_recebimento != null)
				return false;
		} else if (!DATA_recebimento.equals(other.DATA_recebimento))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_idPessoas == null) {
			if (other.NUMR_idPessoas != null)
				return false;
		} else if (!NUMR_idPessoas.equals(other.NUMR_idPessoas))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
}
