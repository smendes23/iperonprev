package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
/*
 * @autor Saulo Mendes
 * OneToMany Estado Civil
 * OneToMany Grau De Instrução
 * Sexo é uma enumeração do tipo String
 * Estado civil é uma enumeração do tipo int
 * */
@Entity
public class Dependentes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)
	private Pessoas NUMR_idDoObjetoPessoas;
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)
	private Pessoas NUMR_idDoObjetoDependentes;
	@OneToOne
	private MotivoFimDependencia NUMR_motivoFimDependencia;
	private Date DATA_fim;
	@OneToOne
//	@NotNull(message="Grau de parentesco obrigatório")
	private GrauParentesco NUMR_grauParentesco;
	@Version
	private int versao;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Pessoas getNUMR_idDoObjetoPessoas() {
		return NUMR_idDoObjetoPessoas;
	}
	public void setNUMR_idDoObjetoPessoas(Pessoas nUMR_idDoObjetoPessoas) {
		NUMR_idDoObjetoPessoas = nUMR_idDoObjetoPessoas;
	}
	public Pessoas getNUMR_idDoObjetoDependentes() {
		return NUMR_idDoObjetoDependentes;
	}
	public void setNUMR_idDoObjetoDependentes(Pessoas nUMR_idDoObjetoDependentes) {
		NUMR_idDoObjetoDependentes = nUMR_idDoObjetoDependentes;
	}
	
	public MotivoFimDependencia getNUMR_motivoFimDependencia() {
		return NUMR_motivoFimDependencia;
	}
	public void setNUMR_motivoFimDependencia(MotivoFimDependencia nUMR_motivoFimDependencia) {
		NUMR_motivoFimDependencia = nUMR_motivoFimDependencia;
	}
	
	public Date getDATA_fim() {
		return DATA_fim;
	}
	public void setDATA_fim(Date dATA_fim) {
		DATA_fim = dATA_fim;
	}
	public GrauParentesco getNUMR_grauParentesco() {
		return NUMR_grauParentesco;
	}
	public void setNUMR_grauParentesco(GrauParentesco nUMR_grauParentesco) {
		NUMR_grauParentesco = nUMR_grauParentesco;
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
		result = prime * result + ((DATA_fim == null) ? 0 : DATA_fim.hashCode());
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + ((NUMR_grauParentesco == null) ? 0 : NUMR_grauParentesco.hashCode());
		result = prime * result + ((NUMR_idDoObjetoDependentes == null) ? 0 : NUMR_idDoObjetoDependentes.hashCode());
		result = prime * result + ((NUMR_idDoObjetoPessoas == null) ? 0 : NUMR_idDoObjetoPessoas.hashCode());
		result = prime * result + ((NUMR_motivoFimDependencia == null) ? 0 : NUMR_motivoFimDependencia.hashCode());
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
		Dependentes other = (Dependentes) obj;
		if (DATA_fim == null) {
			if (other.DATA_fim != null)
				return false;
		} else if (!DATA_fim.equals(other.DATA_fim))
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_grauParentesco == null) {
			if (other.NUMR_grauParentesco != null)
				return false;
		} else if (!NUMR_grauParentesco.equals(other.NUMR_grauParentesco))
			return false;
		if (NUMR_idDoObjetoDependentes == null) {
			if (other.NUMR_idDoObjetoDependentes != null)
				return false;
		} else if (!NUMR_idDoObjetoDependentes.equals(other.NUMR_idDoObjetoDependentes))
			return false;
		if (NUMR_idDoObjetoPessoas == null) {
			if (other.NUMR_idDoObjetoPessoas != null)
				return false;
		} else if (!NUMR_idDoObjetoPessoas.equals(other.NUMR_idDoObjetoPessoas))
			return false;
		if (NUMR_motivoFimDependencia == null) {
			if (other.NUMR_motivoFimDependencia != null)
				return false;
		} else if (!NUMR_motivoFimDependencia.equals(other.NUMR_motivoFimDependencia))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
}
