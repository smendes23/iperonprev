package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class DesbloqueioCtc implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne
	private CertidaoTempoContribuicao NUMR_ctc;
	private boolean FLAG_impressaoCtc;
	private boolean FLAG_impressaoRrc;
	private boolean FLAG_edicao;
	private String DESC_justificativa;
	private Date DT_cadastro;
	@Version
	private int versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public CertidaoTempoContribuicao getNUMR_ctc() {
		return NUMR_ctc;
	}
	public void setNUMR_ctc(CertidaoTempoContribuicao nUMR_ctc) {
		NUMR_ctc = nUMR_ctc;
	}
	public boolean isFLAG_impressaoCtc() {
		return FLAG_impressaoCtc;
	}
	public void setFLAG_impressaoCtc(boolean fLAG_impressaoCtc) {
		FLAG_impressaoCtc = fLAG_impressaoCtc;
	}
	public boolean isFLAG_impressaoRrc() {
		return FLAG_impressaoRrc;
	}
	public void setFLAG_impressaoRrc(boolean fLAG_impressaoRrc) {
		FLAG_impressaoRrc = fLAG_impressaoRrc;
	}
	public boolean isFLAG_edicao() {
		return FLAG_edicao;
	}
	public void setFLAG_edicao(boolean fLAG_edicao) {
		FLAG_edicao = fLAG_edicao;
	}
	public String getDESC_justificativa() {
		return DESC_justificativa;
	}
	public void setDESC_justificativa(String dESC_justificativa) {
		DESC_justificativa = dESC_justificativa;
	}
	public Date getDT_cadastro() {
		return DT_cadastro;
	}
	public void setDT_cadastro(Date dT_cadastro) {
		DT_cadastro = dT_cadastro;
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
		result = prime * result + ((DESC_justificativa == null) ? 0 : DESC_justificativa.hashCode());
		result = prime * result + ((DT_cadastro == null) ? 0 : DT_cadastro.hashCode());
		result = prime * result + (FLAG_edicao ? 1231 : 1237);
		result = prime * result + (FLAG_impressaoCtc ? 1231 : 1237);
		result = prime * result + (FLAG_impressaoRrc ? 1231 : 1237);
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_ctc == null) ? 0 : NUMR_ctc.hashCode());
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
		DesbloqueioCtc other = (DesbloqueioCtc) obj;
		if (DESC_justificativa == null) {
			if (other.DESC_justificativa != null)
				return false;
		} else if (!DESC_justificativa.equals(other.DESC_justificativa))
			return false;
		if (DT_cadastro == null) {
			if (other.DT_cadastro != null)
				return false;
		} else if (!DT_cadastro.equals(other.DT_cadastro))
			return false;
		if (FLAG_edicao != other.FLAG_edicao)
			return false;
		if (FLAG_impressaoCtc != other.FLAG_impressaoCtc)
			return false;
		if (FLAG_impressaoRrc != other.FLAG_impressaoRrc)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_ctc == null) {
			if (other.NUMR_ctc != null)
				return false;
		} else if (!NUMR_ctc.equals(other.NUMR_ctc))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
		
}
