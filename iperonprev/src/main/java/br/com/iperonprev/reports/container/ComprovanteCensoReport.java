package br.com.iperonprev.reports.container;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.models.CensoPrevidenciario;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class ComprovanteCensoReport {

	
	public ComprovanteCensoReport() {}

	public void gera(CensoPrevidenciario cp){
		try{
			Reports report = new Reports();
			report.createReport(Templates.reportTemplate, "comprovanteCenso",new ArrayList<Column>(), fields(), 
					dataSourceComprovante(cp));
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Não foi possível gerar comprovante.");
		}
	}
	
	private List<Field> fields(){
		List<Field> field = new ArrayList<>();
		field.add(new Field("censo","string"));
		field.add(new Field("unidadeRecadastramento","string"));
		field.add(new Field("atendente","string"));
		field.add(new Field("dataAtendimento","string"));
		field.add(new Field("beneficiario","string"));
		field.add(new Field("cpf","string"));
		field.add(new Field("numeroRecadastramento","string"));
		field.add(new Field("matricula","string"));
		field.add(new Field("nascimento","string"));
		field.add(new Field("tituloComprovante","string"));
		return field;
	}
	
	private JRDataSource dataSourceComprovante(CensoPrevidenciario cp) {
		
		String tituloRecadastramento = "COMPROVANTE DE RECADASTRAMENTO PENSIONISTA";
		
		if(cp.getTipoBeneficiario() == 2){
			tituloRecadastramento = "COMPROVANTE DE RECADASTRAMENTO APOSENTADO";
		}
		
		DRDataSource data = new DRDataSource("censo",
				"unidadeRecadastramento",
				"atendente",
				"dataAtendimento",
				"beneficiario",
				"cpf",
				"numeroRecadastramento",
				"matricula",
				"nascimento",
				"tituloComprovante");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		data.add(cp.getNUMR_idCenso().getDESC_descricao(),
				cp.getNUMR_unidade().getDESC_nome(),
				cp.getDESC_atendente(),
				sdf.format(cp.getDATA_recadastramento()),
				cp.getNUMR_pessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome(),
				cp.getNUMR_pessoasFuncionais().getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
				cp.getNUMR_recadastramento(),
				cp.getNUMR_pessoasFuncionais().getDESC_matricula(),
				sdf.format(cp.getNUMR_pessoasFuncionais().getNUMR_idDoObjetoPessoas().getDATA_nascimento()),
				tituloRecadastramento);
		
		return data;
	}
}
