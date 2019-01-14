package br.com.iperonprev.controller.cadastro;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.CertidaoTempoServico;
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

public class CtsBuilder {

	private CertidaoTempoServico cts;

	public CtsBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public CtsBuilder(CertidaoTempoServico cts) {
		this.cts = cts;
	}
	
	public CtsBuilder templateCts(){
		JasperReportBuiderInterface template = new TemplateCts();
		return this;
	}
	
	public CtsBuilder CtsHeader(){
		JasperReportBuiderInterface ctsHeader = new CtsHeader(this.cts);
		return this;
	}
	
	public CtsBuilder ctsHeaderComplement(){
		JasperReportBuiderInterface ctsHeaderComplement = new CtsHeaderComplemento(this.cts);
		return this;
	}
	
	public CtsBuilder ctsFrequencia(){
		JasperReportBuiderInterface ctsFrequencia = new FrequenciaDetailCts();
		return this;
	}
	
	public CtsBuilder ctsFrequenciaColumnFooter(){
		JasperReportBuiderInterface ctsFrequenciaColumnFooter = new CtsColumnFooterFrequencia();
		return this;
	}
	
	public CtsBuilder ctsFrequenciaFooter(){
		JasperReportBuiderInterface ctsFrequenciaFooter = new CtsFooterFrequencia();
		return this;
	}
	
	public CtsBuilder ctsAverbacao(){
		JasperReportBuiderInterface ctsAverbacao = new CtsAverbacao();
		return this;
	}
	
	public CtsBuilder ctsAverbacaoColumnFooter(){
		JasperReportBuiderInterface ctsAverbacaoColumnFooter = new CtsColumnFooterAverbacao();
		return this;
	}

	public CtsBuilder ctsTempoTotal(){
		JasperReportBuiderInterface ctsTempoTotal = new CtsColumnFooterTempoTotal();
		return this;
	}
	
	public CtsBuilder ctsSumari(){
		JasperReportBuiderInterface ctsSumari = new CtsSumari();
		return this;
	}
	
	public void executa(CtsBuilder ctsBuilder){
		
		/*ComponentReportBuilderHelper crbh = new ComponentReportBuilderHelper()
				.addComponent(ctcHeader)
				.addComponent(ctcAdmissaoDemissao)
				.addComponent(ctcHeaderComplemento)
				.addComponent(destinacaoCtc)
				.addComponent(freqCtc)
				.addComponent(columnFooterCtc)
				.addComponent(footerCtc);
		
		GenerateReportWithSub.create(crbh.listaSubReport(), reportCtc);*/
	}
}
