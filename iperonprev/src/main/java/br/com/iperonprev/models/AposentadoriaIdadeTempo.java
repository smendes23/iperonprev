package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.constantes.TipoReajusteEnum;

@Entity
public class AposentadoriaIdadeTempo implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@OneToOne
	private PessoasFuncionais NUMR_idDoObejtoPessoasFuncionais;
	private String NUMR_processo;
	@Enumerated(EnumType.STRING)
	private TipoProventosEnum ENUM_proventos;
	@Enumerated(EnumType.STRING)
	private TipoReajusteEnum ENUM_reajustes;
	private String NUMR_atoConcessorio;
	private Date DATA_publicacao;
	private String DESC_doe;
	private Date DATA_revisaoProcesso;
	@ManyToOne
	private MotivoCessacaoBeneficio NUMR_motivoFimBeneficio;
	private Date DATA_fimBeneficio;
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	private List<AtosLegais> REL_atoLegais = new ArrayList<>();
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
	private List<DecisoesJudiciais> REL_decisaoJudical = new ArrayList<>();
	private transient int NUMR_idade;
	private transient String DESC_tempoDeContruibuicao;
	@Version
	private int NUMR_versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_idDoObejtoPessoasFuncionais() {
		return NUMR_idDoObejtoPessoasFuncionais;
	}
	public void setNUMR_idDoObejtoPessoasFuncionais(PessoasFuncionais nUMR_idDoObejtoPessoasFuncionais) {
		NUMR_idDoObejtoPessoasFuncionais = nUMR_idDoObejtoPessoasFuncionais;
	}
	public String getNUMR_processo() {
		return NUMR_processo;
	}
	public void setNUMR_processo(String nUMR_processo) {
		NUMR_processo = nUMR_processo;
	}
	public TipoProventosEnum getENUM_proventos() {
		return ENUM_proventos;
	}
	public void setENUM_proventos(TipoProventosEnum eNUM_proventos) {
		ENUM_proventos = eNUM_proventos;
	}
	public TipoReajusteEnum getENUM_reajustes() {
		return ENUM_reajustes;
	}
	public void setENUM_reajustes(TipoReajusteEnum eNUM_reajustes) {
		ENUM_reajustes = eNUM_reajustes;
	}
	public String getNUMR_atoConcessorio() {
		return NUMR_atoConcessorio;
	}
	public void setNUMR_atoConcessorio(String nUMR_atoConcessorio) {
		NUMR_atoConcessorio = nUMR_atoConcessorio;
	}
	public Date getDATA_publicacao() {
		return DATA_publicacao;
	}
	public void setDATA_publicacao(Date dATA_publicacao) {
		DATA_publicacao = dATA_publicacao;
	}
	public String getDESC_doe() {
		return DESC_doe;
	}
	public void setDESC_doe(String dESC_doe) {
		DESC_doe = dESC_doe;
	}
	public Date getDATA_revisaoProcesso() {
		return DATA_revisaoProcesso;
	}
	public void setDATA_revisaoProcesso(Date dATA_revisaoProcesso) {
		DATA_revisaoProcesso = dATA_revisaoProcesso;
	}
	public MotivoCessacaoBeneficio getNUMR_motivoFimBeneficio() {
		return NUMR_motivoFimBeneficio;
	}
	public void setNUMR_motivoFimBeneficio(MotivoCessacaoBeneficio nUMR_motivoFimBeneficio) {
		NUMR_motivoFimBeneficio = nUMR_motivoFimBeneficio;
	}
	public Date getDATA_fimBeneficio() {
		return DATA_fimBeneficio;
	}
	public void setDATA_fimBeneficio(Date dATA_fimBeneficio) {
		DATA_fimBeneficio = dATA_fimBeneficio;
	}
	public List<AtosLegais> getREL_atoLegais() {
		return REL_atoLegais;
	}
	public void setREL_atoLegais(List<AtosLegais> rEL_atoLegais) {
		REL_atoLegais = rEL_atoLegais;
	}
	public List<DecisoesJudiciais> getREL_decisaoJudical() {
		return REL_decisaoJudical;
	}
	public void setREL_decisaoJudical(List<DecisoesJudiciais> rEL_decisaoJudical) {
		REL_decisaoJudical = rEL_decisaoJudical;
	}
	public int getNUMR_idade() {
		return NUMR_idade;
	}
	public void setNUMR_idade(int nUMR_idade) {
		NUMR_idade = nUMR_idade;
	}
	public String getDESC_tempoDeContruibuicao() {
		return DESC_tempoDeContruibuicao;
	}
	public void setDESC_tempoDeContruibuicao(String dESC_tempoDeContruibuicao) {
		DESC_tempoDeContruibuicao = dESC_tempoDeContruibuicao;
	}
	public int getNUMR_versao() {
		return NUMR_versao;
	}
	public void setNUMR_versao(int nUMR_versao) {
		NUMR_versao = nUMR_versao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_fimBeneficio == null) ? 0 : DATA_fimBeneficio.hashCode());
		result = prime * result + ((DATA_publicacao == null) ? 0 : DATA_publicacao.hashCode());
		result = prime * result + ((DATA_revisaoProcesso == null) ? 0 : DATA_revisaoProcesso.hashCode());
		result = prime * result + ((DESC_doe == null) ? 0 : DESC_doe.hashCode());
		result = prime * result + ((ENUM_proventos == null) ? 0 : ENUM_proventos.hashCode());
		result = prime * result + ((ENUM_reajustes == null) ? 0 : ENUM_reajustes.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_atoConcessorio == null) ? 0 : NUMR_atoConcessorio.hashCode());
		result = prime * result
				+ ((NUMR_idDoObejtoPessoasFuncionais == null) ? 0 : NUMR_idDoObejtoPessoasFuncionais.hashCode());
		result = prime * result + ((NUMR_motivoFimBeneficio == null) ? 0 : NUMR_motivoFimBeneficio.hashCode());
		result = prime * result + ((NUMR_processo == null) ? 0 : NUMR_processo.hashCode());
		result = prime * result + NUMR_versao;
		result = prime * result + ((REL_atoLegais == null) ? 0 : REL_atoLegais.hashCode());
		result = prime * result + ((REL_decisaoJudical == null) ? 0 : REL_decisaoJudical.hashCode());
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
		AposentadoriaIdadeTempo other = (AposentadoriaIdadeTempo) obj;
		if (DATA_fimBeneficio == null) {
			if (other.DATA_fimBeneficio != null)
				return false;
		} else if (!DATA_fimBeneficio.equals(other.DATA_fimBeneficio))
			return false;
		if (DATA_publicacao == null) {
			if (other.DATA_publicacao != null)
				return false;
		} else if (!DATA_publicacao.equals(other.DATA_publicacao))
			return false;
		if (DATA_revisaoProcesso == null) {
			if (other.DATA_revisaoProcesso != null)
				return false;
		} else if (!DATA_revisaoProcesso.equals(other.DATA_revisaoProcesso))
			return false;
		if (DESC_doe == null) {
			if (other.DESC_doe != null)
				return false;
		} else if (!DESC_doe.equals(other.DESC_doe))
			return false;
		if (ENUM_proventos != other.ENUM_proventos)
			return false;
		if (ENUM_reajustes != other.ENUM_reajustes)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_atoConcessorio == null) {
			if (other.NUMR_atoConcessorio != null)
				return false;
		} else if (!NUMR_atoConcessorio.equals(other.NUMR_atoConcessorio))
			return false;
		if (NUMR_idDoObejtoPessoasFuncionais == null) {
			if (other.NUMR_idDoObejtoPessoasFuncionais != null)
				return false;
		} else if (!NUMR_idDoObejtoPessoasFuncionais.equals(other.NUMR_idDoObejtoPessoasFuncionais))
			return false;
		if (NUMR_motivoFimBeneficio == null) {
			if (other.NUMR_motivoFimBeneficio != null)
				return false;
		} else if (!NUMR_motivoFimBeneficio.equals(other.NUMR_motivoFimBeneficio))
			return false;
		if (NUMR_processo == null) {
			if (other.NUMR_processo != null)
				return false;
		} else if (!NUMR_processo.equals(other.NUMR_processo))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		if (REL_atoLegais == null) {
			if (other.REL_atoLegais != null)
				return false;
		} else if (!REL_atoLegais.equals(other.REL_atoLegais))
			return false;
		if (REL_decisaoJudical == null) {
			if (other.REL_decisaoJudical != null)
				return false;
		} else if (!REL_decisaoJudical.equals(other.REL_decisaoJudical))
			return false;
		return true;
	}
	
}
