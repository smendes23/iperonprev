package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class RepresentanteLegal implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne(cascade=CascadeType.MERGE)
	private Pessoas NUMR_pessoa;
	@ManyToOne(cascade=CascadeType.MERGE)
	private Pessoas NUMR_representante;
	@ManyToOne(cascade=CascadeType.MERGE)
	private TipoRepresentanteLegal NUMR_tipoRepresentante;
	@Version
	private int versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Pessoas getNUMR_pessoa() {
		return NUMR_pessoa;
	}
	public void setNUMR_pessoa(Pessoas nUMR_pessoa) {
		NUMR_pessoa = nUMR_pessoa;
	}
	public Pessoas getNUMR_representante() {
		return NUMR_representante;
	}
	public void setNUMR_representante(Pessoas nUMR_representante) {
		NUMR_representante = nUMR_representante;
	}
	public TipoRepresentanteLegal getNUMR_tipoRepresentante() {
		return NUMR_tipoRepresentante;
	}
	public void setNUMR_tipoRepresentante(TipoRepresentanteLegal nUMR_tipoRepresentante) {
		NUMR_tipoRepresentante = nUMR_tipoRepresentante;
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
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_pessoa == null) ? 0 : NUMR_pessoa.hashCode());
		result = prime * result + ((NUMR_representante == null) ? 0 : NUMR_representante.hashCode());
		result = prime * result + ((NUMR_tipoRepresentante == null) ? 0 : NUMR_tipoRepresentante.hashCode());
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
		RepresentanteLegal other = (RepresentanteLegal) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_pessoa == null) {
			if (other.NUMR_pessoa != null)
				return false;
		} else if (!NUMR_pessoa.equals(other.NUMR_pessoa))
			return false;
		if (NUMR_representante == null) {
			if (other.NUMR_representante != null)
				return false;
		} else if (!NUMR_representante.equals(other.NUMR_representante))
			return false;
		if (NUMR_tipoRepresentante == null) {
			if (other.NUMR_tipoRepresentante != null)
				return false;
		} else if (!NUMR_tipoRepresentante.equals(other.NUMR_tipoRepresentante))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
	
}
