package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.iperonprev.constantes.StatusPericiaEnum;

@Entity
public class Pericia implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto;
	private Date DATA_pericia;
	private Date DATA_proximaPericia;
	private boolean FLAG_naoPericia;
	@OneToOne
	private PessoasFuncionais NUMR_idDoObejtoPessoasFuncionais;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Cid> NUMR_cid;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
	private List<AtestadosMedicos> REL_atestadoMedico = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	@Transient
	private StatusPericiaEnum ENUM_statusPericia;
	@Lob
	private String DESC_observacao;
	@Version
	private int NUMR_versao;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Date getDATA_pericia() {
		return DATA_pericia;
	}
	public void setDATA_pericia(Date dATA_pericia) {
		DATA_pericia = dATA_pericia;
	}
	public Date getDATA_proximaPericia() {
		return DATA_proximaPericia;
	}
	public void setDATA_proximaPericia(Date dATA_proximaPericia) {
		DATA_proximaPericia = dATA_proximaPericia;
	}
	public boolean isFLAG_naoPericia() {
		return FLAG_naoPericia;
	}
	public void setFLAG_naoPericia(boolean fLAG_naoPericia) {
		FLAG_naoPericia = fLAG_naoPericia;
	}
	public PessoasFuncionais getNUMR_idDoObejtoPessoasFuncionais() {
		return NUMR_idDoObejtoPessoasFuncionais;
	}
	public void setNUMR_idDoObejtoPessoasFuncionais(PessoasFuncionais nUMR_idDoObejtoPessoasFuncionais) {
		NUMR_idDoObejtoPessoasFuncionais = nUMR_idDoObejtoPessoasFuncionais;
	}
	public List<Cid> getNUMR_cid() {
		return NUMR_cid;
	}
	public void setNUMR_cid(List<Cid> nUMR_cid) {
		NUMR_cid = nUMR_cid;
	}
	public List<AtestadosMedicos> getREL_atestadoMedico() {
		return REL_atestadoMedico;
	}
	public void setREL_atestadoMedico(List<AtestadosMedicos> rEL_atestadoMedico) {
		REL_atestadoMedico = rEL_atestadoMedico;
	}
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
	}
	public int getNUMR_versao() {
		return NUMR_versao;
	}
	public void setNUMR_versao(int nUMR_versao) {
		NUMR_versao = nUMR_versao;
	}
	
	public StatusPericiaEnum getENUM_statusPericia() {
		return ENUM_statusPericia;
	}
	public void setENUM_statusPericia(StatusPericiaEnum eNUM_statusPericia) {
		ENUM_statusPericia = eNUM_statusPericia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_pericia == null) ? 0 : DATA_pericia.hashCode());
		result = prime * result + ((DATA_proximaPericia == null) ? 0 : DATA_proximaPericia.hashCode());
		result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
		result = prime * result + (FLAG_naoPericia ? 1231 : 1237);
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + ((NUMR_cid == null) ? 0 : NUMR_cid.hashCode());
		result = prime * result
				+ ((NUMR_idDoObejtoPessoasFuncionais == null) ? 0 : NUMR_idDoObejtoPessoasFuncionais.hashCode());
		result = prime * result + NUMR_versao;
		result = prime * result + ((REL_atestadoMedico == null) ? 0 : REL_atestadoMedico.hashCode());
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
		Pericia other = (Pericia) obj;
		if (DATA_pericia == null) {
			if (other.DATA_pericia != null)
				return false;
		} else if (!DATA_pericia.equals(other.DATA_pericia))
			return false;
		if (DATA_proximaPericia == null) {
			if (other.DATA_proximaPericia != null)
				return false;
		} else if (!DATA_proximaPericia.equals(other.DATA_proximaPericia))
			return false;
		if (DESC_observacao == null) {
			if (other.DESC_observacao != null)
				return false;
		} else if (!DESC_observacao.equals(other.DESC_observacao))
			return false;
		if (FLAG_naoPericia != other.FLAG_naoPericia)
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_cid == null) {
			if (other.NUMR_cid != null)
				return false;
		} else if (!NUMR_cid.equals(other.NUMR_cid))
			return false;
		if (NUMR_idDoObejtoPessoasFuncionais == null) {
			if (other.NUMR_idDoObejtoPessoasFuncionais != null)
				return false;
		} else if (!NUMR_idDoObejtoPessoasFuncionais.equals(other.NUMR_idDoObejtoPessoasFuncionais))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		if (REL_atestadoMedico == null) {
			if (other.REL_atestadoMedico != null)
				return false;
		} else if (!REL_atestadoMedico.equals(other.REL_atestadoMedico))
			return false;
		return true;
	}
	
}
