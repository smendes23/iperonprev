package br.com.iperonprev.inigracao;

public class Remuneracao{

	private static final long serialVersionUID = 1L;
	private String poder;
	private String orgao;
	private String matricula;
	private String cpf;
	private String codigo_cargo;
	private String data_posse;
	private String depatarmento;
	private String numero_folha;
	private String numero_rubrica;
	private String valor_rubrica;
	private String competencia;
	public String getPoder() {
		return poder;
	}
	
	public String getOrgao() {
		return orgao;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getCodigo_cargo() {
		return codigo_cargo;
	}
	
	public String getData_posse() {
		return data_posse;
	}
	
	public String getDepatarmento() {
		return depatarmento;
	}
	
	public String getNumero_folha() {
		return numero_folha;
	}
	
	public String getNumero_rubrica() {
		return numero_rubrica;
	}
	
	public String getValor_rubrica() {
		return valor_rubrica;
	}
	
	public String getCompetencia() {
		return competencia;
	}
	
	public Remuneracao(String poder, String orgao, String matricula, String cpf, String codigo_cargo, String data_posse,
			String depatarmento, String numero_folha, String numero_rubrica, String valor_rubrica, String competencia) {
		this.poder = poder;
		this.orgao = orgao;
		this.matricula = matricula;
		this.cpf = cpf;
		this.codigo_cargo = codigo_cargo;
		this.data_posse = data_posse;
		this.depatarmento = depatarmento;
		this.numero_folha = numero_folha;
		this.numero_rubrica = numero_rubrica;
		this.valor_rubrica = valor_rubrica;
		this.competencia = competencia;
	}
	
	public Remuneracao() {}
	public Remuneracao(String line) {
		this.poder = line.substring(0, 1);
		this.orgao = line.substring(2, 11).replaceFirst("^0+(?!$)", "");
		this.matricula = line.substring(11, 21).replaceFirst("^0+(?!$)", "");
		this.cpf = line.substring(21, 32);
		this.codigo_cargo = line.substring(32, 42).replaceFirst("^0+(?!$)", "");
		this.data_posse = line.substring(42, 52);
		this.depatarmento = line.substring(52, 55);
		this.numero_folha = line.substring(55, 57);
		this.numero_rubrica = line.substring(57, 67);
		this.valor_rubrica = new StringBuilder().append(line.substring(67, 80).replaceFirst("^0+(?!$)", "")).append(".").append(line.substring(80, 82)).toString();
		this.competencia = line.substring(82, 88);
	}


	@Override
	public String toString() {
		return "Poder: "+this.poder+" - "+
		"Orgao: "+this.orgao+" - "+
		"Matricula: "+this.matricula+" - "+
		"CPF: "+this.cpf+" - "+
		"Cargo: "+this.codigo_cargo+" - "+
		"Posse: "+this.data_posse+" - "+
		"Departamento: "+this.depatarmento+" - "+
		"Folha: "+this.numero_folha+" - "+
		"Numero Rubrica: "+this.numero_rubrica+" - "+
		"Valor: "+this.valor_rubrica+" - "+
		"Competencia: "+this.competencia;
	}
	
	
}
