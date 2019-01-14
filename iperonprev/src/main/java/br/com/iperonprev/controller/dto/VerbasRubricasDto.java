package br.com.iperonprev.controller.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class VerbasRubricasDto implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	private String descricao;
	private String competencia;
	private BigDecimal valor;
	private int decimoTerceiro;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCompetencia() {
		return competencia;
	}
	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public int getDecimoTerceiro() {
		return decimoTerceiro;
	}
	public void setDecimoTerceiro(int decimoTerceiro) {
		this.decimoTerceiro = decimoTerceiro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}
}
