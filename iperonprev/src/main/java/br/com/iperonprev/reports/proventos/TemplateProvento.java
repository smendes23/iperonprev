package br.com.iperonprev.reports.proventos;

import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.services.beneficio.Aposentadorias.TipoAposentadoriaImpl;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class TemplateProvento
  implements JasperReportBuiderInterface
{
  private PessoasFuncionais pf = new PessoasFuncionais();
  TipoAposentadoriaImpl tipoAposentadoria = new TipoAposentadoriaImpl();
  private String competencia = new String();
  private String percentual = new String();
  
  public TemplateProvento(PessoasFuncionais pf, TipoAposentadoriaImpl tipoAposentadoria)
  {
    this.pf = pf;
    this.tipoAposentadoria = tipoAposentadoria;
  }
  
  public TemplateProvento(PessoasFuncionais pf, TipoAposentadoriaImpl tipoAposentadoria, String percentual)
  {
    this.pf = pf;
    this.tipoAposentadoria = tipoAposentadoria;
    this.percentual = percentual;
  }
  
  public TemplateProvento() {}
  
  public List<Field> listaFields()
  {
    List<Field> field = new ArrayList<Field>();
    field.add(new Field("mat", "string"));
    field.add(new Field("serv", "string"));
    field.add(new Field("cargo", "string"));
    field.add(new Field("orgao", "string"));
    field.add(new Field("tipoProvento", "string"));
    field.add(new Field("tipoAposentadoria", "string"));
    field.add(new Field("enquadramento", "string"));
    field.add(new Field("dataBeneficio", "string"));
    field.add(new Field("proporcionalidade", "string"));
    field.add(new Field("competencia", "string"));
    return field;
  }
  
  public JRDataSource dataSource()
  {
    DRDataSource dataSource = new DRDataSource(new String[] { "mat", "serv", "cargo", "orgao", "tipoProvento", "tipoAposentadoria", "enquadramento", 
      "dataBeneficio", "proporcionalidade", "competencia" });
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    dataSource.add(new Object[] {
      this.pf.getDESC_matricula(), 
      this.pf.getNUMR_idDoObjetoPessoas().getDESC_nome(), 
      this.pf.getNUMR_idDoObjetoCargo().getDESC_nome(), 
      this.pf.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(), 
      this.tipoAposentadoria.getTipoProventos().getNome(), 
      this.tipoAposentadoria.getTipoBeneficio().getNome(), 
      "", 
      sdf.format(this.pf.getDATA_Beneficio()), 
      this.tipoAposentadoria.getProporcionalidade().getTempoLiquido() + " = " + this.percentual, 
      sdf.format(this.pf.getDATA_Beneficio()).substring(3, 10) });
    
    return dataSource;
  }
  
  public String templateDesigner()
  {
    return "proventos_template";
  }
}
