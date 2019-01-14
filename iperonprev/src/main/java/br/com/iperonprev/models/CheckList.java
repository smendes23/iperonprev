package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.iperonprev.constantes.TipoBeneficioEnum;

@Entity
public class CheckList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String DESC_numeroProcesso;
	@ManyToOne
	private PessoasFuncionais REL_pessoasFuncionais;
	@Enumerated(EnumType.STRING)
	private TipoBeneficioEnum ENuM_tipoBeneficio;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_numeroProcesso() {
		return DESC_numeroProcesso;
	}
	public void setDESC_numeroProcesso(String dESC_numeroProcesso) {
		DESC_numeroProcesso = dESC_numeroProcesso;
	}
	public PessoasFuncionais getREL_pessoasFuncionais() {
		return REL_pessoasFuncionais;
	}
	public void setREL_pessoasFuncionais(PessoasFuncionais rEL_pessoasFuncionais) {
		REL_pessoasFuncionais = rEL_pessoasFuncionais;
	}
	public TipoBeneficioEnum getENuM_tipoBeneficio() {
		return ENuM_tipoBeneficio;
	}
	public void setENuM_tipoBeneficio(TipoBeneficioEnum eNuM_tipoBeneficio) {
		ENuM_tipoBeneficio = eNuM_tipoBeneficio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_numeroProcesso == null) ? 0 : DESC_numeroProcesso.hashCode());
		result = prime * result + ((ENuM_tipoBeneficio == null) ? 0 : ENuM_tipoBeneficio.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((REL_pessoasFuncionais == null) ? 0 : REL_pessoasFuncionais.hashCode());
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
		CheckList other = (CheckList) obj;
		if (DESC_numeroProcesso == null) {
			if (other.DESC_numeroProcesso != null)
				return false;
		} else if (!DESC_numeroProcesso.equals(other.DESC_numeroProcesso))
			return false;
		if (ENuM_tipoBeneficio != other.ENuM_tipoBeneficio)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (REL_pessoasFuncionais == null) {
			if (other.REL_pessoasFuncionais != null)
				return false;
		} else if (!REL_pessoasFuncionais.equals(other.REL_pessoasFuncionais))
			return false;
		return true;
	}
	
}
