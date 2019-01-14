package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import br.com.iperonprev.constantes.FundoPrevidenciarioEnum;

@Entity
public class Remuneracoes implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne(cascade=CascadeType.MERGE)
	private PessoasFuncionais NUMR_idDoObjetoFuncional;
	private String NUMR_competencia;
	private int NUMR_folha;
	private int FLAG_decimoTerceiro;
	private BigDecimal VALR_remuneracao;
	@ManyToOne(cascade=CascadeType.MERGE)
	private Rubricas NUMR_rubrica;
	@Enumerated(EnumType.STRING)
	private transient FundoPrevidenciarioEnum ENUM_fundoPrevidenciario;
	@Version
	private int NUMR_versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_idDoObjetoFuncional() {
		return NUMR_idDoObjetoFuncional;
	}
	public void setNUMR_idDoObjetoFuncional(PessoasFuncionais nUMR_idDoObjetoFuncional) {
		NUMR_idDoObjetoFuncional = nUMR_idDoObjetoFuncional;
	}
	public String getNUMR_competencia() {
		return NUMR_competencia;
	}
	public void setNUMR_competencia(String nUMR_competencia) {
		NUMR_competencia = nUMR_competencia;
	}
	public int getNUMR_folha() {
		return NUMR_folha;
	}
	public void setNUMR_folha(int nUMR_folha) {
		NUMR_folha = nUMR_folha;
	}
	public int getFLAG_decimoTerceiro() {
		return FLAG_decimoTerceiro;
	}
	public void setFLAG_decimoTerceiro(int fLAG_decimoTerceiro) {
		FLAG_decimoTerceiro = fLAG_decimoTerceiro;
	}
	public BigDecimal getVALR_remuneracao() {
		return VALR_remuneracao;
	}
	public void setVALR_remuneracao(BigDecimal vALR_remuneracao) {
		VALR_remuneracao = vALR_remuneracao;
	}
	public Rubricas getNUMR_rubrica() {
		return NUMR_rubrica;
	}
	public void setNUMR_rubrica(Rubricas nUMR_rubrica) {
		NUMR_rubrica = nUMR_rubrica;
	}
	public FundoPrevidenciarioEnum getENUM_fundoPrevidenciario() {
		return ENUM_fundoPrevidenciario;
	}
	public void setENUM_fundoPrevidenciario(FundoPrevidenciarioEnum eNUM_fundoPrevidenciario) {
		ENUM_fundoPrevidenciario = eNUM_fundoPrevidenciario;
	}
	public int getNUMR_versao() {
		return NUMR_versao;
	}
	public void setNUMR_versao(int nUMR_versao) {
		NUMR_versao = nUMR_versao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + FLAG_decimoTerceiro;
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_competencia == null) ? 0 : NUMR_competencia.hashCode());
		result = prime * result + NUMR_folha;
		result = prime * result + ((NUMR_idDoObjetoFuncional == null) ? 0 : NUMR_idDoObjetoFuncional.hashCode());
		result = prime * result + ((NUMR_rubrica == null) ? 0 : NUMR_rubrica.hashCode());
		result = prime * result + NUMR_versao;
		result = prime * result + ((VALR_remuneracao == null) ? 0 : VALR_remuneracao.hashCode());
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
		Remuneracoes other = (Remuneracoes) obj;
		if (FLAG_decimoTerceiro != other.FLAG_decimoTerceiro)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_competencia == null) {
			if (other.NUMR_competencia != null)
				return false;
		} else if (!NUMR_competencia.equals(other.NUMR_competencia))
			return false;
		if (NUMR_folha != other.NUMR_folha)
			return false;
		if (NUMR_idDoObjetoFuncional == null) {
			if (other.NUMR_idDoObjetoFuncional != null)
				return false;
		} else if (!NUMR_idDoObjetoFuncional.equals(other.NUMR_idDoObjetoFuncional))
			return false;
		if (NUMR_rubrica == null) {
			if (other.NUMR_rubrica != null)
				return false;
		} else if (!NUMR_rubrica.equals(other.NUMR_rubrica))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		if (VALR_remuneracao == null) {
			if (other.VALR_remuneracao != null)
				return false;
		} else if (!VALR_remuneracao.equals(other.VALR_remuneracao))
			return false;
		return true;
	}
	
	
	
	
	@Override
	public String toString() {
		return 
		"Funcional: "+NUMR_idDoObjetoFuncional+" - "+
		"Competencia: "+NUMR_competencia+" - "+
		"Folha: "+NUMR_folha+" - "+
		"Decimo: "+FLAG_decimoTerceiro+" - "+
		"Valor: "+VALR_remuneracao+" - "+
		"Rubrica: "+NUMR_rubrica;
	}
}
