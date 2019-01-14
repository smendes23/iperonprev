package br.com.iperonprev.reports.funcional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.AfastamentosDisposicao;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class DisposicaoReport implements JasperReportBuiderInterface{
	
	private List<AfastamentosDisposicao> listaDisposicao;

	public DisposicaoReport() {
		// TODO Auto-generated constructor stub
	}
	
	public DisposicaoReport(List<AfastamentosDisposicao> listaDisposicao) {
		this.listaDisposicao = listaDisposicao;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("orgaoDestino", "string"));
		field.add(new Field("tipoOnus", "string"));
		field.add(new Field("funcao", "string"));
		field.add(new Field("doe", "string"));
		field.add(new Field("dataInicio", "string"));
		field.add(new Field("dataFim", "string"));
		field.add(new Field("observacao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource(
				"orgaoDestino", 
				"tipoOnus",
				"funcao",
				"doe",
				"dataInicio",
				"dataFim",
				"observacao");
		try{
			listaDisposicao.forEach(d->{
				dataSource.add(
						d.getNUMR_idDoObjetoOrgao().getDESC_nome(),
						d.getENUM_tipoOnus().getNome(),
						d.getDESC_funcao(),
						d.getDESC_doe(),
						sdf.format(d.getDATA_publicacaoDoe()),
						sdf.format(d.getDATA_dataRetorno()),
						d.getDESC_observacao()
						);
			});
		}catch(Exception e){
			System.out.println("Erro ao gerar Disposições! erro: "+e.getMessage());
		}
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "disposicoes";
	}

}
