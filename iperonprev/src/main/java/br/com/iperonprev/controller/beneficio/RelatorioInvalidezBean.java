package br.com.iperonprev.controller.beneficio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.com.iperonprev.dao.AposentadoriaInvalidezDao;
import br.com.iperonprev.models.AposentadoriaPorInvalidez;
import br.com.iperonprev.models.Cid;
import br.com.iperonprev.reports.container.Reports;
import br.com.iperonprev.reports.container.Templates;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

@Named
@ViewScoped
public class RelatorioInvalidezBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private int proventos;
	private int reajuste;
	private int status;
	
	@PostConstruct
	public void init(){
		proventos = 0;
		reajuste = 0;
		status= 0;
	}
	
	public int getProventos() {
		return proventos;
	}

	public void setProventos(int proventos) {
		this.proventos = proventos;
	}

	public int getReajuste() {
		return reajuste;
	}

	public void setReajuste(int reajuste) {
		this.reajuste = reajuste;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void gera(){
		try{
			List<AposentadoriaPorInvalidez> lista = new AposentadoriaInvalidezDao().listaInvalidezComParametros(this.proventos, this.reajuste, this.status);
			String tituloRel = "Relatório de Aposentadorias por Invalidez";
			if(proventos== 1){
				tituloRel = "Relatório de Aposentadorias por Invalidez com proventos Integrais";
				if(this.reajuste == 1){
					tituloRel = "Relatório de Aposentadorias por Invalidez Integrais reajuste Conforme a Lei";
				}else if(this.reajuste == 2){
					tituloRel = "Relatório de Aposentadorias por Invalidez Integrais com Paridade";
				}
			}else if(proventos == 2){
				tituloRel = "Relatório de Aposentadorias por Invalidez com proventos Proporcionais";
				if(this.reajuste == 1){
					tituloRel = "Relatório de Aposentadorias por Invalidez Integrais reajuste Conforme a Lei";
				}else if(this.reajuste == 2){
					tituloRel = "Relatório de Aposentadorias por Invalidez Integrais com Paridade";
				}
			}
			Reports report = new Reports();
			
			report.createReport(Templates.reportTemplate, "invalidezSintetico", new ArrayList<Column>(), fields(),
					dataSourceInvalidez(tituloRel,lista));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private List<Field> fields() {
		
		List<Field> field = new ArrayList<>();
		
		field.add(new Field("tituloRelatorio", "string"));
		field.add(new Field("nome", "string"));
		field.add(new Field("matricula", "string"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("dataAposentadoria", "string"));
		field.add(new Field("cid", "string"));
		field.add(new Field("ultimaPericia", "string"));
		return field;
	}
	
	private JRDataSource dataSourceInvalidez(String tituloRelatorio,List<AposentadoriaPorInvalidez> listaAposentadoria) {
		DRDataSource ds = new DRDataSource("tituloRelatorio","nome","matricula","orgao","dataAposentadoria","cid","ultimaPericia");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Collections.sort(listaAposentadoria, (a1, a2) -> a1.getNUMR_idDoObejtoPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()
				.compareTo(a2.getNUMR_idDoObejtoPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()));
		listaAposentadoria.forEach(a->{
			List<Cid> listaCid = a.getNUMR_cid();
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < listaCid.size(); i++) {
				if(listaCid.size()-1 == i){
					sb.append(listaCid.get(i).getNUMR_numCid());
				}else{
					sb.append(listaCid.get(i).getNUMR_numCid()).append(", ");
				}
			}
			
			ds.add(tituloRelatorio,
					a.getNUMR_idDoObejtoPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome(),
					a.getNUMR_idDoObejtoPessoasFuncionais().getDESC_matricula(),
					a.getNUMR_idDoObejtoPessoasFuncionais().getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
					sdf.format(a.getDATA_publicacao()),
					sb.toString(),
					sdf.format(a.getDATA_ultimaPericia())
					);
			
		});
		return ds;
	}

}
