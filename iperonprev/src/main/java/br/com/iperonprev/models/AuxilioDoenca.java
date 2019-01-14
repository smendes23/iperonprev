package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class AuxilioDoenca implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@OneToOne
	private PessoasFuncionais NUMR_pessoaFuncional;
	private String NUMR_documento;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Cid> NUMR_cid;
	private Date DATA_inicioBeneficio;
	private Date DATA_fimBeneficio;
	private transient int NUMR_qtdDias;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<AtestadosMedicos> REL_atestadoMedico = new ArrayList<>();
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_pessoaFuncional() {
		return NUMR_pessoaFuncional;
	}
	public void setNUMR_pessoaFuncional(PessoasFuncionais nUMR_pessoaFuncional) {
		NUMR_pessoaFuncional = nUMR_pessoaFuncional;
	}
	public String getNUMR_documento() {
		return NUMR_documento;
	}
	public void setNUMR_documento(String nUMR_documento) {
		NUMR_documento = nUMR_documento;
	}
	public List<Cid> getNUMR_cid() {
		return NUMR_cid;
	}
	public void setNUMR_cid(List<Cid> nUMR_cid) {
		NUMR_cid = nUMR_cid;
	}
	public Date getDATA_inicioBeneficio() {
		return DATA_inicioBeneficio;
	}
	public void setDATA_inicioBeneficio(Date dATA_inicioBeneficio) {
		DATA_inicioBeneficio = dATA_inicioBeneficio;
	}
	public Date getDATA_fimBeneficio() {
		return DATA_fimBeneficio;
	}
	public void setDATA_fimBeneficio(Date dATA_fimBeneficio) {
		DATA_fimBeneficio = dATA_fimBeneficio;
	}
	public int getNUMR_qtdDias() {
		return NUMR_qtdDias;
	}
	public void setNUMR_qtdDias(int nUMR_qtdDias) {
		NUMR_qtdDias = nUMR_qtdDias;
	}
	public List<AtestadosMedicos> getREL_atestadoMedico() {
		return REL_atestadoMedico;
	}
	public void setREL_atestadoMedico(List<AtestadosMedicos> rEL_atestadoMedico) {
		REL_atestadoMedico = rEL_atestadoMedico;
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
		AuxilioDoenca other = (AuxilioDoenca) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	} 
}
