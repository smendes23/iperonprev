package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Cargos implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto = 0;
	@Column(length=100)
	private String DESC_nome;
	@ManyToOne
	private Orgaos NUMR_idDoObjetoOrgaos;
	@ManyToOne
	private Carreiras NUMR_idDoObjetoCarreiras;
	private String FLAG_dedicacaoExclusiva;
	private String FLAG_aposEspecial;
	private String FLAG_cargoTecnico;
	private Date DATA_criacao;
	private Date DATA_extincao;
	@Column(nullable=true)
	private Integer NUMR_cargaHoraria = 0;
	@OneToOne
	private AtosLegais NUMR_idDoObjetoatosLegais;
	@Version
	private int NUMR_versao;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_nome() {
		return DESC_nome;
	}
	public void setDESC_nome(String dESC_nome) {
		DESC_nome = dESC_nome;
	}
	public Orgaos getNUMR_idDoObjetoOrgaos() {
		return NUMR_idDoObjetoOrgaos;
	}
	public void setNUMR_idDoObjetoOrgaos(Orgaos nUMR_idDoObjetoOrgaos) {
		NUMR_idDoObjetoOrgaos = nUMR_idDoObjetoOrgaos;
	}
	public Carreiras getNUMR_idDoObjetoCarreiras() {
		return NUMR_idDoObjetoCarreiras;
	}
	public void setNUMR_idDoObjetoCarreiras(Carreiras nUMR_idDoObjetoCarreiras) {
		NUMR_idDoObjetoCarreiras = nUMR_idDoObjetoCarreiras;
	}
	public String getFLAG_dedicacaoExclusiva() {
		return FLAG_dedicacaoExclusiva;
	}
	public void setFLAG_dedicacaoExclusiva(String fLAG_dedicacaoExclusiva) {
		FLAG_dedicacaoExclusiva = fLAG_dedicacaoExclusiva;
	}
	public String getFLAG_aposEspecial() {
		return FLAG_aposEspecial;
	}
	public void setFLAG_aposEspecial(String fLAG_aposEspecial) {
		FLAG_aposEspecial = fLAG_aposEspecial;
	}
	public String getFLAG_cargoTecnico() {
		return FLAG_cargoTecnico;
	}
	public void setFLAG_cargoTecnico(String fLAG_cargoTecnico) {
		FLAG_cargoTecnico = fLAG_cargoTecnico;
	}
	public Date getDATA_criacao() {
		return DATA_criacao;
	}
	public void setDATA_criacao(Date dATA_criacao) {
		DATA_criacao = dATA_criacao;
	}
	public Date getDATA_extincao() {
		return DATA_extincao;
	}
	public void setDATA_extincao(Date dATA_extincao) {
		DATA_extincao = dATA_extincao;
	}
	public AtosLegais getNUMR_idDoObjetoatosLegais() {
		return NUMR_idDoObjetoatosLegais;
	}
	public void setNUMR_idDoObjetoatosLegais(AtosLegais nUMR_idDoObjetoatosLegais) {
		NUMR_idDoObjetoatosLegais = nUMR_idDoObjetoatosLegais;
	}
	public int getNUMR_versao() {
		return NUMR_versao;
	}
	public void setNUMR_versao(int nUMR_versao) {
		NUMR_versao = nUMR_versao;
	}
	public int getNUMR_cargaHoraria() {
		return NUMR_cargaHoraria;
	}
	public void setNUMR_cargaHoraria(int nUMR_cargaHoraria) {
		NUMR_cargaHoraria = nUMR_cargaHoraria;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_criacao == null) ? 0 : DATA_criacao.hashCode());
		result = prime * result + ((DATA_extincao == null) ? 0 : DATA_extincao.hashCode());
		result = prime * result + ((DESC_nome == null) ? 0 : DESC_nome.hashCode());
		result = prime * result + ((FLAG_aposEspecial == null) ? 0 : FLAG_aposEspecial.hashCode());
		result = prime * result + ((FLAG_cargoTecnico == null) ? 0 : FLAG_cargoTecnico.hashCode());
		result = prime * result + ((FLAG_dedicacaoExclusiva == null) ? 0 : FLAG_dedicacaoExclusiva.hashCode());
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + ((NUMR_idDoObjetoCarreiras == null) ? 0 : NUMR_idDoObjetoCarreiras.hashCode());
		result = prime * result + ((NUMR_idDoObjetoOrgaos == null) ? 0 : NUMR_idDoObjetoOrgaos.hashCode());
		result = prime * result + ((NUMR_idDoObjetoatosLegais == null) ? 0 : NUMR_idDoObjetoatosLegais.hashCode());
		result = prime * result + NUMR_versao;
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
		Cargos other = (Cargos) obj;
		if (DATA_criacao == null) {
			if (other.DATA_criacao != null)
				return false;
		} else if (!DATA_criacao.equals(other.DATA_criacao))
			return false;
		if (DATA_extincao == null) {
			if (other.DATA_extincao != null)
				return false;
		} else if (!DATA_extincao.equals(other.DATA_extincao))
			return false;
		if (DESC_nome == null) {
			if (other.DESC_nome != null)
				return false;
		} else if (!DESC_nome.equals(other.DESC_nome))
			return false;
		if (FLAG_aposEspecial == null) {
			if (other.FLAG_aposEspecial != null)
				return false;
		} else if (!FLAG_aposEspecial.equals(other.FLAG_aposEspecial))
			return false;
		if (FLAG_cargoTecnico == null) {
			if (other.FLAG_cargoTecnico != null)
				return false;
		} else if (!FLAG_cargoTecnico.equals(other.FLAG_cargoTecnico))
			return false;
		if (FLAG_dedicacaoExclusiva == null) {
			if (other.FLAG_dedicacaoExclusiva != null)
				return false;
		} else if (!FLAG_dedicacaoExclusiva.equals(other.FLAG_dedicacaoExclusiva))
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_idDoObjetoCarreiras == null) {
			if (other.NUMR_idDoObjetoCarreiras != null)
				return false;
		} else if (!NUMR_idDoObjetoCarreiras.equals(other.NUMR_idDoObjetoCarreiras))
			return false;
		if (NUMR_idDoObjetoOrgaos == null) {
			if (other.NUMR_idDoObjetoOrgaos != null)
				return false;
		} else if (!NUMR_idDoObjetoOrgaos.equals(other.NUMR_idDoObjetoOrgaos))
			return false;
		if (NUMR_idDoObjetoatosLegais == null) {
			if (other.NUMR_idDoObjetoatosLegais != null)
				return false;
		} else if (!NUMR_idDoObjetoatosLegais.equals(other.NUMR_idDoObjetoatosLegais))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		return true;
	}
	
}
