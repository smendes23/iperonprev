package br.com.iperonprev.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Medicos implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne
	private PessoasFuncionais NUMR_funcionais;
	@ManyToOne
	private  AreaMedica NUMR_areaMedica;
	private String DESC_crm;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_funcionais() {
		return NUMR_funcionais;
	}
	public void setNUMR_funcionais(PessoasFuncionais nUMR_funcionais) {
		NUMR_funcionais = nUMR_funcionais;
	}
	public AreaMedica getNUMR_areaMedica() {
		return NUMR_areaMedica;
	}
	public void setNUMR_areaMedica(AreaMedica nUMR_areaMedica) {
		NUMR_areaMedica = nUMR_areaMedica;
	}
	public String getDESC_crm() {
		return DESC_crm;
	}
	public void setDESC_crm(String dESC_crm) {
		DESC_crm = dESC_crm;
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
		Medicos other = (Medicos) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}
	
}
