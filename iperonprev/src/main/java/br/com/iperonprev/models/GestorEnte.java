package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class GestorEnte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@OneToOne
	private EntesFederados NUMR_idDoEnte;
	@OneToOne
	private Pessoas NUMR_idDoObjetoPessoa;
	private Date DATA_inicioExercicio;
	private Date DATA_fimExercicio;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public EntesFederados getNUMR_idDoEnte() {
		return NUMR_idDoEnte;
	}
	public void setNUMR_idDoEnte(EntesFederados nUMR_idDoEnte) {
		NUMR_idDoEnte = nUMR_idDoEnte;
	}
	public Pessoas getNUMR_idDoObjetoPessoa() {
		return NUMR_idDoObjetoPessoa;
	}
	public void setNUMR_idDoObjetoPessoa(Pessoas nUMR_idDoObjetoPessoa) {
		NUMR_idDoObjetoPessoa = nUMR_idDoObjetoPessoa;
	}
	public Date getDATA_inicioExercicio() {
		return DATA_inicioExercicio;
	}
	public void setDATA_inicioExercicio(Date dATA_inicioExercicio) {
		DATA_inicioExercicio = dATA_inicioExercicio;
	}
	public Date getDATA_fimExercicio() {
		return DATA_fimExercicio;
	}
	public void setDATA_fimExercicio(Date dATA_fimExercicio) {
		DATA_fimExercicio = dATA_fimExercicio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_fimExercicio == null) ? 0 : DATA_fimExercicio.hashCode());
		result = prime * result + ((DATA_inicioExercicio == null) ? 0 : DATA_inicioExercicio.hashCode());
		result = prime * result + ((NUMR_idDoEnte == null) ? 0 : NUMR_idDoEnte.hashCode());
		result = prime * result + ((NUMR_idDoObjetoPessoa == null) ? 0 : NUMR_idDoObjetoPessoa.hashCode());
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
		GestorEnte other = (GestorEnte) obj;
		if (DATA_fimExercicio == null) {
			if (other.DATA_fimExercicio != null)
				return false;
		} else if (!DATA_fimExercicio.equals(other.DATA_fimExercicio))
			return false;
		if (DATA_inicioExercicio == null) {
			if (other.DATA_inicioExercicio != null)
				return false;
		} else if (!DATA_inicioExercicio.equals(other.DATA_inicioExercicio))
			return false;
		if (NUMR_idDoEnte == null) {
			if (other.NUMR_idDoEnte != null)
				return false;
		} else if (!NUMR_idDoEnte.equals(other.NUMR_idDoEnte))
			return false;
		if (NUMR_idDoObjetoPessoa == null) {
			if (other.NUMR_idDoObjetoPessoa != null)
				return false;
		} else if (!NUMR_idDoObjetoPessoa.equals(other.NUMR_idDoObjetoPessoa))
			return false;
		return true;
	}
	
	
}
