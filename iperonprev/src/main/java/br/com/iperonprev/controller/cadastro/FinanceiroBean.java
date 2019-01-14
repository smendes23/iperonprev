package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.models.Cargos;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Remuneracoes;
import br.com.iperonprev.models.Rubricas;
import br.com.iperonprev.services.contribuicao.QualificaCalculoContribuicao;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@SessionScoped
public class FinanceiroBean
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  PessoasFuncionais pf = new PessoasFuncionais();
  Remuneracoes remuneracao = new Remuneracoes();
  Rubricas rubrica = new Rubricas();
  ContribuicoeseAliquotas contribuicao = new ContribuicoeseAliquotas();
  private List<Remuneracoes> filtroDeRemuneracao;
  private int decimoTerceiro;
  private String competenciaInicial;
  private String competenciaFinal;
  static List<Remuneracoes> listaDefinanceiro = new ArrayList<Remuneracoes>();
  private String competenciaPortaria;
  private boolean existePortaria;
  private List<PessoasFuncionais> listaFuncionais = new ArrayList<PessoasFuncionais>();
  private boolean ativaTab = false;
  private String ano;
  List<ContribuicoeseAliquotas> listaDecontribuicoes = new ArrayList<>();
  
  
  
  public List<ContribuicoeseAliquotas> getListaDecontribuicoes() {
	return listaDecontribuicoes;
}

public String getAno() {
	return ano;
}

public void setAno(String ano) {
	this.ano = ano;
}

	public boolean isAtivaTab()
  {
    return this.ativaTab;
  }
  
  public void setAtivaTab(boolean ativaTab)
  {
    this.ativaTab = ativaTab;
  }
  
  public List<PessoasFuncionais> getListaFuncionais()
  {
    return this.listaFuncionais;
  }
  
  public Remuneracoes getRemuneracao()
  {
    return this.remuneracao;
  }
  
  public void setRemuneracao(Remuneracoes remuneracao)
  {
    this.remuneracao = remuneracao;
  }
  
  public Rubricas getRubrica()
  {
    return this.rubrica;
  }
  
  public void setRubrica(Rubricas rubrica)
  {
    this.rubrica = rubrica;
  }
  
  public ContribuicoeseAliquotas getContribuicao()
  {
    return this.contribuicao;
  }
  
  public void setContribuicao(ContribuicoeseAliquotas contribuicao)
  {
    this.contribuicao = contribuicao;
  }
  
  public List<Remuneracoes> getFiltroDeRemuneracao()
  {
    return this.filtroDeRemuneracao;
  }
  
  public void setFiltroDeRemuneracao(List<Remuneracoes> filtroDeRemuneracao)
  {
    this.filtroDeRemuneracao = filtroDeRemuneracao;
  }
  
  public int getDecimoTerceiro()
  {
    return this.decimoTerceiro;
  }
  
  public void setDecimoTerceiro(int decimoTerceiro)
  {
    this.decimoTerceiro = decimoTerceiro;
  }
  
  public String getCompetenciaInicial()
  {
    return this.competenciaInicial;
  }
  
  public void setCompetenciaInicial(String competenciaInicial)
  {
    this.competenciaInicial = competenciaInicial;
  }
  
  public String getCompetenciaFinal()
  {
    return this.competenciaFinal;
  }
  
  public void setCompetenciaFinal(String competenciaFinal)
  {
    this.competenciaFinal = competenciaFinal;
  }
  
  public static List<Remuneracoes> getListaDefinanceiro()
  {
    return listaDefinanceiro;
  }
  
  public static void setListaDefinanceiro(List<Remuneracoes> listaDefinanceiro)
  {
    FuncionalBean.listaDefinanceiro = listaDefinanceiro;
  }
  
  public String getCompetenciaPortaria()
  {
    return this.competenciaPortaria;
  }
  
  public void setCompetenciaPortaria(String competenciaPortaria)
  {
    this.competenciaPortaria = competenciaPortaria;
  }
  
  public boolean isExistePortaria()
  {
    return this.existePortaria;
  }
  
  public void setExistePortaria(boolean existePortaria)
  {
    this.existePortaria = existePortaria;
  }
  
  public PessoasFuncionais getPf()
  {
    return this.pf;
  }
  
  public void setPf(PessoasFuncionais pf)
  {
    this.pf = pf;
  }
  
  private String cpfServidor = new String();
  
  public String getCpfServidor()
  {
    return this.cpfServidor;
  }
  
  public void setCpfServidor(String cpfServidor)
  {
    this.cpfServidor = cpfServidor;
  }
  
  Pessoas pessoas = new Pessoas();
  
  public Pessoas getPessoas()
  {
    return this.pessoas;
  }
  
  public void setPessoas(Pessoas pessoas)
  {
    this.pessoas = pessoas;
  }
  
  public void buscaServidor()
  {
    try
    {
      if (!this.cpfServidor.isEmpty())
      {
        this.pessoas = new PessoasDao().devolvePessoa(this.cpfServidor);
        this.listaFuncionais = new PessoasFuncionaisDao().devolveListaDeFuncionais(this.pessoas.getNUMR_cpf());
      }
      else
      {
        Message.addErrorMessage("Cpf Nulo");
      }
    }
    catch (Exception e)
    {
      Message.addErrorMessage("N�o foi poss�vel carregar os dados pessoais!");
    }
  }
  
  Cargos cargo = new Cargos();
  
  public Cargos getCargo()
  {
    return this.cargo;
  }
  
  public void setCargo(Cargos cargo)
  {
    this.cargo = cargo;
  }
  
  private int idFuncional = 0;
  
  public int getIdFuncional()
  {
    return this.idFuncional;
  }
  
  public void setIdFuncional(int idFuncional)
  {
    this.idFuncional = idFuncional;
  }
  
  public void buscaFuncional(ValueChangeEvent event)
  {
    try
    {
      this.pf = ((PessoasFuncionais)new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(Integer.valueOf(((Integer)event.getNewValue()))));
      this.cargo = this.pf.getNUMR_idDoObjetoCargo();
      this.ativaTab = true;
    }
    catch (Exception e)
    {
      System.out.println("Funcional nulo");
    }
  }
  
  public void limpaDadosFuncionaisServidor()
  {
    novoObjetoRemuneracao();
  }
  
  public void salvarObjetoRemuneracao()
  {
    try
    {
      this.remuneracao.setNUMR_idDoObjetoFuncional(devolveNovoFuncional(this.pf.getNUMG_idDoObjeto()));
      this.remuneracao.setNUMR_rubrica(this.rubrica);
      this.remuneracao.setFLAG_decimoTerceiro(this.decimoTerceiro);
      new GenericPersistence<Remuneracoes>(Remuneracoes.class).salvar(this.remuneracao);
      novoObjetoRemuneracao();
      Message.addSuccessMessage("Remunera��o salva com sucesso!");
    }
    catch (Exception e)
    {
      Message.addErrorMessage("Erro ao salvar Remunera��o!");
    }
  }
  
  private PessoasFuncionais devolveNovoFuncional(int idFuncional)
  {
    return new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(idFuncional);
  }
  
  public void novoObjetoRemuneracao()
  {
    this.remuneracao = new Remuneracoes();
    this.rubrica = new Rubricas();
    this.contribuicao = new ContribuicoeseAliquotas();
    this.decimoTerceiro = 0;
    this.pessoas = new Pessoas();
    this.pf = new PessoasFuncionais();
  }
  
  public void exibeListaDeObjetosRemuneracao()
  {
    listaDefinanceiro = new ArrayList<Remuneracoes>();
    new DialogsPrime().showDialogWithAndHeight(true, true, true, true, "listaDeVerbas", 350, 930);
  }
  
  private static boolean actionButtonVerbas = false;
  
  public void fechaVerbas()
  {
    actionButtonVerbas = true;
    RequestContext.getCurrentInstance().closeDialog("listaDeVerbas");
  }
  
  public void actionEditVerbas()
  {
    actionButtonVerbas = false;
  }
  
  public void pegaInstanciaDialogoRemuneracao(Remuneracoes obj)
  {
    new DialogsPrime().getInstanceObjectDialog(obj);
  }
  
  public void selecionaObjetoDialogoRemuneracao(SelectEvent event)
  {
    if (!actionButtonVerbas)
    {
      this.remuneracao = ((Remuneracoes)event.getObject());
      this.rubrica = this.remuneracao.getNUMR_rubrica();
    }
    actionEditVerbas();
  }
  
  private boolean actionButtonRubricas = false;
  
  public void fechaRubricas()
  {
    actionButtonRubricas = true;
    devolveListaDeRubricas();
    RequestContext.getCurrentInstance().closeDialog("listaDeRubricas");
  }
  
  public void actionEditRubricas()
  {
    actionButtonRubricas = false;
  }
  
  public void exibeListaDeObjetosRubricas()
  {
    new DialogsPrime().showDialogWithAndHeight(true, true, true, true, "listaDeRubricas", 295, 930);
  }
  
  public void selecionaObjetoDialogoRubricas(SelectEvent event)
  {
    actionEditRubricas();
  }
  
  private List<Rubricas> devolveListaDeRubricas()
  {
    return new GenericPersistence<Rubricas>(Rubricas.class).listarTodos("Rubricas");
  }
  
  public List<Rubricas> getListaRubricas()
  {
    return devolveListaDeRubricas();
  }
  
  
  public void onCellEdit(CellEditEvent event) {
      Object oldValue = event.getOldValue();
      Object newValue = event.getNewValue();
       
      if(newValue != null && !newValue.equals(oldValue)) {
          FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
          FacesContext.getCurrentInstance().addMessage(null, msg);
      }
      
  }
  
  public void processaLista(){
	  RemuneracaoDao dao = new RemuneracaoDao();
	  List<ContribuicoeseAliquotas> listaContrib = new ArrayList<>();
	  try {
		  
		  if(!dao.listaRemuneracoesPorAno(this.ano, this.idFuncional).isEmpty()) {
			  
			  dao.listaRemuneracoesPorAno(this.ano, this.idFuncional).forEach(r->{
				  ContribuicoeseAliquotas ca = new ContribuicoeseAliquotas();
					ca.setDESC_competencia(r.getNUMR_competencia());
					ca.setNUMR_idPessoasFuncionais(r.getNUMR_idDoObjetoFuncional());
					ca = new QualificaCalculoContribuicao().executa(ca,r.getNUMR_idDoObjetoFuncional().getDATA_efetivoExercicio(),r.getVALR_remuneracao());
					new GenericPersistence<ContribuicoeseAliquotas>(ContribuicoeseAliquotas.class).salvar(ca);
			  });
			  
		  }
		  
		  listaContrib = dao.listaContribuicoesPorAno(this.ano, this.idFuncional);
		  for (int i = 0; i < 14; i++) {
			  if(listaContrib.get(i).getNUMG_idDoObjeto() > 0) {
				  this.listaDecontribuicoes.add(new ContribuicoeseAliquotas(listaContrib.get(i).getDESC_competencia(),
						  listaContrib.get(i).getVALR_contribuicaoPrevidenciaria(),
						  listaContrib.get(i).getNUMR_aliquotaSegurado(),
						  listaContrib.get(i).getVALR_contribSegurado(),
						  listaContrib.get(i).getVALR_devolSegurado(),
						  listaContrib.get(i).getNUMR_aliquotaPatronal(),
						  listaContrib.get(i).getVALR_contribPatronal(),
						  listaContrib.get(i).getVALR_devolPatronal()));
				  
			  }
		  }
		  

	  }catch(Exception e) {
//		  e.printStackTrace();
	  }
	  System.out.println("Passou pela lista");
  }
}
