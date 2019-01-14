package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.iperonprev.constantes.SexoEnum;
/*
 * @autor Saulo Mendes
 * OneToMany Estado Civil
 * OneToMany Grau De Instrução
 * Sexo é uma enumeração do tipo String
 * Estado civil é uma enumeração do tipo int
 * */
@Entity
public class Pessoas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto = 0;
	private String DESC_nome;
	private Date DATA_nascimento;
	private Date DATA_obito;
	private String DESC_nacionalidade;
	private String DESC_ufNaturalidade;
	private String DESC_naturalidade;
	@Enumerated(EnumType.STRING)
	private SexoEnum DESC_sexo;
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL)
	private EstadoCivil NUMR_estadoCivil;
	@Column(length=100)
	private String DESC_mae;
	@Column(length=100)
	private String DESC_pai;
	@Column(length=14)
	private String NUMR_cpf;
	@Column(length=14)
	private String NUMR_pisPasep;
	@OneToOne
	private GrauDeInstrucao NUMR_instrucao;
	@Column(length=20)
	private String NUMR_identidade;
	private String NUMR_orgaoIdentidade;
	private Date DATA_emissaoIdentidade;
	@Column(length=20) 
	private String DESC_numCertNasc;
	@Column(length=20)
	private  String NUMR_livroCertNasc;
	@Column(length=20)
	private String NUMR_folhaCertNasc;
	@Column(length=20)
	private  String DESC_numCertCas;
	@Column(length=20)
	private  String NUMR_livroCertCas;
	@Column(length=20)
	private  String NUMR_folhaCertCas;
	private String NUMR_ctps;
	private Date DATA_expedicaoCtps;
	private String NUMR_serieCtps;
	@Column(length=20)
	private String NUMR_tituloEleitor;
	@Column(length=8)
	private String NUMR_tituloEleitorZona;
	@Column(length=10)
	private String NUMR_tituloEleitorSecao;
	@Column(length=20)
	private String DESC_ufExpedidorEleitor;
	private String NUMR_telefoneFixo;
	private String NUMR_telefoneCelular;
	private String DESC_email;
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	@ManyToOne(optional=true, fetch=FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
	private Enderecos NUMR_idDoObjetoEndereco;
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getDESC_nome() {
		return DESC_nome;
	}
	public void setDESC_nome(String dESC_nome) {
		DESC_nome = dESC_nome;
	}
	public Date getDATA_nascimento() {
		return DATA_nascimento;
	}
	public void setDATA_nascimento(Date dATA_nascimento) {
		DATA_nascimento = dATA_nascimento;
	}
	public Date getDATA_obito() {
		return DATA_obito;
	}
	public void setDATA_obito(Date dATA_obito) {
		DATA_obito = dATA_obito;
	}
	public String getDESC_nacionalidade() {
		return DESC_nacionalidade;
	}
	public void setDESC_nacionalidade(String dESC_nacionalidade) {
		DESC_nacionalidade = dESC_nacionalidade;
	}
	public String getDESC_ufNaturalidade() {
		return DESC_ufNaturalidade;
	}
	public void setDESC_ufNaturalidade(String dESC_ufNaturalidade) {
		DESC_ufNaturalidade = dESC_ufNaturalidade;
	}
	public String getDESC_naturalidade() {
		return DESC_naturalidade;
	}
	public void setDESC_naturalidade(String dESC_naturalidade) {
		DESC_naturalidade = dESC_naturalidade;
	}
	public SexoEnum getDESC_sexo() {
		return DESC_sexo;
	}
	public void setDESC_sexo(SexoEnum dESC_sexo) {
		DESC_sexo = dESC_sexo;
	}
	public EstadoCivil getNUMR_estadoCivil() {
		return NUMR_estadoCivil;
	}
	public void setNUMR_estadoCivil(EstadoCivil nUMR_estadoCivil) {
		NUMR_estadoCivil = nUMR_estadoCivil;
	}
	public String getDESC_mae() {
		return DESC_mae;
	}
	public void setDESC_mae(String dESC_mae) {
		DESC_mae = dESC_mae;
	}
	public String getDESC_pai() {
		return DESC_pai;
	}
	public void setDESC_pai(String dESC_pai) {
		DESC_pai = dESC_pai;
	}
	public String getNUMR_cpf() {
		return NUMR_cpf;
	}
	public void setNUMR_cpf(String nUMR_cpf) {
		NUMR_cpf = nUMR_cpf;
	}
	public String getNUMR_pisPasep() {
		return NUMR_pisPasep;
	}
	public void setNUMR_pisPasep(String nUMR_pisPasep) {
		NUMR_pisPasep = nUMR_pisPasep;
	}
	public GrauDeInstrucao getNUMR_instrucao() {
		return NUMR_instrucao;
	}
	public void setNUMR_instrucao(GrauDeInstrucao nUMR_instrucao) {
		NUMR_instrucao = nUMR_instrucao;
	}
	public String getNUMR_identidade() {
		return NUMR_identidade;
	}
	public void setNUMR_identidade(String nUMR_identidade) {
		NUMR_identidade = nUMR_identidade;
	}
	public String getNUMR_orgaoIdentidade() {
		return NUMR_orgaoIdentidade;
	}
	public void setNUMR_orgaoIdentidade(String nUMR_orgaoIdentidade) {
		NUMR_orgaoIdentidade = nUMR_orgaoIdentidade;
	}
	public Date getDATA_emissaoIdentidade() {
		return DATA_emissaoIdentidade;
	}
	public void setDATA_emissaoIdentidade(Date dATA_emissaoIdentidade) {
		DATA_emissaoIdentidade = dATA_emissaoIdentidade;
	}
	public String getDESC_numCertNasc() {
		return DESC_numCertNasc;
	}
	public void setDESC_numCertNasc(String dESC_numCertNasc) {
		DESC_numCertNasc = dESC_numCertNasc;
	}
	public String getNUMR_livroCertNasc() {
		return NUMR_livroCertNasc;
	}
	public void setNUMR_livroCertNasc(String nUMR_livroCertNasc) {
		NUMR_livroCertNasc = nUMR_livroCertNasc;
	}
	public String getNUMR_folhaCertNasc() {
		return NUMR_folhaCertNasc;
	}
	public void setNUMR_folhaCertNasc(String nUMR_folhaCertNasc) {
		NUMR_folhaCertNasc = nUMR_folhaCertNasc;
	}
	public String getDESC_numCertCas() {
		return DESC_numCertCas;
	}
	public void setDESC_numCertCas(String dESC_numCertCas) {
		DESC_numCertCas = dESC_numCertCas;
	}
	public String getNUMR_livroCertCas() {
		return NUMR_livroCertCas;
	}
	public void setNUMR_livroCertCas(String nUMR_livroCertCas) {
		NUMR_livroCertCas = nUMR_livroCertCas;
	}
	public String getNUMR_folhaCertCas() {
		return NUMR_folhaCertCas;
	}
	public void setNUMR_folhaCertCas(String nUMR_folhaCertCas) {
		NUMR_folhaCertCas = nUMR_folhaCertCas;
	}
	public String getNUMR_ctps() {
		return NUMR_ctps;
	}
	public void setNUMR_ctps(String nUMR_ctps) {
		NUMR_ctps = nUMR_ctps;
	}
	public Date getDATA_expedicaoCtps() {
		return DATA_expedicaoCtps;
	}
	public void setDATA_expedicaoCtps(Date dATA_expedicaoCtps) {
		DATA_expedicaoCtps = dATA_expedicaoCtps;
	}
	public String getNUMR_serieCtps() {
		return NUMR_serieCtps;
	}
	public void setNUMR_serieCtps(String nUMR_serieCtps) {
		NUMR_serieCtps = nUMR_serieCtps;
	}
	public String getNUMR_tituloEleitor() {
		return NUMR_tituloEleitor;
	}
	public void setNUMR_tituloEleitor(String nUMR_tituloEleitor) {
		NUMR_tituloEleitor = nUMR_tituloEleitor;
	}
	public String getNUMR_tituloEleitorZona() {
		return NUMR_tituloEleitorZona;
	}
	public void setNUMR_tituloEleitorZona(String nUMR_tituloEleitorZona) {
		NUMR_tituloEleitorZona = nUMR_tituloEleitorZona;
	}
	public String getNUMR_tituloEleitorSecao() {
		return NUMR_tituloEleitorSecao;
	}
	public void setNUMR_tituloEleitorSecao(String nUMR_tituloEleitorSecao) {
		NUMR_tituloEleitorSecao = nUMR_tituloEleitorSecao;
	}
	public String getDESC_ufExpedidorEleitor() {
		return DESC_ufExpedidorEleitor;
	}
	public void setDESC_ufExpedidorEleitor(String dESC_ufExpedidorEleitor) {
		DESC_ufExpedidorEleitor = dESC_ufExpedidorEleitor;
	}
	public String getNUMR_telefoneFixo() {
		return NUMR_telefoneFixo;
	}
	public void setNUMR_telefoneFixo(String nUMR_telefoneFixo) {
		NUMR_telefoneFixo = nUMR_telefoneFixo;
	}
	public String getNUMR_telefoneCelular() {
		return NUMR_telefoneCelular;
	}
	public void setNUMR_telefoneCelular(String nUMR_telefoneCelular) {
		NUMR_telefoneCelular = nUMR_telefoneCelular;
	}
	public String getDESC_email() {
		return DESC_email;
	}
	public void setDESC_email(String dESC_email) {
		DESC_email = dESC_email;
	}
	public Enderecos getNUMR_idDoObjetoEndereco() {
		return NUMR_idDoObjetoEndereco;
	}
	public void setNUMR_idDoObjetoEndereco(Enderecos nUMR_idDoObjetoEndereco) {
		NUMR_idDoObjetoEndereco = nUMR_idDoObjetoEndereco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
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
		Pessoas other = (Pessoas) obj;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		return true;
	}
	
}
