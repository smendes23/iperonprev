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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class AvaliacaoPensao implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private Date DATA_inicio;
	private Date DATA_fim;
	@Lob
	private String DESC_observacao;
	@ManyToOne
	private Pensao REL_pensao;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<AtestadosMedicos> REL_atestado = new ArrayList<>();
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Date getDATA_inicio() {
		return DATA_inicio;
	}
	public void setDATA_inicio(Date dATA_inicio) {
		DATA_inicio = dATA_inicio;
	}
	public Date getDATA_fim() {
		return DATA_fim;
	}
	public void setDATA_fim(Date dATA_fim) {
		DATA_fim = dATA_fim;
	}
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
	}
	public Pensao getREL_pensao() {
		return REL_pensao;
	}
	public void setREL_pensao(Pensao rEL_pensao) {
		REL_pensao = rEL_pensao;
	}
	public List<AtestadosMedicos> getREL_atestado() {
		return REL_atestado;
	}
	public void setREL_atestado(List<AtestadosMedicos> rEL_atestado) {
		REL_atestado = rEL_atestado;
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
		AvaliacaoPensao other = (AvaliacaoPensao) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		return true;
	}
}
