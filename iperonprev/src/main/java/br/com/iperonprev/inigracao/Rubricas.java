package br.com.iperonprev.inigracao;

public class Rubricas{

	private static final long serialVersionUID = 1L;

	private String poder;
	private String orgao;
	private String departamento;
	private String codigo;
	private String descricao;
	private String tipoRubrica;
	private String formatoRubrica;
	private String incidenciaContribuicao;
	private String dataInicio;
	private String dataFim;
	private String contribPrevidenciaria;
	

	public Rubricas(String poder, String orgao, String departamento, String codigo, String descricao, String tipoRubrica, 
			String formatoRubrica, String incidenciaContribuicao, String dataInicio, String dataFim, String contribPrevidenciaria) {
		this.poder = poder;
		this.orgao = orgao;
		this.departamento = departamento;
		this.codigo = codigo;
		this.descricao = descricao;
		this.tipoRubrica = tipoRubrica;
		this.formatoRubrica = formatoRubrica;
		this.incidenciaContribuicao = incidenciaContribuicao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.contribPrevidenciaria = contribPrevidenciaria;		
	}

	public String getPoder() {
		return poder;
	}

	public String getOrgao() {
		return orgao;
	}

	public String getDepartamento() {
		return departamento;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}


	public String getTipoRubrica() {
		return tipoRubrica;
	}

	public String getFormatoRubrica() {
		return formatoRubrica;
	}

	public String getIncidenciaContribuicao() {
		return incidenciaContribuicao;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public String getContribPrevidenciaria() {
		return contribPrevidenciaria;
	}

	
	
	public Rubricas(String line) {
		this.poder = line.substring(1, 2);
		this.orgao = line.substring(2, 12).replaceFirst("^0+(?!$)", "");
		this.departamento = line.substring(12, 22).replaceFirst("^0+(?!$)", "");
		this.codigo = line.substring(22, 32);
		this.descricao = line.substring(32, 82);
		this.tipoRubrica = line.substring(82, 83);
		this.formatoRubrica = line.substring(83, 84);
		this.incidenciaContribuicao = line.substring(84, 85);
		this.dataInicio = line.substring(85, 95);
		this.dataFim = line.substring(95, 105);
		this.contribPrevidenciaria = line.substring(105, 106);
	}


	public Rubricas() {
	}
	
	
	public String toString() {
		return "Poder: "+this.poder+" | "+
				"Orgao: "+this.orgao+" | "+
				"Departamento: "+this.departamento+" | "+
				"Código: "+this.codigo+" | "+
				"Descrição: "+this.descricao.trim()+" | "+
				"Tipo Rubrica: "+this.tipoRubrica+" | "+
				"Formato Rubrica: "+this.formatoRubrica+" | "+
				"Incidência Contribuição: "+this.incidenciaContribuicao+" | "+
				"Data Início: "+this.dataInicio+" | "+
				"Data Fim: "+this.dataFim+" | "+
				"Contribuição Previdenciária: "+this.contribPrevidenciaria+" | ";				
	}

	
	
}
