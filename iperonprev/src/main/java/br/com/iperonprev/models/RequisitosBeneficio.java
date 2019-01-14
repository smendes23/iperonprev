package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import br.com.iperonprev.constantes.TipoBeneficioEnum;

@Entity
public class RequisitosBeneficio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne
	private TituloBeneficio REL_tituloBeneficio;
	@Lob
	private String DESC_requisito;
	private boolean FLAG_obrigacaoSegurado;
	private transient String DESC_status;
	@Enumerated(EnumType.STRING)
	private TipoBeneficioEnum ENUM_tipoBeneficio;
	private transient boolean FLAG_arquivo;
	
	public boolean isFLAG_arquivo() {
		return FLAG_arquivo;
	}
	public void setFLAG_arquivo(boolean fLAG_arquivo) {
		FLAG_arquivo = fLAG_arquivo;
	}
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public TituloBeneficio getREL_tituloBeneficio() {
		return REL_tituloBeneficio;
	}
	public void setREL_tituloBeneficio(TituloBeneficio rEL_tituloBeneficio) {
		REL_tituloBeneficio = rEL_tituloBeneficio;
	}
	public String getDESC_requisito() {
		return DESC_requisito;
	}
	public void setDESC_requisito(String dESC_requisito) {
		DESC_requisito = dESC_requisito;
	}
	public boolean isFLAG_obrigacaoSegurado() {
		return FLAG_obrigacaoSegurado;
	}
	public void setFLAG_obrigacaoSegurado(boolean fLAG_obrigacaoSegurado) {
		FLAG_obrigacaoSegurado = fLAG_obrigacaoSegurado;
	}
	public String getDESC_status() {
		return DESC_status;
	}
	public void setDESC_status(String dESC_status) {
		DESC_status = dESC_status;
	}
	public TipoBeneficioEnum getENUM_tipoBeneficio() {
		return ENUM_tipoBeneficio;
	}
	public void setENUM_tipoBeneficio(TipoBeneficioEnum eNUM_tipoBeneficio) {
		ENUM_tipoBeneficio = eNUM_tipoBeneficio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_requisito == null) ? 0 : DESC_requisito.hashCode());
		result = prime * result + ((ENUM_tipoBeneficio == null) ? 0 : ENUM_tipoBeneficio.hashCode());
		result = prime * result + (FLAG_obrigacaoSegurado ? 1231 : 1237);
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((REL_tituloBeneficio == null) ? 0 : REL_tituloBeneficio.hashCode());
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
		RequisitosBeneficio other = (RequisitosBeneficio) obj;
		if (DESC_requisito == null) {
			if (other.DESC_requisito != null)
				return false;
		} else if (!DESC_requisito.equals(other.DESC_requisito))
			return false;
		if (ENUM_tipoBeneficio != other.ENUM_tipoBeneficio)
			return false;
		if (FLAG_obrigacaoSegurado != other.FLAG_obrigacaoSegurado)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (REL_tituloBeneficio == null) {
			if (other.REL_tituloBeneficio != null)
				return false;
		} else if (!REL_tituloBeneficio.equals(other.REL_tituloBeneficio))
			return false;
		return true;
	}
	
}
