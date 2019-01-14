package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.constantes.TipoBeneficioEnum;

@Entity
public class PessoasFuncionais
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer NUMG_idDoObjeto = Integer.valueOf(0);
  @ManyToOne(cascade={javax.persistence.CascadeType.ALL})
  private Pessoas NUMR_idDoObjetoPessoas;
  private Date DATA_efetivoExercicio;
  private Date DATA_posse;
  @Column(length=10)
  private String DESC_matricula;
  @ManyToOne
  private Cargos NUMR_idDoObjetoCargo;
  private Date DATA_exoneracao;
  private Date DATA_Beneficio;
  @ManyToOne
  private SituacaoFuncional NUMR_situacaoFuncional;
  @Column(nullable=true)
  @Enumerated(EnumType.STRING)
  private DecisaoEnum FLAG_abonoPermanencia = DecisaoEnum.NAO;
  @Column(nullable=true)
  @Enumerated(EnumType.STRING)
  private TipoBeneficioEnum ENUM_tipoAposentadoria;
  @OneToOne
  private FundoPrevidenciario NUMR_fundoPrevidenciario;
  @ManyToOne
  private Enquadramento REL_enquadramento;
  @ManyToMany(cascade={javax.persistence.CascadeType.ALL})
  private List<DocumentoFuncional> REL_documentoFuncional;
  @ManyToOne
  private SituacaoPrevidenciaria NUMR_situacaoPrevidenciaria;
  @ManyToOne
  private VinculoPrevidenciario NUMR_vinculoPrevidenciario;
  @Version
  private int NUMR_versao;
  
  public Integer getNUMG_idDoObjeto()
  {
    return this.NUMG_idDoObjeto;
  }
  
  public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto)
  {
    this.NUMG_idDoObjeto = nUMG_idDoObjeto;
  }
  
  public Pessoas getNUMR_idDoObjetoPessoas()
  {
    return this.NUMR_idDoObjetoPessoas;
  }
  
  public void setNUMR_idDoObjetoPessoas(Pessoas nUMR_idDoObjetoPessoas)
  {
    this.NUMR_idDoObjetoPessoas = nUMR_idDoObjetoPessoas;
  }
  
  public Date getDATA_efetivoExercicio()
  {
    return this.DATA_efetivoExercicio;
  }
  
  public void setDATA_efetivoExercicio(Date dATA_efetivoExercicio)
  {
    this.DATA_efetivoExercicio = dATA_efetivoExercicio;
  }
  
  public Date getDATA_posse()
  {
    return this.DATA_posse;
  }
  
  public void setDATA_posse(Date dATA_posse)
  {
    this.DATA_posse = dATA_posse;
  }
  
  public String getDESC_matricula()
  {
    return this.DESC_matricula;
  }
  
  public void setDESC_matricula(String dESC_matricula)
  {
    this.DESC_matricula = dESC_matricula;
  }
  
  public Cargos getNUMR_idDoObjetoCargo()
  {
    return this.NUMR_idDoObjetoCargo;
  }
  
  public void setNUMR_idDoObjetoCargo(Cargos nUMR_idDoObjetoCargo)
  {
    this.NUMR_idDoObjetoCargo = nUMR_idDoObjetoCargo;
  }
  
  public Date getDATA_exoneracao()
  {
    return this.DATA_exoneracao;
  }
  
  public void setDATA_exoneracao(Date dATA_exoneracao)
  {
    this.DATA_exoneracao = dATA_exoneracao;
  }
  
  public Date getDATA_Beneficio()
  {
    return this.DATA_Beneficio;
  }
  
  public void setDATA_Beneficio(Date dATA_Beneficio)
  {
    this.DATA_Beneficio = dATA_Beneficio;
  }
  
  public SituacaoFuncional getNUMR_situacaoFuncional()
  {
    return this.NUMR_situacaoFuncional;
  }
  
  public void setNUMR_situacaoFuncional(SituacaoFuncional nUMR_situacaoFuncional)
  {
    this.NUMR_situacaoFuncional = nUMR_situacaoFuncional;
  }
  
  public DecisaoEnum getFLAG_abonoPermanencia()
  {
    return this.FLAG_abonoPermanencia;
  }
  
  public void setFLAG_abonoPermanencia(DecisaoEnum fLAG_abonoPermanencia)
  {
    this.FLAG_abonoPermanencia = fLAG_abonoPermanencia;
  }
  
  public TipoBeneficioEnum getENUM_tipoAposentadoria()
  {
    return this.ENUM_tipoAposentadoria;
  }
  
  public void setENUM_tipoAposentadoria(TipoBeneficioEnum eNUM_tipoAposentadoria)
  {
    this.ENUM_tipoAposentadoria = eNUM_tipoAposentadoria;
  }
  
  public FundoPrevidenciario getNUMR_fundoPrevidenciario()
  {
    return this.NUMR_fundoPrevidenciario;
  }
  
  public void setNUMR_fundoPrevidenciario(FundoPrevidenciario nUMR_fundoPrevidenciario)
  {
    this.NUMR_fundoPrevidenciario = nUMR_fundoPrevidenciario;
  }
  
  public Enquadramento getREL_enquadramento()
  {
    return this.REL_enquadramento;
  }
  
  public void setREL_enquadramento(Enquadramento rEL_enquadramento)
  {
    this.REL_enquadramento = rEL_enquadramento;
  }
  
  public List<DocumentoFuncional> getREL_documentoFuncional()
  {
    return this.REL_documentoFuncional;
  }
  
  public void setREL_documentoFuncional(List<DocumentoFuncional> rEL_documentoFuncional)
  {
    this.REL_documentoFuncional = rEL_documentoFuncional;
  }
  
  public SituacaoPrevidenciaria getNUMR_situacaoPrevidenciaria()
  {
    return this.NUMR_situacaoPrevidenciaria;
  }
  
  public void setNUMR_situacaoPrevidenciaria(SituacaoPrevidenciaria nUMR_situacaoPrevidenciaria)
  {
    this.NUMR_situacaoPrevidenciaria = nUMR_situacaoPrevidenciaria;
  }
  
  public VinculoPrevidenciario getNUMR_vinculoPrevidenciario()
  {
    return this.NUMR_vinculoPrevidenciario;
  }
  
  public void setNUMR_vinculoPrevidenciario(VinculoPrevidenciario nUMR_vinculoPrevidenciario)
  {
    this.NUMR_vinculoPrevidenciario = nUMR_vinculoPrevidenciario;
  }
  
  public int getNUMR_versao()
  {
    return this.NUMR_versao;
  }
  
  public void setNUMR_versao(int nUMR_versao)
  {
    this.NUMR_versao = nUMR_versao;
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((DATA_Beneficio == null) ? 0 : DATA_Beneficio.hashCode());
	result = prime * result + ((DATA_efetivoExercicio == null) ? 0 : DATA_efetivoExercicio.hashCode());
	result = prime * result + ((DATA_exoneracao == null) ? 0 : DATA_exoneracao.hashCode());
	result = prime * result + ((DATA_posse == null) ? 0 : DATA_posse.hashCode());
	result = prime * result + ((DESC_matricula == null) ? 0 : DESC_matricula.hashCode());
	result = prime * result + ((ENUM_tipoAposentadoria == null) ? 0 : ENUM_tipoAposentadoria.hashCode());
	result = prime * result + ((FLAG_abonoPermanencia == null) ? 0 : FLAG_abonoPermanencia.hashCode());
	result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
	result = prime * result + ((NUMR_fundoPrevidenciario == null) ? 0 : NUMR_fundoPrevidenciario.hashCode());
	result = prime * result + ((NUMR_idDoObjetoCargo == null) ? 0 : NUMR_idDoObjetoCargo.hashCode());
	result = prime * result + ((NUMR_idDoObjetoPessoas == null) ? 0 : NUMR_idDoObjetoPessoas.hashCode());
	result = prime * result + ((NUMR_situacaoFuncional == null) ? 0 : NUMR_situacaoFuncional.hashCode());
	result = prime * result + ((NUMR_situacaoPrevidenciaria == null) ? 0 : NUMR_situacaoPrevidenciaria.hashCode());
	result = prime * result + NUMR_versao;
	result = prime * result + ((NUMR_vinculoPrevidenciario == null) ? 0 : NUMR_vinculoPrevidenciario.hashCode());
	result = prime * result + ((REL_documentoFuncional == null) ? 0 : REL_documentoFuncional.hashCode());
	result = prime * result + ((REL_enquadramento == null) ? 0 : REL_enquadramento.hashCode());
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
	PessoasFuncionais other = (PessoasFuncionais) obj;
	if (DATA_Beneficio == null) {
		if (other.DATA_Beneficio != null)
			return false;
	} else if (!DATA_Beneficio.equals(other.DATA_Beneficio))
		return false;
	if (DATA_efetivoExercicio == null) {
		if (other.DATA_efetivoExercicio != null)
			return false;
	} else if (!DATA_efetivoExercicio.equals(other.DATA_efetivoExercicio))
		return false;
	if (DATA_exoneracao == null) {
		if (other.DATA_exoneracao != null)
			return false;
	} else if (!DATA_exoneracao.equals(other.DATA_exoneracao))
		return false;
	if (DATA_posse == null) {
		if (other.DATA_posse != null)
			return false;
	} else if (!DATA_posse.equals(other.DATA_posse))
		return false;
	if (DESC_matricula == null) {
		if (other.DESC_matricula != null)
			return false;
	} else if (!DESC_matricula.equals(other.DESC_matricula))
		return false;
	if (ENUM_tipoAposentadoria != other.ENUM_tipoAposentadoria)
		return false;
	if (FLAG_abonoPermanencia != other.FLAG_abonoPermanencia)
		return false;
	if (NUMG_idDoObjeto == null) {
		if (other.NUMG_idDoObjeto != null)
			return false;
	} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
		return false;
	if (NUMR_fundoPrevidenciario == null) {
		if (other.NUMR_fundoPrevidenciario != null)
			return false;
	} else if (!NUMR_fundoPrevidenciario.equals(other.NUMR_fundoPrevidenciario))
		return false;
	if (NUMR_idDoObjetoCargo == null) {
		if (other.NUMR_idDoObjetoCargo != null)
			return false;
	} else if (!NUMR_idDoObjetoCargo.equals(other.NUMR_idDoObjetoCargo))
		return false;
	if (NUMR_idDoObjetoPessoas == null) {
		if (other.NUMR_idDoObjetoPessoas != null)
			return false;
	} else if (!NUMR_idDoObjetoPessoas.equals(other.NUMR_idDoObjetoPessoas))
		return false;
	if (NUMR_situacaoFuncional == null) {
		if (other.NUMR_situacaoFuncional != null)
			return false;
	} else if (!NUMR_situacaoFuncional.equals(other.NUMR_situacaoFuncional))
		return false;
	if (NUMR_situacaoPrevidenciaria == null) {
		if (other.NUMR_situacaoPrevidenciaria != null)
			return false;
	} else if (!NUMR_situacaoPrevidenciaria.equals(other.NUMR_situacaoPrevidenciaria))
		return false;
	if (NUMR_versao != other.NUMR_versao)
		return false;
	if (NUMR_vinculoPrevidenciario == null) {
		if (other.NUMR_vinculoPrevidenciario != null)
			return false;
	} else if (!NUMR_vinculoPrevidenciario.equals(other.NUMR_vinculoPrevidenciario))
		return false;
	if (REL_documentoFuncional == null) {
		if (other.REL_documentoFuncional != null)
			return false;
	} else if (!REL_documentoFuncional.equals(other.REL_documentoFuncional))
		return false;
	if (REL_enquadramento == null) {
		if (other.REL_enquadramento != null)
			return false;
	} else if (!REL_enquadramento.equals(other.REL_enquadramento))
		return false;
	return true;
}
  
 
}
