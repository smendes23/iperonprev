package br.com.iperonprev.controller.cadastro;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.joda.time.LocalDate;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.AverbacaoDao;
import br.com.iperonprev.dao.CertidaoTempoDeContribuicaoDao;
import br.com.iperonprev.dao.CertidaoTempoDeServicoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.helper.ComponentReportBuilderHelper;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.CertidaoTempoServico;
import br.com.iperonprev.models.FrequenciaCtc;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.reports.container.GenerateReportWithSub;
import br.com.iperonprev.reports.cts.CtsAverbacao;
import br.com.iperonprev.reports.cts.CtsColumnFooterAverbacao;
import br.com.iperonprev.reports.cts.CtsColumnFooterFrequencia;
import br.com.iperonprev.reports.cts.CtsColumnFooterTempoTotal;
import br.com.iperonprev.reports.cts.CtsFooterFrequencia;
import br.com.iperonprev.reports.cts.CtsHeader;
import br.com.iperonprev.reports.cts.CtsHeaderComplemento;
import br.com.iperonprev.reports.cts.CtsSumari;
import br.com.iperonprev.reports.cts.FrequenciaDetailCts;
import br.com.iperonprev.reports.cts.TemplateCts;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;

@Named
@ViewScoped
public class CtsBean implements GenericBean<CertidaoTempoServico>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Pessoas pessoa = new Pessoas();
	PessoasFuncionais funcionais = new PessoasFuncionais();
	CertidaoTempoServico cts = new CertidaoTempoServico();
	Orgaos orgaoExpedidor = new Orgaos();
	boolean ctsSalva = false;
	
	
	public Orgaos getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(Orgaos orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	public PessoasFuncionais getFuncionais() {
		return funcionais;
	}

	public void setFuncionais(PessoasFuncionais funcionais) {
		this.funcionais = funcionais;
	}

	public CertidaoTempoServico getCts() {
		return cts;
	}

	public void setCts(CertidaoTempoServico cts) {
		this.cts = cts;
	}

	public boolean isCtsSalva() {
		return ctsSalva;
	}

	public void setCtsSalva(boolean ctsSalva) {
		this.ctsSalva = ctsSalva;
	}

	@SuppressWarnings("static-access")
	@PostConstruct
	public void init() {
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

			this.cts.setNUMR_certidao(
					new CertidaoTempoDeContribuicaoDao().devolveNumeroDaCertidao(new LocalDate().now().getYear()));
			
			cts.setNUMR_ano(new LocalDate().now().getYear());
			
			if (ec.getSessionMap().containsKey("funcional")) {
				this.funcionais = (PessoasFuncionais)ec.getSessionMap().get("funcional");
				this.pessoa = this.funcionais.getNUMR_idDoObjetoPessoas();
				
				FacesContext.getCurrentInstance().getExternalContext().getFlash().remove("funcional");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().clear();

				if (new CertidaoTempoDeServicoDao().devolveCertidao(this.funcionais.getNUMG_idDoObjeto()).getNUMR_certidao() > 0) {
					this.cts = new CertidaoTempoDeServicoDao().devolveCertidao(this.funcionais.getNUMG_idDoObjeto());
					this.orgaoExpedidor = this.cts.getREL_orgaoExpedidor();
					this.ctsSalva = true;
				}
			}else{
				System.out.println("Flash vazio");
			}

		} catch (Exception e) {
			System.out.println("Erro ao carregar CtsBean");
		}

	}

	@Override
	public void salvarObjeto() {
		try{
			this.cts.setREL_orgaoExpedidor(this.orgaoExpedidor);
			this.cts.setREL_funcional(this.funcionais);
			new GenericPersistence<CertidaoTempoServico>(CertidaoTempoServico.class).salvar(this.cts);
			this.ctsSalva = true;
			Message.addSuccessMessage("Cts salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Cts");
		}
	}

	@Override
	public void novoObjeto() {
		
	}

	@Override
	public List<CertidaoTempoServico> listaDeObjetos() {
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		
	}

	@Override
	public void pegaInstanciaDialogo(CertidaoTempoServico obj) {
		
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		
	}
	
	public List<Orgaos> getListaDeOrgaos(){
		List<Orgaos> lista = new GenericPersistence<Orgaos>(Orgaos.class).listarTodos("Orgaos");
		Collections.sort(lista, new Comparator<Orgaos>() {
			@Override
			public int compare(Orgaos o1, Orgaos o2) {
				return o1.getDESC_nome().compareTo(o2.getDESC_nome());
			}
		});
		return lista;
	}
	
	int somaTotal = 0;
	public void geraCts() throws IOException {
		try{
			List<FrequenciaCtc> listaFrequencia = new ArrayList<FrequenciaCtc>();
			List<Averbacao> listaAverbacao = new ArrayList<>();
			try {
				List<PessoasFuncionais> listaPf = new ArrayList<>();
				listaPf.add(this.funcionais);
				listaFrequencia = RetornaTempos.retornaListaDeFrequencia(listaPf, this.cts.getDATA_inicioPeriodoApuracao(),cts.getDATA_fimPeriodoApuracao());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro ao carregar lista de Frequencia");
			}
			
			try {
				listaAverbacao = new AverbacaoDao().listaRelacionamenoDoObjeto("Averbacao", "NUMR_pessoasFuncionais", this.funcionais.getNUMG_idDoObjeto());
			} catch (Exception e) {
				System.out.println("Erro ao buscar lista de Averbações!");
			}
			
			this.cts.setLIST_Averbacao(listaAverbacao);
			
			JasperReportBuiderInterface template = new TemplateCts(this.cts.getREL_orgaoExpedidor().getDESC_nome(), this.cts.getREL_orgaoExpedidor().getDESC_sigla());
			JasperReportBuiderInterface ctsHeader = new CtsHeader(this.cts);
			JasperReportBuiderInterface ctsHeaderComplement = new CtsHeaderComplemento(this.cts);
			JasperReportBuiderInterface ctsFrequencia = new FrequenciaDetailCts(listaFrequencia);
			listaFrequencia.forEach(f->{
				somaTotal += f.getTempoLiquido();
			});
			JasperReportBuiderInterface ctsFrequenciaColumnFooter = new CtsColumnFooterFrequencia(somaTotal);
			JasperReportBuiderInterface ctsFrequenciaFooter = new CtsFooterFrequencia();
			JasperReportBuiderInterface ctsAverbacao = new CtsAverbacao(this.cts);
			RetornaTemposAverbacao rta = new RetornaTemposAverbacao(listaAverbacao);
			JasperReportBuiderInterface ctsAverbacaoColumnFooter = new CtsColumnFooterAverbacao(rta.devolveDiasTempoTotalAproveitado());
			JasperReportBuiderInterface ctsTempoTotal = new CtsColumnFooterTempoTotal(somaTotal+rta.devolveDiasTempoTotalAproveitado());
			JasperReportBuiderInterface ctsSumari = new CtsSumari(this.cts.getDESC_anotacoes());
			
			ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
					.addComponent(ctsHeader)
					.addComponent(ctsHeaderComplement)
					.addComponent(ctsFrequencia)
					.addComponent(ctsFrequenciaColumnFooter)
					.addComponent(ctsFrequenciaFooter)
					.addComponent(ctsAverbacao)
					.addComponent(ctsAverbacaoColumnFooter)
					.addComponent(ctsTempoTotal)
					.addComponent(ctsSumari);
			
			GenerateReportWithSub.create(
					crbh.listaSubReport(), 
					template);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
