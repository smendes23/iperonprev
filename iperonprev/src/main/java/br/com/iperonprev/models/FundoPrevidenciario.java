package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import br.com.iperonprev.constantes.TipoGuiaFundoPrevidenciarioEnum;

@Entity
public class FundoPrevidenciario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int NUMG_idDoObjeto;
	private int NUMG_codigo;
	private String DESC_segurado;
	private int COD_patronal;
	private String DESC_patronal;
	@Enumerated(EnumType.STRING)
	private TipoGuiaFundoPrevidenciarioEnum ENUM_tipo;
	
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public int getNUMG_codigo() {
		return NUMG_codigo;
	}
	public void setNUMG_codigo(int nUMG_codigo) {
		NUMG_codigo = nUMG_codigo;
	}
	public String getDESC_segurado() {
		return DESC_segurado;
	}
	public void setDESC_segurado(String dESC_segurado) {
		DESC_segurado = dESC_segurado;
	}
	public int getCOD_patronal() {
		return COD_patronal;
	}
	public void setCOD_patronal(int cOD_patronal) {
		COD_patronal = cOD_patronal;
	}
	public String getDESC_patronal() {
		return DESC_patronal;
	}
	public void setDESC_patronal(String dESC_patronal) {
		DESC_patronal = dESC_patronal;
	}
	public TipoGuiaFundoPrevidenciarioEnum getENUM_tipo() {
		return ENUM_tipo;
	}
	public void setENUM_tipo(TipoGuiaFundoPrevidenciarioEnum eNUM_tipo) {
		ENUM_tipo = eNUM_tipo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + COD_patronal;
		result = prime * result + ((DESC_patronal == null) ? 0 : DESC_patronal.hashCode());
		result = prime * result + ((DESC_segurado == null) ? 0 : DESC_segurado.hashCode());
		result = prime * result + ((ENUM_tipo == null) ? 0 : ENUM_tipo.hashCode());
		result = prime * result + NUMG_codigo;
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
		FundoPrevidenciario other = (FundoPrevidenciario) obj;
		if (COD_patronal != other.COD_patronal)
			return false;
		if (DESC_patronal == null) {
			if (other.DESC_patronal != null)
				return false;
		} else if (!DESC_patronal.equals(other.DESC_patronal))
			return false;
		if (DESC_segurado == null) {
			if (other.DESC_segurado != null)
				return false;
		} else if (!DESC_segurado.equals(other.DESC_segurado))
			return false;
		if (ENUM_tipo != other.ENUM_tipo)
			return false;
		if (NUMG_codigo != other.NUMG_codigo)
			return false;
		return true;
	}
	
}
