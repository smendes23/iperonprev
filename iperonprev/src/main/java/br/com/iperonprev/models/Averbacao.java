package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.iperonprev.constantes.TipoDeducaoEnum;

@Entity
@NamedStoredProcedureQuery(
		name = "limpaconcomitancia",
		procedureName = "SP_LIMPACONCOMITANCIA",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "idFuncional"),
		}
	)
public class Averbacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto = 0;
	@ManyToOne
	private PessoasFuncionais NUMR_pessoasFuncionais;
	@OneToOne
	@NotNull(message="Regime previdenciário obrigatório")
	private RegimesPrevidenciarios NUMR_regime;
	@OneToOne
	@NotNull(message="Iniciativa obrigatória")
	private Iniciativa NUMR_iniciativa;
	@Size(min=5,message="Campo empregador deve conter pelo menos 5 caracteres")
	private String DESC_empregador;
	@Column(length=18)
	private String NUMR_cnpj;
	@Column(length=200)
	private String DESC_funcao;
	@Column(length=23)
	private String NUMR_processo;
	@ManyToOne
	private Orgaos NUMR_orgaoPrevidenciario;
	@NotNull(message="Data de admissão obrigatória")
	private Date DATA_admissao;
	@NotNull(message="Data de demissão obrigatória")
	private Date DATA_demissao;
	private Date DATA_inicioTempoAproveitado;
	private Date DATA_fimTempoAproveitado;
	private Date DATA_inicioConcomitancia;
	private Date DATA_fimConcomitancia;
	@Enumerated(EnumType.STRING)
	private TipoDeducaoEnum NUMR_tipoDeducao;
	private int NUMR_deducao;
	private int NUMR_diasContribuicao;
	@Column(length=50)
	private String DESC_tempoContribuicao;
	@Column(length=50)
	private String DESC_tempoAproveitado;
	private boolean FLAG_exluir = false;
	private boolean FLAG_concomitado = false;
	@ManyToOne
	private AtosLegais REL_atoLegal;
	private boolean FLAG_tempoFicto;
	@Version
	private int NUMR_versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_pessoasFuncionais() {
		return NUMR_pessoasFuncionais;
	}
	public void setNUMR_pessoasFuncionais(PessoasFuncionais nUMR_pessoasFuncionais) {
		NUMR_pessoasFuncionais = nUMR_pessoasFuncionais;
	}
	public RegimesPrevidenciarios getNUMR_regime() {
		return NUMR_regime;
	}
	public void setNUMR_regime(RegimesPrevidenciarios nUMR_regime) {
		NUMR_regime = nUMR_regime;
	}
	public Iniciativa getNUMR_iniciativa() {
		return NUMR_iniciativa;
	}
	public void setNUMR_iniciativa(Iniciativa nUMR_iniciativa) {
		NUMR_iniciativa = nUMR_iniciativa;
	}
	public String getDESC_empregador() {
		return DESC_empregador;
	}
	public void setDESC_empregador(String dESC_empregador) {
		DESC_empregador = dESC_empregador;
	}
	public String getNUMR_cnpj() {
		return NUMR_cnpj;
	}
	public void setNUMR_cnpj(String nUMR_cnpj) {
		NUMR_cnpj = nUMR_cnpj;
	}
	public String getDESC_funcao() {
		return DESC_funcao;
	}
	public void setDESC_funcao(String dESC_funcao) {
		DESC_funcao = dESC_funcao;
	}
	public String getNUMR_processo() {
		return NUMR_processo;
	}
	public void setNUMR_processo(String nUMR_processo) {
		NUMR_processo = nUMR_processo;
	}
	public Orgaos getNUMR_orgaoPrevidenciario() {
		return NUMR_orgaoPrevidenciario;
	}
	public void setNUMR_orgaoPrevidenciario(Orgaos nUMR_orgaoPrevidenciario) {
		NUMR_orgaoPrevidenciario = nUMR_orgaoPrevidenciario;
	}
	public Date getDATA_admissao() {
		return DATA_admissao;
	}
	public void setDATA_admissao(Date dATA_admissao) {
		DATA_admissao = dATA_admissao;
	}
	public Date getDATA_demissao() {
		return DATA_demissao;
	}
	public void setDATA_demissao(Date dATA_demissao) {
		DATA_demissao = dATA_demissao;
	}
	public Date getDATA_inicioTempoAproveitado() {
		return DATA_inicioTempoAproveitado;
	}
	public void setDATA_inicioTempoAproveitado(Date dATA_inicioTempoAproveitado) {
		DATA_inicioTempoAproveitado = dATA_inicioTempoAproveitado;
	}
	public Date getDATA_fimTempoAproveitado() {
		return DATA_fimTempoAproveitado;
	}
	public void setDATA_fimTempoAproveitado(Date dATA_fimTempoAproveitado) {
		DATA_fimTempoAproveitado = dATA_fimTempoAproveitado;
	}
	public Date getDATA_inicioConcomitancia() {
		return DATA_inicioConcomitancia;
	}
	public void setDATA_inicioConcomitancia(Date dATA_inicioConcomitancia) {
		DATA_inicioConcomitancia = dATA_inicioConcomitancia;
	}
	public Date getDATA_fimConcomitancia() {
		return DATA_fimConcomitancia;
	}
	public void setDATA_fimConcomitancia(Date dATA_fimConcomitancia) {
		DATA_fimConcomitancia = dATA_fimConcomitancia;
	}
	public TipoDeducaoEnum getNUMR_tipoDeducao() {
		return NUMR_tipoDeducao;
	}
	public void setNUMR_tipoDeducao(TipoDeducaoEnum nUMR_tipoDeducao) {
		NUMR_tipoDeducao = nUMR_tipoDeducao;
	}
	public int getNUMR_deducao() {
		return NUMR_deducao;
	}
	public void setNUMR_deducao(int nUMR_deducao) {
		NUMR_deducao = nUMR_deducao;
	}
	public int getNUMR_diasContribuicao() {
		return NUMR_diasContribuicao;
	}
	public void setNUMR_diasContribuicao(int nUMR_diasContribuicao) {
		NUMR_diasContribuicao = nUMR_diasContribuicao;
	}
	public String getDESC_tempoContribuicao() {
		return DESC_tempoContribuicao;
	}
	public void setDESC_tempoContribuicao(String dESC_tempoContribuicao) {
		DESC_tempoContribuicao = dESC_tempoContribuicao;
	}
	public String getDESC_tempoAproveitado() {
		return DESC_tempoAproveitado;
	}
	public void setDESC_tempoAproveitado(String dESC_tempoAproveitado) {
		DESC_tempoAproveitado = dESC_tempoAproveitado;
	}
	public boolean isFLAG_exluir() {
		return FLAG_exluir;
	}
	public void setFLAG_exluir(boolean fLAG_exluir) {
		FLAG_exluir = fLAG_exluir;
	}
	public boolean isFLAG_concomitado() {
		return FLAG_concomitado;
	}
	public void setFLAG_concomitado(boolean fLAG_concomitado) {
		FLAG_concomitado = fLAG_concomitado;
	}
	public AtosLegais getREL_atoLegal() {
		return REL_atoLegal;
	}
	public void setREL_atoLegal(AtosLegais rEL_atoLegal) {
		REL_atoLegal = rEL_atoLegal;
	}
	public boolean isFLAG_tempoFicto() {
		return FLAG_tempoFicto;
	}
	public void setFLAG_tempoFicto(boolean fLAG_tempoFicto) {
		FLAG_tempoFicto = fLAG_tempoFicto;
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
		result = prime * result + ((DATA_admissao == null) ? 0 : DATA_admissao.hashCode());
		result = prime * result + ((DATA_demissao == null) ? 0 : DATA_demissao.hashCode());
		result = prime * result + ((DATA_fimConcomitancia == null) ? 0 : DATA_fimConcomitancia.hashCode());
		result = prime * result + ((DATA_fimTempoAproveitado == null) ? 0 : DATA_fimTempoAproveitado.hashCode());
		result = prime * result + ((DATA_inicioConcomitancia == null) ? 0 : DATA_inicioConcomitancia.hashCode());
		result = prime * result + ((DATA_inicioTempoAproveitado == null) ? 0 : DATA_inicioTempoAproveitado.hashCode());
		result = prime * result + ((DESC_empregador == null) ? 0 : DESC_empregador.hashCode());
		result = prime * result + ((DESC_funcao == null) ? 0 : DESC_funcao.hashCode());
		result = prime * result + ((DESC_tempoAproveitado == null) ? 0 : DESC_tempoAproveitado.hashCode());
		result = prime * result + ((DESC_tempoContribuicao == null) ? 0 : DESC_tempoContribuicao.hashCode());
		result = prime * result + (FLAG_concomitado ? 1231 : 1237);
		result = prime * result + (FLAG_exluir ? 1231 : 1237);
		result = prime * result + (FLAG_tempoFicto ? 1231 : 1237);
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_cnpj == null) ? 0 : NUMR_cnpj.hashCode());
		result = prime * result + NUMR_deducao;
		result = prime * result + NUMR_diasContribuicao;
		result = prime * result + ((NUMR_iniciativa == null) ? 0 : NUMR_iniciativa.hashCode());
		result = prime * result + ((NUMR_orgaoPrevidenciario == null) ? 0 : NUMR_orgaoPrevidenciario.hashCode());
		result = prime * result + ((NUMR_pessoasFuncionais == null) ? 0 : NUMR_pessoasFuncionais.hashCode());
		result = prime * result + ((NUMR_processo == null) ? 0 : NUMR_processo.hashCode());
		result = prime * result + ((NUMR_regime == null) ? 0 : NUMR_regime.hashCode());
		result = prime * result + ((NUMR_tipoDeducao == null) ? 0 : NUMR_tipoDeducao.hashCode());
		result = prime * result + NUMR_versao;
		result = prime * result + ((REL_atoLegal == null) ? 0 : REL_atoLegal.hashCode());
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
		Averbacao other = (Averbacao) obj;
		if (DATA_admissao == null) {
			if (other.DATA_admissao != null)
				return false;
		} else if (!DATA_admissao.equals(other.DATA_admissao))
			return false;
		if (DATA_demissao == null) {
			if (other.DATA_demissao != null)
				return false;
		} else if (!DATA_demissao.equals(other.DATA_demissao))
			return false;
		if (DATA_fimConcomitancia == null) {
			if (other.DATA_fimConcomitancia != null)
				return false;
		} else if (!DATA_fimConcomitancia.equals(other.DATA_fimConcomitancia))
			return false;
		if (DATA_fimTempoAproveitado == null) {
			if (other.DATA_fimTempoAproveitado != null)
				return false;
		} else if (!DATA_fimTempoAproveitado.equals(other.DATA_fimTempoAproveitado))
			return false;
		if (DATA_inicioConcomitancia == null) {
			if (other.DATA_inicioConcomitancia != null)
				return false;
		} else if (!DATA_inicioConcomitancia.equals(other.DATA_inicioConcomitancia))
			return false;
		if (DATA_inicioTempoAproveitado == null) {
			if (other.DATA_inicioTempoAproveitado != null)
				return false;
		} else if (!DATA_inicioTempoAproveitado.equals(other.DATA_inicioTempoAproveitado))
			return false;
		if (DESC_empregador == null) {
			if (other.DESC_empregador != null)
				return false;
		} else if (!DESC_empregador.equals(other.DESC_empregador))
			return false;
		if (DESC_funcao == null) {
			if (other.DESC_funcao != null)
				return false;
		} else if (!DESC_funcao.equals(other.DESC_funcao))
			return false;
		if (DESC_tempoAproveitado == null) {
			if (other.DESC_tempoAproveitado != null)
				return false;
		} else if (!DESC_tempoAproveitado.equals(other.DESC_tempoAproveitado))
			return false;
		if (DESC_tempoContribuicao == null) {
			if (other.DESC_tempoContribuicao != null)
				return false;
		} else if (!DESC_tempoContribuicao.equals(other.DESC_tempoContribuicao))
			return false;
		if (FLAG_concomitado != other.FLAG_concomitado)
			return false;
		if (FLAG_exluir != other.FLAG_exluir)
			return false;
		if (FLAG_tempoFicto != other.FLAG_tempoFicto)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_cnpj == null) {
			if (other.NUMR_cnpj != null)
				return false;
		} else if (!NUMR_cnpj.equals(other.NUMR_cnpj))
			return false;
		if (NUMR_deducao != other.NUMR_deducao)
			return false;
		if (NUMR_diasContribuicao != other.NUMR_diasContribuicao)
			return false;
		if (NUMR_iniciativa == null) {
			if (other.NUMR_iniciativa != null)
				return false;
		} else if (!NUMR_iniciativa.equals(other.NUMR_iniciativa))
			return false;
		if (NUMR_orgaoPrevidenciario == null) {
			if (other.NUMR_orgaoPrevidenciario != null)
				return false;
		} else if (!NUMR_orgaoPrevidenciario.equals(other.NUMR_orgaoPrevidenciario))
			return false;
		if (NUMR_pessoasFuncionais == null) {
			if (other.NUMR_pessoasFuncionais != null)
				return false;
		} else if (!NUMR_pessoasFuncionais.equals(other.NUMR_pessoasFuncionais))
			return false;
		if (NUMR_processo == null) {
			if (other.NUMR_processo != null)
				return false;
		} else if (!NUMR_processo.equals(other.NUMR_processo))
			return false;
		if (NUMR_regime == null) {
			if (other.NUMR_regime != null)
				return false;
		} else if (!NUMR_regime.equals(other.NUMR_regime))
			return false;
		if (NUMR_tipoDeducao != other.NUMR_tipoDeducao)
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		if (REL_atoLegal == null) {
			if (other.REL_atoLegal != null)
				return false;
		} else if (!REL_atoLegal.equals(other.REL_atoLegal))
			return false;
		return true;
	}
}
