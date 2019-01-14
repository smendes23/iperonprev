package br.com.iperonprev.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TempoLiquidoAverbacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private int diasAproveitado;
	private String tempoAproveitado;
	@ManyToOne
	private PessoasFuncionais NUMR_pessoaFuncional;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public int getDiasAproveitado() {
		return diasAproveitado;
	}
	public void setDiasAproveitado(int diasAproveitado) {
		this.diasAproveitado = diasAproveitado;
	}
	public String getTempoAproveitado() {
		return tempoAproveitado;
	}
	public void setTempoAproveitado(String tempoAproveitado) {
		this.tempoAproveitado = tempoAproveitado;
	}
	public PessoasFuncionais getNUMR_pessoaFuncional() {
		return NUMR_pessoaFuncional;
	}
	public void setNUMR_pessoaFuncional(PessoasFuncionais nUMR_pessoaFuncional) {
		NUMR_pessoaFuncional = nUMR_pessoaFuncional;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_pessoaFuncional == null) ? 0 : NUMR_pessoaFuncional.hashCode());
		result = prime * result + diasAproveitado;
		result = prime * result + ((tempoAproveitado == null) ? 0 : tempoAproveitado.hashCode());
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
		TempoLiquidoAverbacao other = (TempoLiquidoAverbacao) obj;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_pessoaFuncional == null) {
			if (other.NUMR_pessoaFuncional != null)
				return false;
		} else if (!NUMR_pessoaFuncional.equals(other.NUMR_pessoaFuncional))
			return false;
		if (diasAproveitado != other.diasAproveitado)
			return false;
		if (tempoAproveitado == null) {
			if (other.tempoAproveitado != null)
				return false;
		} else if (!tempoAproveitado.equals(other.tempoAproveitado))
			return false;
		return true;
	}
}
