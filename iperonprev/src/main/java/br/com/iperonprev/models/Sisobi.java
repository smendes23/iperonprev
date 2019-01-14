package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Sisobi implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String NUMR_termo;
	private Date DATA_obito;
	private String DESC_competencia;
	private String NUMR_tipoCartorio;
	private String NUMR_identificacaoCartorio;
	@OneToOne
	@JoinColumn(name="pessoaId")
	private Pessoas NUMR_idDoObjetoPessoa;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getNUMR_termo() {
		return NUMR_termo;
	}
	public void setNUMR_termo(String nUMR_termo) {
		NUMR_termo = nUMR_termo;
	}
	public Date getDATA_obito() {
		return DATA_obito;
	}
	public void setDATA_obito(Date dATA_obito) {
		DATA_obito = dATA_obito;
	}
	public String getDESC_competencia() {
		return DESC_competencia;
	}
	public void setDESC_competencia(String dESC_competencia) {
		DESC_competencia = dESC_competencia;
	}
	public String getNUMR_tipoCartorio() {
		return NUMR_tipoCartorio;
	}
	public void setNUMR_tipoCartorio(String nUMR_tipoCartorio) {
		NUMR_tipoCartorio = nUMR_tipoCartorio;
	}
	public String getNUMR_identificacaoCartorio() {
		return NUMR_identificacaoCartorio;
	}
	public void setNUMR_identificacaoCartorio(String nUMR_identificacaoCartorio) {
		NUMR_identificacaoCartorio = nUMR_identificacaoCartorio;
	}
	public Pessoas getNUMR_idDoObjetoPessoa() {
		return NUMR_idDoObjetoPessoa;
	}
	public void setNUMR_idDoObjetoPessoa(Pessoas nUMR_idDoObjetoPessoa) {
		NUMR_idDoObjetoPessoa = nUMR_idDoObjetoPessoa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_obito == null) ? 0 : DATA_obito.hashCode());
		result = prime * result + ((DESC_competencia == null) ? 0 : DESC_competencia.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_idDoObjetoPessoa == null) ? 0 : NUMR_idDoObjetoPessoa.hashCode());
		result = prime * result + ((NUMR_identificacaoCartorio == null) ? 0 : NUMR_identificacaoCartorio.hashCode());
		result = prime * result + ((NUMR_termo == null) ? 0 : NUMR_termo.hashCode());
		result = prime * result + ((NUMR_tipoCartorio == null) ? 0 : NUMR_tipoCartorio.hashCode());
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
		Sisobi other = (Sisobi) obj;
		if (DATA_obito == null) {
			if (other.DATA_obito != null)
				return false;
		} else if (!DATA_obito.equals(other.DATA_obito))
			return false;
		if (DESC_competencia == null) {
			if (other.DESC_competencia != null)
				return false;
		} else if (!DESC_competencia.equals(other.DESC_competencia))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_idDoObjetoPessoa == null) {
			if (other.NUMR_idDoObjetoPessoa != null)
				return false;
		} else if (!NUMR_idDoObjetoPessoa.equals(other.NUMR_idDoObjetoPessoa))
			return false;
		if (NUMR_identificacaoCartorio == null) {
			if (other.NUMR_identificacaoCartorio != null)
				return false;
		} else if (!NUMR_identificacaoCartorio.equals(other.NUMR_identificacaoCartorio))
			return false;
		if (NUMR_termo == null) {
			if (other.NUMR_termo != null)
				return false;
		} else if (!NUMR_termo.equals(other.NUMR_termo))
			return false;
		if (NUMR_tipoCartorio == null) {
			if (other.NUMR_tipoCartorio != null)
				return false;
		} else if (!NUMR_tipoCartorio.equals(other.NUMR_tipoCartorio))
			return false;
		return true;
	}
	
}
