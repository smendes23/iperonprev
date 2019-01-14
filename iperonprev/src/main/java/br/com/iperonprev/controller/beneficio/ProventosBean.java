package br.com.iperonprev.controller.beneficio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import br.com.iperonprev.controller.cadastro.FinanceiroBean;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.helper.ComponentReportBuilderHelper;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.AtosLegais;
import br.com.iperonprev.models.DemonstrativoFinanceiro;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.ProventosBeneficio;
import br.com.iperonprev.models.Remuneracoes;
import br.com.iperonprev.models.Rubricas;
import br.com.iperonprev.reports.container.GenerateReportWithSub;
import br.com.iperonprev.reports.proventos.DemonstrativoProventos;
import br.com.iperonprev.reports.proventos.FooterProventos;
import br.com.iperonprev.reports.proventos.TemplateProvento;
import br.com.iperonprev.services.beneficio.Aposentadorias.QualificaProporcionalidade;
import br.com.iperonprev.services.beneficio.Aposentadorias.TipoAposentadoriaImpl;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@SessionScoped
public class ProventosBean
  implements GenericBean<ProventosBeneficio>, Serializable
{
  private static final long serialVersionUID = 1L;
  ProventosBeneficio pb = new ProventosBeneficio();
  static List<DemonstrativoFinanceiro> listaDemonstrativo = new ArrayList<DemonstrativoFinanceiro>();
  @ManagedProperty("#{financeiroBean}")
  private FinanceiroBean financeiroBean;
  PessoasFuncionais pf = new PessoasFuncionais();
  DemonstrativoFinanceiro demonstrativo = new DemonstrativoFinanceiro();
  private boolean habilitaImpressao = false;
  TipoAposentadoriaImpl impl = new TipoAposentadoriaImpl();
  private String competencia = new String();
  
  public String getCompetencia()
  {
    return this.competencia;
  }
  
  public void setCompetencia(String competencia)
  {
    this.competencia = competencia;
  }
  
  public boolean isHabilitaImpressao()
  {
    return this.habilitaImpressao;
  }
  
  public void setHabilitaImpressao(boolean habilitaImpressao)
  {
    this.habilitaImpressao = habilitaImpressao;
  }
  
  public DemonstrativoFinanceiro getDemonstrativo()
  {
    return this.demonstrativo;
  }
  
  public void setDemonstrativo(DemonstrativoFinanceiro demonstrativo)
  {
    this.demonstrativo = demonstrativo;
  }
  
  public PessoasFuncionais getPf()
  {
    return this.pf;
  }
  
  public void setPf(PessoasFuncionais pf)
  {
    this.pf = pf;
  }
  
  public void setFinanceiroBean(FinanceiroBean financeiroBean)
  {
    try
    {
      this.financeiroBean = financeiroBean;
    }
    catch (Exception e)
    {
      System.out.println("Erro ao setar financeiro Bean");
    }
  }
  
  Remuneracoes rem = new Remuneracoes();
  
  public void onTabChange(TabChangeEvent event)
  {
    listaDemonstrativo = new ArrayList<DemonstrativoFinanceiro>();
    this.competencia = new String();
    this.demonstrativo = new DemonstrativoFinanceiro();
    this.pf = this.financeiroBean.getPf();
    this.pb = new ProventosBeneficio();
    this.impl = new TipoAposentadoriaImpl();
    if ((verificaDataBeneficioValida(this.pf)) && 
      (verificaSeExisteBeneficio(this.pf)))
    {
      this.impl = new QualificaProporcionalidade().devolveProporcionalidade(this.pf);
      try
      {
        System.out.println("Passou pela verifica��o.");
        this.pb = new GenericPersistence<ProventosBeneficio>(ProventosBeneficio.class).buscaObjetoRelacionamento("ProventosBeneficio", "NUMR_idDoObjetoFuncionais", this.pf.getNUMG_idDoObjeto());
        System.out.println(this.pb.getNUMG_idDoObjeto());
      }
      catch (Exception e)
      {
        System.out.println("N�o foi possivel carregar proventos beneficio.");
      }
    }
    else if (!verificaDataBeneficioValida(this.pf))
    {
      Message.addErrorMessage("Data do Beneficio Nula!");
    }
    else if (!verificaSeExisteBeneficio(this.pf))
    {
      Message.addErrorMessage("N�o existe beneficio para este funcional!");
    }
    else
    {
      Message.addErrorMessage("Existem pendencias para este funcional!");
    }
  }
  
  private void devolveRemuneracao()
  {
    try
    {
      this.rem = new RemuneracaoDao().devolveRemuneracoesContribuicaoPorCompetencia(this.pf.getNUMG_idDoObjeto().intValue(), this.demonstrativo.getNUMR_rubricas().getNUMG_idDoObjeto(), this.competencia);
    }
    catch (Exception e)
    {
      System.out.println("N�o foi possivel carregar remunera�oes");
    }
  }
  
  private boolean verificaSeExisteBeneficio(PessoasFuncionais funcional)
  {
    boolean res = false;
    try
    {
      if (!funcional.getENUM_tipoAposentadoria().equals(null)) {
        res = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("N�o existe beneficio");
    }
    return res;
  }
  
  private boolean verificaDataBeneficioValida(PessoasFuncionais funcional)
  {
    boolean res = false;
    try
    {
      if (funcional.getDATA_Beneficio() != null) {
        res = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Data Beneficio NUla");
    }
    return res;
  }
  
  public ProventosBeneficio getPb()
  {
    return this.pb;
  }
  
  public void setPb(ProventosBeneficio pb)
  {
    this.pb = pb;
  }
  
  public List<DemonstrativoFinanceiro> getListaDemonstrativo()
  {
    return listaDemonstrativo;
  }
  
  public void atualizaDemonstrativo()
  {
    try
    {
      devolveRemuneracao();
      this.demonstrativo.setVALR_remuneracao(this.rem
        .getVALR_remuneracao()
        .multiply(this.impl.getProporcionalidade()
        .getPercentual())
        .divide(new BigDecimal(100)));
      
      this.demonstrativo.setNUMR_percentual(this.impl.getProporcionalidade().getPercentual());
    }
    catch (Exception e)
    {
      Message.addErrorMessage("R�brica ausente na ficha financeira");
    }
  }
  
  public String reinitDemonstrativo()
  {
    this.demonstrativo = new DemonstrativoFinanceiro();
    return null;
  }
  
  public void salvarObjeto()
  {
    try
    {
      System.out.println(listaDemonstrativo.size());
      this.pb.setREL_demonstrativo(listaDemonstrativo);
      this.pb.setNUMR_idDoObjetoFuncionais(this.pf);
      new GenericPersistence<ProventosBeneficio>(ProventosBeneficio.class).salvar(this.pb);
      this.habilitaImpressao = true;
      Message.addSuccessMessage("Provento salvo com sucesso!");
    }
    catch (Exception e)
    {
      e.printStackTrace();
      Message.addErrorMessage("Erro ao salvar Provento!");
    }
  }
  
  public void novoObjeto()
  {
    this.pb = new ProventosBeneficio();
    listaDemonstrativo = new ArrayList<DemonstrativoFinanceiro>();
  }
  
  public List<ProventosBeneficio> listaDeObjetos()
  {
    return null;
  }
  
  public void exibeListaDeObjetos()
  {
    new DialogsPrime().showDialogWithAndHeight(false, false, true, false, "listaProventos", 520, 840);
  }
  
  public void pegaInstanciaDialogo(ProventosBeneficio obj) {}
  
  public void selecionaObjetoDialogo(SelectEvent event) {}
  
  public List<Rubricas> getListaDeRubricas()
  {
    return new GenericPersistence<Rubricas>(Rubricas.class).listarTodos("Rubricas");
  }
  
  public List<AtosLegais> getListaDeAtosLegais()
  {
    return new GenericPersistence<AtosLegais>(AtosLegais.class).listarTodos("AtosLegais");
  }
  
  public void geraProventos()
  {
    try
    {
      JasperReportBuiderInterface template = new TemplateProvento(this.pf, this.impl, String.valueOf(((DemonstrativoFinanceiro)listaDemonstrativo.get(0)).getNUMR_percentual()));
      JasperReportBuiderInterface detail = new DemonstrativoProventos(listaDemonstrativo);
      JasperReportBuiderInterface footer = new FooterProventos(listaDemonstrativo);
      
      ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
        .addComponent(detail)
        .addComponent(footer);
      
      GenerateReportWithSub.create(
        crbh.listaSubReport(), 
        template);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
