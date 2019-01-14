package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class CertidaoTempoContribuicao implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	
	private int NUMR_certidao;
	private int NUMR_ano;
	@OneToOne
	private AtosLegais NUMR_idAtosLegais;
	@OneToOne
	@NotNull(message="Órgão emissor obrigatório")
	private Orgaos NUMR_orgaoExpedidor;
	@NotNull(message="Fonte da informação obrigatória")
	private String DESC_fonte;
	@Lob
	private String DESC_observacao;
	private String DESC_rrc;
	private boolean FLAG_processoJudicial;
	@Temporal(TemporalType.DATE)
	private Calendar DATA_emissao = Calendar.getInstance();
	private boolean FLAG_impresso;
	private boolean FLAG_rrc;
	private boolean FLAG_salvo;
	private Date DATA_ultimaContribuicao;
	@OneToMany(fetch=FetchType.EAGER)
	private List<PessoasFuncionais> REL_listaDeFuncionais;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<DestinacaoCtc> REL_destinacao;
	@Version
	private int NUMR_versao;
	
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public int getNUMR_certidao() {
		return NUMR_certidao;
	}
	public void setNUMR_certidao(int nUMR_certidao) {
		NUMR_certidao = nUMR_certidao;
	}
	public int getNUMR_ano() {
		return NUMR_ano;
	}
	public void setNUMR_ano(int nUMR_ano) {
		NUMR_ano = nUMR_ano;
	}
	public AtosLegais getNUMR_idAtosLegais() {
		return NUMR_idAtosLegais;
	}
	public void setNUMR_idAtosLegais(AtosLegais nUMR_idAtosLegais) {
		NUMR_idAtosLegais = nUMR_idAtosLegais;
	}
	public Orgaos getNUMR_orgaoExpedidor() {
		return NUMR_orgaoExpedidor;
	}
	public void setNUMR_orgaoExpedidor(Orgaos nUMR_orgaoExpedidor) {
		NUMR_orgaoExpedidor = nUMR_orgaoExpedidor;
	}
	
	public String getDESC_fonte() {
		return DESC_fonte;
	}
	public void setDESC_fonte(String dESC_fonte) {
		DESC_fonte = dESC_fonte;
	}
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
	}
	public String getDESC_rrc() {
		return DESC_rrc;
	}
	public void setDESC_rrc(String dESC_rrc) {
		DESC_rrc = dESC_rrc;
	}
	public boolean isFLAG_processoJudicial() {
		return FLAG_processoJudicial;
	}
	public void setFLAG_processoJudicial(boolean fLAG_processoJudicial) {
		FLAG_processoJudicial = fLAG_processoJudicial;
	}
	public Calendar getDATA_emissao() {
		return DATA_emissao;
	}
	public void setDATA_emissao(Calendar dATA_emissao) {
		DATA_emissao = dATA_emissao;
	}
	public boolean isFLAG_impresso() {
		return FLAG_impresso;
	}
	public void setFLAG_impresso(boolean fLAG_impresso) {
		FLAG_impresso = fLAG_impresso;
	}
	public boolean isFLAG_rrc() {
		return FLAG_rrc;
	}
	public void setFLAG_rrc(boolean fLAG_rrc) {
		FLAG_rrc = fLAG_rrc;
	}
	public boolean isFLAG_salvo() {
		return FLAG_salvo;
	}
	public void setFLAG_salvo(boolean fLAG_salvo) {
		FLAG_salvo = fLAG_salvo;
	}
	public Date getDATA_ultimaContribuicao() {
		return DATA_ultimaContribuicao;
	}
	public void setDATA_ultimaContribuicao(Date dATA_ultimaContribuicao) {
		DATA_ultimaContribuicao = dATA_ultimaContribuicao;
	}
	public List<PessoasFuncionais> getREL_listaDeFuncionais() {
		return REL_listaDeFuncionais;
	}
	public void setREL_listaDeFuncionais(List<PessoasFuncionais> rEL_listaDeFuncionais) {
		REL_listaDeFuncionais = rEL_listaDeFuncionais;
	}
	public int getNUMR_versao() {
		return NUMR_versao;
	}
	public void setNUMR_versao(int nUMR_versao) {
		NUMR_versao = nUMR_versao;
	}
	
	public List<DestinacaoCtc> getREL_destinacao() {
		return REL_destinacao;
	}
	public void setREL_destinacao(List<DestinacaoCtc> rEL_destinacao) {
		REL_destinacao = rEL_destinacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + NUMR_certidao;
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
		CertidaoTempoContribuicao other = (CertidaoTempoContribuicao) obj;
		if (NUMR_certidao != other.NUMR_certidao)
			return false;
		return true;
	}
	
}
