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
public class EntesFederados implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	@ManyToOne(cascade=CascadeType.MERGE)
	private Municipios NUMR_idDoObjetoMunicipio;
	@ManyToOne
	private Esferas NURM_idDoObjetoEsfera;
	@Version
	private int NUMR_versao;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Municipios getNUMR_idDoObjetoMunicipio() {
		return NUMR_idDoObjetoMunicipio;
	}
	public void setNUMR_idDoObjetoMunicipio(Municipios nUMR_idDoObjetoMunicipio) {
		NUMR_idDoObjetoMunicipio = nUMR_idDoObjetoMunicipio;
	}
	public Esferas getNURM_idDoObjetoEsfera() {
		return NURM_idDoObjetoEsfera;
	}
	public void setNURM_idDoObjetoEsfera(Esferas nURM_idDoObjetoEsfera) {
		NURM_idDoObjetoEsfera = nURM_idDoObjetoEsfera;
	}
	public int getNUMR_versao() {
		return NUMR_versao;
	}
	public void setNUMR_versao(int nUMR_versao) {
		NUMR_versao = nUMR_versao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + ((NUMR_idDoObjetoMunicipio == null) ? 0 : NUMR_idDoObjetoMunicipio.hashCode());
		result = prime * result + NUMR_versao;
		result = prime * result + ((NURM_idDoObjetoEsfera == null) ? 0 : NURM_idDoObjetoEsfera.hashCode());
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
		EntesFederados other = (EntesFederados) obj;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_idDoObjetoMunicipio == null) {
			if (other.NUMR_idDoObjetoMunicipio != null)
				return false;
		} else if (!NUMR_idDoObjetoMunicipio.equals(other.NUMR_idDoObjetoMunicipio))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		if (NURM_idDoObjetoEsfera == null) {
			if (other.NURM_idDoObjetoEsfera != null)
				return false;
		} else if (!NURM_idDoObjetoEsfera.equals(other.NURM_idDoObjetoEsfera))
			return false;
		return true;
	}
	
}
