package br.com.iperonprev.models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DemonstrativoFinanceiro
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer NUMG_idDoObjeto;
  @ManyToOne
  private Rubricas NUMR_rubricas;
  private BigDecimal NUMR_percentual;
  @ManyToOne
  private AtosLegais NUMR_atosLegais;
  private BigDecimal VALR_remuneracao;
  
  public Rubricas getNUMR_rubricas()
  {
    return this.NUMR_rubricas;
  }
  
  public void setNUMR_rubricas(Rubricas nUMR_rubricas)
  {
    this.NUMR_rubricas = nUMR_rubricas;
  }
  
  public BigDecimal getNUMR_percentual()
  {
    return this.NUMR_percentual;
  }
  
  public void setNUMR_percentual(BigDecimal nUMR_percentual)
  {
    this.NUMR_percentual = nUMR_percentual;
  }
  
  public AtosLegais getNUMR_atosLegais()
  {
    return this.NUMR_atosLegais;
  }
  
  public void setNUMR_atosLegais(AtosLegais nUMR_atosLegais)
  {
    this.NUMR_atosLegais = nUMR_atosLegais;
  }
  
  public BigDecimal getVALR_remuneracao()
  {
    return this.VALR_remuneracao;
  }
  
  public void setVALR_remuneracao(BigDecimal vALR_remuneracao)
  {
    this.VALR_remuneracao = vALR_remuneracao;
  }
  
  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.NUMG_idDoObjeto == null ? 0 : this.NUMG_idDoObjeto.hashCode());
    result = 31 * result + (this.NUMR_atosLegais == null ? 0 : this.NUMR_atosLegais.hashCode());
    result = 31 * result + (this.NUMR_percentual == null ? 0 : this.NUMR_percentual.hashCode());
    result = 31 * result + (this.NUMR_rubricas == null ? 0 : this.NUMR_rubricas.hashCode());
    result = 31 * result + (this.VALR_remuneracao == null ? 0 : this.VALR_remuneracao.hashCode());
    return result;
  }
  
  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    DemonstrativoFinanceiro other = (DemonstrativoFinanceiro)obj;
    if (this.NUMG_idDoObjeto == null)
    {
      if (other.NUMG_idDoObjeto != null) {
        return false;
      }
    }
    else if (!this.NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto)) {
      return false;
    }
    if (this.NUMR_atosLegais == null)
    {
      if (other.NUMR_atosLegais != null) {
        return false;
      }
    }
    else if (!this.NUMR_atosLegais.equals(other.NUMR_atosLegais)) {
      return false;
    }
    if (this.NUMR_percentual == null)
    {
      if (other.NUMR_percentual != null) {
        return false;
      }
    }
    else if (!this.NUMR_percentual.equals(other.NUMR_percentual)) {
      return false;
    }
    if (this.NUMR_rubricas == null)
    {
      if (other.NUMR_rubricas != null) {
        return false;
      }
    }
    else if (!this.NUMR_rubricas.equals(other.NUMR_rubricas)) {
      return false;
    }
    if (this.VALR_remuneracao == null)
    {
      if (other.VALR_remuneracao != null) {
        return false;
      }
    }
    else if (!this.VALR_remuneracao.equals(other.VALR_remuneracao)) {
      return false;
    }
    return true;
  }
}
