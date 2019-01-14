package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rubricas implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String NUMR_codigo;
	private String DESC_descricao;
	private int NUMR_tipoRubrica;
	private Date DATA_inicio;
	private Date DATA_fim;
	private int FLAG_incidenciaContribuicao;
	private int FLAG_contribuicaoPrevidenciaria;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getNUMR_codigo() {
		return NUMR_codigo;
	}
	public void setNUMR_codigo(String nUMR_codigo) {
		NUMR_codigo = nUMR_codigo;
	}
	public String getDESC_descricao() {
		return DESC_descricao;
	}
	public void setDESC_descricao(String dESC_descricao) {
		DESC_descricao = dESC_descricao;
	}
	public int getNUMR_tipoRubrica() {
		return NUMR_tipoRubrica;
	}
	public void setNUMR_tipoRubrica(int nUMR_tipoRubrica) {
		NUMR_tipoRubrica = nUMR_tipoRubrica;
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
	public int getFLAG_incidenciaContribuicao() {
		return FLAG_incidenciaContribuicao;
	}
	public void setFLAG_incidenciaContribuicao(int fLAG_incidenciaContribuicao) {
		FLAG_incidenciaContribuicao = fLAG_incidenciaContribuicao;
	}
	public int getFLAG_contribuicaoPrevidenciaria() {
		return FLAG_contribuicaoPrevidenciaria;
	}
	public void setFLAG_contribuicaoPrevidenciaria(int fLAG_contribuicaoPrevidenciaria) {
		FLAG_contribuicaoPrevidenciaria = fLAG_contribuicaoPrevidenciaria;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_fim == null) ? 0 : DATA_fim.hashCode());
		result = prime * result + ((DATA_inicio == null) ? 0 : DATA_inicio.hashCode());
		result = prime * result + ((DESC_descricao == null) ? 0 : DESC_descricao.hashCode());
		result = prime * result + FLAG_contribuicaoPrevidenciaria;
		result = prime * result + FLAG_incidenciaContribuicao;
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_codigo == null) ? 0 : NUMR_codigo.hashCode());
		result = prime * result + NUMR_tipoRubrica;
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
		Rubricas other = (Rubricas) obj;
		if (DATA_fim == null) {
			if (other.DATA_fim != null)
				return false;
		} else if (!DATA_fim.equals(other.DATA_fim))
			return false;
		if (DATA_inicio == null) {
			if (other.DATA_inicio != null)
				return false;
		} else if (!DATA_inicio.equals(other.DATA_inicio))
			return false;
		if (DESC_descricao == null) {
			if (other.DESC_descricao != null)
				return false;
		} else if (!DESC_descricao.equals(other.DESC_descricao))
			return false;
		if (FLAG_contribuicaoPrevidenciaria != other.FLAG_contribuicaoPrevidenciaria)
			return false;
		if (FLAG_incidenciaContribuicao != other.FLAG_incidenciaContribuicao)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_codigo == null) {
			if (other.NUMR_codigo != null)
				return false;
		} else if (!NUMR_codigo.equals(other.NUMR_codigo))
			return false;
		if (NUMR_tipoRubrica != other.NUMR_tipoRubrica)
			return false;
		return true;
	}
}
