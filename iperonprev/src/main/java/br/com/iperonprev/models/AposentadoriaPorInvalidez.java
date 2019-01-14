package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import br.com.iperonprev.constantes.StatusBeneficioEnum;
import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.constantes.TipoReajusteEnum;

@Entity
public class AposentadoriaPorInvalidez implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	private String NUMR_processo;
	@OneToOne
	private MotivoConcessaoBeneficio NUMR_motivoConcessaoBeneficio;
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	private List<AtosLegais> REL_atoLegais;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Cid> NUMR_cid;
	@Enumerated(EnumType.STRING)
	private TipoProventosEnum ENUM_proventos;
	@Enumerated(EnumType.STRING)
	private TipoReajusteEnum ENUM_reajuste;
	@OneToOne
	private PessoasFuncionais NUMR_idDoObejtoPessoasFuncionais;
	private Date DATA_publicacao;
	private String DESC_doe;
	private Date DATA_revisaoProcesso;
	private Date DATA_ultimaPericia;
	private Date DATA_proximaPericia;
	@OneToOne
	private MotivoCessacaoBeneficio NUMR_motivoCessacaoBeneficio;
	private Date DATA_cessacao;
	private Date DATA_retorno;
	@OneToOne
	private Portaria NUMR_idDoObjetoPortaria;
	private transient int NUMR_idade;
	private transient String DESC_tempoDeContruibuicao;
	private transient String DESC_mensagem;
	private transient StatusBeneficioEnum ENUM_status;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
	private List<AtestadosMedicos> REL_atestadoMedico = new ArrayList<>();
	@Column(nullable=true)
	private String NUMR_atoConcessorio;
	@Version
	private int NUMR_versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getNUMR_processo() {
		return NUMR_processo;
	}
	public void setNUMR_processo(String nUMR_processo) {
		NUMR_processo = nUMR_processo;
	}
	public MotivoConcessaoBeneficio getNUMR_motivoConcessaoBeneficio() {
		return NUMR_motivoConcessaoBeneficio;
	}
	public void setNUMR_motivoConcessaoBeneficio(MotivoConcessaoBeneficio nUMR_motivoConcessaoBeneficio) {
		NUMR_motivoConcessaoBeneficio = nUMR_motivoConcessaoBeneficio;
	}
	public List<AtosLegais> getREL_atoLegais() {
		return REL_atoLegais;
	}
	public void setREL_atoLegais(List<AtosLegais> rEL_atoLegais) {
		REL_atoLegais = rEL_atoLegais;
	}
	public List<Cid> getNUMR_cid() {
		return NUMR_cid;
	}
	public void setNUMR_cid(List<Cid> nUMR_cid) {
		NUMR_cid = nUMR_cid;
	}
	public TipoProventosEnum getENUM_proventos() {
		return ENUM_proventos;
	}
	public void setENUM_proventos(TipoProventosEnum eNUM_proventos) {
		ENUM_proventos = eNUM_proventos;
	}
	public TipoReajusteEnum getENUM_reajuste() {
		return ENUM_reajuste;
	}
	public void setENUM_reajuste(TipoReajusteEnum eNUM_reajuste) {
		ENUM_reajuste = eNUM_reajuste;
	}
	public PessoasFuncionais getNUMR_idDoObejtoPessoasFuncionais() {
		return NUMR_idDoObejtoPessoasFuncionais;
	}
	public void setNUMR_idDoObejtoPessoasFuncionais(PessoasFuncionais nUMR_idDoObejtoPessoasFuncionais) {
		NUMR_idDoObejtoPessoasFuncionais = nUMR_idDoObejtoPessoasFuncionais;
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
	public Date getDATA_ultimaPericia() {
		return DATA_ultimaPericia;
	}
	public void setDATA_ultimaPericia(Date dATA_ultimaPericia) {
		DATA_ultimaPericia = dATA_ultimaPericia;
	}
	public Date getDATA_proximaPericia() {
		return DATA_proximaPericia;
	}
	public void setDATA_proximaPericia(Date dATA_proximaPericia) {
		DATA_proximaPericia = dATA_proximaPericia;
	}
	public MotivoCessacaoBeneficio getNUMR_motivoCessacaoBeneficio() {
		return NUMR_motivoCessacaoBeneficio;
	}
	public void setNUMR_motivoCessacaoBeneficio(MotivoCessacaoBeneficio nUMR_motivoCessacaoBeneficio) {
		NUMR_motivoCessacaoBeneficio = nUMR_motivoCessacaoBeneficio;
	}
	public Date getDATA_cessacao() {
		return DATA_cessacao;
	}
	public void setDATA_cessacao(Date dATA_cessacao) {
		DATA_cessacao = dATA_cessacao;
	}
	public Date getDATA_retorno() {
		return DATA_retorno;
	}
	public void setDATA_retorno(Date dATA_retorno) {
		DATA_retorno = dATA_retorno;
	}
	public Portaria getNUMR_idDoObjetoPortaria() {
		return NUMR_idDoObjetoPortaria;
	}
	public void setNUMR_idDoObjetoPortaria(Portaria nUMR_idDoObjetoPortaria) {
		NUMR_idDoObjetoPortaria = nUMR_idDoObjetoPortaria;
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
	public String getDESC_mensagem() {
		return DESC_mensagem;
	}
	public void setDESC_mensagem(String dESC_mensagem) {
		DESC_mensagem = dESC_mensagem;
	}
	public StatusBeneficioEnum getENUM_status() {
		return ENUM_status;
	}
	public void setENUM_status(StatusBeneficioEnum eNUM_status) {
		ENUM_status = eNUM_status;
	}
	public List<AtestadosMedicos> getREL_atestadoMedico() {
		return REL_atestadoMedico;
	}
	public void setREL_atestadoMedico(List<AtestadosMedicos> rEL_atestadoMedico) {
		REL_atestadoMedico = rEL_atestadoMedico;
	}
	public int getNUMR_versao() {
		return NUMR_versao;
	}
	public void setNUMR_versao(int nUMR_versao) {
		NUMR_versao = nUMR_versao;
	}
	
	public String getNUMR_atoConcessorio() {
		return NUMR_atoConcessorio;
	}
	public void setNUMR_atoConcessorio(String nUMR_atoConcessorio) {
		NUMR_atoConcessorio = nUMR_atoConcessorio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DATA_cessacao == null) ? 0 : DATA_cessacao.hashCode());
		result = prime * result + ((DATA_proximaPericia == null) ? 0 : DATA_proximaPericia.hashCode());
		result = prime * result + ((DATA_publicacao == null) ? 0 : DATA_publicacao.hashCode());
		result = prime * result + ((DATA_retorno == null) ? 0 : DATA_retorno.hashCode());
		result = prime * result + ((DATA_revisaoProcesso == null) ? 0 : DATA_revisaoProcesso.hashCode());
		result = prime * result + ((DATA_ultimaPericia == null) ? 0 : DATA_ultimaPericia.hashCode());
		result = prime * result + ((DESC_doe == null) ? 0 : DESC_doe.hashCode());
		result = prime * result + ((ENUM_proventos == null) ? 0 : ENUM_proventos.hashCode());
		result = prime * result + ((ENUM_reajuste == null) ? 0 : ENUM_reajuste.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_cid == null) ? 0 : NUMR_cid.hashCode());
		result = prime * result
				+ ((NUMR_idDoObejtoPessoasFuncionais == null) ? 0 : NUMR_idDoObejtoPessoasFuncionais.hashCode());
		result = prime * result + ((NUMR_idDoObjetoPortaria == null) ? 0 : NUMR_idDoObjetoPortaria.hashCode());
		result = prime * result
				+ ((NUMR_motivoCessacaoBeneficio == null) ? 0 : NUMR_motivoCessacaoBeneficio.hashCode());
		result = prime * result
				+ ((NUMR_motivoConcessaoBeneficio == null) ? 0 : NUMR_motivoConcessaoBeneficio.hashCode());
		result = prime * result + ((NUMR_processo == null) ? 0 : NUMR_processo.hashCode());
		result = prime * result + NUMR_versao;
		result = prime * result + ((REL_atestadoMedico == null) ? 0 : REL_atestadoMedico.hashCode());
		result = prime * result + ((REL_atoLegais == null) ? 0 : REL_atoLegais.hashCode());
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
		AposentadoriaPorInvalidez other = (AposentadoriaPorInvalidez) obj;
		if (DATA_cessacao == null) {
			if (other.DATA_cessacao != null)
				return false;
		} else if (!DATA_cessacao.equals(other.DATA_cessacao))
			return false;
		if (DATA_proximaPericia == null) {
			if (other.DATA_proximaPericia != null)
				return false;
		} else if (!DATA_proximaPericia.equals(other.DATA_proximaPericia))
			return false;
		if (DATA_publicacao == null) {
			if (other.DATA_publicacao != null)
				return false;
		} else if (!DATA_publicacao.equals(other.DATA_publicacao))
			return false;
		if (DATA_retorno == null) {
			if (other.DATA_retorno != null)
				return false;
		} else if (!DATA_retorno.equals(other.DATA_retorno))
			return false;
		if (DATA_revisaoProcesso == null) {
			if (other.DATA_revisaoProcesso != null)
				return false;
		} else if (!DATA_revisaoProcesso.equals(other.DATA_revisaoProcesso))
			return false;
		if (DATA_ultimaPericia == null) {
			if (other.DATA_ultimaPericia != null)
				return false;
		} else if (!DATA_ultimaPericia.equals(other.DATA_ultimaPericia))
			return false;
		if (DESC_doe == null) {
			if (other.DESC_doe != null)
				return false;
		} else if (!DESC_doe.equals(other.DESC_doe))
			return false;
		if (ENUM_proventos != other.ENUM_proventos)
			return false;
		if (ENUM_reajuste != other.ENUM_reajuste)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_cid == null) {
			if (other.NUMR_cid != null)
				return false;
		} else if (!NUMR_cid.equals(other.NUMR_cid))
			return false;
		if (NUMR_idDoObejtoPessoasFuncionais == null) {
			if (other.NUMR_idDoObejtoPessoasFuncionais != null)
				return false;
		} else if (!NUMR_idDoObejtoPessoasFuncionais.equals(other.NUMR_idDoObejtoPessoasFuncionais))
			return false;
		if (NUMR_idDoObjetoPortaria == null) {
			if (other.NUMR_idDoObjetoPortaria != null)
				return false;
		} else if (!NUMR_idDoObjetoPortaria.equals(other.NUMR_idDoObjetoPortaria))
			return false;
		if (NUMR_motivoCessacaoBeneficio == null) {
			if (other.NUMR_motivoCessacaoBeneficio != null)
				return false;
		} else if (!NUMR_motivoCessacaoBeneficio.equals(other.NUMR_motivoCessacaoBeneficio))
			return false;
		if (NUMR_motivoConcessaoBeneficio == null) {
			if (other.NUMR_motivoConcessaoBeneficio != null)
				return false;
		} else if (!NUMR_motivoConcessaoBeneficio.equals(other.NUMR_motivoConcessaoBeneficio))
			return false;
		if (NUMR_processo == null) {
			if (other.NUMR_processo != null)
				return false;
		} else if (!NUMR_processo.equals(other.NUMR_processo))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		if (REL_atestadoMedico == null) {
			if (other.REL_atestadoMedico != null)
				return false;
		} else if (!REL_atestadoMedico.equals(other.REL_atestadoMedico))
			return false;
		if (REL_atoLegais == null) {
			if (other.REL_atoLegais != null)
				return false;
		} else if (!REL_atoLegais.equals(other.REL_atoLegais))
			return false;
		return true;
	}
}
