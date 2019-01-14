package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.StatusBeneficioEnum;
import br.com.iperonprev.constantes.TipoPensaoEnum;

@Entity
public class Pensao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@OneToOne
	private PessoasFuncionais REL_pessoasFuncionais;
	private String NUMR_processo;
	@Enumerated(EnumType.STRING)
	private DecisaoEnum ENUM_invalido;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="pensao_cid",joinColumns=@JoinColumn(name="pensao_NUMG_idDoObjeto"),
	inverseJoinColumns=@JoinColumn(name="cid_NUMG_idDoObjeto"))
	private List<Cid> NUMR_idDoObjetoCid;
	private Date DATA_inicioDoBeneficio;
	private Date DATA_fimDoBeneficio;
	@Column(nullable=true)
	@Enumerated(EnumType.STRING)
	private DecisaoEnum ENUM_estudante;
	@Column(nullable=true)
	@Enumerated(EnumType.STRING)
	private DecisaoEnum ENUM_emancipado;
	@Lob
	private String DESC_observacao;
	@Column(nullable=true)
	@Enumerated(EnumType.STRING)
	private TipoPensaoEnum ENUM_tipoPensao;
	@OneToOne
	private AtosLegais REL_atoLegais;
	@Column(nullable=true)
	private int NUMR_sequencia = 0;
	@Column(nullable=true)
	private double NUMR_cotaParte = 0;
	@Enumerated(EnumType.STRING)
	@Transient
	private StatusBeneficioEnum ENUM_status;
	@Version
	private int NUMR_versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getREL_pessoasFuncionais() {
		return REL_pessoasFuncionais;
	}
	public void setREL_pessoasFuncionais(PessoasFuncionais rEL_pessoasFuncionais) {
		REL_pessoasFuncionais = rEL_pessoasFuncionais;
	}
	public String getNUMR_processo() {
		return NUMR_processo;
	}
	public void setNUMR_processo(String nUMR_processo) {
		NUMR_processo = nUMR_processo;
	}
	public DecisaoEnum getENUM_invalido() {
		return ENUM_invalido;
	}
	public void setENUM_invalido(DecisaoEnum eNUM_invalido) {
		ENUM_invalido = eNUM_invalido;
	}
	public List<Cid> getNUMR_idDoObjetoCid() {
		return NUMR_idDoObjetoCid;
	}
	public void setNUMR_idDoObjetoCid(List<Cid> nUMR_idDoObjetoCid) {
		NUMR_idDoObjetoCid = nUMR_idDoObjetoCid;
	}
	public Date getDATA_inicioDoBeneficio() {
		return DATA_inicioDoBeneficio;
	}
	public void setDATA_inicioDoBeneficio(Date dATA_inicioDoBeneficio) {
		DATA_inicioDoBeneficio = dATA_inicioDoBeneficio;
	}
	public Date getDATA_fimDoBeneficio() {
		return DATA_fimDoBeneficio;
	}
	public void setDATA_fimDoBeneficio(Date dATA_fimDoBeneficio) {
		DATA_fimDoBeneficio = dATA_fimDoBeneficio;
	}
	public DecisaoEnum getENUM_estudante() {
		return ENUM_estudante;
	}
	public void setENUM_estudante(DecisaoEnum eNUM_estudante) {
		ENUM_estudante = eNUM_estudante;
	}
	public DecisaoEnum getENUM_emancipado() {
		return ENUM_emancipado;
	}
	public void setENUM_emancipado(DecisaoEnum eNUM_emancipado) {
		ENUM_emancipado = eNUM_emancipado;
	}
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
	}
	public TipoPensaoEnum getENUM_tipoPensao() {
		return ENUM_tipoPensao;
	}
	public void setENUM_tipoPensao(TipoPensaoEnum eNUM_tipoPensao) {
		ENUM_tipoPensao = eNUM_tipoPensao;
	}
	public AtosLegais getREL_atoLegais() {
		return REL_atoLegais;
	}
	public void setREL_atoLegais(AtosLegais rEL_atoLegais) {
		REL_atoLegais = rEL_atoLegais;
	}
	public int getNUMR_sequencia() {
		return NUMR_sequencia;
	}
	public void setNUMR_sequencia(int nUMR_sequencia) {
		NUMR_sequencia = nUMR_sequencia;
	}
	public double getNUMR_cotaParte() {
		return NUMR_cotaParte;
	}
	public void setNUMR_cotaParte(double nUMR_cotaParte) {
		NUMR_cotaParte = nUMR_cotaParte;
	}
	public StatusBeneficioEnum getENUM_status() {
		return ENUM_status;
	}
	public void setENUM_status(StatusBeneficioEnum eNUM_status) {
		ENUM_status = eNUM_status;
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
		result = prime * result + ((DATA_fimDoBeneficio == null) ? 0 : DATA_fimDoBeneficio.hashCode());
		result = prime * result + ((DATA_inicioDoBeneficio == null) ? 0 : DATA_inicioDoBeneficio.hashCode());
		result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
		result = prime * result + ((ENUM_emancipado == null) ? 0 : ENUM_emancipado.hashCode());
		result = prime * result + ((ENUM_estudante == null) ? 0 : ENUM_estudante.hashCode());
		result = prime * result + ((ENUM_invalido == null) ? 0 : ENUM_invalido.hashCode());
		result = prime * result + ((ENUM_status == null) ? 0 : ENUM_status.hashCode());
		result = prime * result + ((ENUM_tipoPensao == null) ? 0 : ENUM_tipoPensao.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		long temp;
		temp = Double.doubleToLongBits(NUMR_cotaParte);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((NUMR_idDoObjetoCid == null) ? 0 : NUMR_idDoObjetoCid.hashCode());
		result = prime * result + ((NUMR_processo == null) ? 0 : NUMR_processo.hashCode());
		result = prime * result + NUMR_sequencia;
		result = prime * result + NUMR_versao;
		result = prime * result + ((REL_atoLegais == null) ? 0 : REL_atoLegais.hashCode());
		result = prime * result + ((REL_pessoasFuncionais == null) ? 0 : REL_pessoasFuncionais.hashCode());
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
		Pensao other = (Pensao) obj;
		if (DATA_fimDoBeneficio == null) {
			if (other.DATA_fimDoBeneficio != null)
				return false;
		} else if (!DATA_fimDoBeneficio.equals(other.DATA_fimDoBeneficio))
			return false;
		if (DATA_inicioDoBeneficio == null) {
			if (other.DATA_inicioDoBeneficio != null)
				return false;
		} else if (!DATA_inicioDoBeneficio.equals(other.DATA_inicioDoBeneficio))
			return false;
		if (DESC_observacao == null) {
			if (other.DESC_observacao != null)
				return false;
		} else if (!DESC_observacao.equals(other.DESC_observacao))
			return false;
		if (ENUM_emancipado != other.ENUM_emancipado)
			return false;
		if (ENUM_estudante != other.ENUM_estudante)
			return false;
		if (ENUM_invalido != other.ENUM_invalido)
			return false;
		if (ENUM_status != other.ENUM_status)
			return false;
		if (ENUM_tipoPensao != other.ENUM_tipoPensao)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (Double.doubleToLongBits(NUMR_cotaParte) != Double.doubleToLongBits(other.NUMR_cotaParte))
			return false;
		if (NUMR_idDoObjetoCid == null) {
			if (other.NUMR_idDoObjetoCid != null)
				return false;
		} else if (!NUMR_idDoObjetoCid.equals(other.NUMR_idDoObjetoCid))
			return false;
		if (NUMR_processo == null) {
			if (other.NUMR_processo != null)
				return false;
		} else if (!NUMR_processo.equals(other.NUMR_processo))
			return false;
		if (NUMR_sequencia != other.NUMR_sequencia)
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		if (REL_atoLegais == null) {
			if (other.REL_atoLegais != null)
				return false;
		} else if (!REL_atoLegais.equals(other.REL_atoLegais))
			return false;
		if (REL_pessoasFuncionais == null) {
			if (other.REL_pessoasFuncionais != null)
				return false;
		} else if (!REL_pessoasFuncionais.equals(other.REL_pessoasFuncionais))
			return false;
		return true;
	}
	
}
