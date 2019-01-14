package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Enquadramento implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String DESC_descricao;
	@ManyToOne
	private Cargos REL_cargos;
	private BigDecimal VALR_gratificacao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_descricao() {
		return DESC_descricao;
	}
	public void setDESC_descricao(String dESC_descricao) {
		DESC_descricao = dESC_descricao;
	}
	public Cargos getREL_cargos() {
		return REL_cargos;
	}
	public void setREL_cargos(Cargos rEL_cargos) {
		REL_cargos = rEL_cargos;
	}
	public BigDecimal getVALR_gratificacao() {
		return VALR_gratificacao;
	}
	public void setVALR_gratificacao(BigDecimal vALR_gratificacao) {
		VALR_gratificacao = vALR_gratificacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_descricao == null) ? 0 : DESC_descricao.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((REL_cargos == null) ? 0 : REL_cargos.hashCode());
		result = prime * result + ((VALR_gratificacao == null) ? 0 : VALR_gratificacao.hashCode());
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
		Enquadramento other = (Enquadramento) obj;
		if (DESC_descricao == null) {
			if (other.DESC_descricao != null)
				return false;
		} else if (!DESC_descricao.equals(other.DESC_descricao))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (REL_cargos == null) {
			if (other.REL_cargos != null)
				return false;
		} else if (!REL_cargos.equals(other.REL_cargos))
			return false;
		if (VALR_gratificacao == null) {
			if (other.VALR_gratificacao != null)
				return false;
		} else if (!VALR_gratificacao.equals(other.VALR_gratificacao))
			return false;
		return true;
	}
	
}
