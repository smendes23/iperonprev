package br.com.iperonprev.constantes;

public enum RegrasAposentadoriaEnum {
	/*Regras Permanentes*/
	ARTIGO40INVALIDEZ("Artigo 40, § 1º, inciso I, da Constituição Federal, com redação da EC nº 41/2003"),
	ARTIGO40COMPULSORIA("Artigo 40, § 1º, inciso II, da Constituição Federal, com redação da EC nº 41/2003"),/*Se professor: Proventos integrais senão proporcionais*/
	
	/*Regras de Transição*/
	ARTIGO2("Regra de Transição(Art. 2º EC 41) de acordo lei 10.887/04"),/*Tempo de contribuição, proventos proporcionais checked*/
	ARTIGO3("Regra Art. 3º, EC47/2005"), /*Checked* idade e tempo de contribuição*/
	ARTIGO6("Regra de Transição(Art. 6º EC 41)"), /*Integralidade e Paridade, Idade e tempo de contribuição checked*/
	ARTIGO8I("Regra de Transição (Caput do art. 8º da EC20) Integral - Regra do Direito Adquirido"), /*checked idade e tempo de contribuição integralidade e paridade*/
	ARTIGO8P("Regra de Transição(Art. 8º, § 1º da EC20) Proporcional - Regra do Direito Adquirido")/*Paridade com proporcionalidade*/,
	ARTIGO40A20("Regra Anterior Art. 40, § 1º, III, alínea “a” da CF(EC20) Integral - Regra Por Idade da EC Nº 20/98"),
	ARTIGO40B20("Regra Anterior Art. 40, § 1º, III, alínea “b” da CF(EC20) Proporcional - Regra Por Idade da EC Nº 20/98"),
	ARTIGO40A("Regra Permanente Art. 40, § 1º, III, alínea “a” da CF(EC41) de acordo lei 10.887/04 - Regra Geral"),/*Proventos Integrais || idade e tempo de contribuição*/
	ARTIGO40B("Regra Permanente Art. 40, § 1º, III, alínea “b” da CF(EC41) de acordo lei 10.887/04 - Proporcional - Regra Proporcional Por Idade"),/*Proventos Proporcionais idade checked*/
	ARTIGOPCI("Regra de Policial Civil de acordo com a Lei Complementar Nº 59, de Novembro de 2006."),
	ARTIGOPCP("Regra Policial Civil Art. 1º, de acordo lei 10.887/04"),
	JUDICIAL("Conforme égide jurídica anexada"),
	ARTIGO22("Artigo 22 da LC 432/2008"),
	ARTIGO23("Artigo 23 da LC 432/2008"),
	ARTIGO24("Artigo 24 da LC 432/2008");
	
	private String nome;
	
	RegrasAposentadoriaEnum(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
