package br.com.iperonprev.controller.cadastro;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.joda.time.LocalDate;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.controller.dto.FinanceiroPorMesDto;
import br.com.iperonprev.dao.AverbacaoDao;
import br.com.iperonprev.dao.CertidaoTempoDeContribuicaoDao;
import br.com.iperonprev.dao.ContribuicaoDao;
import br.com.iperonprev.dao.FuncionaisFuncoesDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.RemuneracaoDao;
import br.com.iperonprev.helper.ComponentReportBuilderHelper;
import br.com.iperonprev.helper.ContribuicaoHelper;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.AtosLegais;
import br.com.iperonprev.models.CertidaoTempoContribuicao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.DestinacaoCtc;
import br.com.iperonprev.models.EntesFederados;
import br.com.iperonprev.models.FrequenciaCtc;
import br.com.iperonprev.models.FuncionaisFuncoes;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.reports.container.GenerateReportWithSub;
import br.com.iperonprev.reports.container.Reports;
import br.com.iperonprev.reports.container.Templates;
import br.com.iperonprev.reports.ctc.ColumnFooterCtc;
import br.com.iperonprev.reports.ctc.DestinacaoTempoCtc;
import br.com.iperonprev.reports.ctc.FooterCtc;
import br.com.iperonprev.reports.ctc.FrequenciaDetailCtc;
import br.com.iperonprev.reports.ctc.HeaderCtc;
import br.com.iperonprev.reports.ctc.HeaderCtcAd;
import br.com.iperonprev.reports.ctc.HeaderCtcComplemento;
import br.com.iperonprev.reports.ctc.TemplateCtc;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

@Named
@ViewScoped
public class CtcBean implements GenericBean<CertidaoTempoContribuicao>, Serializable {

	private static final long serialVersionUID = 1L;
    private String cpfServidor = null;
    CertidaoTempoContribuicao ctc = new CertidaoTempoContribuicao();
    PessoasFuncionais funcionais = new PessoasFuncionais();
    GenericDao<CertidaoTempoContribuicao> dao = new CertidaoTempoDeContribuicaoDao();
    Orgaos orgao = new Orgaos();
    private boolean judicial;
    private int orgaoExpedidor;
    Pessoas pessoa = new Pessoas();
    List<PessoasFuncionais> listaCollectionFuncionais = new ArrayList<PessoasFuncionais>();
    DestinacaoCtc destinacao = new DestinacaoCtc();
    List<DestinacaoCtc> listaCollectionDestinacao = new ArrayList<DestinacaoCtc>();
    int idFuncional = 0;
    boolean ctcSalva = false;
    List<Orgaos> listaDeOrgaoDestino = new ArrayList<Orgaos>();

    public int getIdFuncional() {
        return this.idFuncional;
    }

    public void setIdFuncional(int idFuncional) {
        this.idFuncional = idFuncional;
    }

    public PessoasFuncionais getFuncionais() {
        return this.funcionais;
    }

    public void setFuncionais(PessoasFuncionais funcionais) {
        this.funcionais = funcionais;
    }

    public String reinit() {
        this.funcionais = new PessoasFuncionais();
        return null;
    }

    public boolean isCtcSalva() {
        return this.ctcSalva;
    }

    public DestinacaoCtc getDestinacao() {
        return this.destinacao;
    }

    public Orgaos getOrgao() {
        return this.orgao;
    }

    public void setOrgao(Orgaos orgao) {
        this.orgao = orgao;
    }

    public void setDestinacao(DestinacaoCtc destinacao) {
        this.destinacao = destinacao;
    }

    public List<DestinacaoCtc> getListaCollectionDestinacao() {
        return this.listaCollectionDestinacao;
    }

    public Pessoas getPessoa() {
        return this.pessoa;
    }

    @PostConstruct
    public void init() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            new org.joda.time.LocalDate();
            this.ctc.setNUMR_certidao(new CertidaoTempoDeContribuicaoDao().devolveNumeroDaCertidao(LocalDate.now().getYear()));
            new org.joda.time.LocalDate();
            this.ctc.setNUMR_ano(LocalDate.now().getYear());
            this.orgaoExpedidor = 2;
            this.ctc.setFLAG_impresso(false);
            if (ec.getSessionMap().containsKey("funcional")) {
                this.funcionais = (PessoasFuncionais)ec.getSessionMap().get("funcional");
                this.pessoa = new PessoasDao().devolvePessoa(this.funcionais.getNUMR_idDoObjetoPessoas().getNUMR_cpf());
                if (new CertidaoTempoDeContribuicaoDao().devolveCertidao(this.funcionais.getNUMG_idDoObjeto()).getNUMR_certidao() != 0) {
                    this.ctc = new CertidaoTempoDeContribuicaoDao().devolveCertidao(this.funcionais.getNUMG_idDoObjeto());
                    this.listaCollectionFuncionais = this.ctc.getREL_listaDeFuncionais();
                    this.listaCollectionDestinacao = this.ctc.getREL_destinacao();
                    this.orgao = this.ctc.getNUMR_orgaoExpedidor();
                    this.ctcSalva = true;
                }
            }
            this.listaDeOrgaoDestino = new AverbacaoDao().devolveListaDeOrgaosPrevidenciarios();
            this.orgao = new GenericPersistence<Orgaos>(Orgaos.class).buscarPorId(Integer.valueOf(32));
            this.ctc.setNUMR_orgaoExpedidor(this.orgao);
        }
        catch (Exception e) {
            System.out.println("Erro ao carregar CtcBean");
        }
    }

    public void buscarFuncional() {
        try {
        	
        	
            this.funcionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(this.idFuncional);
           if (this.verificaSeFuncionalNulo(this.funcionais)) {
                Message.addErrorMessage("Data de exoneração em branco.");
            }
           
          /* new Thread(() ->{
        	   if (new RemuneracaoDao().verificaSeExisteRemuneracoes(this.funcionais.getNUMG_idDoObjeto()) == false) {
        		   Message.addErrorMessage("Não existe remuneração para este funcional.");
        	   }
        	   
           }).start();*/
           
        }
        catch (Exception e) {
            System.out.println("Erro ao selecionar id funcional");
        }
    }

    private boolean verificaSeFuncionalNulo(PessoasFuncionais pf) {
        boolean res = true;
        try {
            if (pf.getDATA_exoneracao() != null) {
                res = false;
            }
        }
        catch (Exception e) {
            System.out.println("Data de exoneração nula.");
        }
        return res;
    }

    public List<PessoasFuncionais> getListaCollectionFuncionais() {
        return this.listaCollectionFuncionais;
    }

    public String reinitDestinacao() {
        boolean decisao = false;
        for (DestinacaoCtc destinacaoCtc : this.listaCollectionDestinacao) {
            if (destinacaoCtc.getREL_orgaos().getNUMG_idDoObjeto() != this.destinacao.getREL_orgaos().getNUMG_idDoObjeto()) continue;
            decisao = true;
        }
        if (!decisao) {
            this.listaCollectionDestinacao.add(this.destinacao);
        }
        this.destinacao = new DestinacaoCtc();
        return null;
    }

    public int getOrgaoExpedidor() {
        return this.orgaoExpedidor;
    }

    public void setOrgaoExpedidor(int orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }

    public boolean isJudicial() {
        return this.judicial;
    }

    public void setJudicial(boolean judicial) {
        this.judicial = judicial;
    }

    public String getCpfServidor() {
        return this.cpfServidor;
    }

    public void setCpfServidor(String cpfServidor) {
        this.cpfServidor = cpfServidor;
    }

    public CertidaoTempoContribuicao getCtc() {
        return this.ctc;
    }

    public void setCtc(CertidaoTempoContribuicao ctc) {
        this.ctc = ctc;
    }

    public void salvarObjeto() {
        try {
            this.ctc.setNUMR_orgaoExpedidor(this.orgao);
            this.ctc.setREL_listaDeFuncionais(this.listaCollectionFuncionais);
            this.ctc.setREL_destinacao(this.listaCollectionDestinacao);
            this.ctc.setFLAG_salvo(true);
            this.dao.salvaObjeto(this.ctc);
            this.ctcSalva = true;
        }
        catch (Exception e) {
            Message.addErrorMessage((String)"Erro ao salvar Certid\u00e3o.");
        }
    }

    public void novoObjeto() {
    }

    public List<CertidaoTempoContribuicao> listaDeObjetos() {
        return null;
    }

    public void exibeListaDeObjetos() {
    }

    public void pegaInstanciaDialogo(CertidaoTempoContribuicao obj) {
    }

    public void selecionaObjetoDialogo(SelectEvent event) {
    }

    public void exibeDestinacao() {
        new DialogsPrime().showDialog(true, true, true, false, "destinacaoCtc");
    }

    public void fechaDestinacao() {
        RequestContext.getCurrentInstance().closeDialog((Object)"destinacaoCtc");
    }

    public List<DecisaoEnum> getListaDestinadaInss() {
        return Arrays.asList(DecisaoEnum.values());
    }

    public List<AtosLegais> getListaDeAtosLegais() {
        return new GenericPersistence<AtosLegais>(AtosLegais.class).listarTodos("AtosLegais");
    }

    public List<Orgaos> getListaDeOrgaos() {
        return new GenericPersistence<Orgaos>(Orgaos.class).listarTodos("Orgaos");
    }

    public List<EntesFederados> getListaDeEntesFederados() {
        return new GenericPersistence<EntesFederados>(EntesFederados.class).listarTodos("EntesFederados");
    }

    public void recarregaPagina() {
    }

    public void geraCtc() throws IOException {
        try {
            FuncionaisFuncoes ff = new FuncionaisFuncoesDao().devolveFuncionalFuncoes(this.listaCollectionFuncionais.get(0).getNUMR_idDoObjetoPessoas().getNUMR_cpf()).get(0);
            List<FrequenciaCtc> listaFrequencia = RetornaTempos.retornaListaDeFrequencia(this.listaCollectionFuncionais, ff.getDATA_ingressoEnte(),ff.getDATA_egressoEnte());
            TemplateCtc reportCtc = new TemplateCtc();
            HeaderCtc ctcHeader = new HeaderCtc(this.ctc);
            HeaderCtcAd ctcAdmissaoDemissao = new HeaderCtcAd(this.ctc);
            HeaderCtcComplemento ctcHeaderComplemento = new HeaderCtcComplemento(this.ctc);
            ColumnFooterCtc columnFooterCtc = new ColumnFooterCtc(listaFrequencia);
            FooterCtc footerCtc = new FooterCtc(new LocalDate((Object)this.ctc.getDATA_emissao()).toDate());
            FrequenciaDetailCtc freqCtc = new FrequenciaDetailCtc(listaFrequencia);
            DestinacaoTempoCtc destinacaoCtc = new DestinacaoTempoCtc(this.ctc.getREL_destinacao());
            ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper().addComponent((JasperReportBuiderInterface)ctcHeader).addComponent((JasperReportBuiderInterface)ctcAdmissaoDemissao).addComponent((JasperReportBuiderInterface)ctcHeaderComplemento).addComponent((JasperReportBuiderInterface)destinacaoCtc).addComponent((JasperReportBuiderInterface)freqCtc).addComponent((JasperReportBuiderInterface)columnFooterCtc).addComponent((JasperReportBuiderInterface)footerCtc);
            GenerateReportWithSub.create(crbh.listaSubReport(), (JasperReportBuiderInterface)reportCtc);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao gerar Ctc");
        }
    }

    public void geraRrc() throws IOException {
        try {
            if (this.funcionais.getNUMG_idDoObjeto() > 0) {
                ContribuicaoHelper.criaContribuicao((PessoasFuncionais)this.funcionais);
                Reports report = new Reports();
                List<ContribuicoeseAliquotas> listaFinanceiro = new ContribuicaoDao().devolveListaContribuicoes(this.funcionais.getNUMG_idDoObjeto());
                if (listaFinanceiro.size() > 0) {
                    report.createReport(Templates.reportTemplate, "rrc", new ArrayList<>(), this.fieldsRrc(), this.dataSourceRrc(this.funcionais, new ArrayList<Column>(), this.fieldsRrc(), listaFinanceiro));
                    this.ctc.setFLAG_rrc(true);
                    if (this.ctc.getNUMR_certidao() != 0) {
                        new GenericPersistence<CertidaoTempoContribuicao>(CertidaoTempoContribuicao.class).salvar(this.ctc);
                    }
                } else {
                    Message.addErrorMessage((String)"Não existem contribuições para este funcional");
                }
            } else {
                Message.addErrorMessage((String)"Funcional Nulo");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Message.addErrorMessage((String)"Salve a Certidão de Tempo de Contribuição antes de Imprimir!");
        }
    }

    public List<Field> fieldsRrc() {
        ArrayList<Field> field = new ArrayList<Field>();
        field.add(new Field("matricula", "string"));
        field.add(new Field("nome", "string"));
        field.add(new Field("cpf", "string"));
        field.add(new Field("dataNascimento", "string"));
        field.add(new Field("dataAdmissao", "string"));
        field.add(new Field("numero", "string"));
        field.add(new Field("orgaoExpedidor", "string"));
        field.add(new Field("cnpj", "string"));
        field.add(new Field("dataDemissao", "string"));
        field.add(new Field("ano", "string"));
        field.add(new Field("jan", "string"));
        field.add(new Field("fev", "string"));
        field.add(new Field("mar", "string"));
        field.add(new Field("abr", "string"));
        field.add(new Field("mai", "string"));
        field.add(new Field("jun", "string"));
        field.add(new Field("jul", "string"));
        field.add(new Field("ago", "string"));
        field.add(new Field("set", "string"));
        field.add(new Field("out", "string"));
        field.add(new Field("nov", "string"));
        field.add(new Field("dez", "string"));
        field.add(new Field("dataEmissao", "string"));
        field.add(new Field("observacao", "string"));
        field.add(new Field("pis", "string"));
        field.add(new Field("mae", "string"));
        field.add(new Field("textoUnidadeGestora", "string"));
        field.add(new Field("textoHomolagacao", "string"));
        return field;
    }

    private JRDataSource dataSourceRrc(PessoasFuncionais obj, List<Column> columns, List<Field> fields, List<ContribuicoeseAliquotas> listaFinanceiro) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DRDataSource dataSource = new DRDataSource(new String[]{"matricula", "nome", "cpf", "dataNascimento", "dataAdmissao", "numero", "orgaoExpedidor", "cnpj", "dataDemissao", "ano", "jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez", "dataEmissao", "observacao", "pis", "mae", "textoUnidadeGestora", "textoHomolagacao"});
        List<String> listaDeAnos = this.devolveListaDeAnos(listaFinanceiro);
        List<FinanceiroPorMesDto> listaRrc = this.devolveListaFinanceiroRrc(listaDeAnos, listaFinanceiro);
        try {
            listaRrc.forEach(f -> dataSource.add(new Object[]{obj.getDESC_matricula(), this.pessoa.getDESC_nome(), this.pessoa.getNUMR_cpf(), sdf.format(this.pessoa.getDATA_nascimento()), sdf.format(obj.getDATA_efetivoExercicio()), "" + this.ctc.getNUMR_certidao() + "/" + this.ctc.getNUMR_ano(), obj.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(), obj.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_cnpj(), sdf.format(obj.getDATA_exoneracao()), f.getAno(), f.getJan(), f.getFev(), f.getMar(), f.getAbr(), f.getMai(), f.getJun(), f.getJul(), f.getAgo(), f.getSet(), f.getOut(), f.getNov(), f.getDez(), sdf.format(new LocalDate((Object)this.ctc.getDATA_emissao()).toDate()), this.ctc.getDESC_observacao(), this.pessoa.getNUMR_pisPasep(), this.pessoa.getDESC_mae(), "UNIDADE GESTORA DO RPPS", "HOMOLOGO, a presente Certid\u00e3o de Tempo de Contribui\u00e7\u00e3o e declaro que as informa\u00e7\u00f5es nela constantes correspondem com a verdade."}));
        }
        catch (Exception e) {
            System.out.println("Erro ao gerar RRC datasource.");
        }
        return dataSource;
    }

    private List<String> devolveListaDeAnos(List<ContribuicoeseAliquotas> listaContribuicao) {
        String ano = listaContribuicao.get(0).getDESC_competencia().substring(2, 6);
        LinkedHashSet<String> listaAnos = new LinkedHashSet<String>();
        listaAnos.add(ano);
        ArrayList<String> listaDeAnos = new ArrayList<String>();
        try {
            for (ContribuicoeseAliquotas contribuicao : listaContribuicao) {
                if (contribuicao.getDESC_competencia().substring(2, 6).equals(ano)) continue;
                ano = contribuicao.getDESC_competencia().substring(2, 6);
                listaAnos.add(ano);
            }
            listaDeAnos = new ArrayList<>(listaAnos);
            Collections.sort(listaDeAnos);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeAnos;
    }

    private List<FinanceiroPorMesDto> devolveListaFinanceiroRrc(List<String> listaDeAnos, List<ContribuicoeseAliquotas> listaFinanceiro) {
        ArrayList<FinanceiroPorMesDto> lista = new ArrayList<FinanceiroPorMesDto>();
        DecimalFormatter df = new DecimalFormatter();
        try {
            listaDeAnos.forEach(a -> {
                FinanceiroPorMesDto fr = new FinanceiroPorMesDto();
                fr.setAno(a);
                for (ContribuicoeseAliquotas contribuicao : listaFinanceiro) {
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 1 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setJan(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 2 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setFev(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 3 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setMar(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 4 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setAbr(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 5 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setMai(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 6 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setJun(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 7 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setJul(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 8 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setAgo(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 9 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setSet(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 10 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setOut(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) == 11 && contribuicao.getDESC_competencia().substring(2, 6).equals(a)) {
                        fr.setNov(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                        continue;
                    }
                    if (Integer.parseInt(contribuicao.getDESC_competencia().substring(0, 2)) != 12 || !contribuicao.getDESC_competencia().substring(2, 6).equals(a)) continue;
                    fr.setDez(df.formatterToCurrencyBrazilianWithoutSymbol(contribuicao.getVALR_contribuicaoPrevidenciaria()));
                }
                lista.add(fr);
            });
        }
        catch (Exception e) {
            System.out.println("Erro ao gerar RRC");
        }
        return lista;
    }

    public void desbloqueiaEdicaoCtcRrc() {
        RequestContext.getCurrentInstance().execute("PF('desbloqueiaCtcRrc').show();");
    }

    public List<Orgaos> getListaDeOrgaoEmissor() {
        return new AverbacaoDao().devolveListaDeOrgaosPrevidenciarios();
    }

    public List<Orgaos> getListaDeOrgaoDestino() {
        return this.listaDeOrgaoDestino;
    }

    public List<PessoasFuncionais> getListaDeFuncionais() {
        List<PessoasFuncionais> listaDeFuncionais = new ArrayList<PessoasFuncionais>();
        try {
            listaDeFuncionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).listarRelacionamento("PessoasFuncionais", "NUMR_idDoObjetoPessoas", this.pessoa.getNUMG_idDoObjeto());
        }
        catch (Exception e) {
            System.out.println("Erro ao listar funcionais");
        }
        return listaDeFuncionais;
    }

    public void novoIdFuncional() {
        this.idFuncional = 0;
    }
}
