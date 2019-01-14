package br.com.iperonprev.controller.beneficio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.helper.ComponentReportBuilderHelper;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.reports.container.GenerateReportWithSub;
import br.com.iperonprev.reports.simulador.AverbacaoSimulador;
import br.com.iperonprev.reports.simulador.HeaderSimulador;
import br.com.iperonprev.reports.simulador.RegrasSimulador;
import br.com.iperonprev.reports.simulador.TempoContribuicaoSimulador;
import br.com.iperonprev.reports.simulador.TempoDeduzidoSimulador;
import br.com.iperonprev.reports.simulador.TempoServicoSimulador;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@javax.faces.view.ViewScoped
public class SimuladorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	PessoasFuncionais funcional = new PessoasFuncionais();
	Pessoas pessoa = new Pessoas();
	private String cpf;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static List<PessoasFuncionais> listaFuncionais = new ArrayList<>();

	public PessoasFuncionais getFuncional() {
		return funcional;
	}

	public void setFuncional(PessoasFuncionais funcional) {
		this.funcional = funcional;
	}

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void buscaServidor() {
		listaFuncionais = new ArrayList<PessoasFuncionais>();
		try {
			Pessoas pes = new PessoasDao().devolvePessoa(cpf);
			this.pessoa = pes;
			listaFuncionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class)
					.listarRelacionamento("PessoasFuncionais", "NUMR_idDoObjetoPessoas", pes.getNUMG_idDoObjeto());
			this.cpf = new String();
			  if(new PessoasDao().verificaServidorInativo(pes.getNUMR_cpf()) !=false){ 
			  	this.pessoa = pes; 
			  	listaFuncionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).
			  				listarRelacionamento("PessoasFuncionais","NUMR_idDoObjetoPessoas", pes.getNUMG_idDoObjeto()); 
			  				this.cpf = new String(); }else{ this.cpf = new String();
			  	Message.addErrorMessage("Servidor Inativo"); 
			  }

		} catch (Exception e) {
			e.printStackTrace();
			Message.addErrorMessage("Cpf não encontrado");
		}
	}

	public void buscaFuncional(ValueChangeEvent event) {
		this.funcional = (PessoasFuncionais) event.getNewValue();
	}

	public List<PessoasFuncionais> getListaDeFuncionais() {
		return listaFuncionais;
	}

	public void geraSimulacao() {
		try {
			JasperReportBuiderInterface simuladorReport = new HeaderSimulador(this.funcional);
			
			JasperReportBuiderInterface reportAverbacao = new AverbacaoSimulador(this.funcional);
//			JasperReportBuiderInterface reportServico = new TempoServicoSimulador(this.funcional.getNUMR_idDoObjetoPessoas());
			JasperReportBuiderInterface reportContribuicao = new TempoContribuicaoSimulador(this.funcional.getNUMR_idDoObjetoPessoas());
			JasperReportBuiderInterface reportDeducao = new TempoDeduzidoSimulador(this.funcional.getNUMR_idDoObjetoPessoas());
			JasperReportBuiderInterface reportRegras = new RegrasSimulador(this.funcional);
			
			ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
					.addComponent(reportAverbacao)
						.addComponent(new TempoServicoSimulador(this.funcional.getNUMR_idDoObjetoPessoas()))
							.addComponent(reportDeducao)
								.addComponent(reportContribuicao)
									.addComponent(reportRegras);
			GenerateReportWithSub.create(crbh.listaSubReport(), simuladorReport);
		} catch (Exception e) {
			e.printStackTrace();
			Message.addErrorMessage("Não foi possível gerar relatório de simulação!");
		}
	}


}
