package br.com.iperonprev.controller.dto;

public class PeriodosAverbacaoDto {

	private int quantidadeDePeriodos;
	private int totalDiasContribuicao;
	private int totalDiasAproveitados;
	private String contribuicaoPrivada;
	private String contribuicaoPublica;
	private String tempoConcomitante;
	private String outrasDeducoes;
	private String tempoTotalAproveitado;
	private String tempoTotalContribuicao;
	public int getQuantidadeDePeriodos() {
		return quantidadeDePeriodos;
	}
	public void setQuantidadeDePeriodos(int quantidadeDePeriodos) {
		this.quantidadeDePeriodos = quantidadeDePeriodos;
	}
	public int getTotalDiasContribuicao() {
		return totalDiasContribuicao;
	}
	public void setTotalDiasContribuicao(int totalDiasContribuicao) {
		this.totalDiasContribuicao = totalDiasContribuicao;
	}
	public int getTotalDiasAproveitados() {
		return totalDiasAproveitados;
	}
	public void setTotalDiasAproveitados(int totalDiasAproveitados) {
		this.totalDiasAproveitados = totalDiasAproveitados;
	}
	public String getContribuicaoPrivada() {
		return contribuicaoPrivada;
	}
	public void setContribuicaoPrivada(String contribuicaoPrivada) {
		this.contribuicaoPrivada = contribuicaoPrivada;
	}
	public String getContribuicaoPublica() {
		return contribuicaoPublica;
	}
	public void setContribuicaoPublica(String contribuicaoPublica) {
		this.contribuicaoPublica = contribuicaoPublica;
	}
	public String getTempoConcomitante() {
		return tempoConcomitante;
	}
	public void setTempoConcomitante(String tempoConcomitante) {
		this.tempoConcomitante = tempoConcomitante;
	}
	public String getOutrasDeducoes() {
		return outrasDeducoes;
	}
	public void setOutrasDeducoes(String outrasDeducoes) {
		this.outrasDeducoes = outrasDeducoes;
	}
	public String getTempoTotalAproveitado() {
		return tempoTotalAproveitado;
	}
	public void setTempoTotalAproveitado(String tempoTotalAproveitado) {
		this.tempoTotalAproveitado = tempoTotalAproveitado;
	}
	public String getTempoTotalContribuicao() {
		return tempoTotalContribuicao;
	}
	public void setTempoTotalContribuicao(String tempoTotalContribuicao) {
		this.tempoTotalContribuicao = tempoTotalContribuicao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contribuicaoPrivada == null) ? 0 : contribuicaoPrivada.hashCode());
		result = prime * result + ((contribuicaoPublica == null) ? 0 : contribuicaoPublica.hashCode());
		result = prime * result + ((outrasDeducoes == null) ? 0 : outrasDeducoes.hashCode());
		result = prime * result + quantidadeDePeriodos;
		result = prime * result + ((tempoConcomitante == null) ? 0 : tempoConcomitante.hashCode());
		result = prime * result + ((tempoTotalAproveitado == null) ? 0 : tempoTotalAproveitado.hashCode());
		result = prime * result + ((tempoTotalContribuicao == null) ? 0 : tempoTotalContribuicao.hashCode());
		result = prime * result + totalDiasAproveitados;
		result = prime * result + totalDiasContribuicao;
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
		PeriodosAverbacaoDto other = (PeriodosAverbacaoDto) obj;
		if (contribuicaoPrivada == null) {
			if (other.contribuicaoPrivada != null)
				return false;
		} else if (!contribuicaoPrivada.equals(other.contribuicaoPrivada))
			return false;
		if (contribuicaoPublica == null) {
			if (other.contribuicaoPublica != null)
				return false;
		} else if (!contribuicaoPublica.equals(other.contribuicaoPublica))
			return false;
		if (outrasDeducoes == null) {
			if (other.outrasDeducoes != null)
				return false;
		} else if (!outrasDeducoes.equals(other.outrasDeducoes))
			return false;
		if (quantidadeDePeriodos != other.quantidadeDePeriodos)
			return false;
		if (tempoConcomitante == null) {
			if (other.tempoConcomitante != null)
				return false;
		} else if (!tempoConcomitante.equals(other.tempoConcomitante))
			return false;
		if (tempoTotalAproveitado == null) {
			if (other.tempoTotalAproveitado != null)
				return false;
		} else if (!tempoTotalAproveitado.equals(other.tempoTotalAproveitado))
			return false;
		if (tempoTotalContribuicao == null) {
			if (other.tempoTotalContribuicao != null)
				return false;
		} else if (!tempoTotalContribuicao.equals(other.tempoTotalContribuicao))
			return false;
		if (totalDiasAproveitados != other.totalDiasAproveitados)
			return false;
		if (totalDiasContribuicao != other.totalDiasContribuicao)
			return false;
		return true;
	}
}
