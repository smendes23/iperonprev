package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ModulosPerfil implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private boolean acessoCadastro;
	private boolean acessoArrecadacao;
	private boolean acessoBeneficio;
	private boolean acessoCenso;
	private boolean acessoRelatorios;
	private boolean acessoAdministracao;
	private boolean incluirCadastro;
	private boolean incluirArrecadacao;
	private boolean incluirBeneficio;
	private boolean incluirCenso;
	private boolean incluirRelatorios;
	private boolean incluirAdministracao;
	private boolean excluirCadastro;
	private boolean excluirArrecadacao;
	private boolean excluirBeneficio;
	private boolean ecluirCenso;
	private boolean excluirRelatorios;
	private boolean excluirAdministracao;
	@ManyToOne(cascade=CascadeType.ALL)
	private PerfilAcesso perfil;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAcessoCadastro() {
		return acessoCadastro;
	}
	public void setAcessoCadastro(boolean acessoCadastro) {
		this.acessoCadastro = acessoCadastro;
	}
	public boolean isAcessoArrecadacao() {
		return acessoArrecadacao;
	}
	public void setAcessoArrecadacao(boolean acessoArrecadacao) {
		this.acessoArrecadacao = acessoArrecadacao;
	}
	public boolean isAcessoBeneficio() {
		return acessoBeneficio;
	}
	public void setAcessoBeneficio(boolean acessoBeneficio) {
		this.acessoBeneficio = acessoBeneficio;
	}
	public boolean isAcessoCenso() {
		return acessoCenso;
	}
	public void setAcessoCenso(boolean acessoCenso) {
		this.acessoCenso = acessoCenso;
	}
	public boolean isAcessoRelatorios() {
		return acessoRelatorios;
	}
	public void setAcessoRelatorios(boolean acessoRelatorios) {
		this.acessoRelatorios = acessoRelatorios;
	}
	public boolean isAcessoAdministracao() {
		return acessoAdministracao;
	}
	public void setAcessoAdministracao(boolean acessoAdministracao) {
		this.acessoAdministracao = acessoAdministracao;
	}
	public boolean isIncluirCadastro() {
		return incluirCadastro;
	}
	public void setIncluirCadastro(boolean incluirCadastro) {
		this.incluirCadastro = incluirCadastro;
	}
	public boolean isIncluirArrecadacao() {
		return incluirArrecadacao;
	}
	public void setIncluirArrecadacao(boolean incluirArrecadacao) {
		this.incluirArrecadacao = incluirArrecadacao;
	}
	public boolean isIncluirBeneficio() {
		return incluirBeneficio;
	}
	public void setIncluirBeneficio(boolean incluirBeneficio) {
		this.incluirBeneficio = incluirBeneficio;
	}
	public boolean isIncluirCenso() {
		return incluirCenso;
	}
	public void setIncluirCenso(boolean incluirCenso) {
		this.incluirCenso = incluirCenso;
	}
	public boolean isIncluirRelatorios() {
		return incluirRelatorios;
	}
	public void setIncluirRelatorios(boolean incluirRelatorios) {
		this.incluirRelatorios = incluirRelatorios;
	}
	public boolean isIncluirAdministracao() {
		return incluirAdministracao;
	}
	public void setIncluirAdministracao(boolean incluirAdministracao) {
		this.incluirAdministracao = incluirAdministracao;
	}
	public boolean isExcluirCadastro() {
		return excluirCadastro;
	}
	public void setExcluirCadastro(boolean excluirCadastro) {
		this.excluirCadastro = excluirCadastro;
	}
	public boolean isExcluirArrecadacao() {
		return excluirArrecadacao;
	}
	public void setExcluirArrecadacao(boolean excluirArrecadacao) {
		this.excluirArrecadacao = excluirArrecadacao;
	}
	public boolean isExcluirBeneficio() {
		return excluirBeneficio;
	}
	public void setExcluirBeneficio(boolean excluirBeneficio) {
		this.excluirBeneficio = excluirBeneficio;
	}
	public boolean isEcluirCenso() {
		return ecluirCenso;
	}
	public void setEcluirCenso(boolean ecluirCenso) {
		this.ecluirCenso = ecluirCenso;
	}
	public boolean isExcluirRelatorios() {
		return excluirRelatorios;
	}
	public void setExcluirRelatorios(boolean excluirRelatorios) {
		this.excluirRelatorios = excluirRelatorios;
	}
	public boolean isExcluirAdministracao() {
		return excluirAdministracao;
	}
	public void setExcluirAdministracao(boolean excluirAdministracao) {
		this.excluirAdministracao = excluirAdministracao;
	}
	public PerfilAcesso getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilAcesso perfil) {
		this.perfil = perfil;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (acessoAdministracao ? 1231 : 1237);
		result = prime * result + (acessoArrecadacao ? 1231 : 1237);
		result = prime * result + (acessoBeneficio ? 1231 : 1237);
		result = prime * result + (acessoCadastro ? 1231 : 1237);
		result = prime * result + (acessoCenso ? 1231 : 1237);
		result = prime * result + (acessoRelatorios ? 1231 : 1237);
		result = prime * result + (ecluirCenso ? 1231 : 1237);
		result = prime * result + (excluirAdministracao ? 1231 : 1237);
		result = prime * result + (excluirArrecadacao ? 1231 : 1237);
		result = prime * result + (excluirBeneficio ? 1231 : 1237);
		result = prime * result + (excluirCadastro ? 1231 : 1237);
		result = prime * result + (excluirRelatorios ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + (incluirAdministracao ? 1231 : 1237);
		result = prime * result + (incluirArrecadacao ? 1231 : 1237);
		result = prime * result + (incluirBeneficio ? 1231 : 1237);
		result = prime * result + (incluirCadastro ? 1231 : 1237);
		result = prime * result + (incluirCenso ? 1231 : 1237);
		result = prime * result + (incluirRelatorios ? 1231 : 1237);
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
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
		ModulosPerfil other = (ModulosPerfil) obj;
		if (acessoAdministracao != other.acessoAdministracao)
			return false;
		if (acessoArrecadacao != other.acessoArrecadacao)
			return false;
		if (acessoBeneficio != other.acessoBeneficio)
			return false;
		if (acessoCadastro != other.acessoCadastro)
			return false;
		if (acessoCenso != other.acessoCenso)
			return false;
		if (acessoRelatorios != other.acessoRelatorios)
			return false;
		if (ecluirCenso != other.ecluirCenso)
			return false;
		if (excluirAdministracao != other.excluirAdministracao)
			return false;
		if (excluirArrecadacao != other.excluirArrecadacao)
			return false;
		if (excluirBeneficio != other.excluirBeneficio)
			return false;
		if (excluirCadastro != other.excluirCadastro)
			return false;
		if (excluirRelatorios != other.excluirRelatorios)
			return false;
		if (id != other.id)
			return false;
		if (incluirAdministracao != other.incluirAdministracao)
			return false;
		if (incluirArrecadacao != other.incluirArrecadacao)
			return false;
		if (incluirBeneficio != other.incluirBeneficio)
			return false;
		if (incluirCadastro != other.incluirCadastro)
			return false;
		if (incluirCenso != other.incluirCenso)
			return false;
		if (incluirRelatorios != other.incluirRelatorios)
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		return true;
	}
}
