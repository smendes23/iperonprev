package br.com.iperonprev.controller.dto;

import java.io.Serializable;

public class SimuladorDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tempoFaltanteCargo;
	private String tempoFaltanteCarreira;
	private String tempoFaltanteServicoPublico;
	private String tempoFaltanteContribuicao;
	private String tempoFaltanteIdade;
	private String faltanteTempoContribuicaoPedagio;
	private String requisitoTempoCargo;
	private String requisitoTempoCarreira;
	private String requisitoTempoServicoPublico;
	private String requisitoTempoContribuicao;
	private String requisitoContribuicaoPedagio;
	private String requisitoIdade;
	private String status;
	
	private String tituloRegra;
	private String descricaoRegra;
	private String tipoProventosReajustes;
	private String tituloCampoCargo;
	private String tituloCampoCarreira ;
	private String tituloCampoServicoPublico;
	private String tituloCampoTempoContribuicao;
	private String tituloCampoContribuicaoPedagio;
	private String tituloCampoIdade;
	
	public String getTempoFaltanteCargo() {
		return tempoFaltanteCargo;
	}
	public void setTempoFaltanteCargo(String tempoFaltanteCargo) {
		this.tempoFaltanteCargo = tempoFaltanteCargo;
		if(this.tempoFaltanteCargo.substring(0,1).equals("-")){
			this.tempoFaltanteCargo = "";
		}
	}
	public String getTempoFaltanteCarreira() {
		return tempoFaltanteCarreira;
	}
	public void setTempoFaltanteCarreira(String tempoFaltanteCarreira) {
		this.tempoFaltanteCarreira = tempoFaltanteCarreira;
		if(this.tempoFaltanteCarreira.substring(0,1).equals("-")){
			this.tempoFaltanteCarreira = "";
		}
	}
	public String getTempoFaltanteServicoPublico() {
		return tempoFaltanteServicoPublico;
	}
	public void setTempoFaltanteServicoPublico(String tempoFaltanteServicoPublico) {
		this.tempoFaltanteServicoPublico = tempoFaltanteServicoPublico;
		if(this.tempoFaltanteServicoPublico.substring(0,1).equals("-")){
			this.tempoFaltanteServicoPublico = "";
		}
	}
	public String getTempoFaltanteContribuicao() {
		return tempoFaltanteContribuicao;
	}
	public void setTempoFaltanteContribuicao(String tempoFaltanteContribuicao) {
		this.tempoFaltanteContribuicao = tempoFaltanteContribuicao;
		if(this.tempoFaltanteContribuicao.substring(0,1).equals("-")){
			this.tempoFaltanteContribuicao = "";
		}
	}
	public String getTempoFaltanteIdade() {
		return tempoFaltanteIdade;
	}
	public void setTempoFaltanteIdade(String tempoFaltanteIdade) {
		this.tempoFaltanteIdade = tempoFaltanteIdade;
		if(this.tempoFaltanteIdade.substring(0,1).equals("-")){
			this.tempoFaltanteIdade = "";
		}
	}
	public String getFaltanteTempoContribuicaoPedagio() {
		return faltanteTempoContribuicaoPedagio;
	}
	public void setFaltanteTempoContribuicaoPedagio(String faltanteTempoContribuicaoPedagio) {
		this.faltanteTempoContribuicaoPedagio = faltanteTempoContribuicaoPedagio;
		if(this.faltanteTempoContribuicaoPedagio.substring(0,1).equals("-")){
			this.faltanteTempoContribuicaoPedagio = "";
		}
	}
	public String getRequisitoTempoCargo() {
		return requisitoTempoCargo;
	}
	public void setRequisitoTempoCargo(String requisitoTempoCargo) {
		this.requisitoTempoCargo = requisitoTempoCargo;
	}
	public String getRequisitoTempoCarreira() {
		return requisitoTempoCarreira;
	}
	public void setRequisitoTempoCarreira(String requisitoTempoCarreira) {
		this.requisitoTempoCarreira = requisitoTempoCarreira;
	}
	
	public String getRequisitoTempoServicoPublico() {
		
		return requisitoTempoServicoPublico;
	}
	public void setRequisitoTempoServicoPublico(String requisitoTempoServicoPublico) {
		this.requisitoTempoServicoPublico = requisitoTempoServicoPublico;
	}
	public String getRequisitoTempoContribuicao() {
		return requisitoTempoContribuicao;
	}
	public void setRequisitoTempoContribuicao(String requisitoTempoContribuicao) {
		this.requisitoTempoContribuicao = requisitoTempoContribuicao;
	}
	public String getRequisitoContribuicaoPedagio() {
		return requisitoContribuicaoPedagio;
	}
	public void setRequisitoContribuicaoPedagio(String requisitoContribuicaoPedagio) {
		this.requisitoContribuicaoPedagio = requisitoContribuicaoPedagio;
	}
	public String getRequisitoIdade() {
		return requisitoIdade;
	}
	public void setRequisitoIdade(String requisitoIdade) {
		this.requisitoIdade = requisitoIdade;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTituloRegra() {
		return tituloRegra;
	}
	public void setTituloRegra(String tituloRegra) {
		this.tituloRegra = tituloRegra;
	}
	public String getDescricaoRegra() {
		return descricaoRegra;
	}
	public void setDescricaoRegra(String descricaoRegra) {
		this.descricaoRegra = descricaoRegra;
	}
	public String getTipoProventosReajustes() {
		return tipoProventosReajustes;
	}
	public void setTipoProventosReajustes(String tipoProventosReajustes) {
		this.tipoProventosReajustes = tipoProventosReajustes;
	}
	public String getTituloCampoCargo() {
		return tituloCampoCargo;
	}
	public void setTituloCampoCargo(String tituloCampoCargo) {
		this.tituloCampoCargo = tituloCampoCargo;
	}
	public String getTituloCampoCarreira() {
		return tituloCampoCarreira;
	}
	public void setTituloCampoCarreira(String tituloCampoCarreira) {
		this.tituloCampoCarreira = tituloCampoCarreira;
	}
	public String getTituloCampoServicoPublico() {
		return tituloCampoServicoPublico;
	}
	public void setTituloCampoServicoPublico(String tituloCampoServicoPublico) {
		this.tituloCampoServicoPublico = tituloCampoServicoPublico;
	}
	public String getTituloCampoTempoContribuicao() {
		return tituloCampoTempoContribuicao;
	}
	public void setTituloCampoTempoContribuicao(String tituloCampoTempoContribuicao) {
		this.tituloCampoTempoContribuicao = tituloCampoTempoContribuicao;
	}
	public String getTituloCampoContribuicaoPedagio() {
		return tituloCampoContribuicaoPedagio;
	}
	public void setTituloCampoContribuicaoPedagio(String tituloCampoContribuicaoPedagio) {
		this.tituloCampoContribuicaoPedagio = tituloCampoContribuicaoPedagio;
	}
	public String getTituloCampoIdade() {
		return tituloCampoIdade;
	}
	public void setTituloCampoIdade(String tituloCampoIdade) {
		this.tituloCampoIdade = tituloCampoIdade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoRegra == null) ? 0 : descricaoRegra.hashCode());
		result = prime * result
				+ ((faltanteTempoContribuicaoPedagio == null) ? 0 : faltanteTempoContribuicaoPedagio.hashCode());
		result = prime * result
				+ ((requisitoContribuicaoPedagio == null) ? 0 : requisitoContribuicaoPedagio.hashCode());
		result = prime * result + ((requisitoIdade == null) ? 0 : requisitoIdade.hashCode());
		result = prime * result + ((requisitoTempoCargo == null) ? 0 : requisitoTempoCargo.hashCode());
		result = prime * result + ((requisitoTempoCarreira == null) ? 0 : requisitoTempoCarreira.hashCode());
		result = prime * result + ((requisitoTempoContribuicao == null) ? 0 : requisitoTempoContribuicao.hashCode());
		result = prime * result
				+ ((requisitoTempoServicoPublico == null) ? 0 : requisitoTempoServicoPublico.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tempoFaltanteCargo == null) ? 0 : tempoFaltanteCargo.hashCode());
		result = prime * result + ((tempoFaltanteCarreira == null) ? 0 : tempoFaltanteCarreira.hashCode());
		result = prime * result + ((tempoFaltanteContribuicao == null) ? 0 : tempoFaltanteContribuicao.hashCode());
		result = prime * result + ((tempoFaltanteIdade == null) ? 0 : tempoFaltanteIdade.hashCode());
		result = prime * result + ((tempoFaltanteServicoPublico == null) ? 0 : tempoFaltanteServicoPublico.hashCode());
		result = prime * result + ((tipoProventosReajustes == null) ? 0 : tipoProventosReajustes.hashCode());
		result = prime * result + ((tituloCampoCargo == null) ? 0 : tituloCampoCargo.hashCode());
		result = prime * result + ((tituloCampoCarreira == null) ? 0 : tituloCampoCarreira.hashCode());
		result = prime * result
				+ ((tituloCampoContribuicaoPedagio == null) ? 0 : tituloCampoContribuicaoPedagio.hashCode());
		result = prime * result + ((tituloCampoIdade == null) ? 0 : tituloCampoIdade.hashCode());
		result = prime * result + ((tituloCampoServicoPublico == null) ? 0 : tituloCampoServicoPublico.hashCode());
		result = prime * result
				+ ((tituloCampoTempoContribuicao == null) ? 0 : tituloCampoTempoContribuicao.hashCode());
		result = prime * result + ((tituloRegra == null) ? 0 : tituloRegra.hashCode());
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
		SimuladorDto other = (SimuladorDto) obj;
		if (descricaoRegra == null) {
			if (other.descricaoRegra != null)
				return false;
		} else if (!descricaoRegra.equals(other.descricaoRegra))
			return false;
		if (faltanteTempoContribuicaoPedagio == null) {
			if (other.faltanteTempoContribuicaoPedagio != null)
				return false;
		} else if (!faltanteTempoContribuicaoPedagio.equals(other.faltanteTempoContribuicaoPedagio))
			return false;
		if (requisitoContribuicaoPedagio == null) {
			if (other.requisitoContribuicaoPedagio != null)
				return false;
		} else if (!requisitoContribuicaoPedagio.equals(other.requisitoContribuicaoPedagio))
			return false;
		if (requisitoIdade == null) {
			if (other.requisitoIdade != null)
				return false;
		} else if (!requisitoIdade.equals(other.requisitoIdade))
			return false;
		if (requisitoTempoCargo == null) {
			if (other.requisitoTempoCargo != null)
				return false;
		} else if (!requisitoTempoCargo.equals(other.requisitoTempoCargo))
			return false;
		if (requisitoTempoCarreira == null) {
			if (other.requisitoTempoCarreira != null)
				return false;
		} else if (!requisitoTempoCarreira.equals(other.requisitoTempoCarreira))
			return false;
		if (requisitoTempoContribuicao == null) {
			if (other.requisitoTempoContribuicao != null)
				return false;
		} else if (!requisitoTempoContribuicao.equals(other.requisitoTempoContribuicao))
			return false;
		if (requisitoTempoServicoPublico == null) {
			if (other.requisitoTempoServicoPublico != null)
				return false;
		} else if (!requisitoTempoServicoPublico.equals(other.requisitoTempoServicoPublico))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tempoFaltanteCargo == null) {
			if (other.tempoFaltanteCargo != null)
				return false;
		} else if (!tempoFaltanteCargo.equals(other.tempoFaltanteCargo))
			return false;
		if (tempoFaltanteCarreira == null) {
			if (other.tempoFaltanteCarreira != null)
				return false;
		} else if (!tempoFaltanteCarreira.equals(other.tempoFaltanteCarreira))
			return false;
		if (tempoFaltanteContribuicao == null) {
			if (other.tempoFaltanteContribuicao != null)
				return false;
		} else if (!tempoFaltanteContribuicao.equals(other.tempoFaltanteContribuicao))
			return false;
		if (tempoFaltanteIdade == null) {
			if (other.tempoFaltanteIdade != null)
				return false;
		} else if (!tempoFaltanteIdade.equals(other.tempoFaltanteIdade))
			return false;
		if (tempoFaltanteServicoPublico == null) {
			if (other.tempoFaltanteServicoPublico != null)
				return false;
		} else if (!tempoFaltanteServicoPublico.equals(other.tempoFaltanteServicoPublico))
			return false;
		if (tipoProventosReajustes == null) {
			if (other.tipoProventosReajustes != null)
				return false;
		} else if (!tipoProventosReajustes.equals(other.tipoProventosReajustes))
			return false;
		if (tituloCampoCargo == null) {
			if (other.tituloCampoCargo != null)
				return false;
		} else if (!tituloCampoCargo.equals(other.tituloCampoCargo))
			return false;
		if (tituloCampoCarreira == null) {
			if (other.tituloCampoCarreira != null)
				return false;
		} else if (!tituloCampoCarreira.equals(other.tituloCampoCarreira))
			return false;
		if (tituloCampoContribuicaoPedagio == null) {
			if (other.tituloCampoContribuicaoPedagio != null)
				return false;
		} else if (!tituloCampoContribuicaoPedagio.equals(other.tituloCampoContribuicaoPedagio))
			return false;
		if (tituloCampoIdade == null) {
			if (other.tituloCampoIdade != null)
				return false;
		} else if (!tituloCampoIdade.equals(other.tituloCampoIdade))
			return false;
		if (tituloCampoServicoPublico == null) {
			if (other.tituloCampoServicoPublico != null)
				return false;
		} else if (!tituloCampoServicoPublico.equals(other.tituloCampoServicoPublico))
			return false;
		if (tituloCampoTempoContribuicao == null) {
			if (other.tituloCampoTempoContribuicao != null)
				return false;
		} else if (!tituloCampoTempoContribuicao.equals(other.tituloCampoTempoContribuicao))
			return false;
		if (tituloRegra == null) {
			if (other.tituloRegra != null)
				return false;
		} else if (!tituloRegra.equals(other.tituloRegra))
			return false;
		return true;
	}
	
	
}
