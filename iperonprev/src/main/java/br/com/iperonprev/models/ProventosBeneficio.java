package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class ProventosBeneficio
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int NUMG_idDoObjeto;
  @ManyToOne
  private PessoasFuncionais NUMR_idDoObjetoFuncionais;
  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.EAGER)
  private List<DemonstrativoFinanceiro> REL_demonstrativo;
  @Lob
  private String DESC_observacao;
  @Version
  private int versao;
  
  public int getNUMG_idDoObjeto()
  {
    return this.NUMG_idDoObjeto;
  }
  
  public void setNUMG_idDoObjeto(int nUMG_idDoObjeto)
  {
    this.NUMG_idDoObjeto = nUMG_idDoObjeto;
  }
  
  public PessoasFuncionais getNUMR_idDoObjetoFuncionais()
  {
    return this.NUMR_idDoObjetoFuncionais;
  }
  
  public void setNUMR_idDoObjetoFuncionais(PessoasFuncionais nUMR_idDoObjetoFuncionais)
  {
    this.NUMR_idDoObjetoFuncionais = nUMR_idDoObjetoFuncionais;
  }
  
  public List<DemonstrativoFinanceiro> getREL_demonstrativo()
  {
    return this.REL_demonstrativo;
  }
  
  public void setREL_demonstrativo(List<DemonstrativoFinanceiro> rEL_demonstrativo)
  {
    this.REL_demonstrativo = rEL_demonstrativo;
  }
  
  public String getDESC_observacao()
  {
    return this.DESC_observacao;
  }
  
  public void setDESC_observacao(String dESC_observacao)
  {
    this.DESC_observacao = dESC_observacao;
  }
  
  public int getVersao()
  {
    return this.versao;
  }
  
  public void setVersao(int versao)
  {
    this.versao = versao;
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((DESC_observacao == null) ? 0 : DESC_observacao.hashCode());
	result = prime * result + NUMG_idDoObjeto;
	result = prime * result + ((NUMR_idDoObjetoFuncionais == null) ? 0 : NUMR_idDoObjetoFuncionais.hashCode());
	result = prime * result + ((REL_demonstrativo == null) ? 0 : REL_demonstrativo.hashCode());
	result = prime * result + versao;
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
	ProventosBeneficio other = (ProventosBeneficio) obj;
	if (DESC_observacao == null) {
		if (other.DESC_observacao != null)
			return false;
	} else if (!DESC_observacao.equals(other.DESC_observacao))
		return false;
	if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
		return false;
	if (NUMR_idDoObjetoFuncionais == null) {
		if (other.NUMR_idDoObjetoFuncionais != null)
			return false;
	} else if (!NUMR_idDoObjetoFuncionais.equals(other.NUMR_idDoObjetoFuncionais))
		return false;
	if (REL_demonstrativo == null) {
		if (other.REL_demonstrativo != null)
			return false;
	} else if (!REL_demonstrativo.equals(other.REL_demonstrativo))
		return false;
	if (versao != other.versao)
		return false;
	return true;
}
  
  
}
